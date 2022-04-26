package bureau.metier;

import java.util.ArrayList;
import java.util.Objects;

public class Discipline {
    private int idDis;
    private String nom, desc;

    private ArrayList<Employe> listEmploye;

    public Discipline(int idDis, String nom) {
        this.idDis = idDis;
        this.nom = nom;
    }

    public Discipline(int idDis, String nom, String desc, ArrayList<Employe> listEmploye) {
        this.idDis = idDis;
        this.nom = nom;
        this.desc = desc;
        this.listEmploye = listEmploye;
    }

    public int getIdDis() {
        return idDis;
    }

    public void setIdDis(int idDis) {
        this.idDis = idDis;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ArrayList<Employe> getListEmploye() {
        return listEmploye;
    }

    public void setListEmploye(ArrayList<Employe> listEmploye) {
        this.listEmploye = listEmploye;
    }

    @Override
    public String toString() {
        return "Discipline{" +
                "idDis=" + idDis +
                ", nom='" + nom + '\'' +
                ", desc='" + desc + '\'' +
                ", listEmploye=" + listEmploye +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discipline that = (Discipline) o;
        return idDis == that.idDis;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDis);
    }
}
