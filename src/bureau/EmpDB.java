package bureau;

import myconnections.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class EmpDB {

    private Scanner sc = new Scanner(System.in);
    private Connection dbConnect;

    public void gestion() {
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        System.out.println("connexion établie");
        do {
            System.out.println("1.ajout\n2.recherche\n3.modification\n4.suppression\n5.fin");
            System.out.println("choix : ");
            int ch = sc.nextInt();
            sc.skip("\n");
            switch (ch) {
                case 1:
                    ajout();
                    break;
                case 2:
                    recherche();
                    break;
                case 3:
                    modification();
                    break;
                case 4:
                    suppression();
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("choix invalide recommencez ");
            }
        } while (true);

    }

    public void ajout() {

        System.out.println("Maricule :");
        String matricule = sc.nextLine();
        System.out.print("Mail :");
        String mail = sc.nextLine();
        System.out.print("Nom :");
        String nom = sc.nextLine();
        System.out.print("Prenom :");
        String prenom = sc.nextLine();
        System.out.print("Telephone :");
        String tel = sc.nextLine();

        String query1 = "insert into apiemploye(empmatricule,empmail,empnom,empprenom,emptel) values(?,?,?,?,?)";
        String query2 = "select idemp from apiemploye where empnom= ? and empprenom =? and emptel =? and empmail =? and empmatricule =?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1);
             PreparedStatement pstm2 = dbConnect.prepareStatement(query2);
        ) {
            pstm1.setString(1, matricule);
            pstm1.setString(2, mail);
            pstm1.setString(3, nom);
            pstm1.setString(4, prenom);
            pstm1.setString(5, tel);
            int n = pstm1.executeUpdate();
            System.out.println(n + " ligne insérée");
            if (n == 1) {
                pstm2.setString(1, nom);
                pstm2.setString(2, prenom);
                pstm2.setString(3, tel);
                pstm2.setString(4, mail);
                pstm2.setString(5, matricule);
                ResultSet rs = pstm2.executeQuery();
                if (rs.next()) {
                    int idemploye = rs.getInt(1);
                    System.out.println("Id  = " + idemploye);
                } else System.out.println("record introuvable");
                rs.close();
            }

        } catch (SQLException e) {
            System.out.println("erreur sql :" + e);
        }
    }

    public void recherche() {

        System.out.println("Id de l'employée ? ");
        int idrech = sc.nextInt();
        String query = "select * from apiemploye where idemp = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, idrech);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                String matricule = rs.getString(2);
                String mail = rs.getString(3);
                String nom = rs.getString(4);
                String prenom = rs.getString(5);
                String tel = rs.getString(6);
                System.out.printf("%d %s %s %s %s %s %s %s\n", idrech, matricule, mail, nom, prenom, tel);
            } else System.out.println("record introuvable");
            rs.close();
        } catch (SQLException e) {
            System.out.println("erreur sql :" + e);
        }

    }

    public void modification() {
        System.out.println("Id de l'employé ? ");
        int idrech = sc.nextInt();
        sc.skip("\n");
        System.out.println("Nouveau numero téléphone ");
        String ntel = sc.nextLine();
        String query = "update apiemploye set emptel=? where idemp = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setString(1, ntel);
            pstm.setInt(2, idrech);
            int n = pstm.executeUpdate();
            if (n != 0) System.out.println(n + "ligne mise à jour");
            else System.out.println("record introuvable");

        } catch (SQLException e) {
            System.out.println("erreur sql :" + e);
        }
    }


    public void suppression() {
        System.out.println("Id de l'employé ? ");
        int idrech = sc.nextInt();
        String query = "delete * from apiemploye where idemp = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, idrech);
            int n = pstm.executeUpdate();
            if (n != 0) System.out.println(n + "ligne supprimée");
            else System.out.println("record introuvable");

        } catch (SQLException e) {
            System.out.println("erreur sql :" + e);
        }

    }

    public static void main(String[] args) {

        EmpDB g = new EmpDB();
        g.gestion();
    }

}
