package bureau.modele;

import bureau.metier.Discipline;
import bureau.metier.Invest;
import bureau.metier.Projet;

import java.util.ArrayList;

public interface DAOProjet extends DAO<Projet> {

    ArrayList<Invest> listeDisEtInv(Projet pj);
    ArrayList<Discipline> listeSpec(Projet pj);
}
