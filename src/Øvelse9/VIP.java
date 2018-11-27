package Øvelse9;

import java.io.Serializable;

public class VIP extends Sitte implements Serializable {

    private String[][] tilskuer; // tabellstørrelse: antall rader * antall plasser pr rad

    public VIP(String tribunenavn, int kapasitet, int pris, int antRad) {
        super(tribunenavn, kapasitet, pris, antRad);
        tilskuer = new String[antRad][kapasitet / antRad];
    }

    public int finnAntallSolgteBilletter() {
        int sum = 0;
        for (int i = 0; i < tilskuer.length; i++) {
            for (int n = 0; n < tilskuer[i].length; n++) {
                if (tilskuer[i][n] != null) sum++;
            }
        }
        return sum;
    }

    public int finnInntekt() {
        return finnAntallSolgteBilletter() * getPris();
    }

    public Billett[] kjøpBilletter(int antall) {
        return null;
    }

    public Billett[] kjøpBilletter(String[] navn) {
        Billett[] b = new Billett[navn.length];
        int[][] k = finnLedigPlass(navn.length);
        if (k == null) return null;
        for (int i = 0; i < navn.length; i++) {
            tilskuer[k[0][0]][k[0][1] + i] = navn[i];
            b[i] = new SitteplassBillett(getTribuneNavn(), getPris(), tilskuer[0].length, getPlassPrRad());
        }
        return b;
    }

    private int[][] finnLedigPlass(int antall) {
        int[][] k;
        int n;
        for (int i = 0; i < tilskuer.length; i++) {
            if (antall > tilskuer[i].length) return null;
            n = -1;
            while (n != -2) {
                n++;
                if (tilskuer[i][n] != null) continue;
                if (antall > getPlassPrRad() - n) {
                    n = -2;
                } else {
                    k = new int[][]{{i, n}};
                    return k;
                }
            }
        }
        return null;
    }
}
