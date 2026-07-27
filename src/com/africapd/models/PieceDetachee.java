package com.africapd.models;
public class PieceDetachee{
    private String nom, reference;
    private int quantiteStock;
    private double prix;

    public String getNom(){return nom;}

    public String getReference(){return reference;}

    public int getQuantiteStock(){return quantiteStock;}

    public double getPrix(){return prix;}

    public void setNom(String v_nom){
        if(v_nom != null){
            nom = v_nom;
        }else{
            System.out.println("Ce champs est vide");
        }
    }

    public void setReference(String v_reference){
        if(v_reference != null){
            reference = v_reference;
        }else{
            System.out.println("Ce champs est vide");
        }
    }

    public void setPrix(double new_prix){
        if (new_prix > 0) {prix = new_prix;}
    }

    public void setQuantiteStock(int new_quantite){
        if (new_quantite > 0) {quantiteStock = new_quantite;}
    }

    public PieceDetachee(String v_nom, String v_reference, int v_quantiteStock, double v_prix){
        setNom(v_nom);
        setReference(v_reference);
        setQuantiteStock(v_quantiteStock);
        setPrix(v_prix);
    }

    public PieceDetachee(String v_nom, String v_reference){
        setNom(v_nom);
        setReference(v_reference);
    }

    public String afficher(){
        return "Piece[Nom : " + getNom() + " | Reference : " + getReference() + " | Prix : " + getPrix() + " | Quantite : " + getQuantiteStock() + "]";
    }

    @Override
    public String toString(){
        return getNom();
    }
}