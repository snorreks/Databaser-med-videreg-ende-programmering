package Øvelse3;

import java.util.ArrayList;

public class OppgaveOversiktArray {

    private ArrayList<Student> studenter = new ArrayList<Student>();
    private int antStud = 0;

    public boolean regNyStudent(String studentNavn) {
        for (int i = 0; i < studenter.size(); i++) {
            if (studenter.get(i).getNavn().equals(studentNavn)) {
                return false;
            }
        }

        studenter.add(new Student(studentNavn, 0));
        antStud++;
        return true;
    }

    public int finnAntStud() {
        return antStud;
    }

    public int antOppgStud(String studNavn) {
        for (int i = 0; i < antStud; i++) {
            if (studenter.get(i).getNavn().equals(studNavn)) {
                return studenter.get(i).getAntOppg();
            }
        }
        return -1;
    }

    public boolean incOppg(String studNavn, int antall) {
        for (int i = 0; i < antStud; i++) {
            if (studenter.get(i).getNavn().equals(studNavn)) {
                studenter.get(i).setAntOppg(antall);
                return true;
            }
        }
        return false;
    }

    public String[] finnAlleNavn() {
        String[] tab = new String[antStud];
        for (int i = 0; i < antStud; i++) {
            tab[i] = studenter.get(i).getNavn();
        }
        return tab;
    }

    public String toString() {
        String print = "Her kommer informasjon om alle studentene:\n";
        for (int i = 0; i < antStud; i++) {
            print += studenter.get(i).toString() + "\n";
        }
        return print;
    }
}


