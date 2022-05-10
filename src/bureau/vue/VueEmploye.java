package bureau.vue;

import bureau.metier.Employe;

import java.util.ArrayList;
import java.util.List;

public class VueEmploye extends VueBase implements VueEmployeInterface {
    @Override
    public String getMsg(String invite) {
        return null;
    }

    @Override
    public Employe create() {
        String matricule = getMsg("Matriclue:", "[0-9a-zA-z]+");
        String nom = getMsg("Nom:", "[a-z A-z]+");
        String prenom = getMsg("Prenom:", "[a-z A-z]+");
        String telephone = getMsg("Telephone:", "^[0-9]{3}[/]{1}[0-9]{6}$");
        String mail = getMsg("Mail:", "^(.+)@(.+)$");
        Employe newEMP = new Employe(0, nom, prenom, matricule, telephone, mail, null);
        return newEMP;
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
    public void affAll(List lobj) {
        int i = 0;
        for (Object e : lobj) {
            displayMsg((++i) + ". " + e.toString());
        }
    }
}
