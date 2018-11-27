2. Bruk tabellene fra fotball EM 2012, og skriv SQL -setninger for
følgende oppgaver:


2.1 VIEW

Svar på følgende oppgave ved å opprette ett eller flere VIEW :

Finn resultatene for hver kamp inkl.de kampene som endte 0-0 (hvor antall mål er 0):


2.2 VIEW

Skriv ut de lagene som har skåret flest (totalt) antall mål.
PS! Kan være flere enn ett lag.

SELECT teamid, COUNT (*) AS ant_maal FROM goal GROUP BY teamid;


2.3 CREATE TABLE

  Opprett en ny tabell kalt 'stadium' for å lagre data over stadium, som i tillegg til en 'id' og 'stadiumname' inneholder egne attributter for 'town' og 'capacity'.Attr.'id' skal være primærnøkkel i den nye tabellen.


  2.4 INSERT Skriv SQL -kode for å flytte verdiene i attr.stadium i game-tabellen over til attr.stadiumname i din nye stadium-tabellen

    2.5

    Vi ønsker å endre game-tabellen til å inkl.attr.id fra stadium-tabellen som en ny fremmendnøkkel.
    Skriv kode som legger til fremmednøkkelen OG setter inn riktig 'id' fra stadium-tabellen inn i oppdatert utgave av game-tabellen.

    2.6

    Slett stadium-attributtet fra game-tabellen (NB! Ikke bruke
      DELETE her).

    2.7

    Sjekk til slutt:Join av game - tabellen og stadium - tabellen.




