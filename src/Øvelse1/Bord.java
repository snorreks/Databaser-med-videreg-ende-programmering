package Øvelse1;

public class Bord {

    private String[] bord;

    public Bord(int maksbord) {
        bord = new String[maksbord];
    }

    public int antLedig() {
        int antall = 0;
        for (int i = 0; i < bord.length; i++) {
            if (bord[i] != null) antall++;
        }
        return antall;
    }

    public int antOpptatt() {
        return bord.length - antLedig();
    }

    public boolean frigiBord(int[] bordnr) {
        for (int i = 0; i < bordnr.length; i++) {
            if (bordnr[i] >= bord.length || bordnr[i] < 0) return false;
        }
        for (int i = 0; i < bordnr.length; i++) {
            bord[bordnr[i]] = null;
        }
        return true;
    }

    public boolean reserver(String navn, int[] bordnr) {
        for (int i = 0; i < bordnr.length; i++) {
            if (bordnr[i] >= bord.length || bordnr[i] < 0) return false;
        }
        for (int i = 0; i < bordnr.length; i++) {
            if (bord[bordnr[i]] != null) return false;
            bord[bordnr[i]] = navn;
        }
        return true;
    }

    public int[] bordPerson(String navn) {
        int[] personBorder = new int[antBordPerson(navn)];
        int p = 0;
        for (int i = 0; i < bord.length; i++) {
            if (bord[i] == null) continue;
            if (bord[i].equals(navn)) {
                personBorder[p] = i;
                p++;
            }
        }
        return personBorder;
    }

    public int antBordPerson(String navn) {
        int antall = 0;
        for (int i = 0; i < bord.length; i++) {
            if (bord[i] == null) continue;
            if (bord[i].equals(navn)) antall++;
        }
        return antall;
    }


    public String toString() {
        String print = "";
        for (int i = 0; i < bord.length; i++) {
            if (bord[i] != null) {
                print += bord[i] + " har bord nr: " + (i + 1);
                print += "\n";
            }
        }
        return print;
    }


}