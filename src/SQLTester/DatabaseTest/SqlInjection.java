package SQLTester.DatabaseTest; /**
 * SqlInjection.java
 */

import mittBibliotek.Opprydder;

import java.sql.*;

import static javax.swing.JOptionPane.showInputDialog;

class SqlInjection {
    public static void main(String[] args) throws Exception {
        String databasedriver = "com.mysql.jdbc.Driver";
        Class.forName(databasedriver);
        String dbnavn
                = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/xxxxxxx?user=xxxxxxx&password=xxxxxxx";
        Connection forbindelse = null;
        Statement setning = null;
        PreparedStatement prepSetning = null;
        ResultSet res = null;
        ResultSet resPrep = null;
        try {
            forbindelse = DriverManager.getConnection(dbnavn);
            String brukernavn = showInputDialog("Brukernavn:");
            String passord = showInputDialog("Passord:");

            // Statement
            String sqlSetning = "select * from bruker where bruker = '" + brukernavn + "' and passord = '" + passord + "'";
            // select * from bruker where bruker = '' or '1'='1' and passord = '' or '1'='1';
            System.out.println("Statement: " + sqlSetning);
            setning = forbindelse.createStatement();
            res = setning.executeQuery(sqlSetning);
            System.out.println("Resultater fra Statement:");
            while (res.next()) {
                System.out.println(res.getString("bruker") + " " + res.getString("passord"));
            }

            // PreparedStatement
            String prepSqlSetning = "select * from bruker where bruker = ? and passord = ?";
            System.out.println("PrepStatement: " + prepSqlSetning);
            prepSetning = forbindelse.prepareStatement(prepSqlSetning);
            prepSetning.setString(1, brukernavn);
            prepSetning.setString(2, passord);
            resPrep = prepSetning.executeQuery();
            System.out.println("Resultater fra Statement:");
            while (resPrep.next()) {
                System.out.println(resPrep.getString("bruker") + " " + resPrep.getString("passord"));
            }

        } catch (SQLException e) {
            Opprydder.skrivMelding(e, "Feil oppst�tt");
        } finally {
            Opprydder.lukkResSet(res);
            Opprydder.lukkResSet(resPrep);
            Opprydder.lukkSetning(setning);
            Opprydder.lukkSetning(prepSetning);
            Opprydder.lukkForbindelse(forbindelse);
        }
    }
}
