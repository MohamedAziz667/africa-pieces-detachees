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
                "Amadou",
                "Diallo",
                "771234567",
                "Dakar",
                "amdou@gmail.com"
        );

        clientDAO.ajouterClient(nouveauClient);

        System.out.println("Client ajouté.");


        // ==================================================
        // 3. RECHERCHE DU CLIENT
        // ==================================================

        Client client = clientDAO.rechercherClient(nouveauClient.getId());

        if (client == null) {

            System.out.println("Client introuvable. Impossible de créer la vente.");
            return;

        }

        System.out.println("\nClient trouvé :");
        System.out.println(client);


        // ==================================================
        // 4. RECHERCHE DES PIÈCES
        // ==================================================

        PieceDetachee piece1 = pieceDAO.rechercherPiece(1);
        PieceDetachee piece2 = pieceDAO.rechercherPiece(2);


        // Vérification pièce 1
        if (piece1 == null) {

            System.out.println("Pièce avec l'ID 1 introuvable.");
            return;

        }


        // Vérification pièce 2
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
        // 5. CRÉATION DE LA VENTE
        // ==================================================

        Vente vente = new Vente(
                0,
                LocalDate.now(),
                client
        );


        // ==================================================
        // 6. CRÉATION DES LIGNES DE VENTE
        // ==================================================

        VentePiece ventePiece1 = new VentePiece(
                piece1,
                2,
                20000
        );

        VentePiece ventePiece2 = new VentePiece(
                piece2,
                3,
                12000
        );


        // ==================================================
        // 7. AJOUT DES PIÈCES DANS LA VENTE
        // ==================================================

        vente.ajouterPiece(ventePiece1);
        vente.ajouterPiece(ventePiece2);


        // ==================================================
        // 8. ENREGISTREMENT DE LA VENTE
        // ==================================================

        venteDAO.ajouterVente(vente);

        System.out.println("\nVente ajoutée avec succès !");
        System.out.println("ID de la vente : " + vente.getIdVente());


        // ==================================================
        // 9. RECHERCHE DE LA VENTE
        // ==================================================

        Vente venteTrouvee = venteDAO.rechercherVente(
                vente.getIdVente()
        );


        if (venteTrouvee != null) {

            System.out.println("\n==============================");
            System.out.println("VENTE RETROUVÉE");
            System.out.println("==============================");

            System.out.println("ID vente : "
                    + venteTrouvee.getIdVente());

            System.out.println("Date : "
                    + venteTrouvee.getDateVente());

            System.out.println("ID client : "
                    + venteTrouvee.getClient().getId());

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
        }
    }
}