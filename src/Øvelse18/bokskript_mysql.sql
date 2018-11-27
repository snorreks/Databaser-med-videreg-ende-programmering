-- Utgave tilpasset MySQL

-- Sletter tabeller
set FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS eksemplar;
DROP TABLE IF EXISTS boktittel;
set FOREIGN_KEY_CHECKS = 1;

-- Oppretter tabeller med entitetsintegritet (primærnøkkel)


CREATE TABLE boktittel (
  isbn      VARCHAR(30) NOT NULL PRIMARY KEY,
  forfatter VARCHAR(20),
  tittel    VARCHAR(50)
);

CREATE TABLE eksemplar (
  isbn     VARCHAR(30) NOT NULL,
  eks_nr   INTEGER     NOT NULL PRIMARY KEY,
  laant_av VARCHAR(20),
  FOREIGN KEY (isbn) REFERENCES boktittel (isbn)
);
