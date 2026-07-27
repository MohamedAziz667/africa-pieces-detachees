package com.africapd.models;
public class VentePiece {
    // private Vente vente;
    private PieceDetachee piece;
    private int quantite;
    private double prixUnitaire;

    // public Vente getVente(){return vente;}
    public PieceDetachee getPiece(){return piece;}
    public int getQuantite(){return quantite;}
    public double getprixUnitaire(){return prixUnitaire;}

    // public void setVente(Vente c_vente){
    //     if (c_vente != null) {vente = c_vente;}
    // }

    public void setPiece(PieceDetachee c_piece){
        if (c_piece != null) {piece = c_piece;}
    }

    public void setQuantite(int c_quantite){
        if (c_quantite > 0) {quantite = c_quantite;}
    }

    public void setprixUnitaire(double c_prixUnitaire){
        if (c_prixUnitaire > 0) {prixUnitaire = c_prixUnitaire;}
    }

    public String toString(){
        return "[Vente :  Piece : " + getPiece() + " | Quantite : " + getQuantite() + " | Prix unitaire : " + getprixUnitaire() + "]";
    }

    public double calculerSousTotal(){
        return getprixUnitaire() * getQuantite();
    }

    public VentePiece(){}
    public VentePiece(PieceDetachee c_piece, int c_quantite, double c_prixUnitaire){
        // setVente(c_vente);
        setPiece(c_piece);
        setQuantite(c_quantite);
        setprixUnitaire(c_prixUnitaire);
    }

}
