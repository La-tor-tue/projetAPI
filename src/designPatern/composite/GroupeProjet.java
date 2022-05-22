package designPatern.composite;

import java.util.HashSet;
import java.util.Set;

public class GroupeProjet extends SousProjet {

    private String nom;
    private Set<SousProjet> sousProjetSet = new HashSet<>();

    public GroupeProjet(int idpj, String nom) {
        super(idpj);
        this.nom = nom;
    }

    @Override
    public String toString() {
        StringBuilder msg = new StringBuilder(getIdpj() + " " + nom + "\n");

        for (SousProjet s : sousProjetSet) {
            msg.append(s + "\n");
        }
        return msg + " Prix total: " + nom + " " + prixTotal() + "\n";
    }

    @Override
    public float prixTotal() {
        float total = 0;
        for (SousProjet sp : sousProjetSet
        ) {
            total += sp.prixTotal();
        }
        return total;
    }

    public Set<SousProjet> getSousProjetSet() {
        return sousProjetSet;
    }
}
