package bureau.metier;

import java.util.Objects;

public class Employe {

    private int idEmp;
    private String nom, prenom, matricule, tel, mail;

    private Discipline expertise;

    public Employe(int idEmp, String nom, String prenom, String matricule, String tel, String mail, Discipline expertise) {
        this.idEmp = idEmp;
        this.nom = nom;
        this.prenom = prenom;
        this.matricule = matricule;
        this.tel = tel;
        this.mail = mail;
        this.expertise = expertise;
    }

    public Employe(String nom, String prenom, String mail) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
    }

    public int getIdEmp() {
        return idEmp;
    }

    public void setIdEmp(int idEmp) {
        this.idEmp = idEmp;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Discipline getExpertise() {
        return expertise;
    }

    public void setExpertise(Discipline expertise) {
        this.expertise = expertise;
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
                ", expertise=" + expertise +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employe employe = (Employe) o;
        return Objects.equals(nom, employe.nom) && Objects.equals(prenom, employe.prenom) && Objects.equals(mail, employe.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, prenom, mail);
    }
}
