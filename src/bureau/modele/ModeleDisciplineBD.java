package bureau.modele;

import bureau.metier.Discipline;
import bureau.metier.Employe;
import myconnections.DBConnection;

import java.sql.Connection;
import java.sql.*;
import java.util.List;

public class ModeleDisciplineBD implements DAODiscipline {
    private Connection dbConnect;

    public void ModeleEmployeBD() {
        dbConnect = DBConnection.getConnection();
    }

    @Override
    public Discipline create(Discipline newObj) {
        String query = "insert into apidisciplines(disnom,disdescription) values(?,?)";
        String query2 = "Select iddis from apidisciplines where disnom=? and disdescription=?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query)) {
            pstm1.setString(1, newObj.getNom());
            pstm1.setString(2, newObj.getDesc());
            int n = pstm1.executeUpdate();
            if (n != 0) {
                try (PreparedStatement pstm2 = dbConnect.prepareStatement(query2)) {

                    pstm2.setString(1, newObj.getNom());
                    pstm2.setString(2, newObj.getDesc());
                    ResultSet rs = pstm2.executeQuery();
                    if (rs.next()) {
                        int idDis = rs.getInt(1);
                        newObj.setIdDis(idDis);
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
    public boolean delete(Discipline delRech) {
        String query = "delete * from apidisciplines where iddis = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, delRech.getIdDis());
            int n = pstm.executeUpdate();
            if (n == 0) return false;

        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    @Override
    public Discipline read(Discipline reachRech) {
        String query = "select * from apidisciplines where iddis = ?";
        Discipline find;
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, reachRech.getIdDis());
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                int iddis = rs.getInt(1);
                String nom = rs.getString(2);
                String desc = rs.getString(3);
                find = new Discipline(iddis, nom, desc, null);
            } else return null;
        } catch (SQLException e) {
            return null;
        }
        return find;
    }

    @Override
    public Discipline update(Discipline upRech) {
        String query = "update apidisciplines set disdescription=? where iddis = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setString(1, upRech.getDesc());
            pstm.setInt(2, upRech.getIdDis());
            int n = pstm.executeUpdate();
            if (n != 0) return read(upRech);
        } catch (SQLException e) {
            return null;
        }
        return null;
    }

    @Override
    public List<Discipline> readAll() {
        List<Discipline> listDis = null;
        String query = "Select * from apidisciplines";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            ResultSet rs = pstm.executeQuery();
            do {
                int iddis = rs.getInt(1);
                String nom = rs.getString(2);
                String desc = rs.getString(3);
                Discipline find = new Discipline(iddis, nom, desc, null);
                listDis.add(find);
            } while (rs.next());
        } catch (SQLException e) {
            return null;
        }
        return listDis;
    }
}
