package bureau.modele;

import bureau.metier.*;

import java.time.LocalDate;
import java.util.ArrayList;

public interface DAOProjet extends DAO<Projet> {

    ArrayList<Invest> listeDisEtInv(Projet pj);

    ArrayList<Discipline> listeSpec(Projet pj);

    ArrayList<Travail> listTravailEtEmp(Projet pj);

    int totalPour(Projet pj);

    int totalInvet(Projet pj);

    boolean addEmp(Projet pj, int pourcentage, Employe emp, LocalDate date);

    boolean delEmp(Projet pj, Employe emp);

    boolean upEmp(Projet pj, Employe emp, int pourcentage);

    boolean addDis(Projet pj, int quantite, Discipline dis);

    boolean delDis(Projet pj, Discipline dis);

    boolean upDis(Projet pj, Discipline dis, int quantite);
}
