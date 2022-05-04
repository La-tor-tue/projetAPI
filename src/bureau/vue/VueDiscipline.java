package bureau.vue;

import bureau.metier.Discipline;
import bureau.metier.Employe;

import java.util.List;

public class VueDiscipline extends VueBase implements VueDisciplineInterface{
    @Override
    public String getMsg(String invite) {
        return null;
    }

    @Override
    public Discipline create() {
        String nom = getMsg("Nom: ","[:alpha:]");
        String desc= getMsg("Description: ","[a-zA-Z]");
        return new Discipline(0,nom,desc,null);
    }

    @Override
    public void display(Discipline obj) {
        displayMsg(obj.toString());
    }

    @Override
    public Discipline update(Discipline obj) {
        do {
            String c = getMsg("1.Description\n2.FIN","[0-9]");
            switch (c){
                case "1":
                    obj.setDesc(getMsg("Description:","[:alpha:]"));
                    break;
                case "2":
                    return obj;
                default:
                    displayMsg("Choix invalide, entrez un chiffre valable");
            }
        }while (true);
    }

    @Override
    public Integer read() {
        int c = Integer.parseInt(getMsg("Id discipline: ","[0-9]"));
        return c;
    }

    @Override
    public void affAll(List<Discipline> lobj) {
        int i =0;
        for (Discipline d:lobj){
            displayMsg((++i)+". "+d.toString());
        }
    }
}
