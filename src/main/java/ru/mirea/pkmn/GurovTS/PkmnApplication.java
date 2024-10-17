package ru.mirea.pkmn.GurovTS;

import ru.mirea.pkmn.Card;

public class PkmnApplication {
    public static void main(String[] args) throws Exception {
        Card card1 = CardImport.cardImportCRD("src/main/resources/Gengar Gigantamax.crd");
        System.out.println(card1.toString());

        Card mycard = CardImport.frmTxt("src\\main\\resources\\my_card.txt");
        CardExport.cardExporting(mycard);
    }
}
