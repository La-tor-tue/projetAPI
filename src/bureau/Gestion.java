package bureau;

import bureau.modele.*;
import bureau.presenter.PresenterDiscipline;
import bureau.presenter.PresenterEmploye;
import bureau.presenter.PresenterProjet;
import bureau.vue.*;

public class Gestion {

    private PresenterDiscipline pdis;
    private PresenterEmploye pemp;
    private PresenterProjet ppj;

    public Gestion(String modeVue, String modeData) {
        VueDisciplineInterface vuedis;
        VueEmployeInterface vueemp;
        VueProjetInterface vuepj;
        VueBaseInterface vueb;

        DAODiscipline mdd;
        DAOEmploye mde;
        DAOProjet mdp;
        /*
        A BESOIN DES VERISON GRAPHIQUE ET CONNECTE POUR FONCTIONNEE
        if (modeVue.equals("console")){
            vuedis = new VueDiscipline();
            vueemp = new VueEmploye();
            vuepj = new VueProjet();
            vueb = new VueBase();
        }else {

        }

        if (modeData.equals("db")){

        }else {
            mdd = new ModeleDiscipline();
            mdp = new ModeleProjet();
            mde=new ModeleEmploye();
        }

        pdis= new PresenterDiscipline(mdd,vuedis);
        pemp= new PresenterEmploye(mde,vueemp);
        ppj= new PresenterProjet(mdp,vuepj);

        ppj.setPdisc(pdis);
        ppj.setPemp(pemp);
        pemp.setPdis(pdis);

        do {

            int ch = vueb.menu(new String[]{"Projets","Employé","Dis","fin"});

            switch (ch) {
                case 1:
                    ppj.gestion();
                    break;
                case 2:
                    pemp.gestion();
                    break;
                case 3:
                    pdis.gestion();
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("choix invalide");
            }
        } while (true);
        */
    }

    public static void main(String[] args) {
        String modeVue = args[0];
        String modeData = args[1];
        Gestion g = new Gestion(modeVue, modeData);
    }
}

