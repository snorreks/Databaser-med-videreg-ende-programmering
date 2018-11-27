CREATE TABLE sted (
  stedid    INTEGER  NOT NULL,
  stedsnavn CHAR(30) NOT NULL,
  CONSTRAINT pk_sted PRIMARY KEY (stedid)
);

CREATE TABLE etappe (
  enr        INTEGER NOT NULL,
  fra_stedid INTEGER NOT NULL,
  til_stedid INTEGER NOT NULL,
  distanse   INTEGER NOT NULL,
  CONSTRAINT pk_etappe PRIMARY KEY (enr)
);

CREATE TABLE lop (
  lnr      INTEGER  NOT NULL,
  aar      YEAR     NOT NULL, -- MySQLs datatype YEAR lagrer her aarstall med 4 siffer
  lopsnavn CHAR(30) NOT NULL,
  CONSTRAINT pk_lop PRIMARY KEY (lnr, aar)
);

CREATE TABLE lop_etappe (
  lnr    INTEGER NOT NULL,
  aar    YEAR    NOT NULL,
  enr    INTEGER NOT NULL,
  lopenr INTEGER,
  CONSTRAINT pk_lopeta PRIMARY KEY (lnr, aar, enr)
);

CREATE TABLE deltaker (
  deltnr       INTEGER  NOT NULL,
  navn         CHAR(30) NOT NULL,
  nasjonalitet CHAR(30),
  hjemsted     CHAR(30),
  CONSTRAINT pk_deltaker PRIMARY KEY (deltnr)
);

CREATE TABLE tidtaking (
  lnr      INTEGER NOT NULL,
  aar      YEAR    NOT NULL,
  enr      INTEGER NOT NULL,
  deltnr   INTEGER NOT NULL,
  tid      FLOAT, -- spesifiserer ikke datatypen for tid noe nærmere i denne oppgaven
  fullfort BOOLEAN NOT NULL DEFAULT 0,
  CONSTRAINT pk_tidtaking PRIMARY KEY (lnr, aar, enr, deltnr)
);

ALTER TABLE etappe
  ADD CONSTRAINT eta_fk1 FOREIGN KEY (fra_stedid)
REFERENCES sted (stedid);

ALTER TABLE etappe
  ADD CONSTRAINT eta_fk2 FOREIGN KEY (til_stedid)
REFERENCES sted (stedid);

ALTER TABLE lop_etappe
  ADD CONSTRAINT lopet_fk1 FOREIGN KEY (lnr, aar)
REFERENCES lop (lnr, aar);

ALTER TABLE lop_etappe
  ADD CONSTRAINT lopet_fk2 FOREIGN KEY (enr)
REFERENCES etappe (enr);

ALTER TABLE tidtaking
  ADD CONSTRAINT tidtak_fk1 FOREIGN KEY (lnr, aar, enr)
REFERENCES lop_etappe (lnr, aar, enr);

ALTER TABLE tidtaking
  ADD CONSTRAINT tidtak_fk2 FOREIGN KEY (deltnr)
REFERENCES deltaker (deltnr);

-- DATA
INSERT INTO sted (stedid, stedsnavn)
VALUES (1, 'Busan');
INSERT INTO sted (stedid, stedsnavn)
VALUES (2, 'Seoul');
INSERT INTO sted (stedid, stedsnavn)
VALUES (3, 'Incheon');
INSERT INTO sted (stedid, stedsnavn)
VALUES (4, 'Pyongyang');
INSERT INTO sted (stedid, stedsnavn)
VALUES (5, 'Jeju');
INSERT INTO sted (stedid, stedsnavn)
VALUES (6, 'Hanoi');

INSERT INTO etappe (enr, fra_stedid, til_stedid, distanse)
VALUES (1, 6, 5, 100);
INSERT INTO etappe (enr, fra_stedid, til_stedid, distanse)
VALUES (2, 5, 1, 10);
INSERT INTO etappe (enr, fra_stedid, til_stedid, distanse)
VALUES (3, 1, 3, 20);
INSERT INTO etappe (enr, fra_stedid, til_stedid, distanse)
VALUES (4, 3, 2, 40);

INSERT INTO etappe (enr, fra_stedid, til_stedid, distanse)
VALUES (5, 2, 4, 30);
INSERT INTO etappe (enr, fra_stedid, til_stedid, distanse)
VALUES (6, 4, 3, 20);

INSERT INTO lop (lnr, aar, lopsnavn)
VALUES (1, 2018, 'The Hidden Runner');
INSERT INTO lop (lnr, aar, lopsnavn)
VALUES (2, 2016, 'Running Man Championship');
INSERT INTO lop (lnr, aar, lopsnavn)
VALUES (3, 2015, 'The lap of all laps');

