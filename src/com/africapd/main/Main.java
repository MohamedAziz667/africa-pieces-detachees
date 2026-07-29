package com.africapd.main;

import java.time.LocalDate;
import com.africapd.models.*;
import com.africapd.config.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;
import com.africapd.dao.ClientDAO;
public class Main {
    public static void main(String[] args) {
        // 1. Créer un client
        Client client1 = new Client(1, "Amadou", "Diallo", "771234567", "Dakar", "");

        // 2. Créer une vente
        Vente vente1 = new Vente(1, LocalDate.now(), client1);

        // 3. Créer des pièces
        PieceDetachee amortisseur = new PieceDetachee("Amortisseur", "REF-001", 10, 30000);
        PieceDetachee filtre = new PieceDetachee("Filtre huile", "REF-002", 5, 15000);

        VentePiece ventePiece1 = new VentePiece(amortisseur, 2, 20000);
        VentePiece ventePiece2 = new VentePiece(filtre, 3, 12000);

        vente1.ajouterPiece(ventePiece1);
        vente1.ajouterPiece(ventePiece2);

        Facture facture1 = new Facture(01, LocalDate.now(), vente1);
        facture1.genererFacture();

        ClientDAO clientDAO = new ClientDAO();
        // clientDAO.ajouterClient(client1);
        // Client clientTrouve = clientDAO.rechercherClient(1);
        // System.out.println(clientTrouve);
        // System.out.println("Client ajouté avec succès !");
        // client1.setNom("Moussa");
        // clientDAO.modifierClient(client1);
        // System.out.println("Client modifié !");

        clientDAO.supprimerClient(1);
        System.out.println("Client supprimer");
        Client clientsup = clientDAO.rechercherClient(1);
        System.out.println(clientsup);
        
        Client clientModifier = clientDAO.rechercherClient(1);
        System.out.println(clientModifier);

        try {
            Connection conn = DatabaseConnection.getConnection();
            System.out.println("Connexion réussi");
            conn.close();
        } catch (Exception e) {
           System.out.println("Erreur : " + e.getMessage());
        }
    }  
}