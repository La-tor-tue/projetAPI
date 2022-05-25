package bureau.vue;

import bureau.metier.Discipline;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class VueDisciplineGraphique extends VueBaseGraphique implements VueDisciplineInterface {
    @Override
    public Discipline create() {
        JTextField jtfNom = new JTextField();
        JTextField jtdesc = new JTextField();

        Object[] message = {
                "Nom de la discipline", jtfNom,
                "Description", jtdesc
        };

        int opt = JOptionPane.showConfirmDialog(null, message, "Discipline Create", JOptionPane.DEFAULT_OPTION);
        String nom = jtfNom.getText().toString();
        String desc = jtdesc.getText().toString();

        Discipline newDis = new Discipline(0, nom, desc, new ArrayList<>());
        return newDis;
    }

    @Override
    public void display(Discipline obj) {
        displayMsg(obj.toString());
    }

    @Override
    public Discipline update(Discipline obj) {
        do {
            int c = Integer.parseInt(getMsg("1.Description\n2.FIN", "[0-9]"));
            switch (c) {
                case 1:
                    obj.setDesc(getMsg("Description:", "[.]+"));
                    break;
                case 2:
                    return obj;
                default:
                    displayMsg("Choix INVALIDE!");
            }
        } while (true);
    }

    @Override
    public Integer read() {
        int c = Integer.parseInt(getMsg("Id discipline: ", "[0-9]+"));
        return c;
    }

    @Override
    public void affAll(List<Discipline> lobj) {
        int i = 0;
        for (Discipline d : lobj) {
            displayMsg((++i) + ". " + d.toString());
        }
    }
}
