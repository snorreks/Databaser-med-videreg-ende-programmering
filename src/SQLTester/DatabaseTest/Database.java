package SQLTester.DatabaseTest; /**
 * Database.java - "Programmering i Java", 4.utgave - 2009-07-01
 * <p>
 * Dette er en "database-wrapper" for vedlikehold av tabellen Person.
 * Konstrukt�ren �pner en databaseforbindelse som holdes inntil klienten
 * kaller metoden kobleNedforbindelse().
 * <p>
 * Feilh�ndtering: Konstrukt�ren kaster unntaksobjekt dersom
 * JDBC-klassen ikke kan lastes, eller databaseforbindelsen ikke kan �pnes.
 * For �vrig h�ndteres SQLException ved en utskrift i kommandovinduet.
 * Slike unntak skyldes programmeringsfeil og  sendes ikke til klienten.
 */

import mittBibliotek.Opprydder;
import mittBibliotek.Person;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    private Connection forbindelse;

    /**
     * Laster JDBC-klassene og �pner databaseforbindelsen.
     */
    public Database(String dbDriver, String dbNavn) throws Exception {
        try {
            Class.forName(dbDriver);
            forbindelse = DriverManager.getConnection(dbNavn);
        } catch (Exception e) {
            Opprydder.skrivMelding(e, "konstrukt�r");
            throw e;
        }
    }

    /**
     * En ufullstendig testklient f�lger.
     * I utskriften vil sql-setningene fra metodene bli skrevet ut.
     * Se oppgave etter dette delkapitlet for mer fullstendig testing.
     */
    public static void main(String[] args) throws Exception {
        //String dbDriver = "org.apache.derby.jdbc.ClientDriver";
        String dbDriver = "com.mysql.jdbc.Driver";
        String dbNavn
                //= "jdbc:derby://localhost:1527/personer;user=vprg;password=vprg";
                = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/xxxxxxx?user=xxxxxxx&password=xxxxxxx";
        Database db = new Database(dbDriver, dbNavn);
        System.out.println("Totalt antall tester: 5");
        Person personen = db.registrerNyPerson("�se", "Hansen");
        if (personen != null) { // kan kun fortsette dersom registreringen gikk bra
            System.out.println("Database: Test 1 vellykket.");
            int nr = personen.getPersonNr();
            personen.setFornavn("Berit");
            personen.setEtternavn("Sol�s");
            if (db.endreNavn(personen)) {
                System.out.println("Database: Test 2 vellykket.");
            }
            ArrayList<Person> alle = db.finnAlle();
            Person s�k = new Person(nr, "Berit", "Sol�s");
            if (alle.indexOf(s�k) >= 0) {
                System.out.println("Database: Test 3 vellykket.");
            }
            if (db.slettPerson(nr)) {
                System.out.println("Database: Test 4 vellykket.");
            }
            alle = db.finnAlle();
            if (alle.indexOf(s�k) < 0) {
                System.out.println("Database: Test 5 vellykket.");
            }
        }
        db.kobleNedForbindelse();   // NB!
    }

    /**
     * Metode for � lukke forbindelse. M� kalles av klienten f�r programmet avsluttes.
     */
    public void kobleNedForbindelse() {
        Opprydder.lukkForbindelse(forbindelse);
    }

    /**
     * Returnerer en ArrayList som inneholder alle dataene i tabellen person.
     * ArrayListen inneholder personobjekter. Listen er sortert etter etternavn.
     * Returnerer null dersom SQLException er kastet.
     */
    public ArrayList<Person> finnAlle() {
        ArrayList<Person> alle = new ArrayList<Person>();
        String sqlsetning
                = "select persnr, fornavn, etternavn from person order by etternavn, fornavn";
        System.out.println(sqlsetning);

        ResultSet res = null;
        Statement setning = null;
        try {
            setning = forbindelse.createStatement();
            res = setning.executeQuery(sqlsetning);
            while (res.next()) {
                int nr = res.getInt("persnr");
                String fornavn = res.getString("fornavn");
                String etternavn = res.getString("etternavn");
                Person navnet = new Person(nr, fornavn, etternavn);
                alle.add(navnet);
            }
        } catch (SQLException e) {
            Opprydder.skrivMelding(e, "finnAlle()");
            alle = null;
        } finally {  // setningene nedenfor utf�res alltid
            Opprydder.lukkResSet(res);
            Opprydder.lukkSetning(setning);
        }
        return alle;
    }

    /**
     * Endrer navn.
     * Det nye navnet samt personens nummer sendes inn som argument.
     * Returnerer false dersom ingen person med dette nummeret eksisterer,
     * eller SQLException er inntruffet.
     */
    public boolean endreNavn(Person personen) {
        int nr = personen.getPersonNr();
        String nyttFornavn = personen.getFornavn();
        String nyttEtternavn = personen.getEtternavn();
        String sqlsetning = "update person set fornavn = '" + nyttFornavn
                + "', etternavn = '" + nyttEtternavn + "' where persnr = " + nr;
        System.out.println(sqlsetning);

        Statement setning = null;
        try {
            setning = forbindelse.createStatement();
            return (setning.executeUpdate(sqlsetning) != 0);
        } catch (SQLException e) {
            Opprydder.skrivMelding(e, "endreNavn()");
        } finally {
            Opprydder.lukkSetning(setning);
        }
        return false;
    }

    /**
     * Registrerer ny person.
     * Nummer tildeles av systemet, og et fullstendig personobjekt returneres til
     * klienten. Dersom databasetabellen er tom, settes personnr lik 1, ellers
     * settes det lik hittil st�rste nummer + 1.(P� grunn av at data kan slettes,
     * kan denne metoden f�re til hull i nummersekvensen.)
     * Metoden returnerer null dersom feil oppst�tt.
     */
    public Person registrerNyPerson(String fornavn, String etternavn) {
        int nyttNr = 1; // dersom det ikke er data i databasen
        boolean ok = false;
        int antFors�k = 0;
        do {
            ResultSet res = null;
            Statement setning = null;
            try {
                String sqlsetning = "select max(persnr) as maks from person";
                setning = forbindelse.createStatement();
                res = setning.executeQuery(sqlsetning);
                /*
                 * Hvis det ikke er data i tabellen, vil maks() returnere SQL NULL.
                 * getInt() vil omforme denne til 0, og nyttNr blir dermed 1 dersom
                 * det ikke er data i databasen. Det er ok.
                 */
                res.next();
                nyttNr = res.getInt("maks") + 1;
                sqlsetning = "insert into person values("
                        + nyttNr + ", '" + fornavn + "', '" + etternavn + "')";
                System.out.println(sqlsetning);
                setning.executeUpdate(sqlsetning);
                ok = true;
            } catch (SQLException e) {
                /*
                 * Dersom feilen skyldes at person med samme nummer eksisterer fra f�r,
                 *  m� det bety at en annen klient har v�rt inne i databasen mellom
                 *  v�re to sql-setninger. Vi kj�rer derfor gjennom opptil tre ganger til.
                 *  (Dette er noe som vil inntreffe sv�rt sjelden.)
                 */
                if (antFors�k< 4){
                    antFors�k++;
                } else{
                    Opprydder.skrivMelding(e, "registrerNyPerson()");
                    return null;   // RETUR, feil oppst�tt (finally-blokken blir utf�rt)
                }
            } finally {
                Opprydder.lukkResSet(res);
                Opprydder.lukkSetning(setning);
            }
        } while (!ok);
        return new Person(nyttNr, fornavn, etternavn);
    }

    /**
     * Sletter person med gitt nummer.
     * Returnerer false dersom personen ikke finnes, eller SQLException inntruffet.
     */
    public boolean slettPerson(int nr) {
        String sqlsetning = "delete from person where persnr = " + nr;
        System.out.println(sqlsetning);

        Statement setning = null;
        try {
            setning = forbindelse.createStatement();
            return (setning.executeUpdate(sqlsetning) != 0);
        } catch (SQLException e) {
            Opprydder.skrivMelding(e, "slettPerson()");
        } finally {
            Opprydder.lukkSetning(setning);
        }
        return false;
    }
}