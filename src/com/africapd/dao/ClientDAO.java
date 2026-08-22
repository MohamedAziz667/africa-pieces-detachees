package com.africapd.dao;
import com.africapd.config.DatabaseConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import com.africapd.models.Client;

public class ClientDAO {

    public int ajouterClient(Client client){
        String sql = "INSERT INTO CLIENT (nom_client, prenom_client, email_client, contact_client, adresse_client) VALUES(?,?,?,?,?)";
        Connection conn = null;
        int ligne = 0;
        try {
            conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            stmt.setString(1, client.getNom());
            stmt.setString(2, client.getPrenom());
            stmt.setString(3, client.getEmail());
            stmt.setString(4, client.getNumero());
            stmt.setString(5, client.getAdresse());
            ligne = stmt.executeUpdate();
            if (ligne == 0) {
                throw new Exception("Échec de l'ajout du client");
            }else{
                ResultSet rs = stmt.getGeneratedKeys();
                if(rs.next()){
                    int id_client = rs.getInt(1);
                    client.setId(id_client);
                }else{
                    throw new Exception("Impossible de récupérer l'ID du client généré");
                }
            }
            
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }finally{
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        }
        return ligne;
    }

    public Client rechercherClient(int id){
        String sql = "SELECT * FROM Client WHERE id_client = ?";
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Client client = new Client();
                client.setId(rs.getInt("id_client"));
                client.setNom(rs.getString("nom_client"));
                client.setPrenom(rs.getString("prenom_client"));
                client.setEmail(rs.getString("email_client"));
                client.setAdresse(rs.getString("adresse_client"));
                client.setNumero(rs.getString("contact_client"));
                return client;
            }
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        } finally{
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Erreur lors de la fermeture : " + e.getMessage());
            }
        }
        return null;
    }

    public int modifierClient(Client client){
        String sql = "UPDATE Client SET nom_client = ?, prenom_client = ?, contact_client = ?, adresse_client = ?, email_client = ? WHERE id_client = ?";
        int ligne = 0;
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, client.getNom());
            stmt.setString(2, client.getPrenom());
            stmt.setString(3, client.getNumero());
            stmt.setString(4, client.getAdresse());
            stmt.setString(5, client.getEmail());
            stmt.setInt(6, client.getId());
            ligne = stmt.executeUpdate();
            if (ligne == 0) {
                throw new Exception("Aucun client trouvé avec cet ID");
            }
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }finally{
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Erreur lors de la fermeture : " + e.getMessage());
            }
        }
        return ligne;
    }

    public int supprimerClient(int id){
        int ligne = 0;
        String sql = "DELETE FROM Client WHERE id_client = ?";
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ligne = stmt.executeUpdate();
            if (ligne == 0) {
                throw new Exception("Aucun client trouvé avec cet ID");
            }
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }finally{
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Erreur lors de la fermeture : " + e.getMessage());
            }
        }
        return ligne;
    }
}