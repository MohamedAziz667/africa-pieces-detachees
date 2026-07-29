package com.africapd.dao;
import com.africapd.config.DatabaseConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.africapd.models.Client;

public class ClientDAO {

    public void ajouterClient(Client client){
        String sql = "INSERT INTO CLIENT (nom_client, prenom_client, email_client, contact_client, adresse_client) VALUES(?,?,?,?,?)";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, client.getNom());
            stmt.setString(2, client.getPrenom());
            stmt.setString(3, client.getEmail());
            stmt.setString(4, client.getNumero());
            stmt.setString(5, client.getAdresse());
            stmt.executeUpdate();
            conn.close();
            
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    public Client rechercherClient(int id){
        String sql = "SELECT * FROM Client WHERE id_client = ?";
        try {
            Connection conn = DatabaseConnection.getConnection();
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
            conn.close();
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
        return null;
    }

    public int modifierClient(Client client){
        String sql = "UPDATE Client SET nom_client = ?, prenom_client = ?, contact_client = ?, adresse_client = ?, email_client = ? WHERE id_client = ?";
        int ligne = 0;
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, client.getNom());
            stmt.setString(2, client.getPrenom());
            stmt.setString(3, client.getNumero());
            stmt.setString(4, client.getAdresse());
            stmt.setString(5, client.getEmail());
            stmt.setInt(6, client.getId());
            ligne = stmt.executeUpdate();
            conn.close();
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
        return ligne;
    }

    public int supprimerClient(int id){
        int ligne = 0;
        String sql = "DELETE FROM Client WHERE id_client = ?";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ligne = stmt.executeUpdate();
            conn.close();
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
        return ligne;
    }
}