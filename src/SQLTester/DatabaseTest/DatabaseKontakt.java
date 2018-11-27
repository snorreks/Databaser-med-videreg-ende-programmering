package SQLTester.DatabaseTest; /**
 * DatabaseKontakt.java  - "Programmering i Java", 4.utgave - 2009-07-01
 * <p>
 * Programmet kopler seg til en database, og henter ut innholdet i tabellen Person.
 * SQL-skript for aa lage tabellen Person er gjengitt nederst i denne filen.
 * <p>
 * Stien til databasedriveren er satt opp i CLASSPATH. Dersom driveren er en
 * zip- eller jar-fil, maa boode sti og filnavn inn i CLASSPATH.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

class DatabaseKontakt {
    public static void main(String[] args) throws Exception {

        /* Hvis du bruker en annen driver, m� du skifte ut strengkonstanten i neste linje. */
        //String databasedriver = "org.apache.derby.jdbc.ClientDriver";
        String databasedriver = "com.mysql.jdbc.Driver";
        Class.forName(databasedriver);  // laster inn driverklassen

        /*
         * V�r database heter persondata, og den kj�rer p� samme maskin som
         * dette klientprogrammet. Strengkonstanten i neste linje m� du
         * tilpasse din situasjon.
         */
        String databasenavn
                //= "jdbc:derby://localhost:1527/personer;user=vprg;password=vprg";
                = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/xxxxxxx?user=xxxxxxx&password=xxxxxxx";
        Connection forbindelse = DriverManager.getConnection(databasenavn);
        Statement setning = forbindelse.createStatement();
        ResultSet res = setning.executeQuery("select * from person");
        while (res.next()) {
            int persNr = res.getInt("persnr");
            String fornavn = res.getString("fornavn");
            String etternavn = res.getString("etternavn");
            System.out.println(persNr + ": " + fornavn + " " + etternavn);
        }
        res.close();
        setning.close();
        forbindelse.close();
    }
}

/*
SQL-skript for � lage tabellen Person:

create table person(
 persnr    integer primary key,
 fornavn   varchar(30) not null,
 etternavn varchar(30) not null);

insert into person values (100, 'Ole', 'Hansen');
insert into person values (101, 'Anne Grethe', '�s');
insert into person values (102, 'Jonny', 'Hansen');

Utskrift fra programmet:

100: Ole Hansen
101: Anne Grethe �s
102: Jonny Hansen
*/