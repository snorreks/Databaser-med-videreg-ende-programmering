package Øvelse3;

import static javax.swing.JOptionPane.*;

class Meny {
    public final String NY_STUDENT = "Ny student";
    public final String AVSLUTT = "Avslutt";
    private String[] muligeValg = {NY_STUDENT, AVSLUTT};  // f�rste gang, ingen studenter registrert

    private OppgaveOversikt oversikt;

    public Meny(OppgaveOversikt oversikt) {
        this.oversikt = oversikt;
    }

    /**
     * Metoden leser inn valget som en streng, og returnerer den.
     * Valget skal være argument til metoden utførValgtOppgave().
     * Hvis programmet skal avsluttes, returneres null.
     */
    public String lesValg() {
        int antStud = oversikt.finnAntStud();
        String valg = (String) showInputDialog(null, "Velg fra listen, " + antStud + " studenter:", "Godkjente oppgaver",
                DEFAULT_OPTION, null, muligeValg, muligeValg[0]);
        if (AVSLUTT.equals(valg)) {
            valg = null;
        }
        return valg;
    }

    public void utførValgtOppgave(String valg) {
        if (valg != null && !valg.equals(AVSLUTT)) {
            if (valg.equals(NY_STUDENT)) {
                registrerNyStudent();
            } else {
                registrerOppgaver(valg);  // valg er navnet til studenten
            }
        }
    }

    /**
     * Metoden registrere ny student.
     * Hvis student med dette navnet allerede eksisterer, skjer ingen registrering.
     * Resultatet av operasjonen skrives ut til brukeren.
     */
    private void registrerNyStudent() {
        String navnNyStud = null;
        do {
            navnNyStud = showInputDialog("Oppgi navn: ");
        } while (navnNyStud == null);

        navnNyStud = navnNyStud.trim();
        if (oversikt.regNyStudent(navnNyStud)) {
            showMessageDialog(null, navnNyStud + " er registrert.");
            String[] alleNavn = oversikt.finnAlleNavn();
            String[] nyMuligeValg = new String[alleNavn.length + 2];
            for (int i = 0; i < alleNavn.length; i++) {
                nyMuligeValg[i] = alleNavn[i];
            }
            nyMuligeValg[alleNavn.length] = NY_STUDENT;
            nyMuligeValg[alleNavn.length + 1] = AVSLUTT;
            muligeValg = nyMuligeValg;
        } else {
            showMessageDialog(null, navnNyStud + " er allerede registrert.");
        }
    }

    private void registrerOppgaver(String studNavn) {
        String melding = "Oppgi antall nye oppgaver som skal godkjennes for " + studNavn + ": ";
        int antOppgøkning = 0;
        boolean registrert = false;
        do { // gjentar inntil registrering aksepteres av objektet oversikt
            try {
                antOppgøkning = lesHeltall(melding);
                oversikt.incOppg(studNavn, antOppgøkning);  // kan ikke returnere false, pga navn alltid gyldig
                registrert = true; // kommer hit bare dersom exception ikke blir kastet
            } catch (IllegalArgumentException e) {  // kommer hit hvis studenter f�r negativt antall oppgaver
                melding = "Du skrev " + antOppgøkning + ". \nIkke godkjent økning for " + studNavn + ". Prøv igjen: ";
            }
        } while (!registrert);

        melding = "Totalt antall oppgaver registrert på " + studNavn + " er " + oversikt.antOppgStud(studNavn) + ".";
        showMessageDialog(null, melding);
    }

    /* Hjelpemetode som g�r i l�kke inntil brukeren skriver et heltall. */
    private int lesHeltall(String melding) {
        int tall = 0;
        boolean ok = false;
        do {  // gjentar inntil brukerinput kan tolkes som tall
            String tallLest = showInputDialog(melding);
            try {
                tall = Integer.parseInt(tallLest);
                ok = true;
            } catch (Exception e) {
                showMessageDialog(null, "Kan ikke tolke det du skrev som tall. Prøv igjen. ");
            }
        } while (!ok);
        return tall;
    }
}


class Klient {
    public static void main(String[] args) {

        OppgaveOversikt oversikt = new OppgaveOversikt();
        Meny meny = new Meny(oversikt);
        String valg = meny.lesValg();
        while (valg != null) {
            meny.utførValgtOppgave(valg);
            valg = meny.lesValg();
        }
        System.out.println(oversikt);
    }
}