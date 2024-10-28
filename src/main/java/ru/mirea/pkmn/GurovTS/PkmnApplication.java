package ru.mirea.pkmn.GurovTS;

import com.fasterxml.jackson.databind.JsonNode;
import ru.mirea.pkmn.Card;
import ru.mirea.pkmn.GurovTS.web.http.PkmnHttpClient;

import java.io.IOException;
import java.util.stream.Collectors;

public class PkmnApplication {

    public static void main(String[] args) throws Exception {
        //Card card1 = CardImport.cardImportCRD("src/main/resources/Croagunk.crd");
        //System.out.println(card1.toString());

        Card mycard = CardImport.frmTxt("src\\main\\resources\\my_card.txt");
        //System.out.println(mycard);
        //CardExport.cardExporting(mycard);
        PkmnHttpClient pkmnHttpClient = new PkmnHttpClient();

        JsonNode card = pkmnHttpClient.getPokemonCard(mycard.getName(), mycard.getNumber());
        //System.out.println(card.toPrettyString());



    }

}
