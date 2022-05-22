package designPatern.observer;

public class Employe extends Observer {

    protected int idEmp;
    protected String nom, prenom, matricule, tel, mail;

    public Employe(int idEmp, String nom, String prenom, String matricule, String tel, String mail) {
        this.idEmp = idEmp;
        this.nom = nom;
        this.prenom = prenom;
        this.matricule = matricule;
        this.tel = tel;
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "Employe{" +
                "idEmp=" + idEmp +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", matricule='" + matricule + '\'' +
                ", tel='" + tel + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }

    @Override
    public void update(String msg) {
        System.out.println(nom + " " + prenom + " a été notifié de : " + msg);
    }
}
