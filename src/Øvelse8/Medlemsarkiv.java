package Øvelse8;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class Medlemsarkiv {

    private ArrayList<BonusMedlem> medlemer = new ArrayList<>();
    private LocalDate date = LocalDate.now();

    public Medlemsarkiv() {
    }

    public int finnPoeng(int medlNr, String passord) {
        for (BonusMedlem m : medlemer) {
            if (m.getMedlnr() == medlNr) {
                if (m.okPassord(passord)) {
                    return m.getPoeng();
                }
                return -1;
            }
        }
        return -2;
    }

    public boolean registrerPoeng(int medlNr, int nyPoeng) {
        for (BonusMedlem m : medlemer) {
            if (m.getMedlnr() == medlNr) {
                m.registrerPoeng(nyPoeng);
                return true;
            }
        }
        return false;
    }

    public int nyMedlem(Personalia pers, LocalDate innmeldt) {
        int nr = finnLedigNr();
        medlemer.add(new BasicMedlem(nr, pers, innmeldt));
        return nr;
    }

    public void sjekkMedlemmer() {
        int i = -1;
        for (BonusMedlem m : medlemer) {
            i++;
            if (m instanceof GullMedlem) continue;
            if (m instanceof BasicMedlem) {
                if (m.finnKvalPoeng(date) < 25000) continue;
                if (m.finnKvalPoeng(date) < 75000) {
                    medlemer.set(i, new SoelvMedlem(m.getMedlnr(), m.getPers(), m.getInnmeldtDato(), m.getPoeng()));
                } else {
                    medlemer.set(i, new GullMedlem(m.getMedlnr(), m.getPers(), m.getInnmeldtDato(), m.getPoeng()));
                }
            }
            if (m instanceof SoelvMedlem) {
                if (m.finnKvalPoeng(date) < 50000) continue;
                medlemer.set(i, new GullMedlem(m.getMedlnr(), m.getPers(), m.getInnmeldtDato(), m.getPoeng()));
            }
        }
    }

    public int getMedlNr(String fornavn) {
        for (BonusMedlem m : medlemer) {
            if (m.getPers().getFornavn().equals(fornavn)) {
                return m.getMedlnr();
            }
        }
        return -1;
    }

    public String getType(int nr) {
        for (BonusMedlem m : medlemer) {
            if (m.getMedlnr() == nr) {
                if (m instanceof BasicMedlem) return "Basic";
                if (m instanceof SoelvMedlem) return "Soelv";
                return "Gull";
            }
        }
        return "MedlemsNr er ikke registrert";
    }

    public BonusMedlem getMedlem(int nr) {
        for (BonusMedlem m : medlemer) {
            if (m.getMedlnr() == nr) return m;
        }
        return null;
    }

    private int finnLedigNr() {
        Random r = new Random();
        int p = 0;
        boolean run = true;
        while (run) {
            p = r.nextInt();
            run = false;
            for (BonusMedlem m : medlemer) {
                if (p == m.getMedlnr()) run = true;
            }
        }
        return p;
    }
}

class Test {
    public static void main(String[] args) {
        Personalia ole = new Personalia("Olsen", "Ole", "ole.olsen@dot.com", "ole");
        Personalia tove = new Personalia("Hansen", "Tove", "tove.hansen@dot.com", "tove");
        Personalia bob = new Personalia("Bob", "Per", "bob.bobsen@dot.com", "bobb");
        Personalia karl = new Personalia("Karl", "Sverre", "karl.sverresen@dot.com", "karls");
        LocalDate testdato1 = LocalDate.of(2017, 6, 10);
        LocalDate testdato2 = LocalDate.of(2018, 1, 06);
        LocalDate testdato3 = LocalDate.of(2016, 1, 06);

        Medlemsarkiv arkiv = new Medlemsarkiv();

        int oleNr = arkiv.nyMedlem(ole, testdato1);
        int toveNr = arkiv.nyMedlem(tove, testdato2);
        int bobNr = arkiv.nyMedlem(bob, testdato1);
        int karlNr = arkiv.nyMedlem(karl, testdato3);

        arkiv.registrerPoeng(oleNr, 75000);
        arkiv.registrerPoeng(toveNr, 55000);
        arkiv.registrerPoeng(bobNr, 1000);
        arkiv.registrerPoeng(karlNr, 800000); // skal retunere basic siden tiden er over 1 år.

        arkiv.sjekkMedlemmer();

        String print = "";

        //print += "Ole har type: "+arkiv.getType(oleNr) + "\n";
        print += "Ole har typen: " + arkiv.getMedlem(oleNr) + "\n";
        print += "Tove har type: " + arkiv.getType(toveNr) + "\n";
        print += "Bob har type: " + arkiv.getType(bobNr) + "\n";
        print += "Karl har type: " + arkiv.getType(karlNr) + "\n";

        System.out.print(print);
    }
}