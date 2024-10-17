package ru.mirea.pkmn;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class CardExport {
    public static void cardExporting(Card pokemon){
        try {
            ObjectOutputStream outp = new ObjectOutputStream(new FileOutputStream("src\\main\\resources\\" + pokemon.getName() + ".crd"));
            outp.writeObject(pokemon);
            outp.flush();
            outp.close();
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при создании файла");
        }

    }
}
