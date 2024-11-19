package ru.mirea.pkmn.GurovTS;

import com.fasterxml.jackson.databind.JsonNode;
import ru.mirea.pkmn.Card;
import ru.mirea.pkmn.GurovTS.web.http.PkmnHttpClient;
import ru.mirea.pkmn.GurovTS.web.jdbc.DatabaseServiceImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.stream.Collectors;

public class PkmnApplication {

    public static void main(String[] args) throws Exception {
        PkmnHttpClient pkmnHttpClient = new PkmnHttpClient();
        DatabaseServiceImpl db = new DatabaseServiceImpl();

        Card mycard = CardImport.frmTxt("src\\main\\resources\\my_card.txt");
        CardImport.getDescript(mycard, pkmnHttpClient);

        //System.out.println("Моя карточка: " + mycard);
        //CardExport.cardExporting(mycard);

        //Card import_some_card = CardImport.cardImportCRD("src/main/resources/Pyroar.crd");
        //System.out.println("Импортированная карточка: " + import_some_card);
        db.createPokemonOwner(mycard.getPokemonOwner());
        db.saveCardToDatabase(mycard);



    }

}
