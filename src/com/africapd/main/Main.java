package com.africapd.main;

import java.time.LocalDate;

import com.africapd.dao.ClientDAO;
import com.africapd.dao.PieceDetacheeDAO;
import com.africapd.dao.VenteDAO;

import com.africapd.models.Client;
import com.africapd.models.PieceDetachee;
import com.africapd.models.Vente;
import com.africapd.models.VentePiece;

public class Main {

    public static void main(String[] args) {

        // ==================================================
        // 1. CRÉATION DES DAO
        // ==================================================

        ClientDAO clientDAO = new ClientDAO();
        PieceDetacheeDAO pieceDAO = new PieceDetacheeDAO();
        VenteDAO venteDAO = new VenteDAO();


        // ==================================================
        // 2. AJOUT D'UN CLIENT
        // ==================================================

        Client nouveauClient = new Client(
                0,
                "Ndoye",
                "Fall",
                "703221012",
                "dakar",
                "ndoyefall@gmail.com"
        );

        int ligneClient = clientDAO.ajouterClient(nouveauClient);

        if (ligneClient == 1) {
            System.out.println("Client ajouté avec succès.");
            System.out.println("ID généré : " + nouveauClient.getId());
        } else {
            System.out.println("Échec de l'ajout du client.");
            return;
        }


        // ==================================================
        // 3. RECHERCHE DU CLIENT
        // ==================================================

        Client client = clientDAO.rechercherClient(nouveauClient.getId());

        if (client == null) {
            System.out.println("Client introuvable. Impossible de continuer.");
            return;
        }

        System.out.println("\n==============================");
        System.out.println("CLIENT TROUVÉ");
        System.out.println("==============================");
        System.out.println(client);


        // ==================================================
        // 4. AJOUT D'UNE NOUVELLE PIÈCE
        // ==================================================

        PieceDetachee nouvellePiece = new PieceDetachee(
                "Plaquette de frein",
                "REF-TEST-001",
                20,
                25000
        );

        int lignePiece = pieceDAO.ajouterPiece(nouvellePiece);

        if (lignePiece == 1) {
            System.out.println("\nPièce ajoutée avec succès.");
            System.out.println("ID généré : " + nouvellePiece.getId());
        } else {
            System.out.println("Échec de l'ajout de la pièce.");
            return;
        }


        // ==================================================
        // 5. RECHERCHE DE LA PIÈCE AJOUTÉE
        // ==================================================

        PieceDetachee pieceTrouvee = pieceDAO.rechercherPiece(
                nouvellePiece.getId()
        );

        if (pieceTrouvee == null) {
            System.out.println("Pièce introuvable.");
            return;
        }

        System.out.println("\n==============================");
        System.out.println("PIÈCE TROUVÉE");
        System.out.println("==============================");
        System.out.println("ID : " + pieceTrouvee.getId());
        System.out.println(pieceTrouvee.afficher());


        // ==================================================
        // 6. MODIFICATION DE LA PIÈCE
        // ==================================================

        pieceTrouvee.setNom("Plaquette de frein Premium");
        pieceTrouvee.setReference("REF-TEST-001-MOD");
        pieceTrouvee.setQuantiteStock(30);
        pieceTrouvee.setPrix(30000);

        int ligneModification = pieceDAO.modifierPieceDetachee(
                pieceTrouvee
        );

        if (ligneModification == 1) {
            System.out.println("\nPièce modifiée avec succès.");
        } else {
            System.out.println("Échec de la modification de la pièce.");
            return;
        }


        // ==================================================
        // 7. RECHERCHE APRÈS MODIFICATION
        // ==================================================

        PieceDetachee pieceModifiee = pieceDAO.rechercherPiece(
                pieceTrouvee.getId()
        );

        if (pieceModifiee == null) {
            System.out.println("Impossible de retrouver la pièce modifiée.");
            return;
        }

        System.out.println("\n==============================");
        System.out.println("PIÈCE APRÈS MODIFICATION");
        System.out.println("==============================");
        System.out.println("ID : " + pieceModifiee.getId());
        System.out.println(pieceModifiee.afficher());


        // ==================================================
        // 8. RECHERCHE DES PIÈCES POUR LA VENTE
        // ==================================================

        PieceDetachee piece1 = pieceDAO.rechercherPiece(1);
        PieceDetachee piece2 = pieceDAO.rechercherPiece(2);

        if (piece1 == null) {
            System.out.println("Pièce avec l'ID 1 introuvable.");
            return;
        }

        if (piece2 == null) {
            System.out.println("Pièce avec l'ID 2 introuvable.");
            return;
        }

        System.out.println("\nPremière pièce trouvée :");
        System.out.println("ID : " + piece1.getId());
        System.out.println(piece1.afficher());

        System.out.println("\nDeuxième pièce trouvée :");
        System.out.println("ID : " + piece2.getId());
        System.out.println(piece2.afficher());


        // ==================================================
        // 9. CRÉATION DE LA VENTE
        // ==================================================

        Vente vente = new Vente(
                0,
                LocalDate.now(),
                client
        );


        // ==================================================
        // 10. CRÉATION DES LIGNES DE VENTE
        // ==================================================

        VentePiece ventePiece1 = new VentePiece(
                piece1,
                2,
                20000
        );

        VentePiece ventePiece2 = new VentePiece(
                piece2,
                1,
                12000
        );


        // ==================================================
        // 11. AJOUT DES PIÈCES DANS LA VENTE
        // ==================================================

        vente.ajouterPiece(ventePiece1);
        vente.ajouterPiece(ventePiece2);


        // ==================================================
        // 12. ENREGISTREMENT DE LA VENTE
        // ==================================================

        venteDAO.ajouterVente(vente);

        if (vente.getIdVente() == 0) {
            System.out.println("Échec de l'ajout de la vente.");
            return;
        }

        System.out.println("\nVente ajoutée avec succès !");
        System.out.println("ID de la vente : " + vente.getIdVente());


        // ==================================================
        // 13. RECHERCHE DE LA VENTE
        // ==================================================

        Vente venteTrouvee = venteDAO.rechercherVente(
                vente.getIdVente()
        );

        if (venteTrouvee != null) {

            System.out.println("\n==============================");
            System.out.println("VENTE RETROUVÉE");
            System.out.println("==============================");

            System.out.println(
                    "ID vente : " + venteTrouvee.getIdVente()
            );

            System.out.println(
                    "Date : " + venteTrouvee.getDateVente()
            );

            System.out.println(
                    "ID client : "
                    + venteTrouvee.getClient().getId()
            );

            System.out.println("\nLignes de vente :");

            for (VentePiece vp : venteTrouvee.getligneVente()) {

                System.out.println(
                        "ID pièce : "
                        + vp.getPiece().getId()
                        + " | Quantité : "
                        + vp.getQuantite()
                        + " | Prix unitaire : "
                        + vp.getprixUnitaire()
                );
            }

        } else {

            System.out.println(
                    "\nImpossible de retrouver la vente."
            );

            return;
        }


        // ==================================================
        // 14. SUPPRESSION DE LA PIÈCE DE TEST
        // ==================================================

        int ligneSuppression = pieceDAO.supprimerPiece(
                nouvellePiece.getId()
        );

        if (ligneSuppression == 1) {
            System.out.println("\nPièce de test supprimée avec succès.");
        } else {
            System.out.println("Échec de la suppression de la pièce.");
            return;
        }


        // ==================================================
        // 15. VÉRIFICATION APRÈS SUPPRESSION
        // ==================================================

        PieceDetachee pieceSupprimee = pieceDAO.rechercherPiece(
                nouvellePiece.getId()
        );

        if (pieceSupprimee == null) {
            System.out.println(
                    "Vérification réussie : la pièce n'existe plus."
            );
        } else {
            System.out.println(
                    "Problème : la pièce existe encore."
            );
        }

    }

}