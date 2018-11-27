package Øvelse8;

import java.time.LocalDate;
import java.time.Period;

abstract class BonusMedlem {

    private final int medlNr;
    private final Personalia pers;
    private final LocalDate innmeldtDato;
    private int poeng = 0;

    public BonusMedlem(int medlNr, Personalia pers, LocalDate innmeldtDato) {
        this.medlNr = medlNr;
        this.pers = pers;
        this.innmeldtDato = innmeldtDato;
    }

    /*
        public BonusMedlem(int medlNr, Personalia pers, LocalDate innmeldtDato, int poeng){
            this.medlNr = medlNr;
            this.pers = pers;
            this.innmeldtDato = innmeldtDato;
            this.poeng = poeng;
        }
    */
    protected int getMedlnr() {
        return medlNr;
    }

    protected Personalia getPers() {
        return pers;
    }

    protected LocalDate getInnmeldtDato() {
        return innmeldtDato;
    }

    protected int getPoeng() {
        return poeng;
    }

    protected int finnKvalPoeng(LocalDate date) {
        int aarMellom = Period.between(innmeldtDato, date).getYears();
        return (aarMellom >= 1) ? 0 : poeng;
    }

    protected boolean okPassord(String passord) {
        return (pers.okPassord(passord));

    }

    protected void registrerPoeng(int nyPoeng) {
        poeng += nyPoeng;
    }
}

class TestBonusmedlem {
    public static void main(String[] args) {
        Personalia ole = new Personalia("Olsen", "Ole", "ole.olsen@dot.com", "ole");
        Personalia tove = new Personalia("Hansen", "Tove", "tove.hansen@dot.com", "tove");
        LocalDate testdato = LocalDate.of(2008, 2, 10);
        System.out.println("Totalt antall tester: 8");

        BasicMedlem b1 = new BasicMedlem(100, ole, LocalDate.of(2006, 2, 15));
        b1.registrerPoeng(30000);
        if (b1.finnKvalPoeng(testdato) == 0 && b1.getPoeng() == 30000) {
            System.out.println("Test 1 ok");
        }
        b1.registrerPoeng(15000);
        if (b1.finnKvalPoeng(testdato) == 0 && b1.getPoeng() == 45000) {
            System.out.println("Test 2 ok");
        }

        BasicMedlem b2 = new BasicMedlem(110, tove, LocalDate.of(2007, 3, 5));
        b2.registrerPoeng(30000);
        if (b2.finnKvalPoeng(testdato) == 30000 && b2.getPoeng() == 30000) {
            System.out.println("Test 3 ok");
        }

        SoelvMedlem b3 = new SoelvMedlem(b2.getMedlnr(), b2.getPers(), b2.getInnmeldtDato(), b2.getPoeng());
        b3.registrerPoeng(50000);
        if (b3.finnKvalPoeng(testdato) == 90000 && b3.getPoeng() == 90000) {
            System.out.println("Test 4 ok");
        }

        GullMedlem b4 = new GullMedlem(b3.getMedlnr(), b3.getPers(), b3.getInnmeldtDato(), b3.getPoeng());
        b4.registrerPoeng(30000);
        if (b4.finnKvalPoeng(testdato) == 135000 && b4.getPoeng() == 135000) {
            System.out.println("Test 5 ok");
        }

        testdato = LocalDate.of(2008, 12, 10);
        if (b4.finnKvalPoeng(testdato) == 0 && b4.getPoeng() == 135000) {
            System.out.println("Test 6 ok");
        }

        if (!ole.okPassord("OOO")) {
            System.out.println("Test 7 ok");
        }
        if (tove.okPassord("tove")) {
            System.out.println("Test 8 ok");
        }
    }
}