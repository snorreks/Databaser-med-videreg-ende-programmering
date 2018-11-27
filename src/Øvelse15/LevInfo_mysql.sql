set FOREIGN_KEY_CHECKS = 0;

DROP TABLE ordredetalj;
DROP TABLE prisinfo;
DROP TABLE ordrehode;
DROP TABLE delinfo;
DROP TABLE levinfo;
DROP TABLE levinfoutenFylke;
DROP VIEW kan_levere;
DROP VIEW leverere;
DROP VIEW levinfoUtenFylke2;
DROP VIEW sted2;
DROP TABLE sted;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE delinfo (
  delnr       INTEGER,
  beskrivelse VARCHAR(30) NOT NULL,
  CONSTRAINT delinfo_pk PRIMARY KEY (delnr)
);

CREATE TABLE levinfo (
  levnr   INTEGER,
  navn    VARCHAR(20) NOT NULL,
  adresse VARCHAR(20) NOT NULL,
  levby   VARCHAR(20) NOT NULL,
  fylke   VARCHAR(20) NOT NULL,
  postnr  INTEGER     NOT NULL,
  CONSTRAINT levinfo_pk PRIMARY KEY (levnr)
);

CREATE TABLE ordrehode (
  ordrenr INTEGER,
  dato    DATE    NOT NULL,
  levnr   INTEGER NOT NULL,
  status  CHAR(1) NOT NULL,
  CONSTRAINT ordrehode_pk PRIMARY KEY (ordrenr),
  CONSTRAINT ordrehode_fk FOREIGN KEY (levnr) REFERENCES levinfo (levnr)
);

CREATE TABLE ordredetalj (
  ordrenr INTEGER,
  delnr   INTEGER NOT NULL,
  kvantum INTEGER NOT NULL,
  CONSTRAINT ordredetalj_pk PRIMARY KEY (ordrenr, delnr),
  CONSTRAINT ordredetalj_fk1 FOREIGN KEY (ordrenr) REFERENCES ordrehode (ordrenr),
  CONSTRAINT ordredetalj_fk2 FOREIGN KEY (delnr) REFERENCES delinfo (delnr)
);

CREATE TABLE prisinfo (
  delnr     INTEGER,
  levnr     INTEGER,
  katalognr CHAR(6),
  pris      REAL,
  CONSTRAINT prisinfo_pk PRIMARY KEY (delnr, levnr),
  CONSTRAINT prisinfo_fk1 FOREIGN KEY (delnr) REFERENCES delinfo (delnr),
  CONSTRAINT prisinfo_fk2 FOREIGN KEY (levnr) REFERENCES levinfo (levnr)
);

insert into delinfo (delnr, beskrivelse)
values (51173, 'Binders');
insert into delinfo (delnr, beskrivelse)
values (1, 'Kontorstol');
insert into delinfo (delnr, beskrivelse)
values (51200, 'Linjalsett');
insert into delinfo (delnr, beskrivelse)
values (3, 'Pult');
insert into delinfo (delnr, beskrivelse)
values (4, 'Skrivebord');
insert into delinfo (delnr, beskrivelse)
values (1909, 'Skriveunderlag');
insert into delinfo (delnr, beskrivelse)
values (201, 'Svarte kulepenner');
insert into delinfo (delnr, beskrivelse)
values (202, 'Blå kulepenner');

insert into levinfo (levnr, navn, adresse, levby, fylke, postnr)
values (6, 'Kontorekspressen AS', 'Skolegata 3', 'Oslo', 'Oslo', 1234);
insert into levinfo (levnr, navn, adresse, levby, fylke, postnr)
values (82, 'Kontordata AS', 'Åsveien 178', 'Trondheim', 'S-Trøndelag', 7023);
insert into levinfo (levnr, navn, adresse, levby, fylke, postnr)
values (9, 'Kontorutstyr AS', 'Villa Villekulla', 'Ås', 'Østfold', 1456);
insert into levinfo (levnr, navn, adresse, levby, fylke, postnr)
values (44, 'Billig og Bra AS', 'Aveny 56', 'Oslo', 'Oslo', 1222);
insert into levinfo (levnr, navn, adresse, levby, fylke, postnr)
values (12, 'Mister Office AS', 'Storgt 56', 'Ås', 'Østfold', 1456);
insert into levinfo (levnr, navn, adresse, levby, fylke, postnr)
values (81, 'Kontorbutikken AS', 'Gjennomveien 78', 'Ål', 'Telemark', 3345);

