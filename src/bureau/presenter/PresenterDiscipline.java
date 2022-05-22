package bureau.presenter;

import bureau.metier.Discipline;
import bureau.metier.Employe;
import bureau.modele.DAODiscipline;
import bureau.vue.VueDisciplineInterface;

import java.util.List;

public class PresenterDiscipline {

    private DAODiscipline mdd;
    private VueDisciplineInterface vued;

    public PresenterDiscipline(DAODiscipline mdd, VueDisciplineInterface vued) {
        this.mdd = mdd;
        this.vued = vued;
    }

    public void gestion() {
        do {
            int c = vued.menu(new String[]{"Ajout", "Recherche", "Mise à jour", "Supression", "Voir tous", "FIN"});
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
        Discipline newdis = vued.create();
        newdis = mdd.create(newdis);
        if (newdis == null) {
            vued.displayMsg("Erreur lors de la création");
            return;
        }

        vued.displayMsg("Client créé");
        vued.display(newdis);
    }

    protected Discipline research() {
        int nrech = vued.read();
        Discipline dis = new Discipline(nrech, null);
        dis = mdd.read(dis);
        if (dis == null) {
            vued.displayMsg("Client introuvable");
            return null;
        }
        vued.display(dis);
        return dis;
    }

    protected void update() {
        Discipline dis = research();
        if (dis != null) {
            dis = vued.update(dis);
            mdd.update(dis);
        }
    }

    protected void delete() {
        Discipline dis = research();
        if (dis != null) {
            Boolean ok = mdd.delete(dis);
            if (ok) {
                vued.displayMsg("Suppression effectué");
                return;
            } else {
                vued.displayMsg("Suppression raté");
                return;
            }
        }
    }

    protected void affAll() {
        vued.affAll(mdd.readAll());
    }

    protected Discipline choixAffAll() {
        List<Discipline> ldis = mdd.readAll();
        affAll();
        do {
            int c = Integer.parseInt(vued.getMsg("Choix?", "[0-9]{1,2}"));
            if (c == 0)
                return null;
            if (c > 0 && c <= ldis.size()) return ldis.get(c - 1);

        } while (true);
    }
}
