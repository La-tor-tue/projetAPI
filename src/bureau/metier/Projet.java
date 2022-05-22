package bureau.metier;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

/**
 * classe metier de GroupeProjet
 *
 * @author BRANDON MORIAU
 * @version 0.1
 */
public class Projet {

    /**
     * id d'un projet
     */
    private int idPj;
    /**
     * Nom du projet
     */
    private String nom;
    /**
     * Date de début du projet
     * Date de fin du projet
     */
    private LocalDate dateDebut, dateFin;

    private BigDecimal cout;
    /**
     * List des employés et de leur pourcentage du projet
     */
    private ArrayList<Travail> listTravail;
    /**
     * Liste des discipline et Quantité JH du projet
     */
    private ArrayList<Invest> listInvest;

    /**
     * @param idPj        id projet
     * @param nom         nom du projet
     * @param dateDebut   date de début du projet
     * @param dateFin     date de fin du projet
     * @param listTravail liste des employés et leur pourcentage et leur date Engagement du projet
     * @param listInvest  liste des disciplines avec leur quantite JH du projet
     *                    constructeur parametré
     */

    public Projet(int idPj, String nom, LocalDate dateDebut, LocalDate dateFin, BigDecimal cout, ArrayList<Travail> listTravail, ArrayList<Invest> listInvest) {
        this.idPj = idPj;
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.cout = cout;
        this.listTravail = listTravail;
        this.listInvest = listInvest;
    }

    /**
     * @param idPj id projet
     * @param nom  nom du projet
     */

    public Projet(int idPj, String nom) {
        this.idPj = idPj;
        this.nom = nom;
    }

    /**
     * getter
     *
     * @return id projet
     */
    public int getIdPj() {
        return idPj;
    }

    /**
     * setter
     *
     * @param idPj id projet
     */
    public void setIdPj(int idPj) {
        this.idPj = idPj;
    }

    /**
     * getter
     *
     * @return nom du projet
     */
    public String getNom() {
        return nom;
    }

    /**
     * setter
     *
     * @param nom nom du projet
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * getter
     *
     * @return date de debut du projet
     */
    public LocalDate getDateDebut() {
        return dateDebut;
    }

    /**
     * setter
     *
     * @param dateDebut date de fin du projet
     */
    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     * getter
     *
     * @return date de fin du projet
     */
    public LocalDate getDateFin() {
        return dateFin;
    }

    /**
     * setter
     *
     * @param dateFin date de fin du projet
     */
    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    /**
     * getter
     *
     * @return liste de travail du projet
     */
    public ArrayList<Travail> getListTravail() {
        return listTravail;
    }

    /**
     * setter
     *
     * @param listTravail liste de travail du projet
     */
    public void setListTravail(ArrayList<Travail> listTravail) {
        this.listTravail = listTravail;
    }

    /**
     * getter
     *
     * @return liste d'investissement du projet
     */

    public ArrayList<Invest> getListInvest() {
        return listInvest;
    }

    /**
     * setter
     *
     * @param listInvest liste d'investissement du projet
     */

    public void setListInvest(ArrayList<Invest> listInvest) {
        this.listInvest = listInvest;
    }

    public BigDecimal getCout() {
        return cout;
    }

    public void setCout(BigDecimal cout) {
        this.cout = cout;
    }


    @Override
    public String toString() {
        return "GroupeProjet{" +
                "idPj=" + idPj +
                ", nom='" + nom + '\'' +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", cout=" + cout +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Projet projet = (Projet) o;
        return idPj == projet.idPj;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPj);
    }

    /**
     * @param dis      disipline à ajouter
     * @param quantite quantité HJ de l'invest
     *                 <p>
     *                 Verifie si la discipline est présente, l'ajoute si absente
     * @return true si ajouté
     */
    public boolean addDiscipline(Discipline dis, int quantite) {
        Invest inv = new Invest(quantite, dis);
        if (!listInvest.contains(inv)) {
            listInvest.add(inv);
            return true;
        }
        return false;
    }

    /**
     * @param dis      discipline à modifier
     * @param quantite nouvelle quantité
     *                 modification de la quantité JH
     * @return true si modifié
     */

    public boolean modifDiscipline(Discipline dis, int quantite) {
        Invest inv = new Invest(quantite, dis);
        if (listInvest.contains(inv)) {
            listInvest.get(listInvest.indexOf(inv)).setQuantiteJH(quantite);
            return true;
        }
        return false;
    }

    /**
     * @param dis discipline à supprimé
     *            supprime la discipline si présente
     * @return true si supprimé
     */

    public boolean suppDiscipline(Discipline dis) {
        Invest inv = new Invest(dis);
        if (listInvest.contains(inv)) {
            listInvest.remove(inv);
            return true;
        }
        return false;
    }

    /**
     * @param emp         employé à ajouter
     * @param pourcentage sa participation
     * @param dateEng     sa date d'engagement
     *                    si l'employé est absent alors il est ajouté
     * @return true si ajouté
     */

    public boolean addEmploye(Employe emp, int pourcentage, LocalDate dateEng) {
        Travail tra = new Travail(pourcentage, dateEng, emp);
        if (!listTravail.contains(tra)) {
            listTravail.add(tra);
            return true;
        }
        return false;
    }

    /**
     * @param emp         Employé à modifier
     * @param pourcentage nouveau pourcentage
     *                    modifie le pourcentage en conservant la date d'engagement
     * @return true si modifié
     */

    public boolean modifEmploye(Employe emp, int pourcentage) {
        Travail tra = new Travail(pourcentage, null, emp);
        if (listTravail.contains(tra)) {
            listTravail.get(listTravail.indexOf(tra)).setPourcentage(pourcentage);
            return true;
        }
        return false;
    }

    /**
     * @param emp employé à supprimer
     *            supprime un employé si présent
     * @return true si supprimé
     */

    public boolean suppEmploye(Employe emp) {
        Travail tra = new Travail(emp);
        if (!listTravail.contains(tra)) {
            listTravail.remove(tra);
            return true;
        }
        return false;
    }

    /**
     * Getteur
     *
     * @return retourne la liste d'investissement ou null si vide
     */

    public ArrayList<Invest> listeDisciplinesEtInvestissement() {
        if (listInvest.isEmpty()) return null;
        return listInvest;
    }

    /**
     * calcule du total de quantité JH d'un projet
     *
     * @return le total de quantité HJ du projet (0 si aucune heure)
     */

    public int investissementTotal() {
        int total = 0;
        for (int i = 0; i < listInvest.size(); i++) {
            total = total + listInvest.get(i).getQuantiteJH();
        }
        return total;
    }

    /**
     * @return la liste de travail ou null si vide
     */
    public ArrayList<Travail> listeEmployeEtPourcentageEtDate() {
        if (listTravail.isEmpty()) return null;
        return listTravail;
    }

    /**
     * calcule le total pourcentage de travail du projet
     *
     * @return le total du pourcentage de travail
     */
    public int totalPourcentage() {
        int total = 0;
        for (Travail t : listTravail) {
            total = total + t.getPourcentage();
        }
        return total;
    }

    /**
     * Parcour les spécialités des employés du projet
     *
     * @return les spécialités ou null si aucune spé
     */

    public ArrayList<Discipline> listeSpecialites() {
        ArrayList<Discipline> listSpe = new ArrayList<>();
        for (Travail t : listTravail) {
            if (!listSpe.contains(t.getEmp().getExpertise()))
                listSpe.add(t.getEmp().getExpertise());
        }

        if (listSpe.isEmpty()) return null;
        return listSpe;
    }
}
