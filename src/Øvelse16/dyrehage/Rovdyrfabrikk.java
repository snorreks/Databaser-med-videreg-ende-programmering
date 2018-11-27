package Øvelse16.dyrehage;

public class Rovdyrfabrikk {


    public SkandinaviskeRovdyr nyBinne(String navn, int ankommetDato, int fDato, String adresse, int antKull) {
        return new Hunnindivid("Binne", "Ursus arctos",
                "Ursidae", ankommetDato, adresse,
                navn, fDato, Farlighet.Dødelig, antKull);
    }

    public SkandinaviskeRovdyr nyHannbjørn(String navn, int ankommetDato, int fDato, String adresse) {
        return new Hannindivid("Hannbjørn", "Ursus arctos",
                "Ursidae", ankommetDato, adresse,
                navn, fDato, Farlighet.Dødelig);
    }

    public SkandinaviskeRovdyr nyUlvetispe(String navn, int ankommetDato, int fDato, String adresse, int antKull) {
        return new Hunnindivid("Ulv", "Canis lupus",
                "Canidae", ankommetDato, adresse,
                navn, fDato, Farlighet.Skadelig, antKull);
    }

    public SkandinaviskeRovdyr nyUlvehann(String navn, int ankommetDato, int fDato, String adresse) {
        return new Hannindivid("Ulv", "Canis lupus",
                "Canidae", ankommetDato, adresse,
                navn, fDato, Farlighet.Skadelig);
    }

}
