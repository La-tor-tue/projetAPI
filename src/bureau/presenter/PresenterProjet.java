package bureau.presenter;

import bureau.metier.*;
import bureau.modele.DAOProjet;
import bureau.vue.VueProjetInterface;

import java.time.LocalDate;
import java.util.ArrayList;

public class PresenterProjet {

    private DAOProjet mdp;
    private VueProjetInterface vuep;
    private PresenterEmploye pemp;
    private PresenterDiscipline pdisc;

    public PresenterProjet(DAOProjet mdp, VueProjetInterface vuep) {
        this.mdp = mdp; //Injection Dependence
        this.vuep = vuep;
    }

    public void gestion() {
        Projet pj = research();
        do {
            int c = vuep.menu(new String[]{"Ajout", "Recherche", "Modification", "Suppression", "Voir tous", "Gestion Avancé", "FIN"});
            switch (c) {
                case 1:
                    add();
                    break;
                case 2:
                    research();
                    break;
                case 3:
                    update();
                    break;
                case 4:
                    delete();
                    break;
                case 5:
                    affAll();
                    break;
                case 6:
                    detailProjet();
                    break;
                case 7:
                    return;
            }
        } while (true);
    }


    public void detailProjet() {
        Projet pj = research();

        if (pj != null) {
            do {
                ArrayList l = null;
                int t;
                int c = vuep.menu(new String[]{"Afficher Spécialités", "Afficher les investissments", "Afficher les Employés", "Gestion Employé", "Gestion Discipline", "Total pourcentage", "Investissement total", "FIN"});
                switch (c) {
                    case 1:
                        l = pj.listeSpecialites();
                        break;
                    case 2:
                        l = pj.listeDisciplinesEtInvestissement();
                        break;
                    case 3:
                        l = pj.listeEmployeEtPourcentageEtDate();
                        break;
                    case 4:
                        detailProjetTravail();
                        break;
                    case 5:
                        detailProjetInvestissement();
                        break;
                    case 6:
                        vuep.displayMsg("Pourcentage total: " + pj.totalPourcentage());
                        break;
                    case 7:
                        vuep.displayMsg("Pourcentage total: " + pj.investissementTotal());
                        break;
                    case 8:
                        return;
                }
                if (l == null) {
                    if (c < 4) {
                        vuep.displayMsg("Erreur");
                        continue;
                    }
                }
                if (l.isEmpty()) vuep.displayMsg("Aucun élèment");
                else vuep.affListOBJ(l);
            } while (true);
        }
    }

    public void detailProjetTravail() {
        do {
            int c = vuep.menu(new String[]{"Ajout Employé", "Suppression Employé", "Modifié Employé", "FIN"});
            switch (c) {
                case 1:
                    addEmp();
                    break;
                case 2:
                    delEmp();
                    break;
                case 3:
                    upEmp();
                    break;
                case 4:
                    return;
            }
        } while (true);
    }


    public void detailProjetInvestissement() {
        do {
            int c = vuep.menu(new String[]{"Ajout Discipline", "Suppression Discipline", "Modifié Discipline", "FIN"});
            switch (c) {
                case 1:
                    addDis();
                    break;
                case 2:
                    delDis();
                    break;
                case 3:
                    upDis();
                    break;
                case 4:
                    return;
            }
        } while (true);
    }


    public void add() {
        Projet newP = vuep.create();
        newP = mdp.create(newP);
        if (newP == null) {
            vuep.displayMsg("Erreur pendant la création");
            return;
        }

        vuep.displayMsg("Projet crée");
        vuep.display(newP);
    }

    public Projet research() {
        int idpj = vuep.read();
        Projet pj = new Projet(idpj, "");
        pj = mdp.read(pj);
        if (pj != null) {
            vuep.display(pj);
            return pj;
        }
        return null;
    }

    public void update() {

        Projet pj = research();
        if (pj != null) {
            vuep.display(pj);
            pj = vuep.update(pj);
            mdp.update(pj);
            return;
        }
        vuep.displayMsg("Pj innexistant");
    }

    public void delete() {
        Projet pj = research();
        if (pj != null) {
            Boolean ok = mdp.delete(pj);
            if (ok) {
                vuep.displayMsg("Suppression effectué");
                return;
            } else {
                vuep.displayMsg("Suppression raté");
                return;
            }
        }
        vuep.displayMsg("Pj innexistant");
    }

    public void affAll() {
        vuep.affListOBJ(mdp.readAll());
    }

    public void addEmp() {
        Projet pj = research();
        if (pj == null) return;
        Employe emp = pemp.choixAffAll();
        if (emp == null) return;
        int pourcentage = Integer.parseInt(vuep.getMsg("Pourcentage: "));
        LocalDate date = vuep.getDate();
        boolean ok = mdp.addEmp(pj, pourcentage, emp, date);
        if (ok) vuep.displayMsg("Employé enregistrer pour le projet: " + pj.getNom());
        else vuep.displayMsg("Erreur lors de l'enregistrement");
    }

    public void delEmp() {
        Projet pj = research();
        if (pj == null) return;
        Employe emp = pemp.choixAffAll();
        if (emp == null) return;
        boolean ok = mdp.delEmp(pj, emp);
        if (ok) vuep.displayMsg("Employé supprimé pour le projet: " + pj.getNom());
        else vuep.displayMsg("Erreur lors de la suppresion");
    }

    public void upEmp() {
        Projet pj = research();
        if (pj == null) return;
        Employe emp = pemp.choixAffAll();
        if (emp == null) return;
        int pourcentage = Integer.parseInt(vuep.getMsg("Pourcentage: "));
        boolean ok = mdp.upEmp(pj, emp, pourcentage);
        if (ok) vuep.displayMsg("Employé modifié pour le projet: " + pj.getNom());
        else vuep.displayMsg("Erreur lors de la modification");

    }

    public void addDis() {
        Projet pj = research();
        if (pj == null) return;
        Discipline dis = pdisc.choixAffAll();
        if (dis == null) return;
        int quantite = Integer.parseInt(vuep.getMsg("Quantité J/H: "));
        boolean ok = mdp.addDis(pj, quantite, dis);
        if (ok) vuep.displayMsg("Discipline enregistré pour le projet: " + pj.getNom());
        else vuep.displayMsg("Erreur lors de l'enregistrement");
    }

    public void upDis() {
        Projet pj = research();
        if (pj == null) return;
        Discipline dis = pdisc.choixAffAll();
        if (dis == null) return;
        int quantite = Integer.parseInt(vuep.getMsg("Quantité J/H: "));
        boolean ok = mdp.upDis(pj, dis, quantite);
        if (ok) vuep.displayMsg("Discipline modifié pour le projet: " + pj.getNom());
        else vuep.displayMsg("Erreur lors de la modification");
    }


    public void delDis() {
        Projet pj = research();
        if (pj == null) return;
        Discipline dis = pdisc.choixAffAll();
        if (dis == null) return;
        boolean ok = mdp.delDis(pj, dis);
        if (ok) vuep.displayMsg("Discipline supprimé pour le projet: " + pj.getNom());
        else vuep.displayMsg("Erreur lors de la suppresion");
    }

}
