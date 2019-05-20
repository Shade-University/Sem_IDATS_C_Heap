
package pamatky;

import java.io.Serializable;

/**
 *
 * @author Tomáš Vondra
 */
public class Zamek implements Serializable {
    //Třída zámek by teoreticky měla mít interface, jenže to by potom tahle třída byla prázdná.
    //V testech se to neřeší, takže tohle je asi OK
    private int id;
    private String nazev;
    private String gps;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public Zamek(int id, String nazev, String gps) {
        this.id = id;
        this.nazev = nazev;
        this.gps = gps;
    }

    @Override
    public String toString() {
        return "Zamek{" + "id=" + id + ", nazev=" + nazev + ", gps=" + gps + '}';
    }
    
}