insert into ordrehode (ordrenr, dato, levnr, status)
values (11, '1986-05-10', 6, 'c');
insert into ordrehode (ordrenr, dato, levnr, status)
values (12, '1986-07-17', 82, 'c');
insert into ordrehode (ordrenr, dato, levnr, status)
values (13, '1986-09-13', 44, 'p');
insert into ordrehode (ordrenr, dato, levnr, status)
values (14, '1986-12-17', 44, 'p');
insert into ordrehode (ordrenr, dato, levnr, status)
values (15, '1987-01-03', 44, 'p');
insert into ordrehode (ordrenr, dato, levnr, status)
values (16, '1987-01-31', 6, 'c');
insert into ordrehode (ordrenr, dato, levnr, status)
values (17, '1987-05-14', 6, 'c');
insert into ordrehode (ordrenr, dato, levnr, status)
values (18, '1987-05-12', 82, 'p');


insert into ordredetalj (ordrenr, delnr, kvantum)
values (11, 1, 5);
insert into ordredetalj (ordrenr, delnr, kvantum)
values (11, 201, 100);
insert into ordredetalj (ordrenr, delnr, kvantum)
values (11, 202, 100);
insert into ordredetalj (ordrenr, delnr, kvantum)
values (11, 1909, 6);
insert into ordredetalj (ordrenr, delnr, kvantum)
values (11, 51200, 20);
insert into ordredetalj (ordrenr, delnr, kvantum)
values (12, 3, 2);
insert into ordredetalj (ordrenr, delnr, kvantum)
values (12, 201, 50);
insert into ordredetalj (ordrenr, delnr, kvantum)
values (12, 202, 60);
insert into ordredetalj (ordrenr, delnr, kvantum)
values (13, 51173, 20);
insert into ordredetalj (ordrenr, delnr, kvantum)
values (14, 201, 100);
insert into ordredetalj (ordrenr, delnr, kvantum)
values (14, 202, 100);
insert into ordredetalj (ordrenr, delnr, kvantum)
values (14, 51173, 30);
insert into ordredetalj (ordrenr, delnr, kvantum)
values (15, 201, 100);
insert into ordredetalj (ordrenr, delnr, kvantum)
values (15, 202, 100);
insert into ordredetalj (ordrenr, delnr, kvantum)
values (16, 201, 50);
insert into ordredetalj (ordrenr, delnr, kvantum)
values (16, 202, 50);
insert into ordredetalj (ordrenr, delnr, kvantum)
values (16, 51173, 20);
insert into ordredetalj (ordrenr, delnr, kvantum)
values (16, 1909, 10);
insert into ordredetalj (ordrenr, delnr, kvantum)
values (17, 1, 10);
insert into ordredetalj (ordrenr, delnr, kvantum)
values (17, 3, 1);
insert into ordredetalj (ordrenr, delnr, kvantum)
values (17, 4, 5);
insert into ordredetalj (ordrenr, delnr, kvantum)
values (18, 3, 2);
insert into ordredetalj (ordrenr, delnr, kvantum)
values (18, 4, 8);

insert into prisinfo (delnr, levnr, katalognr, pris)
values (51173, 6, '37S', 0.57);
insert into prisinfo (delnr, levnr, katalognr, pris)
values (51173, 44, '312/2', 0.44);
insert into prisinfo (delnr, levnr, katalognr, pris)
values (51173, 82, '300021', 0.35);
insert into prisinfo (delnr, levnr, katalognr, pris)
values (1, 6, '97s', 120.00);
insert into prisinfo (delnr, levnr, katalognr, pris)
values (1, 9, 'x120', 219.99);
insert into prisinfo (delnr, levnr, katalognr, pris)
values (51200, 6, '54s', 7.35);
insert into prisinfo (delnr, levnr, katalognr, pris)
values (1909, 9, 'X7770', 3.00);
insert into prisinfo (delnr, levnr, katalognr, pris)
values (201, 44, '100/1', 1.60);
insert into prisinfo (delnr, levnr, katalognr, pris)
values (201, 6, '21s', 1.90);
insert into prisinfo (delnr, levnr, katalognr, pris)
values (202, 44, '101/1', 1.50);
insert into prisinfo (delnr, levnr, katalognr, pris)
values (202, 9, '22s', 1.76);
insert into prisinfo (delnr, levnr, katalognr, pris)
values (3, 82, '2077', 1299.00);
insert into prisinfo (delnr, levnr, katalognr, pris)
values (4, 82, '2177', 899.00);
insert into prisinfo (delnr, levnr, katalognr, pris)
values (201, 82, '3140', 2.60);
insert into prisinfo (delnr, levnr, katalognr, pris)
values (202, 82, '3141', 1.50);
insert into prisinfo (delnr, levnr, katalognr, pris)
values (3, 6, '34P', 1199.00);
insert into prisinfo (delnr, levnr, katalognr, pris)
values (4, 6, '67P', 550.00);
insert into prisinfo (delnr, levnr, katalognr, pris)
values (1909, 6, '53P', 0.85);
insert into prisinfo (delnr, levnr, katalognr, pris)
values (202, 6, '345u', 6.50);
insert into prisinfo (delnr, levnr, katalognr, pris)
values (3, 9, 'a48', 1050.00);
-- Oppgave 1:

