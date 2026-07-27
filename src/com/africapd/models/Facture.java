package com.africapd.models;
import java.time.LocalDate;
public class Facture {
    private int idFacture;
    private LocalDate dateFacture;
    private Vente vente;

    public int getIdFacture(){return idFacture;}
    public LocalDate getDateFacture(){return dateFacture;}
    public Vente getVente(){return vente;}

    public void setIdFacture(int c_idFacture){
        if (c_idFacture > 0) {idFacture = c_idFacture;}
    }

    public void setDateFacture(LocalDate c_dateFacture){dateFacture = c_dateFacture;}
   
    public void setVente(Vente c_vente){
        if (c_vente != null) {vente = c_vente;}
    }

    public Facture(){}
    public Facture(int c_idFacture, LocalDate c_dateFacture, Vente c_vente){
        setIdFacture(c_idFacture);
        setDateFacture(c_dateFacture);
        setVente(c_vente);
    }

    public void genererFacture(){
        System.out.println("=========================================");
        System.out.println("AFRICA PIÈCES DÉTACHÉES");
        System.out.println("Votre spécialiste automobile");
        System.out.println("═════════════════════════════════════════════");
        System.out.println("Facture N° : " + getIdFacture());
        System.out.println("Date     : " + getDateFacture());
        System.out.println("═════════════════════════════════════════════");
        System.out.println("Client   : " + vente.getClient());
        System.out.println("Téléphone  : " + vente.getClient().getNumero());
        System.out.println("═════════════════════════════════════════════");
        System.out.println("DÉSIGNATION          QTÉ    PRIX U   TOTAL");
        for (VentePiece vp : vente.getligneVente()) {
            System.out.println(vp.getPiece() + " | " + vp.getQuantite() + " | " + vp.getprixUnitaire() + " | " + vp.calculerSousTotal());
        }
        System.out.println("═════════════════════════════════════════════");
        System.out.println("TOTAL À PAYER        : " + vente.calculerTotal());
        System.out.println("═════════════════════════════════════════════");
    }

    
}