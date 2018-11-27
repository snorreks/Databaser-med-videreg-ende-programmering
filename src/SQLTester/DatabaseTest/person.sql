CREATE TABLE person (
  persnr    INTEGER PRIMARY KEY,
  fornavn   VARCHAR(30) NOT NULL,
  etternavn VARCHAR(30) NOT NULL
);
INSERT INTO person
VALUES (100, 'Ole', 'Hansen');
INSERT INTO person
VALUES (101, 'Anne Grethe', 'Ås');
INSERT INTO person
VALUES (102, 'Jonny', 'Hansen');
