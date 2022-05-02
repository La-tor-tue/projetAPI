package bureau.modele;

import bureau.metier.Discipline;
import bureau.metier.Invest;
import bureau.metier.Projet;

import java.util.ArrayList;
import java.util.List;

public class ModeleProjet implements DAOProjet {

    private List<Projet> projets = new ArrayList<>();

    @Override
    public Projet create(Projet newP) {
        if (projets.contains(newP)) return null;
        projets.add(newP);
        return newP;
    }

    @Override
    public Projet read(Projet rechP) {
        int numPj = rechP.getIdPj();
        for (Projet p : projets) {
            if (p.getIdPj() == numPj) return p;
        }
        return null;
    }

    @Override
    public Projet update(Projet upP) {
        Projet pj = read(upP);
        if (pj == null) return null;
        pj.setListInvest(upP.getListInvest());
        pj.setNom(upP.getNom());
        pj.setDateDebut(upP.getDateDebut());
        pj.setDateFin(upP.getDateFin());
        pj.setListTravail(upP.getListTravail());
        return pj;
    }

    @Override
    public List<Projet> readAll() {
        return projets;
    }

    @Override
    public boolean delete(Projet delP) {
        Projet pj = read(delP);
        if (pj != null && pj.getListInvest().isEmpty() && pj.getListTravail().isEmpty()) { //ATTENTION AJOUT DE CONTRAINTE D AGGREFATION FORTE
            projets.remove(pj);
            return true;
        } else return false;
    }


    @Override
    public ArrayList<Invest> listeDisEtInv(Projet pj) {
        return pj.listeDisciplinesEtInvestissement();
    }

    @Override
    public ArrayList<Discipline> listeSpec(Projet pj) {
        return pj.listeSpecialites();
    }

    @Override
    public int totalPour(Projet pj) {
        return pj.totalPourcentage();
    }

    @Override
    public int totalInvet(Projet pj) {
        return pj.investissementTotal();
    }


}
