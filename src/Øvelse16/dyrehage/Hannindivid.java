package Øvelse16.dyrehage;

public class Hannindivid extends Individ {

    public Hannindivid(String norskNavn, String latNavn, String latFamilie,
                       int ankommetDato, String adresse, String navn, int fDato, Farlighet farlig) {
        super(norskNavn, latNavn, latFamilie, ankommetDato, adresse, navn, fDato, true, farlig);
    }

    public int getAntKull() {
        return 0;
    }

    public void leggTilKull(int antall) {
    }

    public void leggTilNyttKull() {
    }
}
