package bureau;

import bureau.modele.*;
import bureau.presenter.PresenterDiscipline;
import bureau.presenter.PresenterEmploye;
import bureau.presenter.PresenterProjet;
import bureau.vue.*;

public class Gestion {

    private PresenterDiscipline pDis;
    private PresenterEmploye pEmp;
    private PresenterProjet pPj;

    public Gestion(String modeVue, String modeData) {
        VueDisciplineInterface vueDis;
        VueEmployeInterface vueEmp;
        VueProjetInterface vuePj;
        VueBaseInterface vueBase;

        DAODiscipline mdD;
        DAOEmploye mdE;
        DAOProjet mdP;
        /*
        A BESOIN DES VERISON GRAPHIQUE ET CONNECTE POUR FONCTIONNEE
        if (modeVue.equals("console")){*/
        vueDis = new VueDiscipline();
        vueEmp = new VueEmploye();
        vuePj = new VueProjet();
        vueBase = new VueBase();
            /*
        }else {

        }
        */
        if (modeData.equals("db")){
        mdD=new ModeleDisciplineBD();
        mdP=new ModeleProjetBD();
        mdE=new ModeleEmploye();

        }else {
            mdD = new ModeleDiscipline();
            mdP = new ModeleProjet();
            mdE = new ModeleEmploye();
        }

        pDis= new PresenterDiscipline(mdD,vueDis);
        pEmp= new PresenterEmploye(mdE,vueEmp);
        pPj= new PresenterProjet(mdP,vuePj);

        pPj.setPdisc(pDis);
        pPj.setPemp(pEmp);
        pEmp.setPdis(pDis);

        do {

            int ch = vueBase.menu(new String[]{"Projets","Employé","Dis","fin"});

            switch (ch) {
                case 1:
                    pPj.gestion();
                    break;
                case 2:
                    pEmp.gestion();
                    break;
                case 3:
                    pDis.gestion();
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("choix invalide");
            }
        } while (true);

    }

    public static void main(String[] args) {
        String modeVue = args[0];
        String modeData = args[1];
        Gestion g = new Gestion(modeVue, modeData);
    }
}

