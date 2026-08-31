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
        if (c_idFacture >= 0) {idFacture = c_idFacture;}
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

    public void genererFacture() {

        System.out.println("==================================================");
        System.out.println("              AFRICA PIÈCES DÉTACHÉES");
        System.out.println("              Votre spécialiste automobile");
        System.out.println("              Dakar, Sénégal");
        System.out.println("==================================================");

        System.out.println("FACTURE N° : FAC-2026-" + String.format("%05d", getIdFacture()));
        System.out.println("DATE       : " + getDateFacture());

        System.out.println("--------------------------------------------------");

        System.out.println("CLIENT");
        System.out.println("Nom        : "
                + vente.getClient().getNom()
                + " "
                + vente.getClient().getPrenom());

        System.out.println("Téléphone  : "
                + vente.getClient().getNumero());

        System.out.println("--------------------------------------------------");
}

    
}