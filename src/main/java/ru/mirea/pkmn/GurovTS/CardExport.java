package ru.mirea.pkmn.GurovTS;

import ru.mirea.pkmn.Card;

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
            System.out.println("Файл создан по адресу src\\main\\resources\\" + pokemon.getName() +".crd");
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при создании файла ");
        }

    }
}
