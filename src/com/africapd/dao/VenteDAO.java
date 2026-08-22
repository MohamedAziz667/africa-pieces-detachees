package com.africapd.dao;

import com.africapd.config.DatabaseConnection;
import com.africapd.models.Vente;
import com.africapd.models.VentePiece;
import com.africapd.models.Client;
import com.africapd.models.PieceDetachee;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class VenteDAO {

    public void ajouterVente(Vente vente){
        String sql = "INSERT INTO VENTE(date_vente, fk_id_client) VALUES(?, ?);";
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            conn.setAutoCommit(false);

            stmt.setDate(1, java.sql.Date.valueOf(vente.getDateVente()));
            stmt.setInt(2, vente.getClient().getId());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int idVente = rs.getInt(1);
                vente.setIdVente(idVente);
                
                String requete = "INSERT INTO VENTE_PIECE(fk_id_vente, fk_id_piece, quantite, prix_unitaire) VALUES(?, ?, ?, ?)";
                PreparedStatement stmt1 = conn.prepareStatement(requete);
                for (VentePiece vp : vente.getligneVente()) {
                    stmt1.setInt(1, idVente);
                    stmt1.setInt(2, vp.getPiece().getId());
                    stmt1.setInt(3, vp.getQuantite());
                    stmt1.setDouble(4, vp.getprixUnitaire());
                    stmt1.executeUpdate();
                }
            }else{
                throw new Exception("Impossible de récupérer l'ID de la vente généré");
            }
            conn.commit();
        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    System.err.println("Erreur : " + ex.getMessage());
                }
            }
            System.err.println("Erreur : " + e.getMessage());
        } finally{
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Erreur lors de la fermeture : " + e.getMessage());
                }
            }
        }
    }

    public Vente rechercherVente(int id_vente){
        String sql = "SELECT * FROM VENTE WHERE id_vente = ?";
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id_vente);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Vente vente = new Vente();
                Client client = new Client();
                vente.setIdVente(rs.getInt("id_vente"));
                vente.setDateVente(rs.getDate("date_vente").toLocalDate());
                client.setId(rs.getInt("fk_id_client"));
                vente.setClient(client);
                String requete = "SELECT * FROM VENTE_PIECE WHERE fk_id_vente = ?";
                PreparedStatement stmt1 = conn.prepareStatement(requete);
                stmt1.setInt(1, id_vente);
                ResultSet rsL = stmt1.executeQuery();
                while(rsL.next()) {
                    VentePiece ventePiece = new VentePiece();
                    PieceDetachee piece = new PieceDetachee();
                    piece.setId(rsL.getInt("fk_id_piece"));
                    ventePiece.setPiece(piece);
                    ventePiece.setQuantite(rsL.getInt("quantite"));
                    ventePiece.setprixUnitaire(rsL.getDouble("prix_unitaire"));
                    vente.ajouterPiece(ventePiece);
                }

                return vente;
            }else{
                throw new Exception("Aucun vente n'a été retrouver");
            }
        } catch (Exception e) {
            System.err.println("Erreur : " + e.getMessage());
        }finally{
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

    public int modifierVente(Vente vente){
        String sql = "UPDATE VENTE SET date_vente = ?, fk_id_client = ? WHERE id_vente = ?";
        int ligne = 0;
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            conn.setAutoCommit(false);
            stmt.setDate(1, java.sql.Date.valueOf(vente.getDateVente()));
            stmt.setInt(2, vente.getClient().getId());
            stmt.setInt(3, vente.getIdVente());
            ligne = stmt.executeUpdate();
            if (ligne == 0) {
                throw new Exception("Aucune vente trouvée avec cet ID");
            }
            String rqtDelete = "DELETE FROM VENTE_PIECE WHERE fk_id_vente = ?";
            PreparedStatement stmt1 = conn.prepareStatement(rqtDelete);
            stmt1.setInt(1, vente.getIdVente());
            stmt1.executeUpdate();

            String rqtInsert = "INSERT INTO VENTE_PIECE(fk_id_vente, fk_id_piece, quantite, prix_unitaire) VALUES(?, ?, ?, ?)";
            PreparedStatement stmt2 = conn.prepareStatement(rqtInsert);
            for (VentePiece vp : vente.getligneVente()) {
                stmt2.setInt(1, vente.getIdVente());
                stmt2.setInt(2, vp.getPiece().getId());
                stmt2.setInt(3, vp.getQuantite());
                stmt2.setDouble(4, vp.getprixUnitaire());
                stmt2.executeUpdate();
            }
            conn.commit();
        } catch (Exception e) {
            try {
                if (conn != null) {
                conn.rollback();
            }
            } catch (SQLException ex) {
                System.out.println("Erreur : " + ex.getMessage());
            }
            System.err.println("Erreur : " + e.getMessage());
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

    public int supprimerVente(int id_vente){
        String rqt1 = "DELETE FROM VENTE_PIECE WHERE fk_id_vente = ?";
        int ligne = 0;
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(rqt1);
            conn.setAutoCommit(false);
            stmt.setInt(1, id_vente);
            stmt.executeUpdate();
            
            String rqt2 = "DELETE FROM VENTE WHERE id_vente = ?";
            PreparedStatement stmt2 = conn.prepareStatement(rqt2);
            stmt2.setInt(1, id_vente);
            ligne = stmt2.executeUpdate();
            if (ligne == 0) {
                throw new Exception("Aucune vente trouvée avec cet ID");
            }
            conn.commit();
            
        } catch (Exception e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                System.out.println("Erreur : " + ex.getMessage());
            }
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