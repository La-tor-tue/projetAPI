package bureau.presenter;

import bureau.metier.Employe;
import bureau.modele.DAOEmploye;
import bureau.vue.VueEmployeInterface;

import java.util.List;

public class PresenterEmploye {

    private DAOEmploye mde;
    private VueEmployeInterface vuee;
    private PresenterDiscipline pdis;

    public PresenterEmploye(DAOEmploye mde, VueEmployeInterface vuee) {
        this.mde = mde;
        this.vuee = vuee;
    }

    public void setPdis(PresenterDiscipline pdis) {
        this.pdis = pdis;
    }

    public void gestion() {
        do {
            int c = vuee.menu(new String[]{"Ajout", "Recherche", "Mise à jour", "Supression", "Voir tous", "FIN"});
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
                    return;
            }
        } while (true);

    }

    protected void add() {
        Employe newEMP = vuee.create();
        newEMP.setExpertise(pdis.choixAffAll());
        newEMP = mde.create(newEMP);
        if (newEMP == null) {
            vuee.displayMsg("erreur création");
            return;
        }

        vuee.displayMsg("Employé créé");
        vuee.display(newEMP);
    }

    protected Employe research() {
        int nrech = vuee.read();
        Employe emp = new Employe(nrech, null, null, null, null, null, null);
        emp = mde.read(emp);
        if (emp == null) {
            vuee.displayMsg("Employe introuvable");
            return null;
        }
        vuee.display(emp);
        return emp;
    }

    protected void update() {
        Employe emp = research();
        if (emp != null) {
            emp = vuee.update(emp);
            mde.update(emp);
        }
    }

    protected void delete() {
        Employe emp = research();
        if (emp != null) {
            Boolean ok = mde.delete(emp);
            if (ok) {
                vuee.displayMsg("Suppression effectué");
                return;
            } else {
                vuee.displayMsg("Suppression raté");
                return;
            }
        }
    }

    protected void affAll() {
        vuee.affAll(mde.readAll());
    }

    protected Employe choixAffAll() {
        List<Employe> lemp = mde.readAll();
        affAll();
        do {
            int c = Integer.parseInt(vuee.getMsg("Choix?"));
            if (c == 0)
                return null;
            if (c > 0 && c <= lemp.size()) return lemp.get(c - 1);

        } while (true);
    }
}