-- a) List ut all informasjon (ordrehode og ordredetalj) om ordrer for leverandør nr 44.

SELECT DISTINCT *
FROM ordrehode
       NATURAL JOIN ordredetalj
WHERE levnr = 44;

-- b) Finn navn og by ("LevBy") for leverandører som kan levere del nummer 1.

SELECT navn, levby
FROM prisinfo
       NATURAL JOIN levinfo
WHERE delnr = 1;

-- c) Finn nummer, navn og pris for den leverandør som kan levere del nummer 201 til billigst pris.

SELECT levinfo.levnr, levinfo.navn, pris
FROM prisinfo
       NATURAL JOIN levinfo
WHERE delnr = 201
ORDER BY pris
LIMIT 1;

-- d) Lag fullstendig oversikt over ordre nr 16, med ordrenr, dato, delnr, beskrivelse, kvantum, (enhets-)pris og beregnet beløp (=pris*kvantum).

SELECT ordredetalj.ordrenr,
       ordrehode.dato,
       ordredetalj.delnr,
       delinfo.beskrivelse,
       ordredetalj.kvantum,
       prisinfo.pris,
       (prisinfo.pris * ordredetalj.kvantum)AS beregnet_beløp
FROM ordredetalj
       NATURAL JOIN ordrehode
       NATURAL JOIN delinfo
       NATURAL JOIN prisinfo
WHERE ordredetalj.ordrenr = 16;

SELECT ordredetalj.ordrenr,
       ordrehode.dato,
       delnr,
       delinfo.beskrivelse,
       ordredetalj.kvantum,
       prisinfo.pris,
       CONCAT(prisinfo.pris * ordredetalj.kvantum) AS beregnet_beløp
FROM ordredetalj
       NATURAL JOIN ordrehode
       NATURAL JOIN delinfo
       NATURAL JOIN prisinfo
WHERE ordrehode.ordrenr = 16;

-- e) Finn delnummer og leverandørnummer for deler som har en pris som er høyere enn prisen for del med katalognr X7770.

SELECT delnr, levnr, pris
FROM prisinfo
WHERE pris > (SELECT pris from prisinfo where katalognr = 'X7770');

/* f) 1 Tenk deg at tabellen levinfo skal deles i to. Sammenhengen mellom by og fylke skal tas ut av tabellen.
Det er unødvendig å lagre fylketilhørigheten for hver forekomst av by. Lag én ny tabell som inneholder byer og fylker.
Fyll denne med data fra levinfo. Lag også en tabell som er lik levinfo unntatt kolonnen Fylke.
(Denne splittingen av tabellen levinfo gjelder bare i denne oppgaven. I resten av oppgavesettet antar du at du har den opprinnelige levinfo-tabellen.)
*/

CREATE TABLE sted (
  sted_id INTEGER,
  levby   VARCHAR(20) NOT NULL,
  fylke   VARCHAR(20) NOT NULL,
  CONSTRAINT sted_pk PRIMARY KEY (sted_id)
);

CREATE TABLE levinfoutenFylke (
  levnr   INTEGER,
  navn    VARCHAR(20) NOT NULL,
  adresse VARCHAR(20) NOT NULL,
  levby   VARCHAR(20) NOT NULL,
  postnr  INTEGER     NOT NULL,
  CONSTRAINT levinfo_pk PRIMARY KEY (levnr)
);

CREATE VIEW sted2 AS (SELECT levby, fylke
                      FROM levinfo);

SELECT *
FROM sted2;

CREATE VIEW levinfoUtenFylke2 AS (SELECT levnr, navn, adresse, levby, postnr
                                  FROM levinfo);

