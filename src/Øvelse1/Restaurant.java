package Øvelse1;

import java.time.LocalDateTime;

public class Restaurant {

    private final int startYear;
    LocalDateTime now = LocalDateTime.now();
    private String navn;
    private Bord bord;
    private int year = now.getYear();


    public Restaurant(String navn, int startYear, int maksborder) {
        this.navn = navn;
        this.startYear = startYear;
        bord = new Bord(maksborder);
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String nyNavn) {
        navn = nyNavn;
    }

    public int getYear() {
        return startYear;
    }

    public int getAlder() {
        return year - startYear;
    }

    public int antLedig() {
        return bord.antLedig();
    }

    public int antOpptatt() {
        return bord.antOpptatt();
    }

    public boolean reserver(String navn, int[] bordnr) {
        return bord.reserver(navn, bordnr);
    }

    public int[] bordPerson(String navn) {
        return bord.bordPerson(navn);
    }

    public boolean frigiBord(int[] borderFri) {
        return bord.frigiBord(borderFri);
    }

    public int[] inputTilTabell(String tall) {
        String[] tab = tall.split("\\-");
        int[] bordTab = new int[tab.length];
        for (int i = 0; i < tab.length; i++) {
            bordTab[i] = Integer.parseInt(tab[i]) - 1;
        }
        return bordTab;
    }

    public String toString() {
        String print = "";
        print += "Restaurant: " + navn + ", " + getAlder() + " år gammel";
        print += "\nBorder: \n";
        print += bord.toString();
        return print;
    }
}