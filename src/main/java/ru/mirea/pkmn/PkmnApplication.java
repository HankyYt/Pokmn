package ru.mirea.pkmn;

public class PkmnApplication {
    public static void main(String[] args) throws Exception {
        Card card1 = CardImport.cardImportCRD("src/main/resources/Croagunk.crd");
        System.out.println(card1.toString());
        //Card mycard = CardImport.frmTxt("src\\main\\resources\\my_card.txt");
        //System.out.println(mycard.toString());
        //CardExport.cardExporting(mycard);
    }
}
