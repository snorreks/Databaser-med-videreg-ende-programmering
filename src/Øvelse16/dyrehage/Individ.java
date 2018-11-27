package Øvelse16.dyrehage;

abstract class Individ extends Dyr implements SkandinaviskeRovdyr {

    private final String NAVN;
    private final int F_DATO;
    private final boolean HANNDYR;
    private final Farlighet FARLIG;


    public Individ(String norskNavn, String latNavn, String latFamilie,
                   int ankommetDato, String adresse, String navn, int fDato, boolean hanndyr, Farlighet farlig) {
        super(norskNavn, latNavn, latFamilie, ankommetDato, adresse);
        NAVN = navn;
        F_DATO = fDato;
        HANNDYR = hanndyr;
        FARLIG = farlig;
    }

    public String getNavn() {
        return NAVN;
    }

    public int getFdato() {
        return F_DATO;
    }

    public int getAlder() {
        return 2018 - F_DATO;
    }

    @Override
    public String getAdresse() {
        return super.getAdresse();
    }

    public boolean getHanndyr() {
        return HANNDYR;
    }

    public Farlighet getFarlig() {
        return FARLIG;
    }

    public void flytt(String nyAdresse) {
        adresse = nyAdresse;
    }

    public String skrivUtInfo() {
        return toString();
    }

    public String toString() {
        String print = "\n";
        print += NAVN + ":\n";
        print += "Kjønn: " + ((HANNDYR == true) ? "boie" : "dame") + "\n";
        print += "Adresse: " + adresse + "\n";
        print += "Artsnavn: " + getLatNavn() + "\n";
        print += "Artsfamilie: " + getLatFamilie() + "\n";
        print += "Farlighet: " + FARLIG + "\n";
        return print;
    }

    public int getAntKull() {
        return 0;
    }

    public void leggTilKull(int antall) {
    }

    public void leggTilNyttKull() {
    }

}
