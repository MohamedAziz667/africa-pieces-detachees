package com.africapd.models;
public class Client extends Personne{
    private String adresse;

    public String getAdresse(){return adresse;}
    public void setAdresse(String c_adresse){
        if (c_adresse != null && c_adresse.length() >= 2) {adresse = c_adresse;}
    }

    public Client(){}
    public Client(int id, String nom, String prenom, String numero, String c_adresse){
        super(id, nom, prenom, numero);
        setAdresse(c_adresse);
    }

    public String toString(){
        return "[ID : " + getId() + " | Nom : " + getNom() + " | Prenom : " + getPrenom() + " | Adresse : " + getAdresse() + " | Numero " + getNumero() + "]";
    }

    @Override
    public void afficherPersonne(){
        System.out.println("=======Information du Client=======");
        System.out.println("ID : " + getId());
        System.out.println("Nom : " + getNom());
        System.out.println("Prenom : " + getPrenom());
        System.out.println("Adresse : " + getAdresse());
        System.out.println("Numero : " + getNumero());
    }

}