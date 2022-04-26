package bureau.metier;

import java.util.Objects;

public class Invest {

    private int quantiteJH;

    private Discipline disc;

    public Invest(Discipline disc) {
        this.disc = disc;
    }

    public Invest(int quantiteJH, Discipline disc) {
        this.quantiteJH = quantiteJH;
        this.disc = disc;
    }

    public int getQuantiteJH() {
        return quantiteJH;
    }

    public void setQuantiteJH(int quantiteJH) {
        this.quantiteJH = quantiteJH;
    }

    public Discipline getDisc() {
        return disc;
    }

    public void setDisc(Discipline disc) {
        this.disc = disc;
    }

    @Override
    public String toString() {
        return "Invest{" +
                "quantiteJH=" + quantiteJH +
                ", disc=" + disc +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invest invest = (Invest) o;
        return Objects.equals(disc, invest.disc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(disc);
    }
}
