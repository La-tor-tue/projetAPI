package bureau.vue;

import bureau.metier.Invest;
import bureau.metier.Projet;
import bureau.metier.Travail;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VueProjet extends VueBase implements VueProjetInterface {

    @Override
    public Projet create() {
        String nom = getMsg("Nom", ".+");
        LocalDate dateDebut = getDate("Date de Début:");
        LocalDate dateFin;
        do {
            dateFin = getDate("Date de Fin:");
        } while (dateFin.isBefore(dateDebut));

        double tmp = Double.parseDouble(getMsg("Cout monétaire:", "[0-9]+"));
        BigDecimal cout = BigDecimal.valueOf(tmp);
        cout = cout.setScale(2, RoundingMode.HALF_UP);
        Projet newP = new Projet(0, nom, dateDebut, dateFin, cout, null, null);
        return newP;
    }

    @Override
    public void display(Projet obj) {
        displayMsg(obj.toString());
        if (!obj.getListInvest().isEmpty() && obj.getListInvest() != null) {
            String c = getMsg("Afficher les investissements? (O/N)", "[onON]{1}");
            if (c.equals("o") || c.equals("O")) {
                affListOBJ(obj.getListInvest());
            }
        }
        if (!obj.getListTravail().isEmpty() && obj.getListTravail() != null) {
            String c = getMsg("Afficher les employé(e)s travaillant sur le projet?", "[onON]{1}");
            if (c.equals("o") || c.equals("O")) {
                affListOBJ(obj.getListTravail());
            }
        }
    }

    @Override
    public Projet update(Projet obj) {
        do {
            int c = Integer.parseInt(getMsg("1.changement de date butoir\n2.changement cout\n3.fin", "[0-9]"));
            switch (c) {
                case 1:
                    LocalDate dateFin;
                    do {
                        dateFin = getDate("Nouvelle date butoire:");
                    } while (obj.getDateDebut().isAfter(dateFin));
                    obj.setDateFin(dateFin);
                    break;
                case 2:
                    double tmp = Double.parseDouble(getMsg("Cout monétaire:", "[0-9]+"));
                    BigDecimal cout = BigDecimal.valueOf(tmp);
                    cout = cout.setScale(2, RoundingMode.HALF_UP);
                    obj.setCout(cout);
                    break;
                case 3:
                    return obj;
                default:
                    displayMsg("Choix INVALIDE!");
            }

        } while (true);

    }

    @Override
    public Integer read() {
        int id = Integer.parseInt(getMsg("Numero de projet:", "[0-9]+"));
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
        int i = 0;
        for (Object o : listObj) {
            displayMsg((++i) + "." + o.toString());
        }
    }

    @Override
    public LocalDate getDate(String invite) {
        displayMsg(invite);
        int j;
        int m;
        do {
            j = Integer.parseInt(getMsg("Jour:", "[0-9]{1,2}"));
        } while (j < 1 || j > 31);
        do {
            m = Integer.parseInt(getMsg("Mois:", "[0-9]{1,2}"));
        } while (m < 1 || j > 12);
        int a = Integer.parseInt(getMsg("Année:", "[0-9]{4}"));
        LocalDate date = LocalDate.of(a, m, j);
        return date;
    }

}
