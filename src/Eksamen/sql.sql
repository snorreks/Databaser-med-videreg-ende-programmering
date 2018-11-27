CREATE TABLE STED (
  stedid    INTEGER  NOT NULL,
  stedsnavn CHAR(30) NOT NULL,
  CONSTRAINT pk_sted PRIMARY KEY (stedid)
);
CREATE TABLE ETAPPE (
  enr        INTEGER NOT NULL,
  fra_stedid INTEGER NOT NULL,
  til_stedid INTEGER NOT NULL,
  distanse   INTEGER NOT NULL,
  CONSTRAINT pk_etappe PRIMARY KEY (enr)
);
CREATE TABLE LOP (
  lnr      INTEGER  NOT NULL,
  aar      YEAR     NOT NULL, -- MySQLs datatype YEAR lagrer her aarstall med 4 siffer
  lopsnavn CHAR(30) NOT NULL,
  CONSTRAINT pk_lop PRIMARY KEY (lnr, aar)
);
CREATE TABLE LOP_ETAPPE (
  lnr    INTEGER NOT NULL,
  aar    YEAR    NOT NULL,
  enr    INTEGER NOT NULL,
  lopenr INTEGER,
  CONSTRAINT pk_lopeta PRIMARY KEY (lnr, aar, enr)
);
CREATE TABLE DELTAKER (
  deltnr       INTEGER  NOT NULL,
  navn         CHAR(30) NOT NULL,
  nasjonalitet CHAR(30),
  hjemsted     CHAR(30),
  CONSTRAINT pk_deltaker PRIMARY KEY (deltnr)
);
CREATE TABLE TIDTAKING (
  lnr      INTEGER NOT NULL,
  aar      YEAR    NOT NULL,
  enr      INTEGER NOT NULL,
  deltnr   INTEGER NOT NULL,
  tid      FLOAT, -- spesifiserer ikke datatypen for tid noe nærmere i denne oppgaven
  fullfort BOOLEAN NOT NULL DEFAULT 0,
  CONSTRAINT pk_tidtaking PRIMARY KEY (lnr, aar, enr, deltnr)
);
ALTER TABLE ETAPPE
  ADD CONSTRAINT eta_fk1 FOREIGN KEY (fra_stedid)
REFERENCES STED (stedid);
ALTER TABLE ETAPPE
  ADD CONSTRAINT eta_fk2 FOREIGN KEY (til_stedid)
REFERENCES STED (stedid);
4
ALTER TABLE LOP_ETAPPE
  ADD CONSTRAINT lopet_fk1 FOREIGN KEY (lnr, aar)
REFERENCES LOP (lnr, aar);
ALTER TABLE LOP_ETAPPE
  ADD CONSTRAINT lopet_fk2 FOREIGN KEY (enr)
REFERENCES ETAPPE (enr);
ALTER TABLE TIDTAKING
  ADD CONSTRAINT tidtak_fk1 FOREIGN KEY (lnr, aar, enr)
REFERENCES LOP_ETAPPE (lnr, aar, enr);
ALTER TABLE TIDTAKING
  ADD CONSTRAINT tidtak_fk2 FOREIGN KEY (deltnr)
REFERENCES DELTAKER (deltnr);


SELECT *
FROM LOP
       NATURAL JOIN TIDTAKING;


SELECT DISTINCT *
FROM LOP,
     TIDTAKING
WHERE LOP.aar = TIDTAKING.aar;