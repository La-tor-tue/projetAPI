package bureau.vue;

import bureau.metier.Discipline;
import bureau.metier.Employe;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class VueEmployeGraphique extends VueBaseGraphique implements VueEmployeInterface {
    @Override
    public Employe create() {
        JTextField jtfNom = new JTextField();
        JTextField jtfPrenom = new JTextField();
        JTextField jtfMatricule = new JTextField();
        JTextField jtfTel = new JTextField();
        JTextField jtfMail = new JTextField();

        Object[] message = {
                "Nom", jtfNom,
                "Prénom", jtfPrenom,
                "Matricule", jtfMatricule,
                "Téléphone", jtfTel,
                "Mail", jtfMail,
        };

        int opt = JOptionPane.showConfirmDialog(null, message, "Nouvel Employé", JOptionPane.DEFAULT_OPTION);
        String nom = jtfNom.getText().toString();
        String prenom = jtfPrenom.getText().toString();
        String matricule = jtfMatricule.getText().toString();
        String téléphone = jtfTel.getText().toString();
        String mail = jtfMail.getText().toString();
        Employe newEmp = new Employe(0, nom, prenom, matricule, téléphone, mail, null);
        return newEmp;
    }

    @Override
    public void display(Employe obj) {
        displayMsg(obj.toString());
    }

    @Override
    public Employe update(Employe obj) {
        do {
            int c = Integer.parseInt(getMsg("1.Matricule\n2.Téléphone\n3.FIN", "[0-9]"));
            switch (c) {
                case 1:
                    obj.setMatricule(getMsg("Matricule:", "[0-9a-zA-Z]+"));
                    break;
                case 2:
                    obj.setTel(getMsg("Telephone:", "^[0-9]{3}[/]{1}[0-9]{6}$"));
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
        int c = Integer.parseInt(getMsg("Id employé: ", "[0-9]+"));
        return c;
    }

    @Override
    public void affAll(List<Employe> lobj) {
        int i = 0;
        for (Employe e : lobj) {
            displayMsg((++i) + ". " + e.toString());
        }
    }
}
