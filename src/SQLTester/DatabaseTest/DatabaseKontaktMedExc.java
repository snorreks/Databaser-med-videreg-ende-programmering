package SQLTester.DatabaseTest; /**
 * DatabaseKontaktMedExc.java
 * Utvidet med unntaksh�ndtering
 */

import java.sql.*;

class DatabaseKontaktMedExc {
    public static void main(String[] args) {
        String databasedriver = "org.apache.derby.jdbc.ClientDriver";
        Connection forbindelse = null;
        try {
            Class.forName(databasedriver);
            String databasenavn = "jdbc:derby://localhost:1527/personer;user=vprg;password=vprg";
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
            try {
                if (res != null) res.close();
            } catch (SQLException e) {
                System.out.println("Feil 3: " + e);
            } finally {
                try {
                    if (setning != null) setning.close();
                } catch (SQLException e) {
                    System.out.println("Feil 4: " + e);
                } finally {
                    try {
                        if (forbindelse != null) forbindelse.close();
                    } catch (SQLException e) {
                        System.out.println("Feil 5:" + e);
                    }
                } // finally, lukking av Statement-objekt
            } // finally, lukking av ResultSet-objekt
        } // finally
    }
}