package bureau.vue;

import bureau.metier.Projet;

import javax.swing.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VueProjetGraphique extends VueBaseGraphique implements VueProjetInterface {
    @Override
    public Projet create() {
        JTextField jtfNom = new JTextField();
        JTextField jtDDJ = new JTextField();
        JTextField jtDDM = new JTextField();
        JTextField jtDDA = new JTextField();
        JTextField jtDFJ = new JTextField();
        JTextField jtDFM = new JTextField();
        JTextField jtDFA = new JTextField();
        JTextField jtCout = new JTextField();

        Object[] message = {
                "Nom du Projet", jtfNom,
                "Date debut", " ",
                "Jour", jtDDJ,
                "Mois", jtDDM,
                "Année", jtDDA,
                "Date fin", " ",
                "Jour", jtDFJ,
                "Mois", jtDFM,
                "Année", jtDFA,
                "Cout", jtCout
        };

        int opt = JOptionPane.showConfirmDialog(null, message, "Création Projet", JOptionPane.DEFAULT_OPTION);
        String nom = jtfNom.getText().toString();
        int j = Integer.parseInt(jtDDJ.getText());
        int m = Integer.parseInt(jtDDM.getText());
        int a = Integer.parseInt(jtDDA.getText());
        LocalDate dateDebut = LocalDate.of(a, m, j);

        j = Integer.parseInt(jtDFJ.getText());
        m = Integer.parseInt(jtDFM.getText());
        a = Integer.parseInt(jtDFA.getText());
        LocalDate dateFin = LocalDate.of(a, m, j);
        BigDecimal cout = new BigDecimal(jtCout.getText());
        Projet newPj = new Projet(0, nom, dateDebut, dateFin, cout, new ArrayList<>(), new ArrayList<>());
        return newPj;
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
        return null;
    }
}
