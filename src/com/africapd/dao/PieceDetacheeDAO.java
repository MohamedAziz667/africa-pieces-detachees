package com.africapd.dao;

import com.africapd.models.PieceDetachee;
import com.africapd.config.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PieceDetacheeDAO {
    
    public int ajouterPiece(PieceDetachee piece){
        String sql = "INSERT INTO PIECE_DETACHEE (nom_piece, reference_piece, quantite_stock, prix_unitaire) VALUES (?, ?, ?, ?)";
        Connection conn = null;
        int ligne = 0;
        try {
            conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, piece.getNom());
            stmt.setString(2, piece.getReference());
            stmt.setInt(3, piece.getQuantiteStock());
            stmt.setDouble(4, piece.getPrix());
            ligne = stmt.executeUpdate();
            if (ligne != 1) {
                throw new Exception("Échec de l'ajout de la pièce");
            }
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int id_piece = rs.getInt(1);
                piece.setId(id_piece);
            }else{
                throw new Exception("Impossible de récupérer l'ID de la pièce généré");
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

    public PieceDetachee rechercherPiece(int id){
        String sql = "SELECT * FROM PIECE_DETACHEE WHERE id_piece = ?";
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                PieceDetachee piece = new PieceDetachee();
                piece.setId(rs.getInt("id_piece"));
                piece.setNom(rs.getString("nom_piece"));
                piece.setReference(rs.getString("reference_piece"));
                piece.setQuantiteStock(rs.getInt("quantite_stock"));
                piece.setPrix(rs.getDouble("prix_unitaire"));
                return piece;
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

    public int modifierPieceDetachee(PieceDetachee piece){
        int ligne = 0;
        Connection conn = null;
        String sql = "UPDATE PIECE_DETACHEE SET nom_piece = ?, reference_piece = ?, quantite_stock = ?, prix_unitaire = ? WHERE id_piece = ?";
        try {
            conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, piece.getNom());
            stmt.setString(2, piece.getReference());
            stmt.setInt(3, piece.getQuantiteStock());
            stmt.setDouble(4, piece.getPrix());
            stmt.setInt(5, piece.getId());
            ligne = stmt.executeUpdate();
            if (ligne == 0) {
                throw new Exception("Aucune pièce trouvée avec cet ID");
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

    public int supprimerPiece(int id){
        int ligne = 0;
        String sql = "DELETE FROM PIECE_DETACHEE WHERE id_piece = ?";
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ligne = stmt.executeUpdate();
            if (ligne == 0) {
                throw new Exception("Aucune pièce trouvée");
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