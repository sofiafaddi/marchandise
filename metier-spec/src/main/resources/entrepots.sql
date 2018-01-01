-- Informations générales sur un entrepôt
CREATE TABLE IF NOT EXISTS entrepot (
  nom      VARCHAR(255) PRIMARY KEY,
  capacite FLOAT NOT NULL
);
-- Informations générales sur une marchandise
CREATE TABLE IF NOT EXISTS marchandise (
  ref         INTEGER PRIMARY KEY,
  nom         VARCHAR(255) NOT NULL,
  vol_unit    FLOAT        NOT NULL,
  description VARCHAR(255) NULL
);
-- Quantité d'une marchandise en stock dans un entrepôt
CREATE TABLE IF NOT EXISTS stock (
  nom_e    VARCHAR(255) REFERENCES entrepot (nom),
  ref_m    INTEGER REFERENCES marchandise (ref),
  quantite INTEGER NOT NULL,
  PRIMARY KEY (nom_e, ref_m)
);
-- Livraison vers un magasin
CREATE TABLE IF NOT EXISTS livraison (
  id        INTEGER PRIMARY KEY,
  ref_m     INTEGER REFERENCES marchandise (ref),
  nom_e     VARCHAR(255) REFERENCES entrepot (nom),
  magasin   VARCHAR(255) NOT NULL,
  quantite  INTEGER      NOT NULL,
  creation  TIMESTAMP    NOT NULL,
  prevue    DATE         NOT NULL,
  effectuee DATE         NULL
);
-- Approvisionnement d'un fournisseur
CREATE TABLE IF NOT EXISTS approvisionnement (
  id          INTEGER PRIMARY KEY,
  ref_m       INTEGER REFERENCES marchandise (ref),
  nom_e       VARCHAR(255) REFERENCES entrepot (nom),
  fournisseur VARCHAR(255) NOT NULL,
  quantite    INTEGER      NOT NULL,
  creation    TIMESTAMP    NOT NULL,
  prevu       DATE         NOT NULL,
  effectue    DATE         NULL
);

-- sequence pouvant être utilisée pour générer des identifiants entiers
CREATE SEQUENCE IF NOT EXISTS hibernate_sequence;

