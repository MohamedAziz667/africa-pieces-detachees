package com.africapd.dao;

import com.africapd.config.DatabaseConnection;
import com.africapd.models.Facture;
import com.africapd.models.Vente;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FactureDAO {
    
    public void ajouterFacture(Facture facture){
        String sql = "INSERT INTO FACTURE (date_facture, fk_id_vente) VALUES (?, ?)";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            // LocalDate → java.sql.Date pour envoyer à MySQL
            stmt.setDate(1, java.sql.Date.valueOf(facture.getDateFacture()));
            stmt.setInt(2, facture.getIdFacture());
            stmt.setInt(3,facture.getVente().getIdVente());
            stmt.executeUpdate();
            conn.close();
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    public Facture rechercherFacture(int id){
        String sql = "SELECT * FROM FACTURE WHERE id_facture = ?";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Facture facture = new Facture();
                Vente vente = new Vente();
                
                facture.setIdFacture(rs.getInt("id_facture"));
                vente.setIdVente(rs.getInt("fk_id_vente"));
                facture.setVente(vente);
                // java.sql.Date → LocalDate pour recevoir de MySQL
                facture.setDateFacture(rs.getDate("date_facture").toLocalDate());
                return facture;
            }
            conn.close();
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
        return null;
    }

    public int modifierFacture(Facture facture){
        int ligne = 0;
        String sql = "UPDATE FACTURE SET date_facture = ?, fk_id_vente = ? WHERE id_facture = ?";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDate(1, java.sql.Date.valueOf(facture.getDateFacture()));
            stmt.setInt(2, facture.getVente().getIdVente());
            stmt.setInt(3, facture.getIdFacture());
            ligne = stmt.executeUpdate();
            return ligne;
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
        return ligne;
    }

    public int supprimerFacture(int id){
        int ligne = 0;
        String sql = "DELETE FROM FACTURE WHERE id_facture = ?";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ligne = stmt.executeUpdate();
            return ligne;
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
        return ligne;
    }
}
