package com.africapd.models;
import java.util.ArrayList;
import java.time.LocalDate;
public class Vente {
    private int idVente;
    private LocalDate dateVente = LocalDate.now(); // date d'aujourd'hui automatiquement
    private Client client;
    private ArrayList<VentePiece> ligneVente = new ArrayList<>();

    public int getIdVente(){return idVente;}
    public LocalDate getDateVente(){return dateVente;}
    public Client getClient(){return client;}
    public ArrayList<VentePiece> getligneVente(){return ligneVente;}

    public void setIdVente(int c_idVente){
        if (c_idVente > 0) {idVente = c_idVente;}
    }

    public void setDateVente(LocalDate c_dateVente){dateVente = c_dateVente;}
    public void setClient(Client c_client){
        if (c_client != null) {client = c_client;}
    }

    public String toString(){
        return "[Id_Vente : " + getIdVente() + " | Date : " + getDateVente() + " | Client : " + getClient() + "]";
    }

    public void ajouterPiece(VentePiece vp){
        ligneVente.add(vp);
    }

    public double calculerTotal(){
        double total = 0;
        for(VentePiece vp : ligneVente){
            total += vp.calculerSousTotal();
        }
        return total;
    }

    public Vente(){}
    public Vente(int c_idVente, LocalDate c_dateVente, Client c_client){
        setIdVente(c_idVente);
        setDateVente(c_dateVente);
        setClient(c_client);
    }

}