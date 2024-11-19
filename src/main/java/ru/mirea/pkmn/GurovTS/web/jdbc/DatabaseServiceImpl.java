package ru.mirea.pkmn.GurovTS.web.jdbc;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.mirea.pkmn.*;
import ru.mirea.pkmn.GurovTS.CardImport;

import java.io.*;
import java.sql.*;
import java.util.Properties;
import java.util.UUID;

public class DatabaseServiceImpl implements DatabaseService {

    private final Connection connection;
    private final Properties databaseProperties;


    public DatabaseServiceImpl() throws SQLException, IOException {

        // Загружаем файл database.properties

        databaseProperties = new Properties();
        databaseProperties.load(new FileInputStream("/src/main/resources/database.properties"));

        // Подключаемся к базе данных

        connection = DriverManager.getConnection(
                databaseProperties.getProperty("database.url"),
                databaseProperties.getProperty("database.user"),
                databaseProperties.getProperty("database.password")
        );
        System.out.println("Connection is "+(connection.isValid(0) ? "up" : "down"));
    }


    public Card getCardFromDatabase(UUID cardName) {
        Card pokemon = new Card();
        try {
            String query = String.format("SELECT * FROM card WHERE (name = '%s');", cardName);
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(query);

            if (rs.next()) {

                UUID evolves_from = (UUID) rs.getObject("evolves_from");
                pokemon.setName(rs.getString("name"));
                getPokemonCard(pokemon, rs);
            }
        } catch (SQLException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return pokemon;
    }

    public void getPokemonCard(Card pokemon, ResultSet rs) throws SQLException, JsonProcessingException {
        UUID evolves_from = (UUID) rs.getObject("evolves_from");
        pokemon.setEvolvesFrom(evolves_from == null ? null : getCardFromDatabase(evolves_from));
        pokemon.setNumber(Integer.parseInt(rs.getString("card_number")));
        pokemon.setHp(rs.getInt("hp"));
        pokemon.setPokemonOwner(getStudentFromDatabase((UUID) rs.getObject("pokemon_owner")));
        pokemon.setRegulationMark(rs.getString("regulation_mark").charAt(0));
        pokemon.setWeaknessType(EnergyType.valueOf(rs.getString("weakness_type")));
        pokemon.setGameSet(rs.getString("game_set"));
        String resist = rs.getString("resistance_type");
        pokemon.setResistanceType(resist == null ? null : EnergyType.valueOf(resist));
        pokemon.setPokemonStage(PokemonStage.valueOf(rs.getString("stage").toUpperCase()));
        pokemon.setRetreatCost(rs.getString("retreat_cost"));
        pokemon.setSkills(CardImport.parseAttackSkillsFromJson(rs.getString("attack_skills")));
    }

    @Override
    public Card getCardFromDatabase(String cardName) {
        Card pokemon = new Card();
        try {
            String query = String.format("SELECT * FROM card WHERE (name = '%s');", cardName);
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(query);

            if (rs.next()) {

                UUID evolves_from = (UUID) rs.getObject("evolves_from");
                pokemon.setName(rs.getString("name"));
                getPokemonCard(pokemon, rs);
            }
        } catch (SQLException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return pokemon;
    }

    @Override
    public Student getStudentFromDatabase(String studentName) {
        Student st = new Student();

        try {
            String[] studentFullName = studentName.split(" ");
            String query = String.format("SELECT * FROM student WHERE (\"familyName\" = '%s' AND \"firstName\" = '%s' AND \"patronicName\" = '%s');",
                    studentFullName[0], studentFullName[1], studentFullName[2]);
            getStudentCard(st, query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return st;
    }

    public Student getStudentFromDatabase(UUID studentName) throws SQLException {
        Student st = new Student();
        try {
            String query = String.format("SELECT * FROM student WHERE (id = '%s');", studentName);
            getStudentCard(st, query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return st;
    }

    public void getStudentCard(Student st, String query) throws SQLException {
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery(query);

        if(rs.next()){

            st.setFirstName(rs.getString("firstName"));
            st.setFamilyName(rs.getString("familyName"));
            st.setSurName(rs.getString("patronicName"));
            st.setGroup(rs.getString("group"));

        }
        else {
            throw new RuntimeException("Empty result from database");
        }
    }

    @Override
    public UUID createPokemonOwner(Student owner) {
        UUID ownerId = UUID.randomUUID();

        try {
            String query = String.format("INSERT INTO student (\"firstName\", \"familyName\", \"patronicName\", \"group\", \"id\") " +
                            "VALUES ('%s', '%s', '%s', '%s', '%s');",
                    owner.getFirstName(), owner.getFamilyName(), owner.getSurName(), owner.getGroup(), ownerId);
            System.out.println(query);
            connection.createStatement().executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ownerId;
    }

    @Override
    public void saveCardToDatabase(Card card) {
        StringBuilder queryBase = new StringBuilder("INSERT INTO card(");
        StringBuilder query = new StringBuilder("VALUES(");
        if (card.getEvolvesFrom() != null){
            queryBase.append("evolves_from, ");
            try {
                ResultSet rs = connection.createStatement().executeQuery(String.format("SELECT id FROM card WHERE (name = '%s');", card.getEvolvesFrom().getName()));
                rs.next();
                query.append("'").append((UUID) rs.getObject("id")).append("', ");
            } catch (SQLException e){
                saveCardToDatabase(card.getEvolvesFrom());
                try {
                    ResultSet rs = connection.createStatement().executeQuery(String.format("SELECT id FROM card WHERE (name = '%s');", card.getEvolvesFrom().getName()));
                    rs.next();
                    query.append("'").append((UUID) rs.getObject("id")).append("',");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        if (card.getPokemonOwner() != null) {
            queryBase.append(" pokemon_owner,");
            try{
                String tmp = String.format("SELECT id FROM student WHERE (\"familyName\" = '%s' AND \"firstName\" = '%s' AND \"patronicName\" = '%s');",
                        card.getPokemonOwner().getFamilyName(), card.getPokemonOwner().getFirstName(), card.getPokemonOwner().getSurName());
                ResultSet rs = connection.createStatement().executeQuery(tmp);
                rs.next();
                query.append("'").append((UUID) rs.getObject("id")).append("', ");
            }catch (Exception e){
                query.append("'").append(createPokemonOwner(card.getPokemonOwner())).append("', ");
            }

        }
        queryBase.append(" id, name, hp, game_set, stage, retreat_cost, weakness_type, resistance_type, attack_skills, pokemon_type, regulation_mark, card_number) ");
        query.append("'").append(UUID.randomUUID()).append("', '");
        query.append(card.getName()).append("', ");
        query.append(card.getHp()).append(", '");
        query.append(card.getGameSet()).append("', '");
        query.append(card.getPokemonStage()).append("', '");
        query.append(card.getRetreatCost()).append("', '");
        query.append(card.getWeaknessType()).append("', '");
        query.append(card.getResistanceType()).append("', '");
        query.append("[");
        for (AttackSkill as : card.getSkills()){
            query.append(as.toString().replace('\'', '`')).append(", ");
        }
        query.delete(query.length()-2, query.length()-1);
        query.append("]").append("', '");
        query.append(card.getPokemonType()).append("', '");
        query.append(card.getRegulationMark()).append("', ");
        query.append(card.getNumber());
        query.append(");");

        System.out.println(queryBase.toString() + query.toString());

        try {
            connection.createStatement().executeUpdate(queryBase.toString() + query.toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
