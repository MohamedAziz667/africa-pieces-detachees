package com.africapd.dao;
import com.africapd.config.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
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
}