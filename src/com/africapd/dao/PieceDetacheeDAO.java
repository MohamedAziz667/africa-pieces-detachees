package com.africapd.dao;

import com.africapd.models.PieceDetachee;
import com.africapd.config.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PieceDetacheeDAO {
    
    public void ajouterPiece(PieceDetachee piece){
        String sql = "INSERT INTO PIECE_DETACHEE (nom_piece, reference_piece, quantite_stock, prix_unitaire) VALUES (?, ?, ?, ?)";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, piece.getNom());
            stmt.setString(2, piece.getReference());
            stmt.setInt(3, piece.getQuantiteStock());
            stmt.setDouble(4, piece.getPrix());
            stmt.executeUpdate();
            conn.close();
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    public PieceDetachee rechercherPiece(int id){
        String sql = "SELECT * FROM PIECE_DETACHEE WHERE id_piece = ?";
        try {
            Connection conn = DatabaseConnection.getConnection();
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
            conn.close();
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
        return null;
    }

    public int modifierPieceDetachee(PieceDetachee piece){
        int ligne = 0;
        String sql = "UPDATE PIECE_DETACHEE SET nom_piece = ?, reference_piece = ?, quantite_stock = ?, prix_unitaire = ? WHERE id_piece = ?";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, piece.getNom());
            stmt.setString(2, piece.getReference());
            stmt.setInt(3, piece.getQuantiteStock());
            stmt.setDouble(4, piece.getPrix());
            stmt.setInt(5, piece.getId());
            ligne = stmt.executeUpdate();
            conn.close();
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
        return ligne;
    }

    public int supprimerPiece(int id){
        int ligne = 0;
        String sql = "DELETE FROM PIECE_DETACHEE WHERE id_piece = ?";
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