package designPatern.composite;

public class Projet extends SousProjet {
    protected String nom;
    protected int idpj;
    protected float prix;

    public Projet(int idpj, String nom, float prix) {
        super(idpj);
        this.nom = nom;
        this.prix = prix;
    }

    @Override
    public float prixTotal() {
        return prix;
    }

    @Override
    public String toString() {
        return "Projet{" +
                "nom='" + nom + '\'' +
                ", idpj=" + idpj +
                ", prix=" + prix +
                '}';
    }
}
