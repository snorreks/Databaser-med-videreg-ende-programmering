package Øvelse4;

/**
 * Kunde.java
 * Inneholder kundedata.
 */

class Kunde {
    private final String navn;
    private final String tlf;

    /**
     * Konstrukt�r:
     * B�de navn og telefon m� oppgis, de kan ikke v�re verken null eller tomme strenger.
     */
    public Kunde(String navn, String tlf) {
        if (navn == null || navn.trim().equals("")) {
            throw new IllegalArgumentException("Navn m� oppgis.");
        }
        if (tlf == null || tlf.trim().equals("")) {
            throw new IllegalArgumentException("Tlf m� oppgis.");
        }
        this.navn = navn.trim();
        this.tlf = tlf.trim();
    }

    public String getNavn() {
        return navn;
    }

    public String getTlf() {
        return tlf;
    }

    public String toString() {
        return navn + ", tlf " + tlf;
    }
}