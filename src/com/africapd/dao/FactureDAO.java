package com.africapd.dao;

import com.africapd.config.DatabaseConnection;
import com.africapd.models.Facture;
import com.africapd.models.Vente;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class FactureDAO {
    
    public int ajouterFacture(Facture facture){
        int ligne = 0;
        Connection conn = null;
        String sql = "INSERT INTO FACTURE (date_facture, fk_id_vente) VALUES (?, ?)";
        try {
            conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // LocalDate → java.sql.Date pour envoyer à MySQL
            stmt.setDate(1, java.sql.Date.valueOf(facture.getDateFacture()));
            stmt.setInt(2,facture.getVente().getIdVente());
            ligne = stmt.executeUpdate();
            if (ligne != 1) {
                throw new Exception("Échec de l'ajout de la facture.");
            }else{
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    int id_facture = rs.getInt(1);
                    facture.setIdFacture(id_facture);
                }else{
                    throw new Exception("Impossible de récupérer l'ID de la facture générée");
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

    public Facture rechercherFacture(int id){
        String sql = "SELECT * FROM FACTURE WHERE id_facture = ?";
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
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
        return null;
    }

    public int modifierFacture(Facture facture){
        int ligne = 0;
        Connection conn = null;
        String sql = "UPDATE FACTURE SET date_facture = ?, fk_id_vente = ? WHERE id_facture = ?";
        try {
            conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDate(1, java.sql.Date.valueOf(facture.getDateFacture()));
            stmt.setInt(2, facture.getVente().getIdVente());
            stmt.setInt(3, facture.getIdFacture());
            ligne = stmt.executeUpdate();
            if (ligne != 1) {
                throw new Exception("Aucune facture trouvée avec cet ID");
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

    public int supprimerFacture(int id){
        int ligne = 0;
        Connection conn = null;
        String sql = "DELETE FROM FACTURE WHERE id_facture = ?";
        try {
            conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ligne = stmt.executeUpdate();
            if (ligne != 1) {
                throw new Exception("Aucune facture trouvée avec cet ID");
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
}
