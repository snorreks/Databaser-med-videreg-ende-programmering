package Øvelse4;

/*Reserver rom, gitt tidspunkt fra og til, antall personer samt navn og telefonnummer til kunden.
Finn antall rom
Finn et bestemt rom, gitt indeks
Finn et bestemt rom, gitt romnr

Inne i klassen Konferansesenter skal du ha en ArrayList av romobjekter.
Skisser metodene i klassen Konferansesenter.
Det b�r gi ideer til hvilke metoder du trenger i klassen Rom.

*/

import java.util.ArrayList;

class Konferansesenter {
    private final String navn;
    private ArrayList<Rom> rom = new ArrayList<Rom>();

    public Konferansesenter(String navn) {
        this.navn = navn;
    }

    public boolean addRom(int romNr, int size) {
        for (int i = 0; i < rom.size(); i++) {
            if (rom.get(i).getRomNr() == romNr) return false;
        }
        rom.add(new Rom(romNr, size));
        return true;
    }

    public boolean reserverRom(String navn, String tlf, int size, Tidspunkt start, Tidspunkt slutt) {
        for (int i = 0; i < rom.size(); i++) {
            if (rom.get(i).sjekkPlass(size) && rom.get(i).sjekkLedig(start, slutt)) {
                rom.get(i).addReservasjon(start, slutt, new Kunde(navn, tlf));
                return true;
            }
        }
        return false;
    }

    public int getAntRom() {
        return rom.size();
    }

    public Rom getRom(int index) {
        return rom.get(index);
    }

    public Rom getRomNr(int romNr) {
        for (int i = 0; i < rom.size(); i++) {
            if (romNr == (rom.get(i).getRomNr())) return rom.get(i);
        }
        return null;
    }

    public boolean regNyRom(int nyRomNr, int nySize) {
        for (Rom romer : rom) {
            if (romer.getRomNr() == nyRomNr) return false;
        }
        rom.add(new Rom(nyRomNr, nySize));
        return true;
    }


    public String toString() {
        //rom.sort();
        String print = navn + "'s Konferansesenter:\n";
        for (int i = 0; i < rom.size(); i++) {
            print += rom.get(i);
        }
        return print;
    }
}