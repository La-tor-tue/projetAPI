package bureau.metier;

import java.time.LocalDate;
import java.util.Objects;

public class Travail {

    private int pourcentage;
    private LocalDate dateEngagement;

    private Employe emp;

    public Travail(int pourcentage, LocalDate dateEngagement, Employe emp) {
        this.pourcentage = pourcentage;
        this.dateEngagement = dateEngagement;
        this.emp = emp;
    }

    public Travail(Employe emp) {
        this.emp = emp;
    }

    public int getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(int pourcentage) {
        this.pourcentage = pourcentage;
    }

    public LocalDate getDateEngagement() {
        return dateEngagement;
    }

    public void setDateEngagement(LocalDate dateEngagement) {
        this.dateEngagement = dateEngagement;
    }

    public Employe getEmp() {
        return emp;
    }

    public void setEmp(Employe emp) {
        this.emp = emp;
    }

    @Override
    public String toString() {
        return "Travail{" +
                "pourcentage=" + pourcentage +
                ", dateEngagement=" + dateEngagement +
                ", emp=" + emp +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Travail travail = (Travail) o;
        return Objects.equals(emp, travail.emp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emp);
    }
}
