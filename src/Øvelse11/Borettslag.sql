-- MySQL
-- DROP-setninger i tilfelle vi må kjøre scriptet på nytt.

DROP TABLE IF EXISTS leilighet;
DROP TABLE IF EXISTS andelseier;
DROP TABLE IF EXISTS bygning;
DROP TABLE IF EXISTS borettslag;
DROP TABLE IF EXISTS poststed;

-- Lager tabellene, legger inn NOT NULL- og UNIQUE-krav der det er naturlig
-- Vær forsiktig med å legge inn slike krav, det er vanskelig å forandre
-- dem i ettertid.

CREATE TABLE poststed (
  postnr   SMALLINT,
  poststed VARCHAR(20) NOT NULL,
  CONSTRAINT poststed_pk PRIMARY KEY (postnr)
);

CREATE TABLE borettslag (
  bolag_navn VARCHAR(20),
  bolag_adr  VARCHAR(40) NOT NULL UNIQUE,
  etabl_aar  SMALLINT    NOT NULL,
  postnr     SMALLINT    NOT NULL,
  CONSTRAINT borettslag_pk PRIMARY KEY (bolag_navn)
);

CREATE TABLE bygning (
  bygn_id     INTEGER     NOT NULL AUTO_INCREMENT,
  bygn_adr    VARCHAR(40) NOT NULL,
  ant_etasjer INTEGER              DEFAULT 1,
  bolag_navn  VARCHAR(20) NOT NULL,
  postnr      SMALLINT    NOT NULL,
  CONSTRAINT bygning_pk PRIMARY KEY (bygn_id)
);

CREATE TABLE leilighet (
  leil_nr     INTEGER  NOT NULL AUTO_INCREMENT,
  ant_rom     SMALLINT NOT NULL,
  ant_kvm     REAL     NOT NULL,
  etasje      SMALLINT          DEFAULT 1,
  bygn_id     INTEGER  NOT NULL,
  and_eier_nr INTEGER  NOT NULL UNIQUE,
  CONSTRAINT leilighet_pk PRIMARY KEY (leil_nr)
);

CREATE TABLE andelseier (
  and_eier_nr INTEGER     NOT NULL AUTO_INCREMENT,
  fornavn     VARCHAR(30) NOT NULL,
  etternavn   VARCHAR(30) NOT NULL,
  telefon     CHAR(15),
  ansiennitet SMALLINT,
  bolag_navn  VARCHAR(20) NOT NULL,
  CONSTRAINT andelseier_pk PRIMARY KEY (and_eier_nr)
);

-- Fremmednøkler

ALTER TABLE borettslag
  ADD CONSTRAINT borettslag_fk1 FOREIGN KEY (postnr)
REFERENCES poststed (postnr);

ALTER TABLE bygning
  ADD CONSTRAINT bygning_fk1 FOREIGN KEY (postnr)
REFERENCES poststed (postnr);

ALTER TABLE bygning
  ADD CONSTRAINT bygning_fk2 FOREIGN KEY (bolag_navn)
REFERENCES borettslag (bolag_navn);

ALTER TABLE leilighet
  ADD CONSTRAINT leilighet_fk1 FOREIGN KEY (bygn_id)
REFERENCES bygning (bygn_id);

ALTER TABLE leilighet
  ADD CONSTRAINT leilighet_fk2 FOREIGN KEY (and_eier_nr)
REFERENCES andelseier (and_eier_nr);

ALTER TABLE andelseier
  ADD CONSTRAINT andelseier_fk2 FOREIGN KEY (bolag_navn)
REFERENCES borettslag (bolag_navn);

-- Legger inn gyldige data

INSERT INTO poststed (postnr, poststed)
VALUES (2020, 'Skedsmokorset');
INSERT INTO poststed (postnr, poststed)
VALUES (6408, 'Aureosen');
INSERT INTO poststed (postnr, poststed)
VALUES (7033, 'Trondheim');
INSERT INTO poststed (postnr, poststed)
VALUES (7020, 'Trondheim');

