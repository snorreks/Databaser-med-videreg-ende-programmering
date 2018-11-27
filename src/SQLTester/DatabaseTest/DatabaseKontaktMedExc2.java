package SQLTester.DatabaseTest;/*
 * DatabaseKontaktMedExc2.java
 * Bruker klassen Opprydder til frigjooring av databaseressurser.
 *
 */

import java.sql.*;

class DatabaseKontaktMedExc2 {
    public static void main(String[] args) {
        String databasedriver = "com.mysql.jdbc.Driver";
        Connection forbindelse = null;
        try {
            Class.forName(databasedriver);
            String databasenavn = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/xxxxxxx?user=xxxxxxx&password=xxxxxxx";
            forbindelse = DriverManager.getConnection(databasenavn);
        } catch (Exception e) {
            System.out.println("Feil 1: " + e);
            System.exit(0);
        }

        Statement setning = null;
        ResultSet res = null;
        try {
            setning = forbindelse.createStatement();
            res = setning.executeQuery("select * from person");
            while (res.next()) {
                int persNr = res.getInt("persnr");
                String fornavn = res.getString("fornavn");
                String etternavn = res.getString("etternavn");
                System.out.println(persNr + ": " + fornavn + " " + etternavn);
            }
        } catch (SQLException e) {
            System.out.println("Feil 2: " + e);
        } finally {
            Opprydder.lukkResSet(res);
            Opprydder.lukkSetning(setning);
            Opprydder.lukkForbindelse(forbindelse);
        } // finally
    }
}