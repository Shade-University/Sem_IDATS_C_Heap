package pamatky;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import kolekce.KolekceException;

/**
 *
 * @author user
 */
public interface PamatkyInterface {

    public void importDatZTXT() throws IOException, FileNotFoundException, KolekceException;

    public void vybuduj(Zamek[] prvky) throws KolekceException;

    public void prebuduj() throws KolekceException;

    public void vloz(Zamek z) throws KolekceException;

    public Zamek odeberMax() throws KolekceException;

    public Zamek zpristupniMax() throws KolekceException;

    public void zrus();

    public boolean jePrazdny();

    public Iterator<Zamek> iterator();
}
