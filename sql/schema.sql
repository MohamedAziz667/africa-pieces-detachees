CREATE TABLE CLIENT(
    id_client int AUTO_INCREMENT PRIMARY KEY,
    nom_client varchar(50) NOT NULL,
    prenom_client varchar(50) NOT NULL,
    email_client varchar(50) NOT NULL UNIQUE,
    contact_client varchar(50) NOT NULL
);

CREATE TABLE PIECE_DETACHEE(
    id_piece int AUTO_INCREMENT PRIMARY KEY,
    nom_piece varchar(50) NOT NULL,
    reference_piece varchar(50) NOT NULL,
    quantite_stock int NOT NULL,
    prix_unitaire DECIMAL(10, 2) NOT NULL
);

CREATE TABLE FOURNISSEUR(
    id_fournisseur int AUTO_INCREMENT PRIMARY KEY,
    nom_fournisseur varchar(50) NOT NULL,
    prenom_fournisseur varchar(50) NOT NULL,
    contact_fournisseur varchar(50) NOT NULL
);

CREATE TABLE VENTE(
    id_vente int AUTO_INCREMENT PRIMARY KEY,
    date_vente DATE NOT NULL,
    fk_id_client int,
    FOREIGN KEY(fk_id_client) REFERENCES CLIENT(id_client)
);

CREATE TABLE VENTE_PIECE(
    fk_id_vente int,
    fk_id_piece int,
    quantite int NOT NULL,
    prix_u DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY(fk_id_vente, fk_id_piece),
    FOREIGN KEY(fk_id_vente) REFERENCES VENTE(id_vente),
    FOREIGN KEY(fk_id_piece) REFERENCES PIECE_DETACHEE(id_piece)
);

CREATE TABLE FOURNISSEUR_PIECE(
    fk_id_fournisseur int,
    fk_id_piece int,
    PRIMARY KEY(fk_id_fournisseur, fk_id_piece),
    FOREIGN KEY(fk_id_fournisseur) REFERENCES FOURNISSEUR(id_fournisseur),
    FOREIGN KEY(fk_id_piece) REFERENCES PIECE_DETACHEE(id_piece)
);

CREATE TABLE FACTURE(
    id_facture int AUTO_INCREMENT PRIMARY KEY,
    fk_id_vente int,
    FOREIGN KEY(fk_id_vente) REFERENCES VENTE(id_vente),
    date_facture DATE NOT NULL
);