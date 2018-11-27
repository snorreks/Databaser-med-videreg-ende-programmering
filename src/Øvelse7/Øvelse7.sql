DROP TABLE IF EXISTS oppdrag;
DROP TABLE IF EXISTS vikartjeneste;
DROP TABLE IF EXISTS bedrift;
DROP TABLE IF EXISTS kandi_kvali;
DROP TABLE IF EXISTS kvalifikasjon;
DROP TABLE IF EXISTS kandidat;


CREATE TABLE kandidat (
  kandidat_nr INTEGER      NOT NULL AUTO_INCREMENT PRIMARY KEY,
  fornavn     VARCHAR(50)  NOT NULL,
  etternavn   VARCHAR(50)  NOT NULL,
  telefon     CHAR(15)     NOT NULL,
  epost       VARCHAR(100) NOT NULL

);
CREATE TABLE kvalifikasjon (
  kvalifikasjon_id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  beskrivelse      TEXT
);
CREATE TABLE kandi_kvali (
  kandidat_nr      INTEGER NOT NULL,
  kvalifikasjon_id INTEGER NOT NULL,
  PRIMARY KEY (kandidat_nr, kvalifikasjon_id),
  FOREIGN KEY (kandidat_nr) REFERENCES kandidat (kandidat_nr),
  FOREIGN KEY (kvalifikasjon_id) REFERENCES kvalifikasjon (kvalifikasjon_id)
);
CREATE TABLE bedrift (
  organisasjon_nr INTEGER      NOT NULL PRIMARY KEY,
  bedrift_navn    VARCHAR(50)  NOT NULL,
  telefon         CHAR(15)     NOT NULL,
  epost           VARCHAR(100) NOT NULL
);

CREATE TABLE vikartjeneste (
  organisasjon_nr INTEGER      NOT NULL AUTO_INCREMENT PRIMARY KEY,
  navn            VARCHAR(50)  NOT NULL,
  epost           VARCHAR(100) NOT NULL,
  telefon         CHAR(15)     NOT NULL
);

CREATE TABLE oppdrag (
  jobb_id          INTEGER        NOT NULL PRIMARY KEY AUTO_INCREMENT,
  startdato        DATE           NOT NULL,
  sluttdato        DATE           NOT NULL,
  ant_timer        INTEGER        NOT NULL,
  egt_startdato    DATE,
  egt_sluttdato    DATE,
  egt_ant_timer    INTEGER,
  kandidat_nr      INTEGER UNIQUE,
  kvalifikasjon_id INTEGER UNIQUE NOT NULL,
  organisasjon_nr  INTEGER UNIQUE NOT NULL,
  FOREIGN KEY (kandidat_nr) REFERENCES kandidat (kandidat_nr),
  FOREIGN KEY (kvalifikasjon_id) REFERENCES kvalifikasjon (kvalifikasjon_id),
  FOREIGN KEY (organisasjon_nr) REFERENCES bedrift (organisasjon_nr)
);

INSERT INTO kandidat (kandidat_nr, fornavn, etternavn, telefon, epost)
VALUES (DEFAULT, "Snorre", "Strand", 41235369, "snorrestrand@hotmail.com");
INSERT INTO kandidat (kandidat_nr, fornavn, etternavn, telefon, epost)
VALUES (DEFAULT, "Per", "Bob", 23549602, "PerBobErKul123@myspace.com");
INSERT INTO kandidat (kandidat_nr, fornavn, etternavn, telefon, epost)
VALUES (DEFAULT, "Kari", "Jonsen", 74829564, "kari420_dragonslayer@hotmail.com");
INSERT INTO kandidat (kandidat_nr, fornavn, etternavn, telefon, epost)
VALUES (DEFAULT, "Knut", "Knutsen", 75836729, "Knutærn69@hotmail.com");
INSERT INTO kandidat (kandidat_nr, fornavn, etternavn, telefon, epost)
VALUES (DEFAULT, "Harry", "Jonsen", 12349564, "Jonsen@hotmail.com");


INSERT INTO kvalifikasjon (kvalifikasjon_id, beskrivelse)
VALUES (DEFAULT, "Smart");
INSERT INTO kvalifikasjon (kvalifikasjon_id, beskrivelse)
VALUES (DEFAULT, "Morsom");
INSERT INTO kvalifikasjon (kvalifikasjon_id, beskrivelse)
VALUES (DEFAULT, "Sosial");
INSERT INTO kvalifikasjon (kvalifikasjon_id, beskrivelse)
VALUES (DEFAULT, "Kul");

