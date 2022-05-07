package bureau.vue;

import bureau.metier.Employe;

import java.util.List;

public class VueEmploye extends VueBase implements VueEmployeInterface {
    @Override
    public String getMsg(String invite) {
        return null;
    }

    @Override
    public Employe create() {
        String matricule = getMsg("Matriclue:", "[0-9a-zA-z]");
        String nom = getMsg("Nom:", "[a-zA-z]");
        String prenom = getMsg("Prenom:", "[a-zA-z]");
        String telephone = getMsg("Telephone:", "[0-9]");
        String mail = getMsg("Mail:", "[0-9a-zA-Z]");
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
            String c = getMsg("1.Matricule\n2.Téléphone\n3.FIN", "[0-9]");
            switch (c) {
                case "1":
                    obj.setMatricule(getMsg("Matricule:", "[0-9a-zA-Z]"));
                    break;
                case "2":
                    obj.setTel(getMsg("Telephone:", "[0-9]"));
                    break;
                case "3":
                    return obj;
                default:
                    displayMsg("Choix invalide, entrez un chiffre valable");
            }
        } while (true);
    }

    @Override
    public Integer read() {
        int c = Integer.parseInt(getMsg("Id employé: ", "[0-9]"));
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
