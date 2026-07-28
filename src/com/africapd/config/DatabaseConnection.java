package com.africapd.config; // Ce fichier appartient au dossier config

// On importe les outils nécessaires pour la connexion MySQL
import java.sql.Connection;     // Représente une connexion active à la base de données
import java.sql.DriverManager;  // C'est lui qui établit la connexion avec MySQL
import java.sql.SQLException;   // Gère les erreurs liées à la base de données

public class DatabaseConnection {

    // L'adresse de notre base de données MySQL
    // jdbc = technologie | mysql = type de DB | localhost = notre ordi | 3306 = port MySQL
    private static final String URL = "jdbc:mysql://localhost:3306/africa_pieces_detachees";
    
    // L'utilisateur MySQL — avec XAMPP c'est "root" par défaut
    private static final String USER = "root";
    
    // Le mot de passe — vide par défaut avec XAMPP
    private static final String PASSWORD = "";

    // Cette méthode retourne une connexion active à la base de données
    // Elle sera appelée par tous les DAO qui ont besoin de parler à MySQL
    // "throws SQLException" signifie : si la connexion échoue, une erreur sera signalée
    public static Connection getConnection() throws SQLException {
        
        // DriverManager essaie de se connecter à MySQL avec nos paramètres
        // Si ça réussit → retourne la connexion
        // Si ça échoue → lance une SQLException
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