INSERT INTO kandi_kvali (kandidat_nr, kvalifikasjon_id)
VALUES (1, 2);
INSERT INTO kandi_kvali (kandidat_nr, kvalifikasjon_id)
VALUES (1, 3);
INSERT INTO kandi_kvali (kandidat_nr, kvalifikasjon_id)
VALUES (2, 4);
INSERT INTO kandi_kvali (kandidat_nr, kvalifikasjon_id)
VALUES (3, 1);
INSERT INTO kandi_kvali (kandidat_nr, kvalifikasjon_id)
VALUES (4, 2);

INSERT INTO bedrift (organisasjon_nr, bedrift_navn, telefon, epost)
VALUES (1234, "Pizzabakern", "23232323", "PizzaBakern@yahoo.no");
INSERT INTO bedrift (organisasjon_nr, bedrift_navn, telefon, epost)
VALUES (1235, "Skole", "41414141", "SkoleVikar@hotmail.no");
INSERT INTO bedrift (organisasjon_nr, bedrift_navn, telefon, epost)
VALUES (1236, "COOP Mega", "75757575", "CoopMega@gmail.com");

INSERT INTO vikartjeneste (organisasjon_nr, navn, epost, telefon)
VALUES (1234, "VikarTjenesten", "lol@email.com", 74835937);

INSERT INTO oppdrag (jobb_id,
                     startdato,
                     sluttdato,
                     ant_timer,
                     egt_startdato,
                     egt_sluttdato,
                     egt_ant_timer,
                     kandidat_nr,
                     kvalifikasjon_id,
                     organisasjon_nr)
VALUES (DEFAULT, DATE('2018-02-03'), DATE('2018-05-06'), 10, NULL, NULL, NULL, 1, 2, 1234);
INSERT INTO oppdrag (jobb_id,
                     startdato,
                     sluttdato,
                     ant_timer,
                     egt_startdato,
                     egt_sluttdato,
                     egt_ant_timer,
                     kandidat_nr,
                     kvalifikasjon_id,
                     organisasjon_nr)
VALUES (DEFAULT, DATE('2018-04-04'), DATE('2018-07-07'), 24, NULL, NULL, NULL, 2, 3, 1235);
INSERT INTO oppdrag (jobb_id,
                     startdato,
                     sluttdato,
                     ant_timer,
                     egt_startdato,
                     egt_sluttdato,
                     egt_ant_timer,
                     kandidat_nr,
                     kvalifikasjon_id,
                     organisasjon_nr)
VALUES (DEFAULT, DATE('2018-05-05'), DATE('2018-08-08'), 30, NULL, NULL, NULL, 3, 4, 1236);

-- oppgave d)

-- 1
SELECT bedrift_navn, telefon, epost
FROM bedrift;

-- 2
SELECT jobb_id, bedrift.bedrift_navn, bedrift.telefon
FROM oppdrag
       LEFT JOIN bedrift ON bedrift.organisasjon_nr = oppdrag.organisasjon_nr;
SELECT jobb_id, bedrift.bedrift_navn, bedrift.telefon
FROM oppdrag
       NATURAL JOIN bedrift;

-- 3
SELECT kandidat.etternavn, kandidat.fornavn, kvalifikasjon.beskrivelse
FROM kandidat
       NATURAL JOIN kandi_kvali
       NATURAL JOIN kvalifikasjon;

-- 4

SELECT DISTINCT etternavn, fornavn
FROM kandidat
WHERE kandidat_nr NOT IN (SELECT kandidat_nr FROM kandi_kvali);

SELECT etternavn, fornavn, kvalifikasjon.beskrivelse
FROM kandidat
       LEFT JOIN kandi_kvali ON (kandidat.kandidat_nr = kandi_kvali.kandidat_nr)
       LEFT JOIN kvalifikasjon ON (kvalifikasjon.kvalifikasjon_id = kandi_kvali.kvalifikasjon_id);

-- 5 Skriv ut jobbhistorikken til en bestemt vikar, gitt kandidatnr.
-- Vikarnavn, sluttdato, oppdragsnr og bedriftsnavn skal med.

SELECT kandidat.fornavn, kandidat.etternavn, oppdrag.sluttdato, oppdrag.jobb_id, bedrift.bedrift_navn
FROM kandidat
       NATURAL JOIN oppdrag
       NATURAL JOIN bedrift
WHERE kandidat_nr = 1;

SELECT kandidat.fornavn, kandidat.etternavn, oppdrag.sluttdato, oppdrag.jobb_id, bedrift.bedrift_navn
FROM kandidat
       LEFT JOIN oppdrag ON kandidat.kandidat_nr = oppdrag.kandidat_nr
       LEFT JOIN bedrift ON oppdrag.organisasjon_nr = bedrift.organisasjon_nr
WHERE kandidat.kandidat_nr = 1;
