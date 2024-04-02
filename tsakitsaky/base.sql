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

CREATE TABLE billet_etudiant_vendu(
    id SERIAL PRIMARY KEY,
    id_billet INT,
    id_etudiant VARCHAR(50),
    nb_billet DOUBLE PRECISION,

    FOREIGN KEY(id_billet) REFERENCES billet(id),
    FOREIGN KEY(id_etudiant) REFERENCES etudiant(id)
);

CREATE SEQUENCE etudiant_seq START 1 INCREMENT 1;



--donnee
INSERT INTO utilisateur VALUES(default, 'steph@gmail.com', md5('1234'), 1);
INSERT INTO utilisateur VALUES(default, 'rabe@gmail.com', md5('1234'), 0);

INSERT INTO billet VALUES(default, 20000);
INSERT INTO billet VALUES(default, 30000);

INSERT INTO etudiant VALUES('ETU0' || NEXTVAL('etudiant_seq'), 'RANDRIA', 'Lita', 'Homme');
INSERT INTO etudiant VALUES('ETU0' || NEXTVAL('etudiant_seq'), 'RAKOTOBE', 'Jean', 'Homme');
INSERT INTO etudiant VALUES('ETU0' || NEXTVAL('etudiant_seq'), 'RABENANAHARY', 'Tiana', 'Homme');
INSERT INTO etudiant VALUES('ETU0' || NEXTVAL('etudiant_seq'), 'ANDRIANJATOVO', 'Fidy', 'Homme');
INSERT INTO etudiant VALUES('ETU0' || NEXTVAL('etudiant_seq'), 'RASOARILALA', 'Lova', 'Femme');
INSERT INTO etudiant VALUES('ETU0' || NEXTVAL('etudiant_seq'), 'RAZAFINDRAKOTO', 'Zo', 'Femme');

SELECT * FROM utilisateur WHERE mail LIKE 'steph@gmail.com' AND mot_de_passe LIKE md5('1234');

CREATE OR REPLACE VIEW V_nb_type_billet_vendu AS
SELECT 
    ROW_NUMBER() OVER (ORDER BY e.id, b.id) AS id,
    e.id AS id_etudiant, 
    e.nom, 
    e.prenom, 
    b.prix, 
    COALESCE(bev.nb_billet, 0) AS nb_billet
FROM 
    etudiant e
CROSS JOIN 
    billet b
LEFT JOIN 
    billet_etudiant_vendu bev ON bev.id_etudiant = e.id AND bev.id_billet = b.id;

