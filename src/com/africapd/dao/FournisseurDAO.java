package com.africapd.dao;

import com.africapd.config.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.africapd.models.Fournisseur;
public class FournisseurDAO {
    
    public int ajouterFournisseur(Fournisseur fournisseur){
        int ligne = 0;
        Connection conn = null;
        String sql = "INSERT INTO FOURNISSEUR (nom_fournisseur, prenom_fournisseur, contact_fournisseur) VALUES (?, ?, ?)";
        try {
            conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, fournisseur.getNom());
            stmt.setString(2, fournisseur.getPrenom());
            stmt.setString(3, fournisseur.getNumero());
            ligne = stmt.executeUpdate();
            if (ligne == 0) {
                throw new Exception("Échec de l'ajout du fournisseur");
            }else{
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    int id_fournisseur = rs.getInt(1);
                    fournisseur.setId(id_fournisseur);
                }else{
                    throw new Exception("Impossible de récupérer l'ID du fournisseur généré");
                }
            }
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }finally{
            try {
                if(conn != null){
                conn.close();
            }
            } catch (SQLException e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        }
        return ligne;
    }

    public Fournisseur rechercherFournisseur(int id){
        String sql = "SELECT * FROM FOURNISSEUR WHERE id_fournisseur = ?";
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
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

    public int modifierFournisseur(Fournisseur fournisseur){
        int ligne = 0;
        Connection conn = null;
        String sql = "UPDATE FOURNISSEUR SET nom_fournisseur = ?, prenom_fournisseur = ?, contact_fournisseur = ? WHERE id_fournisseur = ?";
        try {
            conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, fournisseur.getNom());
            stmt.setString(2, fournisseur.getPrenom());
            stmt.setString(3, fournisseur.getNumero());
            stmt.setInt(4, fournisseur.getId());
            ligne = stmt.executeUpdate();
            if (ligne == 0) {
                throw new Exception("Aucun fournisseur trouvé avec cet ID");
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

    public int supprimerFournisseur(int id){
        int ligne = 0;
        Connection conn = null;
        String sql = "DELETE FROM FOURNISSEUR WHERE id_fournisseur = ?";
        try {
            conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ligne = stmt.executeUpdate();
            if (ligne == 0) {
                throw new Exception("Aucun fournisseur trouvé avec cet ID");
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
