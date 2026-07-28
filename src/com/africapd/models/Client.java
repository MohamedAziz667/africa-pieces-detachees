package com.africapd.models;
public class Client extends Personne{
    private String adresse;
    private String email;

    public String getAdresse(){return adresse;}
    public String getEmail(){return email != null ? email : "Non renseigné";}

    public void setAdresse(String c_adresse){
        if (c_adresse != null && c_adresse.length() >= 2) {adresse = c_adresse;}
    }

    public void setEmail(String c_email){
        if(c_email != null && c_email.length() >= 2 && c_email.contains("@")) {email = c_email;}
    }

    public Client(){}
    public Client(int id, String nom, String prenom, String numero, String c_adresse, String c_email){
        super(id, nom, prenom, numero);
        setAdresse(c_adresse);
        setEmail(c_email);
    }

    public Client(int id, String nom, String prenom, String numero, String c_adresse){
        super(id, nom, prenom, numero);
        setAdresse(c_adresse);
        // email null
    }

    public String toString(){
        return "[ID : " + getId() + " | Nom : " + getNom() + " | Prenom : " + getPrenom() + " | Adresse : " + getAdresse() + " | Numero " + getNumero() + " | Email : " + getEmail() + "]";
    }

    @Override
    public void afficherPersonne(){
        System.out.println("=======Information du Client=======");
        System.out.println("ID : " + getId());
        System.out.println("Nom : " + getNom());
        System.out.println("Prenom : " + getPrenom());
        System.out.println("Adresse : " + getAdresse());
        System.out.println("Numero : " + getNumero());
        System.out.println("Email : " + getEmail());
    }

}