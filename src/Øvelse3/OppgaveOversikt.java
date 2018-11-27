package Øvelse3;

public class OppgaveOversikt {

    private Student[] studenter = new Student[5];
    private int antStud = 0;

    public boolean regNyStudent(String studentNavn) {
        for (int i = 0; i < antStud; i++) {
            if (studenter[i].getNavn().equals(studentNavn)) return false;
        }
        if (antStud >= studenter.length) {
            Student[] nyTab = new Student[studenter.length + 5];
            for (int i = 0; i < antStud; i++) {
                nyTab[i] = studenter[i];
            }
            studenter = nyTab;
        }

        studenter[antStud] = new Student(studentNavn, 0);
        antStud++;
        return true;
    }

    public int finnAntStud() {
        return antStud;
    }

    public int antOppgStud(String studNavn) {
        for (int i = 0; i < antStud; i++) {
            if (studenter[i].getNavn().equals(studNavn)) {
                return studenter[i].getAntOppg();
            }
        }
        return -1;
    }

    public boolean incOppg(String studNavn, int antall) {
        for (int i = 0; i < antStud; i++) {
            if (studenter[i].getNavn().equals(studNavn)) {

                studenter[i].addAntOppg(antall);
                return true;
            }
        }
        return false;
    }

    public String[] finnAlleNavn() {
        String[] tab = new String[antStud];
        for (int i = 0; i < antStud; i++) {
            tab[i] = studenter[i].getNavn();
        }
        return tab;
    }

    public String toString() {
        String print = "Her kommer informasjon om alle studentene:\n";
        for (int i = 0; i < antStud; i++) {
            print += studenter[i].toString() + "\n";
        }
        return print;
    }
}


