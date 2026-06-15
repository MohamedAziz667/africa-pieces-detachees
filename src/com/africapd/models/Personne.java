public class Personne {
    private int id;
    private String nom, prenom, numero;

    public int getId(){return id;}
    public String getNom(){return nom;}
    public String getPrenom(){return prenom;}
    public String getNumero(){return numero;}

    public void setNom(String c_nom){
        if (c_nom != null && c_nom.length() > 2) {nom = c_nom;}
    }

    public void setPrenom(String c_prenom){
        if (c_prenom != null && c_prenom.length() > 2) {prenom = c_prenom;}
    }

    public void setNumero(String c_numero){
        if (c_numero != null) {numero = c_numero;}
    }

    public void setId(int c_id){
        if (c_id > 0) {id = c_id;}
    }

    public void afficherPersonne(){
        System.out.println("=======Information de la Personne=======");
        System.out.println("ID : " + getId());
        System.out.println("Nom : " + getNom());
        System.out.println("Prenom : " + getPrenom());
        System.out.println("Numero : " + getNumero());
    }

    public Personne(){}

    public Personne(int c_id, String c_nomClient, String c_prenom, String c_numero){
        setId(c_id);
        setNom(c_nomClient);
        setPrenom(c_prenom);
        setNumero(c_numero);
    }
}
