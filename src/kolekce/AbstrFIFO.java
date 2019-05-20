package kolekce;

import java.util.Iterator;

/**
 *
 * @author Tomáš Vondra
 * @param <T>
 * Fronta vytvořená ze SEM A
 */
public class AbstrFIFO<T> implements AbstrFIFOInterface<T>{

    private final DoubleList<T> seznam;
    
    public AbstrFIFO() throws KolekceException{
        this.seznam = new AbstrDoubleList<>();
    }
    @Override
    public void zrus() {
        seznam.zrus();
    }

    @Override
    public boolean jePrazdny() {
        return seznam.jePrazdny();
    }

    @Override
    public void vloz(T data) throws KolekceException {
        seznam.vlozPosledni(data);
    }

    @Override
    public T odeber() throws KolekceException {
        return seznam.odeberPrvni();
    }

    @Override
    public Iterator<T> iterator() {
        return seznam.iterator();
    }

}
