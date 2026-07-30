package com.africapd.dao;

import com.africapd.config.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.africapd.models.Fournisseur;
public class FournisseurDAO {
    
    public void ajouterFournisseur(Fournisseur fournisseur){
        String sql = "INSERT INTO FOURNISSEUR (nom_fournisseur, prenom_fournisseur, contact_fournisseur) VALUES (?, ?, ?)";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, fournisseur.getNom());
            stmt.setString(2, fournisseur.getPrenom());
            stmt.setString(3, fournisseur.getNumero());
            stmt.executeUpdate();
            conn.close();
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    public Fournisseur rechercherFournisseur(int id){
        String sql = "SELECT * FROM FOURNISSEUR WHERE id_fournisseur = ?";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Fournisseur fournisseur = new Fournisseur();
                fournisseur.setId(rs.getInt("id_fournisseur"));
                fournisseur.setNom(rs.getString("nom_fournisseur"));
                fournisseur.setPrenom(rs.getString("prenom_fournisseur"));
                fournisseur.setNumero(rs.getString("contact_fournisseur"));
                return fournisseur;
            }
            conn.close();
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
        return null;
    }

    public int modifierFournisseur(Fournisseur fournisseur){
        int ligne = 0;
        String sql = "UPDATE FOURNISSEUR SET nom_fournisseur = ?, prenom_fournisseur = ?, contact_fournisseur = ? WHERE id_fournisseur = ?";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, fournisseur.getNom());
            stmt.setString(2, fournisseur.getPrenom());
            stmt.setString(3, fournisseur.getNumero());
            stmt.setInt(4, fournisseur.getId());
            ligne = stmt.executeUpdate();
            conn.close();
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
        return ligne;
    }

    public int supprimerFournisseur(int id){
        int ligne = 0;
        String sql = "DELETE FROM FOURNISSEUR WHERE id_fournisseur = ?";
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
