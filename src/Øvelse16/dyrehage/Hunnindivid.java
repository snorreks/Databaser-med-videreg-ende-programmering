package Øvelse16.dyrehage;

public class Hunnindivid extends Individ {
    private int antKull;

    public Hunnindivid(String norskNavn, String latNavn, String latFamilie,
                       int ankommetDato, String adresse, String navn, int fDato, Farlighet farlig, int antKull) {
        super(norskNavn, latNavn, latFamilie, ankommetDato, adresse, navn, fDato, false, farlig);
        this.antKull = antKull;
    }

    public int getAntKull() {
        return antKull;
    }

    public void leggTilKull(int antall) {
        antKull += antall;
    }

    public void leggTilNyttKull() {
        antKull++;
    }

    public String skrivUtInfo() {
        String print = toString();
        print += "Antall kull: " + antKull;
        return print;
    }
}