package ru.mirea.pkmn.GurovTS.web.jdbc;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.mirea.pkmn.*;

import java.sql.SQLException;
import java.util.UUID;

public interface DatabaseService {

    Card getCardFromDatabase(String cardName);

    Student getStudentFromDatabase(String studentName) throws SQLException;

    void saveCardToDatabase(Card card) throws SQLException, JsonProcessingException;

    UUID createPokemonOwner(Student owner);
}