INSERT INTO borettslag (bolag_navn, bolag_adr, etabl_aar, postnr)
VALUES ('Tertitten', 'Åsveien 100', 1980, 7020);
INSERT INTO borettslag (bolag_navn, bolag_adr, etabl_aar, postnr)
VALUES ('Sisiken', 'Brurød', 1990, 7033);
INSERT INTO borettslag (bolag_navn, bolag_adr, etabl_aar, postnr)
VALUES ('Lerken', 'Storgt 5', 2000, 6408);

INSERT INTO andelseier (and_eier_nr, fornavn, etternavn, telefon, ansiennitet, bolag_navn)
VALUES (DEFAULT, 'Even', 'Trulsbo', '56667743', 3, 'Tertitten');
INSERT INTO andelseier (and_eier_nr, fornavn, etternavn, telefon, ansiennitet, bolag_navn)
VALUES (DEFAULT, 'Anna', 'Olsen', '45674588', 10, 'Tertitten');
INSERT INTO andelseier (and_eier_nr, fornavn, etternavn, telefon, ansiennitet, bolag_navn)
VALUES (DEFAULT, 'Ingrid', 'Olsen', '45785388', 8, 'Tertitten');
INSERT INTO andelseier (and_eier_nr, fornavn, etternavn, telefon, ansiennitet, bolag_navn)
VALUES (DEFAULT, 'Arne', 'Torp', '78565388', 7, 'Tertitten');
INSERT INTO andelseier (and_eier_nr, fornavn, etternavn, telefon, ansiennitet, bolag_navn)
VALUES (DEFAULT, 'Arne', 'Martinsen', '78555388', 4, 'Sisiken');

INSERT INTO bygning (bygn_id, bygn_adr, ant_etasjer, bolag_navn, postnr)
VALUES (DEFAULT, 'Åsveien 100a', 3, 'Tertitten', 7020);
INSERT INTO bygning (bygn_id, bygn_adr, ant_etasjer, bolag_navn, postnr)
VALUES (DEFAULT, 'Åsveien 100b', 3, 'Tertitten', 7020);
INSERT INTO bygning (bygn_id, bygn_adr, ant_etasjer, bolag_navn, postnr)
VALUES (DEFAULT, 'Åsveien 100c', 6, 'Tertitten', 7020);
INSERT INTO bygning (bygn_id, bygn_adr, ant_etasjer, bolag_navn, postnr)
VALUES (DEFAULT, 'Storgt 10', 2, 'Sisiken', 7020);

-- bruker defaultverdien for antall etasjer
INSERT INTO bygning (bygn_id, bygn_adr, bolag_navn, postnr)
VALUES (DEFAULT, 'Åsveien 100', 'Tertitten', 7020);

INSERT INTO leilighet (leil_nr, ant_rom, ant_kvm, etasje, bygn_id, and_eier_nr)
VALUES (DEFAULT, 5, 110, 3, 1, 1);
INSERT INTO leilighet (leil_nr, ant_rom, ant_kvm, etasje, bygn_id, and_eier_nr)
VALUES (DEFAULT, 5, 110, 3, 1, 2);
INSERT INTO leilighet (leil_nr, ant_rom, ant_kvm, etasje, bygn_id, and_eier_nr)
VALUES (DEFAULT, 2, 50, 1, 3, 3);

-- bruker defaultverdien for etasje
INSERT INTO leilighet (leil_nr, ant_rom, ant_kvm, bygn_id, and_eier_nr)
VALUES (DEFAULT, 5, 110, 1, 4);

-- ------------------------------------------------------------------------------------------------------------------------------------------------

-- Oppgave 1:

SELECT *
FROM borettslag
where borettslag.etabl_aar BETWEEN 1975 AND 1985;

-- Oppgave 2:

