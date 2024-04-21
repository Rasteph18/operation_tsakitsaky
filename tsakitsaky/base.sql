CREATE DATABASE tsakitsaky;

\c tsakitsaky

CREATE TABLE utilisateur(
    id SERIAL PRIMARY KEY,
    mail VARCHAR(250) UNIQUE NOT NULL,
    mot_de_passe VARCHAR(250) NOT NULL,
    super_utilisateur INT NOT NULL
);

CREATE TABLE billet(
    id SERIAL PRIMARY KEY,
    prix DOUBLE PRECISION
);

CREATE TABLE etudiant(
    id VARCHAR(50) PRIMARY KEY,
    nom VARCHAR(250),
    prenom VARCHAR(250),
    sexe VARCHAR(50)
);

CREATE TABLE axe(
    id SERIAL PRIMARY KEY,
    nom VARCHAR(150)
);

CREATE TABLE details_axe(
    id SERIAL PRIMARY KEY,
    id_axe INT,
    lieu VARCHAR(250),

    FOREIGN KEY(id_axe) REFERENCES axe(id)
);

CREATE TABLE billet_etudiant_vendu(
    id SERIAL PRIMARY KEY,
    id_billet INT,
    id_etudiant VARCHAR(50),
    nom_client VARCHAR(250),
    contact VARCHAR(150),
    nb_billet DOUBLE PRECISION,
    id_axe INT,

    FOREIGN KEY(id_billet) REFERENCES billet(id),
    FOREIGN KEY(id_etudiant) REFERENCES etudiant(id),
    FOREIGN KEY(id_axe) REFERENCES axe(id)
);

CREATE TABLE produit(
    id SERIAL PRIMARY KEY,
    nom VARCHAR(150),
    unite_mesure VARCHAR(50)
);

CREATE TABLE cout_revient_unitaire_produit(
    id SERIAL PRIMARY KEY,
    id_produit INT,
    prix DOUBLE PRECISION,
    unite DOUBLE PRECISION,

    FOREIGN KEY(id_produit) REFERENCES produit(id)
);

CREATE TABLE pack(
    id SERIAL PRIMARY KEY,
    nom VARCHAR(150),
    id_billet INT,
    photo VARCHAR(250),

    FOREIGN KEY(id_billet) REFERENCES billet(id)
);

CREATE TABLE details_pack(
    id SERIAL PRIMARY KEY,
    id_pack INT,
    id_produit INT,
    quantite DOUBLE PRECISION,

    FOREIGN KEY(id_pack) REFERENCES pack(id),
    FOREIGN KEY(id_produit) REFERENCES produit(id)
);


CREATE TABLE paiement_billet(
    id SERIAL PRIMARY KEY,
    id_etudiant VARCHAR(50),
    etat_paiement INT, -- total:10 ||  partiel:20
    montant DOUBLE PRECISION,

    FOREIGN KEY(id_etudiant) REFERENCES etudiant(id)
);


CREATE SEQUENCE etudiant_seq START 1 INCREMENT 1;



CREATE OR REPLACE VIEW V_nb_type_billet_vendu AS
SELECT 
    ROW_NUMBER() OVER (ORDER BY e.id, b.id) AS id,
    e.id AS id_etudiant, 
    e.nom, 
    e.prenom, 
    b.prix, 
    SUM(COALESCE(bev.nb_billet, 0)) AS nb_billet
FROM 
    etudiant e
CROSS JOIN 
    billet b
LEFT JOIN 
    billet_etudiant_vendu bev ON bev.id_etudiant = e.id AND bev.id_billet = b.id
GROUP BY
    e.id, b.id, e.nom, e.prenom, b.prix;



SELECT 
    ROW_NUMBER() OVER (ORDER BY e.id, b.id) AS id,
    e.nom,
    e.prenom,
    bev.nom_client,
    bev.contact,
    a.nom,
    SUM(bev.nb_billet) AS nb_billet,
    b.prix,
    SUM(bev.nb_billet * b.prix) AS montant
FROM 
    billet_etudiant_vendu bev
JOIN
    etudiant e ON e.id = bev.id_etudiant
JOIN 
    billet b ON b.id = bev.id_billet
JOIN 
    axe a ON a.id = bev.id_axe
GROUP BY
    e.id, b.id, e.nom, bev.nom_client, bev.contact, a.nom, b.prix;



--donnee
INSERT INTO utilisateur VALUES(default, 'steph@gmail.com', md5('1234'), 1); --admin:1 || normal:0
INSERT INTO utilisateur VALUES(default, 'rabe@gmail.com', md5('1234'), 0);

INSERT INTO billet VALUES(default, 20000);
INSERT INTO billet VALUES(default, 30000);

INSERT INTO axe VALUES(default, 'Axe1');
INSERT INTO axe VALUES(default, 'Axe2');

INSERT INTO details_axe VALUES(default, 1, 'Tanjombato');
INSERT INTO details_axe VALUES(default, 1, 'Andoharanofotsy');
INSERT INTO details_axe VALUES(default, 1, 'Iavoloha');
INSERT INTO details_axe VALUES(default, 2, 'Anosy');
INSERT INTO details_axe VALUES(default, 2, 'Analakely');
INSERT INTO details_axe VALUES(default, 2, 'Ambohijatovo'); 




INSERT INTO etudiant VALUES('ETU0' || NEXTVAL('etudiant_seq'), 'RANDRIA', 'Lita', 'Homme');
INSERT INTO etudiant VALUES('ETU0' || NEXTVAL('etudiant_seq'), 'RAKOTOBE', 'Jean', 'Homme');
INSERT INTO etudiant VALUES('ETU0' || NEXTVAL('etudiant_seq'), 'RABENANAHARY', 'Tiana', 'Homme');
INSERT INTO etudiant VALUES('ETU0' || NEXTVAL('etudiant_seq'), 'ANDRIANJATOVO', 'Fidy', 'Homme');
INSERT INTO etudiant VALUES('ETU0' || NEXTVAL('etudiant_seq'), 'RASOARILALA', 'Lova', 'Femme');
INSERT INTO etudiant VALUES('ETU0' || NEXTVAL('etudiant_seq'), 'RAZAFINDRAKOTO', 'Zo', 'Femme');

INSERT INTO produit VALUES(default, 'Tsatsiou', 'g');
INSERT INTO produit VALUES(default, 'Beignet crevette', 'piece');
INSERT INTO produit VALUES(default, 'Saucisse fumee', 'piece');
INSERT INTO produit VALUES(default, 'Boulette chinoise', 'piece');
INSERT INTO produit VALUES(default, 'Poulet fumee', 'g');
INSERT INTO produit VALUES(default, 'Cochon laquee', 'g');
INSERT INTO produit VALUES(default, 'Emballage', 'piece');

INSERT INTO cout_revient_unitaire_produit VALUES(default, 1, 10000, 100);
INSERT INTO cout_revient_unitaire_produit VALUES(default, 2, 300, 1);
INSERT INTO cout_revient_unitaire_produit VALUES(default, 3, 1500, 1);
INSERT INTO cout_revient_unitaire_produit VALUES(default, 4, 1000, 1);
INSERT INTO cout_revient_unitaire_produit VALUES(default, 5, 8000, 100);
INSERT INTO cout_revient_unitaire_produit VALUES(default, 6, 10000, 100);
INSERT INTO cout_revient_unitaire_produit VALUES(default, 7, 1000, 1);



SELECT * FROM utilisateur WHERE mail LIKE 'steph@gmail.com' AND mot_de_passe LIKE md5('1234');



