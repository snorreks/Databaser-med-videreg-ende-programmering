package Øvelse18;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {

    private String databaseDriver = "com.mysql.jdbc.Driver";
    //private String databaseNavn = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/snorreks?user=snorreks&password=QdtMxzl1";
    private String databaseNavn = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/snorreks?autoReconnect=true&useSSL=false";
    private Connection forbindelse = null;

    public Database() {
        try {
            Class.forName(databaseDriver);
            forbindelse = DriverManager.getConnection(databaseNavn, "snorreks", "QdtMxzl1");
        } catch (Exception e) {
            System.err.println("Feil 1: " + e);
            System.exit(0);
        }
    }

    public void lukkForbindelse() {
        try {
            forbindelse.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void autoForbindelse() {
        try {
            forbindelse.setAutoCommit(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean regNyBok(Bok nyBok) {

        String inputBokTittel = "INSERT INTO boktittel VALUES ('" + nyBok.getIsbn() + "', '"
                + nyBok.getForfatter() + "', '" + nyBok.getTittel() + "')";
        String inputEksemplar = "INSERT INTO eksemplar VALUES ('" + nyBok.getIsbn() + "',1)";
        try (Statement setning = forbindelse.createStatement();
             ResultSet res = setning.executeQuery("SELECT ISBN FROM boktittel")
        ) {

            forbindelse.setAutoCommit(false);
            while (res.next()) {
                if (res.getString("isbn").equals(nyBok.getIsbn())) return false;
            }
            setning.executeUpdate(inputBokTittel);
            setning.executeUpdate(inputEksemplar);


            forbindelse.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            autoForbindelse();
        }
        return true;
    }

    public int regNyttEksemplar(String isbn) {
        int eks_nr = 1;
        try (Statement setning = forbindelse.createStatement();
             ResultSet res = setning.executeQuery("SELECT * FROM eksemplar where isbn =" + isbn)
        ) {
            forbindelse.setAutoCommit(false);

            while (res.next()) {
                eks_nr++;
            }

            if (eks_nr == 0) return 0;

            String input = "INSERT INTO eksemplar VALUES ('" + isbn + "'," + eks_nr + ",NULL )";


            setning.executeUpdate(input);

            forbindelse.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            autoForbindelse();
        }
        return eks_nr;
    }

    private boolean checkIsbn(String isbn) {
        try (Statement setning = forbindelse.createStatement();
             ResultSet res = setning.executeQuery("SELECT ISBN FROM boktittel")
        ) {
            while (res.next()) {
                if (res.getString("isbn").equals(isbn)) return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean lånUtEksemplar(String isbn, String navn, int eksNr) {
        if (!checkIsbn(isbn)) return false;
        boolean riktigEksNr = false;
        try (Statement setning = forbindelse.createStatement();
             ResultSet res = setning.executeQuery("SELECT * FROM eksemplar where isbn =" + isbn)
        ) {
            while (res.next() && !riktigEksNr) {
                if (res.getInt("eks_nr") == eksNr) riktigEksNr = true;
            }
            if (!riktigEksNr) return false;
            forbindelse.setAutoCommit(false);

            String input = "update eksemplar set laant_av = '" + navn + "' where isbn = '" + isbn + "' and eks_nr = " + eksNr;

            setning.executeUpdate(input);

            forbindelse.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            autoForbindelse();
        }
        return true;
    }

    public void getPersoner() {
        try (
                Statement setning = forbindelse.createStatement();
                ResultSet res = setning.executeQuery("SELECT * FROM person")) {
            while (res.next()) {
                int persNr = res.getInt("persnr");
                String fornavn = res.getString("fornavn");
                String etternavn = res.getString("etternavn");
                System.out.println(persNr + ": " + fornavn + " " + etternavn);
            }
        } catch (Exception e) {
        }
    }
}

class Test {
    public static void main(String[] args) {
        Database data = new Database();
        if (data.regNyBok(new Bok("23", "Bok", "Per"))) System.out.println("funka");
        if (data.regNyBok(new Bok("23", "Bok2", "Bob Petter"))) System.out.println("funka");
        System.out.println(data.regNyttEksemplar("23"));

        if (data.lånUtEksemplar("23", "Per Bob", 1)) System.out.println("fikk lånt ut bok");
        if (data.lånUtEksemplar("23", "Karl Per ", 5)) System.out.println("dette skal ikke funke");
        if (data.lånUtEksemplar("26", "Karl Per ", 1)) System.out.println("dette skal ikke funke");

        data.lukkForbindelse();
    }
}
