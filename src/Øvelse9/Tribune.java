package Øvelse9;

import java.io.*;
import java.util.Arrays;

abstract class Tribune implements Serializable {

    private final String tribunenavn;
    private final int kapasitet;
    private final int pris;

    public Tribune(String tribunenavn, int kapasitet, int pris) {
        this.tribunenavn = tribunenavn;
        this.kapasitet = kapasitet;
        this.pris = pris;
    }

    protected int finnAntallSolgteBilletter() {
        return 0;
    }

    protected int finnInntekt() {
        return finnAntallSolgteBilletter() * pris;
    }

    protected int getPris() {
        return pris;
    }

    protected String getTribuneNavn() {
        return tribunenavn;
    }

    protected int getKapasitet() {
        return kapasitet;
    }

    abstract Billett[] kjøpBilletter(int antall);

    abstract Billett[] kjøpBilletter(String[] navn);

    public String toString() {
        String print = "";
        print += "--" + tribunenavn + ":\n";
        print += "Kapasitet: " + kapasitet + "\n";
        print += "Antall solgte billetter: " + finnAntallSolgteBilletter() + "\n";
        print += "Inntekten: " + finnInntekt() + "\n";
        return print;
    }
}

class Test {

    public static Tribune[] lesFraFil(String dir) {
        try {
            FileInputStream innstrøm = new FileInputStream(dir);
            ObjectInputStream inn = new ObjectInputStream(innstrøm);
            Tribune[] lest = (Tribune[]) inn.readObject();
            inn.close();
            System.out.println("(Klarte å lese fra fil)\n");
            return lest;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static boolean skrivTilFil(String filnavn, Tribune[] t) {
        try {
            ObjectOutputStream ut = new ObjectOutputStream(new FileOutputStream(filnavn));
            ut.writeObject(t);
            ut.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void main(String[] args) {

        String[] liste1 = {"Per", "Bob", "Karl", "Olsen"};
        String[] liste2 = {"RikeMann", "Rik Fyr", "PerOlsen"};
        String dir = "src/Øvelse9/t.ser";
        Tribune[] tribuner = new Tribune[4];

        File f = new File(dir);


        if (f.exists() && !f.isDirectory()) {
            tribuner = lesFraFil(dir);
        } else {
            tribuner[0] = new Staa("StåTribune", 20, 50);
            tribuner[1] = new Staa("StaaTribune Stor", 40, 45);
            tribuner[2] = new Sitte("SitteTribune", 50, 100, 5);
            tribuner[3] = new VIP("VIP TRIBUNE", 5, 500, 1);

            tribuner[0].kjøpBilletter(5);
            tribuner[1].kjøpBilletter(10);
            tribuner[2].kjøpBilletter(liste1);
            tribuner[3].kjøpBilletter(liste2);
        }

        Arrays.sort(tribuner, (a, b) -> b.finnInntekt() - a.finnInntekt());

        String print = "";

        for (Tribune t : tribuner) {
            print += t.toString();
        }
        System.out.print(print);

        if (skrivTilFil(dir, tribuner)) System.out.println("\n(Klarte å skrive til " + dir + ")");
    }
}