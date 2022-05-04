package bureau.modele;

import bureau.metier.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ModeleProjetBD implements DAOProjet{

    @Override
    public Projet create(Projet newObj) {
        return null;
    }

    @Override
    public boolean delete(Projet delRech) {
        return false;
    }

    @Override
    public Projet read(Projet reachRech) {
        return null;
    }

    @Override
    public Projet update(Projet upRech) {
        return null;
    }

    @Override
    public List<Projet> readAll() {
        return null;
    }

    @Override
    public ArrayList<Invest> listeDisEtInv(Projet pj) {
        return null;
    }

    @Override
    public ArrayList<Discipline> listeSpec(Projet pj) {
        return null;
    }

    @Override
    public ArrayList<Travail> listTravailEtEmp(Projet pj) {
        return null;
    }

    @Override
    public int totalPour(Projet pj) {
        return 0;
    }

    @Override
    public int totalInvet(Projet pj) {
        return 0;
    }

    @Override
    public boolean addEmp(Projet pj, int pourcentage, Employe emp, LocalDate date) {
        return false;
    }

    @Override
    public boolean delEmp(Projet pj, Employe emp) {
        return false;
    }

    @Override
    public boolean upEmp(Projet pj, Employe emp, int pourcentage) {
        return false;
    }

    @Override
    public boolean addDis(Projet pj, int quantite, Discipline dis) {
        return false;
    }

    @Override
    public boolean delDis(Projet pj, Discipline dis) {
        return false;
    }

    @Override
    public boolean upDis(Projet pj, Discipline dis, int quantite) {
        return false;
    }
}
