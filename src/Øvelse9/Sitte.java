package Øvelse9;

import java.io.Serializable;

public class Sitte extends Tribune implements Serializable {

    private int[] antOpptatt;  // tabellstørrelse: antall rader
    private int plassPrRad;

    public Sitte(String tribunenavn, int kapasitet, int pris, int antRad) {
        super(tribunenavn, kapasitet, pris);
        antOpptatt = new int[antRad];
        plassPrRad = kapasitet / antRad;
    }

    public int finnAntallSolgteBilletter() {
        int sum = 0;
        for (int i = 0; i < antOpptatt.length; i++) {
            sum += antOpptatt[i];
        }
        return sum;
    }

    public int finnInntekt() {
        return finnAntallSolgteBilletter() * getPris();
    }

    public Billett[] kjøpBilletter(int antall) {
        Billett[] b = new Billett[antall];
        for (int i = 0; i < antOpptatt.length; i++) {
            if (antall <= plassPrRad - antOpptatt[i]) {
                for (int n = 0; n < antall; n++) {
                    b[n] = new SitteplassBillett(getTribuneNavn(), getPris(), antOpptatt.length, plassPrRad);
                }
                antOpptatt[i] += antall;
                return b;
            }
        }
        return null;
    }

    public Billett[] kjøpBilletter(String[] navn) {
        Billett[] b = new Billett[navn.length];
        for (int i = 0; i < antOpptatt.length; i++) {
            if (navn.length <= plassPrRad - antOpptatt[i]) {
                for (int n = 0; n < navn.length; n++) {
                    b[n] = new SitteplassBillett(getTribuneNavn(), getPris(), antOpptatt.length, plassPrRad);
                }
                antOpptatt[i] += navn.length;
                return b;
            }
        }
        return null;
    }

    protected int getPlassPrRad() {
        return plassPrRad;
    }
}
