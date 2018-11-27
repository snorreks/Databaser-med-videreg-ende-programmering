package SQLTester.DatabaseTest;/*
 * DatabaseKontaktMedExc3.java
 * Bruker try-with resources til frigj�ring av databaseressurser.
 *
 */

import java.sql.*;

class DatabaseKontaktMedExc3 {
    public static void main(String[] args) {
        String databasedriver = "com.mysql.jdbc.Driver";
        Connection forbindelse = null;
        try {
            Class.forName(databasedriver);
            String databasenavn = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/xxxxxxx?user=xxxxxxx&password=xxxxxxx";
            forbindelse = DriverManager.getConnection(databasenavn);
        } catch (Exception e) {
            System.out.println("Feil oppst�tt ved tilkopling til databasen:\n" + e.getMessage());
            System.exit(0);
        }

        //Opprydder.lukkForbindelse(forbindelse); // for � framprovosere unntak fra createStatement

        // Java7: Ny utgave av try-setningen der vi kan ramse opp ressurser som skal lukkes ved utgang av setningen.
        // Ressursene m� tilh�re klasser som implementerer interfacet AutoClosable. Interfacet krever at metoden close() er
        // implementert, og den blir kalt automatisk.
        try (Statement setning = forbindelse.createStatement();
             ResultSet res = setning.executeQuery("select * from person")) {  // feilstaving av tabellnavn gir unntak fra executeQuery()
            while (res.next()) {
                int persNr = res.getInt("persnr");
                String fornavn = res.getString("fornavn");
                String etternavn = res.getString("etternavn");
                System.out.println(persNr + ": " + fornavn + " " + etternavn);
            }
        } catch (SQLException e) {
            System.out.println("Unntaksobjekt kastet fra " + e.getClass().getSimpleName());
            System.out.println("Full stack trace:");
            e.printStackTrace();
        }

        Opprydder.lukkForbindelse(forbindelse);
    }
}