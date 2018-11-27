package SQLTester.DatabaseTest;/*
 * ResultSetUpdateable.java
 *
 DROP TABLE person;
 CREATE TABLE person(
   persnr     INTEGER PRIMARY KEY,
   fornavn    VARCHAR(30) NOT NULL,
   etternavn VARCHAR(30) NOT NULL);
 INSERT INTO person VALUES (100, 'Ole', 'Hansen');
 INSERT INTO person VALUES (101, 'Anne Grethe', '�s');
 INSERT INTO person VALUES (102, 'Jonny', 'Hansen');

 */

import java.sql.*;

import static javax.swing.JOptionPane.showMessageDialog;

class ResultSetUpdateable {

    public static void visResultatsett(ResultSet res) throws SQLException {
        boolean mer = res.first();
        while (mer) {
            int persNr = res.getInt("persnr");
            String fornavn = res.getString("fornavn");
            String etternavn = res.getString("etternavn");
            System.out.println(persNr + ": " + fornavn + " " + etternavn);
            mer = res.next();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        String databasedriver = "com.mysql.jdbc.Driver";
        Connection forbindelse = null;

        try {
            Class.forName(databasedriver);  // laster inn driverklassen
            String databasenavn = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/xxxxxxx?user=xxxxxxx&password=xxxxxxx";
            forbindelse = DriverManager.getConnection(databasenavn);
        } catch (Exception e) {
            System.out.println("Feil oppst�tt ved tilkopling til databasen:\n" + e.getMessage());
            System.exit(0);
        }

        PreparedStatement setning = null;
        ResultSet res = null;
        try {
            setning = forbindelse.prepareStatement("select * from person order by persnr", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            res = setning.executeQuery();

            visResultatsett(res);
            showMessageDialog(null, "Oppstart: Trykk OK når vi skal fortsette");

            // Skal endre personnummeret i rad 3
            res.absolute(3);
            res.updateInt("persnr", 555);
            res.updateRow();
            visResultatsett(res);
            showMessageDialog(null, "Har endret personnummer rad 3: Trykk OK n�r vi skal fortsette");

            // Skal legge inn en ny rad
            res.moveToInsertRow();
            res.updateInt("persnr", 371);
            res.updateString("fornavn", "Unni");
            res.updateString("etternavn", "Olsen");
            res.insertRow();
            visResultatsett(res);
            showMessageDialog(null, "Har lagt inn ny rad: Trykk OK n�r vi skal fortsette");

            // Skal slette f�rste rad
            res.first();
            System.out.println("Sletter person " + res.getInt("persnr"));
            res.deleteRow();
            visResultatsett(res);
            showMessageDialog(null, "Har slettet f�rste rad: Trykk OK n�r vi skal fortsette");

        } catch (SQLException e) {
            Opprydder.lukkSetning(setning);
            Opprydder.lukkForbindelse(forbindelse);
        }
    }
}
