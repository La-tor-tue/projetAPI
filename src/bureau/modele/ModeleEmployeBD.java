package bureau.modele;

import bureau.metier.Discipline;
import bureau.metier.Employe;
import bureau.metier.Projet;
import myconnections.DBConnection;

import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModeleEmployeBD implements DAOEmploye {
    protected Connection dbConnect;

    public ModeleEmployeBD() {
        dbConnect = DBConnection.getConnection();
    }

    @Override
    public Employe create(Employe newObj) {
        String query1 = "insert into apiemploye(empmatricule,empmail,empnom,empprenom,emptel,iddis) values(?,?,?,?,?,?)";
        String query2 = "select idemp from apiemploye where empnom= ? and empprenom =? and emptel =? and empmail =? and empmatricule =?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1)) {
            pstm1.setString(1, newObj.getMatricule());
            pstm1.setString(2, newObj.getMail());
            pstm1.setString(3, newObj.getNom());
            pstm1.setString(4, newObj.getPrenom());
            pstm1.setString(5, newObj.getTel());
            pstm1.setInt(6, newObj.getExpertise().getIdDis());
            int n = pstm1.executeUpdate();
            if (n == 0) return null;
            if (n == 1) {
                try (PreparedStatement pstm2 = dbConnect.prepareStatement(query2)) {
                    pstm2.setString(1, newObj.getNom());
                    pstm2.setString(2, newObj.getPrenom());
                    pstm2.setString(3, newObj.getTel());
                    pstm2.setString(4, newObj.getMail());
                    pstm2.setString(5, newObj.getMatricule());
                    ResultSet rs = pstm2.executeQuery();
                    if (rs.next()) {
                        int idemploye = rs.getInt(1);
                        newObj.setIdEmp(idemploye);
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
    public boolean delete(Employe delRech) {
        String query = "delete * from apiemploye where idemp = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, delRech.getIdEmp());
            int n = pstm.executeUpdate();
            if (n == 0) return false;

        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    @Override
    public Employe read(Employe reachRech) {
        String query = "select * from apiemployedis where idemp = ?";
        Employe find;
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, reachRech.getIdEmp());
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                int idemp = rs.getInt(1);
                String matricule = rs.getString(2);
                String mail = rs.getString(3);
                String nom = rs.getString(4);
                String prenom = rs.getString(5);
                String tel = rs.getString(6);
                int idDis = rs.getInt(7);
                String disNom = rs.getString(8);
                String desc = rs.getString(9);
                Discipline dis = new Discipline(idDis, disNom, desc, null);
                find = new Employe(idemp, nom, prenom, matricule, tel, mail, dis);
            } else return null;
        } catch (SQLException e) {
            return null;
        }
        return find;
    }

    @Override
    public Employe update(Employe upRech) {
        String query = "update apiemploye set emptel=? where idemp = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setString(1, upRech.getTel());
            pstm.setInt(2, upRech.getIdEmp());
            int n = pstm.executeUpdate();
            if (n != 0) return read(upRech);
        } catch (SQLException e) {
            return null;
        }
        return null;
    }

    @Override
    public ArrayList<Employe> readAll() {
        ArrayList<Employe> listEmp = null;
        String query = "Select * from apiemployedis";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            ResultSet rs = pstm.executeQuery();
            do {
                int idemp = rs.getInt(1);
                String matricule = rs.getString(2);
                String mail = rs.getString(3);
                String nom = rs.getString(4);
                String prenom = rs.getString(5);
                String tel = rs.getString(6);
                int idDis = rs.getInt(7);
                String disNom = rs.getString(8);
                String desc = rs.getString(9);
                Discipline dis = new Discipline(idDis, disNom, desc, null);
                Employe find = new Employe(idemp, nom, prenom, matricule, tel, mail, dis);
                listEmp.add(find);
            } while (rs.next());
        } catch (SQLException e) {
            return null;
        }
        return listEmp;
    }
}
