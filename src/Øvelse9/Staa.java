package Øvelse9;

import java.io.Serializable;

public class Staa extends Tribune implements Serializable {

    private int antSolgteBilletter;

    public Staa(String tribunenavn, int kapasitet, int pris) {
        super(tribunenavn, kapasitet, pris);
    }

    public int finnAntallSolgteBilletter() {
        return antSolgteBilletter;
    }

    public int finnInntekt() {
        return finnAntallSolgteBilletter() * getPris();
    }

    public Billett[] kjøpBilletter(int antall) {
        if (antall >= getKapasitet() - antSolgteBilletter) return null;
        Billett[] b = new Billett[antall];
        for (int i = 0; i < antall; i++) {
            b[i] = new StaaplassBillett(getTribuneNavn(), getPris());
        }
        antSolgteBilletter += antall;
        return b;
    }

    public Billett[] kjøpBilletter(String[] navn) {
        if (navn.length >= getKapasitet() - antSolgteBilletter) return null;
        Billett[] b = new Billett[navn.length];
        for (int i = 0; i < b.length; i++) {
            b[i] = new StaaplassBillett(getTribuneNavn(), getPris());
        }
        antSolgteBilletter += navn.length;
        return b;
    }
}
