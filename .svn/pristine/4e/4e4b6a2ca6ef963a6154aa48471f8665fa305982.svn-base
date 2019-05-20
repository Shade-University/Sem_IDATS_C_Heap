package pamatky;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kolekce.AbstrHeap;
import kolekce.AbstrHeapInterface;
import kolekce.KolekceException;

/**
 *
 * @author Tomáš Vondra
 */
public class Pamatky implements PamatkyInterface {

    private static final String SOUBOR = "data.csv";
    private static final String IDPATTERN = "(\\d*?);"; //=> Vezme všechny čísla od začátku po ;
    private static final String GPSPATTERN = "[N]\\d(.*?)[F]";  //=> Vyhledá N s nějakým číslem. Poté vezme všechno mezi tím a písmenem F.
    private static final String NAZEVPATTERN = "2000(.*?)[\\^]"; //=> Vyhlledá všechno mezi stringem 2000 a ^. 

    private final ZamekVzdalenostComparator comparator;
    private final AbstrHeapInterface<Zamek> heap;

    public Pamatky() {
        comparator = new ZamekVzdalenostComparator();
        heap = new AbstrHeap<>(comparator);

    }

    @Override
    public void importDatZTXT() throws FileNotFoundException, IOException, KolekceException {
        String id;
        String gps;
        String nazev;

        try (BufferedReader reader = new BufferedReader(new FileReader(SOUBOR))) {
            String line;
            while ((line = reader.readLine()) != null) {
                id = getMatch(line, IDPATTERN, 1);
                gps = getMatch(line, GPSPATTERN, 0).replaceFirst(".$", "").trim();
                //Abychom měli hezkou gps i s N, musíme vzít celej ten string. A z celého toho stringu odstranit F
                nazev = getMatch(line, NAZEVPATTERN, 1).trim();
                //Trim odstraní přebytečné mezery

                Zamek zamek = new Zamek(Integer.parseInt(id), nazev, gps);
                this.vloz(zamek);
            }
        }
    }

    @Override
    public void vybuduj(Zamek[] prvky) throws KolekceException {
        heap.vybuduj(prvky);
    }

    @Override
    public void prebuduj() throws KolekceException {
        heap.prebuduj();
    }

    @Override
    public void vloz(Zamek z) throws KolekceException {
        heap.vloz(z);

    }

    @Override
    public Zamek odeberMax() throws KolekceException {
        return heap.odeberMax();
    }

    @Override
    public Zamek zpristupniMax() throws KolekceException {
        return heap.zpristupniMax();
    }

    @Override
    public void zrus() {
        heap.zrus();
    }

    @Override
    public boolean jePrazdny() {
        return heap.jePrazdny();
    }

    @Override
    public Iterator<Zamek> iterator() {
        return heap.iterator();
    }
    
    private String getMatch(String text, String pattern, int group){
        Pattern p = Pattern.compile(pattern, Pattern.MULTILINE);
        Matcher m = p.matcher(text);
        
        if(m.find()){
            return m.group(group);
        }
        
        return null;
    }

}