INSERT INTO lop_etappe (lnr, aar, enr, lopenr)
VALUES (1, 2018, 1, 1);
INSERT INTO lop_etappe (lnr, aar, enr, lopenr)
VALUES (1, 2018, 2, 2);
INSERT INTO lop_etappe (lnr, aar, enr, lopenr)
VALUES (2, 2016, 3, 1);
INSERT INTO lop_etappe (lnr, aar, enr, lopenr)
VALUES (2, 2016, 4, 2);
INSERT INTO lop_etappe (lnr, aar, enr, lopenr)
VALUES (2, 2016, 5, 3);

INSERT INTO deltaker (deltnr, navn, nasjonalitet, hjemsted)
VALUES (1, 'Lee Kwang Soo', 'Korea', 'Seoul');
INSERT INTO deltaker (deltnr, navn, nasjonalitet, hjemsted)
VALUES (2, 'Yoo Jae Suk', 'Korea', 'Seoul');
INSERT INTO deltaker (deltnr, navn, nasjonalitet, hjemsted)
VALUES (3, 'Jackie Chan', 'China', 'Hong Kong');
INSERT INTO deltaker (deltnr, navn, nasjonalitet, hjemsted)
VALUES (4, 'Anders', 'Norge', 'Steinkjer');
INSERT INTO deltaker (deltnr, navn, nasjonalitet, hjemsted)
VALUES (5, 'Christian', 'Vietnam', 'Hanoi');

INSERT INTO tidtaking (lnr, aar, enr, deltnr, tid, fullfort)
VALUES (2, 2016, 3, 1, 100, TRUE);
INSERT INTO tidtaking (lnr, aar, enr, deltnr, tid, fullfort)
VALUES (2, 2016, 4, 1, 100, TRUE);
INSERT INTO tidtaking (lnr, aar, enr, deltnr, tid, fullfort)
VALUES (2, 2016, 5, 1, 60, TRUE);
INSERT INTO tidtaking (lnr, aar, enr, deltnr, tid, fullfort)
VALUES (2, 2016, 3, 2, 80, TRUE);
INSERT INTO tidtaking (lnr, aar, enr, deltnr, tid, fullfort)
VALUES (2, 2016, 4, 2, 80, TRUE);
INSERT INTO tidtaking (lnr, aar, enr, deltnr, tid, fullfort)
VALUES (2, 2016, 5, 2, 200, FALSE);

INSERT INTO tidtaking (lnr, aar, enr, deltnr, tid, fullfort)
VALUES (1, 2018, 1, 3, 100, TRUE);
INSERT INTO tidtaking (lnr, aar, enr, deltnr, tid, fullfort)
VALUES (1, 2018, 2, 3, 70, TRUE);
INSERT INTO tidtaking (lnr, aar, enr, deltnr, tid, fullfort)
VALUES (1, 2018, 1, 4, 45, TRUE);
INSERT INTO tidtaking (lnr, aar, enr, deltnr, tid, fullfort)
VALUES (1, 2018, 2, 4, 42, TRUE);
INSERT INTO tidtaking (lnr, aar, enr, deltnr, tid, fullfort)
VALUES (1, 2018, 1, 5, 12, TRUE);
INSERT INTO tidtaking (lnr, aar, enr, deltnr, tid, fullfort)
VALUES (1, 2018, 2, 5, 89, FALSE);


SELECT enr
FROM etappe
where enr NOT IN (SELECT DISTINCT enr from lop_etappe);

SELECT navn
FROM deltaker
       natural join tidtaking
       natural join lop_etappe
where fullfort is true
GROUP BY navn
HAVING COUNT(*) IN (SELECT COUNT(*) FROM lop_etappe);


select navn
from deltaker
       natural join tidtaking
       natural join lop_etappe
where lnr = 1
  AND fullfort is TRUE
GROUP BY navn
having count(*) = (select count(*) from lop
                                          natural join lop_etappe where lnr = 1);


SELECT DISTINCT navn
from deltaker
       natural join tidtaking
       natural join lop_etappe
       natural join etappe
       join sted on til_stedid = stedid
where stedsnavn = 'hai'
  and fullfort is true
  and lopenr IN (SELECT count(*) from lop_etappe GROUP BY lnr)

select avg(deltnr)
from deltaker;

select min(distanse) as distanse
from etappe
       natural join lop_etappe
where lnr = 1;

SELECT SUM(tidtaking.tid) / COUNT(DISTINCT deltnr) AS avg_tid
FROM tidtaking
WHERE tidtaking.lnr = 1