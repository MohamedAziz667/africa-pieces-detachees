# Africa Pièces Détachées 🚗

Application de gestion interne pour une boutique de vente 
de pièces détachées automobiles.

## 📋 Description

Africa Pièces Détachées est une application desktop développée 
en Java permettant au gérant d'une boutique de gérer 
efficacement ses opérations quotidiennes : gestion des pièces, 
des stocks, des clients, des ventes, des fournisseurs 
et la génération de factures.

## 🛠️ Technologies utilisées

- **Java** — Programmation orientée objet
- **MySQL** — Base de données relationnelle
- **JDBC** — Connexion Java ↔ MySQL
- **UML** — Modélisation (cas d'utilisation, classes)

## 📁 Architecture du projet

src/com/africapd/

├── config/      → Connexion à la base de données

├── models/      → Classes métier (Client, Vente...)

├── dao/         → Requêtes SQL

├── services/    → Logique métier

├── ui/          → Interface console

└── main/        → Point d'entrée

## 🗄️ Base de données

Le schéma complet est disponible dans `sql/schema.sql`.

## 👤 Auteur

**Abdoul Aziz Mohamed Touré**  
Étudiant en Licence 2 — Informatique Appliquée 
à la Gestion des Entreprises (IAGE)