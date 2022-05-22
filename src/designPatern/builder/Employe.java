package designPatern.builder;

import java.util.Objects;

public class Employe {
    protected int idEmp;
    protected String nom, prenom, matricule, tel, mail;

    private Employe(EmployeBuilder eb) {
        this.idEmp = eb.idEmp;
        this.mail = eb.mail;
        this.tel = eb.tel;
        this.nom = eb.nom;
        this.prenom = eb.prenom;
        this.matricule = eb.matricule;
    }

    public int getIdEmp() {
        return idEmp;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getMatricule() {
        return matricule;
    }

    public String getTel() {
        return tel;
    }

    public String getMail() {
        return mail;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employe employe = (Employe) o;
        return idEmp == employe.idEmp;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEmp);
    }

    public static class EmployeBuilder {

        protected int idEmp;
        protected String nom, prenom, matricule, tel, mail;

        public EmployeBuilder(int idEmp, String nom, String prenom, String matricule) throws Exception {
            if (idEmp <= 0 || nom == null || prenom == null || matricule == null)
                throw new Exception("Informations Incomplete");
        }

        public EmployeBuilder setMail(String mail) {
            this.mail = mail;
            return this;
        }

        public EmployeBuilder setTel(String tel) {
            this.tel = tel;
            return this;
        }

        public Employe build() throws Exception {
            return new Employe(this);
        }
    }
}
