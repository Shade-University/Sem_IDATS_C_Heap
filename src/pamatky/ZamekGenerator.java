package pamatky;

import java.util.Iterator;
import java.util.Random;
import kolekce.AbstrHeap;
import kolekce.AbstrHeapInterface;
import kolekce.KolekceException;

/**
 *
 * @author Tomáš Vondra
 */
public final class ZamekGenerator {

    private ZamekGenerator() {
    }

    public static Iterator<Zamek> generujZamky(int pocet) throws KolekceException {
        AbstrHeapInterface<Zamek> heap = new AbstrHeap<>(new ZamekVzdalenostComparator());
        Integer id;
        String nazev;
        String gps;
        
        for (int i = 0; i < pocet; i++) {

            id = getRandomNumber(1000, 1);
            nazev = getRandomChar() + String.valueOf(getRandomNumber(10, 1));
            gps = createGPS();
            Zamek z = new Zamek(id,nazev,gps);
            heap.vloz(z);
        }

        return heap.iterator();
    }

    private static String createGPS() {
        StringBuilder sb = new StringBuilder();

        sb.append('N');
        sb.append(getRandomNumber(99, 30));
        sb.append(' ');
        sb.append(getRandomNumber(99, 30));
        sb.append('.');
        sb.append(getRandomNumber(9999, 1000));
        sb.append(' ');
        sb.append('E');
        sb.append(getRandomNumber(99, 30));
        sb.append(' ');
        sb.append(getRandomNumber(99, 30));
        sb.append('.');
        sb.append(getRandomNumber(9999, 1000));

        return sb.toString();
    }

    private static char getRandomChar() {
        return (char) (new Random().nextInt(26) + 'a');
    } //Pomocná na vrácení random znaku

    private static int getRandomNumber(int max, int min) {

        return new Random().nextInt((max - min) + 1) + min;
    } //Pomocná na vrácení random čísla v rozmezí

}
