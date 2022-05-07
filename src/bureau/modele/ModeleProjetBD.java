package bureau.modele;

import bureau.metier.*;
import myconnections.DBConnection;
import oracle.jdbc.proxy.annotation.Pre;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ModeleProjetBD implements DAOProjet {
    private Connection dbConnect;

    public void ModeleEmployeBD() {
        dbConnect = DBConnection.getConnection();
    }


    @Override
    public Projet create(Projet newObj) {
        String query1 = "insert into apiprojet(pjtitre,pjdatedebut,pjdatefin,pjcout) values(?,?,?,?)";
        String query2 = "select idemp from apiprojet where pjtitre= ? and pjdatedebut =? and datefin =? and epjcout =?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1)) {
            pstm1.setString(1, newObj.getNom());
            pstm1.setDate(2, Date.valueOf(newObj.getDateDebut()));
            pstm1.setDate(3, Date.valueOf(newObj.getDateFin()));
            pstm1.setFloat(4, newObj.getCout());
            int n = pstm1.executeUpdate();
            if (n == 0) return null;
            if (n == 1) {
                try (PreparedStatement pstm2 = dbConnect.prepareStatement(query2)) {
                    pstm2.setString(1, newObj.getNom());
                    pstm2.setDate(2, Date.valueOf(newObj.getDateDebut()));
                    pstm2.setDate(3, Date.valueOf(newObj.getDateFin()));
                    pstm2.setFloat(4, newObj.getCout());
                    ResultSet rs = pstm2.executeQuery();
                    if (rs.next()) {
                        int idpj = rs.getInt(1);
                        newObj.setIdPj(idpj);
                    } else return null;
                } catch (SQLException e) {
                    return null;
                }
            }
        } catch (SQLException e) {
            return null;
        }

        return newObj;
    }

    @Override
    public boolean delete(Projet delRech) {
        String query = "delete * from apiprojet where idpj = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, delRech.getIdPj());
            int n = pstm.executeUpdate();
            if (n == 0) return false;

        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    @Override
    public Projet read(Projet reachRech) {
        String query1 = "Select * from apiprojet where idpj=?";
        String query2 = "Select * from apiprojetetinvest where idpj=?";
        String query3 = "Select * from apiprojetettravail where idpj=?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1)) {
            pstm1.setInt(1, reachRech.getIdPj());
            ResultSet rs = pstm1.executeQuery();
            if (rs.next()) {
                reachRech.setNom(rs.getString(2));
                //DATE DEMANDER AU PROF !!!!!!!!!!!!!!!
                /*
                reachRech.setDateDebut(rs.getDate(3));
                reachRech.setDateFin(rs.getDate(4));
                */
                reachRech.setCout(rs.getFloat(5));
                try (PreparedStatement pstm2 = dbConnect.prepareStatement(query2)) {
                    pstm2.setInt(1, reachRech.getIdPj());
                    rs = pstm2.executeQuery();

                    do {
                        int iddis = rs.getInt(3);
                        String nom = rs.getString(4);
                        int quantite = rs.getInt(5);
                        Discipline tmp = new Discipline(iddis, nom);
                        Invest find = new Invest(quantite, tmp);
                        reachRech.getListInvest().add(find);
                    } while (rs.next());
                } catch (SQLException e) {
                    return null;
                }
                try (PreparedStatement pstm3 = dbConnect.prepareStatement(query3)) {
                    pstm3.setInt(1, reachRech.getIdPj());
                    rs = pstm3.executeQuery();
                    do {
                        int idemp = rs.getInt(3);
                        int pour = rs.getInt(7);
                        //LocalDate dateEng = rs.getDate(8);
                        Employe tmp = new Employe(idemp, null, null, null, null, null, null);
                        Travail find = new Travail(pour, null, tmp);
                        reachRech.getListTravail().add(find);
                    } while (rs.next());
                } catch (SQLException e) {
                    return null;
                }
            }
        } catch (SQLException e) {
            return null;
        }
        return reachRech;
    }

    @Override
    public Projet update(Projet upRech) {

        String query = "update apiprojet set pjdatefin=? , pjcout=? where idpj = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setDate(1, Date.valueOf(upRech.getDateFin()));
            pstm.setFloat(2, upRech.getCout());
            pstm.setInt(2, upRech.getIdPj());
            int n = pstm.executeUpdate();
            if (n != 0) return read(upRech);
        } catch (SQLException e) {
            return null;
        }
        return null;
    }

    @Override
    public List<Projet> readAll() {
        List<Projet> listPj = null;
        String query = "Select idpj from apiprojet";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            ResultSet rs = pstm.executeQuery();
            do {
                Projet find = new Projet(rs.getInt(1), null);
                find = read(find);
                listPj.add(find);
            } while (rs.next());
        } catch (SQLException e) {
            return null;
        }
        return listPj;
    }

    @Override
    public ArrayList<Invest> listeDisEtInv(Projet pj) {
        ArrayList<Invest> listInv = null;
        String query = "Select  * from apiprojetetinvest where idpj=?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, pj.getIdPj());
            ResultSet rs = pstm.executeQuery();
            do {
                int iddis = rs.getInt(1);
                String nom = rs.getString(2);
                int quantite = rs.getInt(3);
                Discipline dis = new Discipline(iddis, nom);
                Invest inv = new Invest(quantite, dis);
                listInv.add(inv);
            } while (rs.next());
        } catch (SQLException e) {
            return null;
        }
        return listInv;
    }

    @Override
    public ArrayList<Discipline> listeSpec(Projet pj) {
        ArrayList<Discipline> listSpec = null;
        String query = "Select  * from apiprojetetinvest where idpj=?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, pj.getIdPj());
            ResultSet rs = pstm.executeQuery();
            do {
                int iddis = rs.getInt(1);
                String nom = rs.getString(2);
                Discipline dis = new Discipline(iddis, nom);
                listSpec.add(dis);
            } while (rs.next());
        } catch (SQLException e) {
            return null;
        }
        return listSpec;
    }

    @Override
    public ArrayList<Travail> listTravailEtEmp(Projet pj) {
        ArrayList<Travail> listTravail = null;
        String query = "Select  * from apiprojetettravail where idpj=?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, pj.getIdPj());
            ResultSet rs = pstm.executeQuery();
            do {
                int idemp = rs.getInt(1);
                String nom = rs.getString(2);
                int pour = rs.getInt(3);
                //LocalDate dateEng = rs.getDate(8);
                Employe emp = new Employe(idemp, nom, null, null, null, null, null);
                Travail find = new Travail(pour, null, emp);
                listTravail.add(find);
            } while (rs.next());
        } catch (SQLException e) {
            return null;
        }
        return listTravail;
    }

    @Override
    public int totalPour(Projet pj) {
        int total=0;
        String query = "Select  * from apiprojetetinvest where idpj=?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, pj.getIdPj());
            ResultSet rs = pstm.executeQuery();
            do {
                total=total+rs.getInt(1);
            } while (rs.next());
        } catch (SQLException e) {
            return 0;
        }
        return total;
    }

    @Override
    public int totalInvet(Projet pj) {
        int total=0;
        String query = "Select  * from apiprojetettravail where idpj=?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, pj.getIdPj());
            ResultSet rs = pstm.executeQuery();
            do {
                total=total+rs.getInt(1);
            } while (rs.next());
        } catch (SQLException e) {
            return 0;
        }
        return total;
    }

    @Override
    public boolean addEmp(Projet pj, int pourcentage, Employe emp, LocalDate date) {
        return false;
    }

    @Override
    public boolean delEmp(Projet pj, Employe emp) {
        return false;
    }

    @Override
    public boolean upEmp(Projet pj, Employe emp, int pourcentage) {
        return false;
    }

    @Override
    public boolean addDis(Projet pj, int quantite, Discipline dis) {
        return false;
    }

    @Override
    public boolean delDis(Projet pj, Discipline dis) {
        return false;
    }

    @Override
    public boolean upDis(Projet pj, Discipline dis, int quantite) {
        return false;
    }
}
