package Øvelse16.dyrehage;

public class Fiskestim extends Dyregruppe {

    private final int LENGDE;
    private final boolean DELE;

    public Fiskestim(String norskNavn, String latNavn, String latFamilie, int ankommetDato,
                     String adresse, String gruppenavn, int antIndivider, int lengde, boolean dele) {
        super(norskNavn, latNavn, latFamilie, ankommetDato, adresse, gruppenavn, antIndivider);
        LENGDE = lengde;
        DELE = dele;
    }

    public int getLengde() {
        return LENGDE;
    }

    public boolean getDele() {
        return DELE;
    }
}