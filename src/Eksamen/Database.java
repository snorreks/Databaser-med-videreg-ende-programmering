package Eksamen;

import java.sql.*;
import java.util.ArrayList;

public class Database {

    private final static String username = "snorreks";
    private final static String password = "QdtMxzl1";
    protected static Connection con = null;
    private static String databasedriver = "com.mysql.jdbc.Driver";
    private static String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/snorreks?autoReconnect=true&useSSL=false";

    public static boolean connectToDatabase() {
        try {
            Class.forName(databasedriver);
            con = DriverManager.getConnection(databasename, username, password);
            //DriverManager.getConnection(connectionString);
        } catch (Exception e) {
            System.err.println("Feil: " + e);
            return false;
        }
        return true;
    }

    public static void lukkForbindelse(Connection forbindelse) {
        try {
            if (forbindelse != null) {
                forbindelse.close();
            }
        } catch (SQLException e) {
            System.out.println("Problem med å lukke forbindelsen: " + e);
        }
    }

    public boolean changeTable(String values, String table, int id) {
        String insert = "INSERT INTO " + table + " VALUES(DEFAULT, " + values + ");";
        String update = "UPDATE " + table + " SET " + values + " WHERE id=" + id + ";";
        String delete = "DELETE FROM " + table + " WHERE id=" + id + ";";
        try (PreparedStatement stmt = con.prepareStatement(insert)) {
            stmt.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
            return false;
        }//trenger ikke finally for å close stmt siden vi har try-with-resources Statement
        return true;
    }

    public ArrayList<Klassenavn> getKlasseList() {
        ArrayList<Klassenavn> list = new ArrayList<>();
        String sentence = "SELECT * FROM TableKlasse;";
        try (Statement stmt = con.createStatement();
             ResultSet res = stmt.executeQuery(sentence)) {
            while (res.next()) {
                int id = res.getInt("id");
                String navn = res.getString("navn");
                list.add(new KlasseNavn(id, navn));
            }
        } catch (SQLException se) {
            System.err.println
            )
            se.printStackTrace();
            return null;
        }
        //trenger ikke finally for å close stmt siden vi har try-with-resources Statement
        return list;
    }
}