-- ii) Lag en virtuell tabell (view) slik at brukerne i størst mulig grad kan jobbe på samme måte mot de to nye tabellene som den gamle.
-- Prøv ulike kommandoer mot tabellen (select, update, delete, insert). Hvilke begrensninger, hvis noen, har brukerne i forhold til tidligere?

UPDATE levinfoUtenFylke2
SET adresse = 2;
SELECT *
FROM levinfoUtenFylke2;

-- g) Anta at en vurderer å slette opplysningene om de leverandørene som ikke er representert i Prisinfo-tabellen.
-- Finn ut hvilke byer en i tilfelle ikke får leverandør i. (Du skal ikke utføre slettingen.)
-- (Tips: Svaret skal bli kun én by, "Ål".)

SELECT levby
FROM levinfo
WHERE levby NOT IN(SELECT levby FROM levinfo
                                       NATURAL JOIN prisinfo);

SELECT levby
FROM levinfo
WHERE levby NOT IN(SELECT levby FROM levinfo
                                       NATURAL JOIN prisinfo);
-- h) Finn leverandørnummer for den leverandør som kan levere ordre nr 18 til lavest totale beløp (vanskelig).


# et view som kan levere noen av delene eller alle:
CREATE VIEW leverere AS
  SELECT levnr, prisinfo.delnr, CONCAT(pris * ordredetalj.kvantum) AS totalpris
  FROM prisinfo
         JOIN ordredetalj ON prisinfo.delnr = ordredetalj.delnr
  WHERE ordredetalj.ordrenr = 18;

# et view som kan levere alle delene:
CREATE VIEW kan_levere AS
  SELECT levnr, SUM(totalpris) AS totalpris
  FROM leverere
  GROUP BY levnr
  HAVING COUNT(*) = (SELECT COUNT(*) FROM ordredetalj WHERE ordrenr = 18);

# finne hvem som leverer billigst utifra viewet "kan_levere":
SELECT levnr
FROM kan_levere
WHERE kan_levere.totalpris = (SELECT MIN(totalpris) AS totalpris FROM kan_levere);

-- Bruk Bok-databasen fra Øving 6. Gå gjennom datasettet og finn ut hvor det ligger NULL-verdier.

-- a) Sett opp en SELECT-setning som er UNION mellom alle forlag med Oslo-nummer
-- (telefonnummer begynner med 2) og alle som ikke er Oslo-nummer.
-- Får du med forlaget med NULL-verdi på telefonnummer? Hvis ikke, utvid unionen med en mengde til.

SELECT forlag_navn, telefon
FROM forlag
WHERE telefon LIKE '2%'
UNION
SELECT forlag_navn, telefon
FROM forlag
WHERE telefon NOT LIKE '2%'
UNION
SELECT forlag_navn, telefon
FROM forlag;

/* b)Sett opp SQL-setninger som finner gjennomsnittlig alder på forfattere der fødselsåret er oppgitt.
 For forfattere der dødsåret ikke er oppgitt, skal du kun ta med de som er født etter 1900.
  Tips for å få ut året i år:
MySQL: SELECT YEAR(CURRENT_DATE) FROM ... hvilken tabell som helst ...
*/

SELECT CONCAT(fornavn, " ", etternavn) AS navn, CONCAT(dod_aar - fode_aar) AS alder
FROM forfatter
WHERE dod_aar IS NOT NULL;

# lever enda, og er født etter 1900

SELECT CONCAT(fornavn, " ", etternavn) AS navn, CONCAT(YEAR(CURRENT_DATE) - fode_aar) AS alder
FROM forfatter
WHERE fode_aar >= 1900
  AND dod_aar IS NULL;

# Lage et view av begge select-setningene

CREATE VIEW forfatterview AS
  SELECT CONCAT(fornavn, " ", etternavn) AS navn, CONCAT(dod_aar - fode_aar) AS alder
  FROM forfatter
  WHERE dod_aar IS NOT NULL
  UNION
  SELECT CONCAT(fornavn, " ", etternavn) AS navn, CONCAT(YEAR(CURRENT_DATE) - fode_aar) AS alder
  FROM forfatter
  WHERE fode_aar >= 1900
    AND dod_aar IS NULL;

# Select-setning som gir ut gjennomsnittlig alder til forfattere:

SELECT CONCAT(SUM(alder) / COUNT(*)) gj_snitt_alder
FROM forfatterview;

-- c)Sett opp SQL-setninger som finner hvor stor andel av forfatterne som ble med i beregningene under b).

SELECT CONCAT(COUNT(*) / (SELECT COUNT(*) FROM forfatter) * 100) AS prosentdel_forfattere
FROM forfatterview;