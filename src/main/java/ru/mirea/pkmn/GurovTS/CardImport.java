package ru.mirea.pkmn.GurovTS;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import ru.mirea.pkmn.*;
import ru.mirea.pkmn.GurovTS.web.http.PkmnHttpClient;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class CardImport {
    public static Card frmTxt(String path) throws Exception {
        BufferedReader BR = new BufferedReader(new FileReader(path));

        Card pokemon = new Card();

        for (int i = 1; i <= 13; i++) {
            String line = BR.readLine();
            switch (i) {
                case 1:
                    pokemon.setPokemonStage(PokemonStage.valueOf(line));
                    break;
                case 2:
                    pokemon.setName(line);
                    break;
                case 3:
                    pokemon.setHp(Integer.parseInt(line));
                    break;
                case 4:
                    pokemon.setPokemonType(EnergyType.valueOf(line));
                    break;
                case 5:
                    pokemon.setEvolvesFrom(line.equalsIgnoreCase("none") ? null : frmTxt(line));
                    break;
                case 6:
                    pokemon.setSkills(getAttacks(line));
                    break;
                case 7:
                    pokemon.setWeaknessType(line.equalsIgnoreCase("none") ? null : EnergyType.valueOf(line.toUpperCase()));
                    break;
                case 8:
                    pokemon.setResistanceType(line.equalsIgnoreCase("none") ? null : EnergyType.valueOf(line.toUpperCase()));
                    break;
                case 9:
                    pokemon.setRetreatCost(line);
                    break;
                case 10:
                    pokemon.setGameSet(line);
                    break;
                case 11:
                    pokemon.setRegulationMark(line.charAt(0));
                    break;
                case 12:
                    pokemon.setPokemonOwner(getPOwner(line));
                    break;
                case 13:
                    pokemon.setNumber(Integer.parseInt(line)); break;
            }
        }

        BR.close();
        return pokemon;
    }

    private static ArrayList<AttackSkill> getAttacks(String s) throws Exception {
        ArrayList<AttackSkill> result = new ArrayList<>();
        for (String Skill : s.split(",")) {
            String[] line = Skill.split(" / ");
            result.add(new AttackSkill(line[1], "", line[0], Integer.parseInt(line[2])));
        }
        return result;
    }

    public static void getDescript(Card pokemon, PkmnHttpClient pkmnHttpClient) throws IOException {
        if(pokemon.getEvolvesFrom() != null){
            getDescript(pokemon.getEvolvesFrom(), pkmnHttpClient);
        }

        List<JsonNode> tmp = pkmnHttpClient.getPokemonCard(pokemon.getName(), pokemon.getNumber()).findValues("text");
        for (int i = 0; i < tmp.size(); i++){
            pokemon.getSkills().get(i).setDescription(tmp.get(i).asText());
        }
    }

    public static ArrayList<AttackSkill> parseAttackSkillsFromJson(String json) throws JsonProcessingException {
        ArrayList<AttackSkill> result = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode tmp = (ArrayNode) objectMapper.readTree(json);
        for(int i = 0; i < tmp.size(); i++){
            JsonNode ji = tmp.get(i);
            AttackSkill as = new AttackSkill();
            as.setDescription(ji.findValue("text").toString());
            as.setCost(ji.findValue("cost").toString());
            as.setDamage((ji.get("damage").asInt()));
            as.setName(ji.findValue("name").toString());
            result.add(as);
        }
        return result;
    }

    private static Student getPOwner(String s) throws Exception {
        Student Stud = new Student();
        if (s.equalsIgnoreCase("none")) return Stud;
        String[] line = s.split(" / ");
        Stud.setFamilyName(line[0]);
        Stud.setFirstName(line[1]);
        Stud.setSurName(line[2]);
        Stud.setGroup(line[3]);
        return Stud;
    }

    public static <RandomClass> Card cardImportCRD(String path) throws ClassNotFoundException{
        try {
            ObjectInputStream inp = new ObjectInputStream(new FileInputStream(path));
            return (Card) inp.readObject();
        } catch (IOException e){
            throw new RuntimeException("Некорректный путь до файла");
        }
    }
}

