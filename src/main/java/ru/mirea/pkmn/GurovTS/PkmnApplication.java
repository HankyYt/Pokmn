package ru.mirea.pkmn.GurovTS;

import com.fasterxml.jackson.databind.JsonNode;
import ru.mirea.pkmn.Card;
import ru.mirea.pkmn.GurovTS.web.http.PkmnHttpClient;

import java.io.IOException;
import java.util.stream.Collectors;

public class PkmnApplication {
    public PkmnApplication() throws IOException {
    }

    public static void main(String[] args) throws Exception {
        Card card1 = CardImport.cardImportCRD("src/main/resources/Croagunk.crd");
        System.out.println(card1.toString());

        //Card mycard = CardImport.frmTxt("src\\main\\resources\\my_card.txt");
        //System.out.println(mycard);
        //CardExport.cardExporting(mycard);
    }

    private static void ClientCreating() throws IOException {
        PkmnHttpClient pkmnHttpClient = new PkmnHttpClient();

        JsonNode card = pkmnHttpClient.getPokemonCard("Pikachu V", "86");
        System.out.println(card.toPrettyString());

        System.out.println(card.findValues("attacks")
                .stream()
                .map(JsonNode::toPrettyString)
                .collect(Collectors.toSet()));
    }
}
