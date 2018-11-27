package Øvelse3;

public class Student {
    private final String navn;
    private int antOppg;

    public Student(String navn, int antOppg) {
        this.navn = navn;
    }

    public String getNavn() {
        return navn;
    }

    public int getAntOppg() {
        return antOppg;
    }

    public void setAntOppg(int antOppg) {
        //if(antOppg) throw IllegalArgumentException;
        this.antOppg = antOppg;
    }

    public void addAntOppg(int antOppg) {
        //if(antOppg) throw IllegalArgumentException;
        this.antOppg += antOppg;
    }

    public String toString() {
        String print = "";
        print += "Navn: " + navn + ", antall oppgaver løst: " + antOppg;
        return print;
    }
}