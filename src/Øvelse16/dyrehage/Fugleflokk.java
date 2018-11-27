package Øvelse16.dyrehage;

public class Fugleflokk extends Dyregruppe {

    private final int VEKT;
    private final boolean SWIM;

    public Fugleflokk(String norskNavn, String latNavn, String latFamilie, int ankommetDato,
                      String adresse, String gruppenavn, int antIndivider, int vekt, boolean swim) {
        super(norskNavn, latNavn, latFamilie, ankommetDato, adresse, gruppenavn, antIndivider);
        VEKT = vekt;
        SWIM = swim;
    }
}
