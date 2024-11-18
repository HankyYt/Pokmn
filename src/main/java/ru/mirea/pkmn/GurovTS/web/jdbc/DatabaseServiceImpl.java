package ru.mirea.pkmn.GurovTS.web.jdbc;

import ru.mirea.pkmn.Card;
import ru.mirea.pkmn.Student;

import java.io.*;
import java.sql.*;
import java.util.Properties;
import java.util.UUID;

public class DatabaseServiceImpl implements DatabaseService {

    private final Connection connection;

    private final Properties databaseProperties;
    private Card card;

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

    @Override
    public Card getCardFromDatabase(String cardName) {
        Card pokemon = new Card();
        try{
            String query = String.format("SELECT * FROM card WHERE (name = '%s');", cardName);
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(query);

            if(rs.next()){

                UUID evolves_from = (UUID) rs.getObject("evolves_from");
                pokemon.setName(cardName);
                getCardBase(pokemon, rs);
        }

        return pokemon;
    } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        @Override
    public Student getStudentFromDatabase(String studentName) {
        // Реализовать получение данных о студенте из БД
        return null;
    }



    @Override
    public void createPokemonOwner(Student owner) {

    }

    @Override
    public void saveCardToDatabase(Card card) {
        // Реализовать отправку данных карты в БД
    }

}