SELECT CONCAT(fornavn, ' ', etternavn, ', ansiennitet: ', ansiennitet, ' år') AS "andels eiere"
FROM andelseier;

-- Oppgave 3:

SELECT MIN(etabl_aar)AS elsteEtablÅr
FROM borettslag;

-- Oppgave 4:

SELECT DISTINCT bygn_adr
FROM bygning
       NATURAL JOIN leilighet
WHERE ant_rom >= 3;

-- Oppgave 5:

SELECT count(bolag_navn = 'Tertitten') AS AntallBygninger
FROM borettslag;

-- Oppgave 6:

-- Lag en liste som viser antall bygninger i hvert enkelt borettslag.
-- Listen skal være sortert på borettslagsnavn.
-- Husk at det kan finnes borettslag uten bygninger - de skal også med.

SELECT borettslag.bolag_navn, count(bygn_id) AS antBygninger
FROM borettslag
       LEFT JOIN bygning ON bygning.bolag_navn = borettslag.bolag_navn
GROUP BY borettslag.bolag_navn
ORDER BY borettslag.bolag_navn;

-- Oppgave 7:
-- Finn antall leiligheter i borettslaget "Tertitten".

SELECT count(leil_nr)AS antLeiligheter
FROM leilighet
       NATURAL JOIN bygning
       NATURAL JOIN borettslag
WHERE bolag_navn = 'Tertitten';

-- Oppgave 8:
-- Hvor høyt kan du bo i borettslaget "Tertitten"?

SELECT max(ant_etasjer)AS HøyestEtasje
FROM bygning
       NATURAL JOIN leilighet
WHERE bolag_navn = 'Tertitten';

-- Oppgave 9:
-- Finn navn og nummer til andelseiere som ikke har leilighet.

SELECT CONCAT(fornavn, ' ', etternavn) AS Navn, andelseier.and_eier_nr AS EierNr
FROM leilighet
       RIGHT JOIN andelseier ON leilighet.and_eier_nr = andelseier.and_eier_nr
WHERE leilighet.and_eier_nr IS NULL;

-- Oppgave 10:
-- Finn antall andelseiere pr borettslag, sortert etter antallet.
-- Husk at det kan finnes borettslag uten andelseiere - de skal også med.

SELECT borettslag.bolag_navn, count(and_eier_nr) AS antEiere
FROM borettslag
       LEFT JOIN andelseier ON borettslag.bolag_navn = andelseier.bolag_navn
GROUP BY bolag_navn
ORDER BY antEiere DESC;

-- Oppgave 11:
-- Skriv ut en liste over alle andelseiere.
-- For de som har leilighet, skal leilighetsnummeret skrives ut.

SELECT DISTINCT concat(fornavn, ' ', etternavn)                AS Andelseiere,
                IFnull(leilighet.leil_nr, 'Har ikke lelighet') AS LeilighetNr
FROM andelseier
       LEFT JOIN leilighet ON leilighet.and_eier_nr = andelseier.and_eier_nr;

-- Oppgave 12:
-- Hvilke borettslag har 🙃 leiligheter med eksakt 4 rom?

SELECT bolag_navn
FROM bygning
       NATURAL JOIN leilighet
WHERE ant_rom = 4;

-- Oppgave 13:
/*
Skriv ut en liste over antall andelseiere pr postnr og poststed, begrenset til
 de som bor i leiligheter tilknyttet et borettslag.
Husk at postnummeret til disse er postnummeret til bygningen de bor i,
 og ikke postnummeret til borettslaget.
Du trenger ikke ta med poststeder med 0 andelseiere.
(Ekstraoppgave: Hva hvis vi vil ha med poststeder med 0 andelseiere?)
*/

SELECT DISTINCT bygning.postnr AS postnr, poststed AS poststed, count(and_eier_nr) AS antalEiere
FROM (poststed NATURAL JOIN borettslag NATURAL JOIN bygning NATURAL JOIN andelseier)
GROUP BY and_eier_nr;
