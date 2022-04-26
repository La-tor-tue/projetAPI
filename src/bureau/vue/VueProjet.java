package bureau.vue;

import bureau.metier.Invest;
import bureau.metier.Projet;
import bureau.metier.Travail;

import java.time.LocalDate;
import java.util.List;

public class VueProjet extends VueBase implements VueProjetInterface {

    @Override
    public String getMsg(String invite) {
        return null;
    }

    @Override
    public Projet create() {
        String nom = getMsg("Nom", "[a-zA-Z]");
        displayMsg("Date de Début:");
        int j = Integer.parseInt(getMsg("Jour:", "[0-9]"));
        int m = Integer.parseInt(getMsg("Mois:", "[0-9]"));
        int a = Integer.parseInt(getMsg("Année:", "[0-9]"));
        LocalDate dateDebut = LocalDate.of(a, m, j);
        displayMsg("Date de Fin:");
        j = Integer.parseInt(getMsg("Jour:", "[0-9]"));
        m = Integer.parseInt(getMsg("Mois:", "[0-9]"));
        a = Integer.parseInt(getMsg("Année:", "[0-9]"));
        LocalDate dateFin = LocalDate.of(a, m, j);
        Projet newP = new Projet(0, nom, dateDebut, dateFin, null, null);
        return newP;
    }

    @Override
    public void display(Projet obj) {
        displayMsg(obj.toString());
        if (!obj.getListInvest().isEmpty()) {
            String c = getMsg("Afficher les investissements? (O/N)", "[onON]{1}");
            if (c.equals("o") || c.equals("O")) {
                for (Invest i : obj.getListInvest()) {
                    displayMsg(i.toString());
                }
            }
        }
        else if (!obj.getListInvest().isEmpty()){
            String c = getMsg("Afficher les employé(e)s travaillant sur le projet?", "[onON]{1}");
            if (c.equals("o") || c.equals("O")) {
                for (Travail t : obj.getListTravail()) {
                    displayMsg(t.toString());
                }
            }
        }
    }

    @Override
    public Projet update(Projet obj) {
        do {
            String c = getMsg("1.changement de date butoir\n2.fin", "[0-9]");
            switch (c) {
                case "1":
                    displayMsg("Nouvelle date butoire:");
                    int j = Integer.parseInt(getMsg("Jour:", "[0-9]"));
                    int m = Integer.parseInt(getMsg("Mois:", "[0-9]"));
                    int a = Integer.parseInt(getMsg("Année:", "[0-9]"));
                    LocalDate dateFin = LocalDate.of(a, m, j);
                    obj.setDateFin(dateFin);
                    break;
                case "2":
                    return obj;
                default:
                    displayMsg("choix invalide");
            }

        } while (true);

    }

    @Override
    public Integer read() {
        int id = Integer.parseInt(getMsg("Numero de projet:", "[0-9]"));
        return id;
    }

    @Override
    public void affAll(List<Projet> lobj) {
        int i = 0;
        for (Projet p : lobj) {
            displayMsg((++i) + ". " + p.toString());
        }
    }


    @Override
    public void affListOBJ(List listObj) {
        int i =0;
        for(Object o:listObj){
            displayMsg((++i)+"."+o.toString());
        }
    }
}
