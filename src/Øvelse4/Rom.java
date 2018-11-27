package Øvelse4;

/*
Inne i klassen Rom skal du ha en ArrayList av reservasjonsobjekter.
Programmer metodene i klassen Rom. Lag et testprogram for denne klassen p� tilsvarende m�te som
det er gjort for de klassene som er gitt. Pr�v ut at metodene virker,
f.eks. om de h�ndterer overlapp i tid ved reservasjon av rommet.
*/

import java.util.ArrayList;

class Rom implements Comparable<Rom> {
    private int romNr;
    private int size;
    private ArrayList<Reservasjon> reservasjoner = new ArrayList<Reservasjon>();

    public Rom(int romNr, int size) {
        this.romNr = romNr;
        this.size = size;
    }

    public boolean sjekkPlass(int size) {
        return this.size >= size;
    }

    public boolean sjekkLedig(Tidspunkt start, Tidspunkt slutt) {
        for (int i = 0; i < reservasjoner.size(); i++) {
            if (reservasjoner.get(i).overlapp(start, slutt)) return false;
        }
        return true;
    }

    public boolean addReservasjon(Tidspunkt fraTid, Tidspunkt tilTid, Kunde kunde) {
        if (fraTid == null || tilTid == null || kunde == null) return false;
        reservasjoner.add(new Reservasjon(fraTid, tilTid, kunde));
        return true;
    }

    public int getRomNr() {
        return romNr;
    }

    public int getSize() {
        return size;
    }

    public String printRes() {
        String print = "Rom nr: " + romNr + " har disse reservasjonene:\n";
        for (int i = 0; i < reservasjoner.size(); i++) {
            print += reservasjoner.get(i) + "\n";
        }
        return print;
    }


    public int compareTo(Rom detAndre) {
        if (romNr < detAndre.getRomNr()) {
            return -1;
        } else if (romNr > detAndre.getRomNr()) {
            return 1;
        } else {
            return 0;
        }
    }

    public String toString() {
        String print = "Rom nr: " + getRomNr() + ", størrelse: " + getSize() + ", reservasjoner: " + reservasjoner.size() + "\n";
        return print;
    }
}