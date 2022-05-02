package bureau.presenter;

import bureau.metier.Projet;
import bureau.modele.DAOProjet;
import bureau.vue.VueProjetInterface;

import java.util.ArrayList;

public class PresenterProjet {

    private DAOProjet mdp;
    private VueProjetInterface vuep;
    private PresenterEmploye PEmp;
    private PresenterDiscipline PDisc;

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
                int t=-1;
                int c = vuep.menu(new String[]{"Afficher Spécialités","Afficher les investissments","Afficher les Employés","FIN"});
                switch (c) {
                    case 1:
                        l=pj.listeSpecialites();
                        break;
                    case 2:
                        l=pj.listeDisciplinesEtInvestissement();
                        break;
                    case 3:
                        l=pj.listeEmployeEtPourcentageEtDate();
                        break;
                    case 4:
                        return;
                }
                
                if (l==null){
                    vuep.displayMsg("Erreur");
                    continue;
                }
                if (l.isEmpty()) vuep.displayMsg("Aucun élèment");
                else vuep.affListOBJ(l);
            } while (true);
        }
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

}
