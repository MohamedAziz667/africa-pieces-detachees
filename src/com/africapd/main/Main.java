package com.africapd.main;

import java.time.LocalDate;
import com.africapd.models.*;
public class Main {
    public static void main(String[] args) {
        // 1. Créer un client
Client client1 = new Client(1, "Amadou", "Diallo", "771234567", "Dakar");

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
    }
}
