package ru.mirea.gurovts.pkmn.GurovTS;

import ru.mirea.gurovts.pkmn.Card;

public class PkmnApplication {
    public static void main(String[] args) throws Exception {
        Card card1 = CardImport.cardImportCRD("src/main/resources/crdAggronV.crd");
        System.out.println(card1.toString());
        Card mycard = CardImport.frmTxt("src\\main\\resources\\my_card.txt");
        System.out.println(mycard.toString());
        CardExport.cardExporting(mycard);
    }
}
