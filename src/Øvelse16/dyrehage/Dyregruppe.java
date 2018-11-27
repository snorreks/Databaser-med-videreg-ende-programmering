package Øvelse16.dyrehage;

abstract class Dyregruppe extends Dyr {
    private final String GRUPPENAVN;
    private int antIndivider;

    public Dyregruppe(String norskNavn, String latNavn, String latFamilie, int ankommetDato, String adresse, String gruppenavn, int antIndivider) {
        super(norskNavn, latNavn, latFamilie, ankommetDato, adresse);
        GRUPPENAVN = gruppenavn;
        this.antIndivider = antIndivider;
    }

    public String getGruppenavn() {
        return GRUPPENAVN;
    }

    public int getAntIndivider() {
        return antIndivider;
    }

    public void setAntIndivider(int antIndivider) {
        this.antIndivider = antIndivider;
    }

    @Override
    public String getNorskNavn() {
        return "gruppe av " + norskNavn;
        //må gjøre norsNavn i klassen Dyr protected,
        // eller mekke en getNorskNAvn metode
        //og fjerne final
    }

    public String toString() {
        return GRUPPENAVN + " " + antIndivider;
    }
}
