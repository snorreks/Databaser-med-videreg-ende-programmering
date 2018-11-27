package Øvelse4;

/*
Lag et klientprogram der brukeren kan legge inn data.
Sørg for at alle metodene i klassen Konferansesenter blir prøvd ut.
Bruk for eksempel følgende mal:
Begynn med å registrere alle rommene.
Les inn noen reservasjoner.
Skriv ut all registrert informasjon.
Bruk metodene som finner antall rom og rom gitt indeks.
Prøv ut metoden som finnet er rom, gitt romnummer.
La brukeren skrive inn romnummeret. All info om rommet,
inkludert reservasjonene skal skrives ut.
 */

import Meny.FlerBokser;
import Meny.Flervalg;

import static javax.swing.JOptionPane.*;

public class Klient {
    public static void main(String[] args) {


        String navn = showInputDialog(null, "Skriv navnet på konferansesenteret:");
        Konferansesenter konfsenter = new Konferansesenter(navn);

        FlerBokser romBokser = new FlerBokser(2);
        romBokser.fill("Rom nr: ");
        romBokser.fill("Størrelse på rommet: ");


        FlerBokser resBokser = new FlerBokser(5);
        resBokser.fill("Navn");
        resBokser.fill("Tlf");
        resBokser.fill("Ant personer");
        resBokser.fill("Start");
        resBokser.fill("Slutt");

        Flervalg flervalg = new Flervalg();


        boolean run = true;
        while (run) {
            String[] options = {"Reg ny rom", "Ant Rom", "Reserver rom", "Finn rom", "Skriv ut registrert", "Avslutt"};
            int valg = showOptionDialog(null, "Mulige valg: ", navn + "'s Konferansesenter", YES_NO_OPTION, INFORMATION_MESSAGE, null, options, options[0]);
            if (valg == -1 || valg == 5) run = false;
            switch (valg) {
                case (0): //reg ny rom
                    String[] svar = romBokser.show(navn + " Informasjon om rommet");
                    try {
                        if (konfsenter.regNyRom(Integer.parseInt(svar[0]), Integer.parseInt(svar[1]))) {
                            showMessageDialog(null, "Velykket");
                            flervalg.add(svar[0]);
                        } else showMessageDialog(null, "Rommet er registrert fra før");
                    } catch (NumberFormatException nfe) {
                        showMessageDialog(null, "Du skrev ikke inn tall");
                    }
                    break;
                case (1): //Finn ant rom
                    showMessageDialog(null, "Det er " + konfsenter.getAntRom() + " rom i " + navn + "'s Konferansesenter.");
                    break;
                case (2): // reserver rom
                    String[] resSvar = resBokser.show(navn + " Informasjon om reservasjonen");

                    if (konfsenter.reserverRom(resSvar[0], resSvar[1], Integer.parseInt(resSvar[2]),
                            new Tidspunkt(Long.parseLong(resSvar[3])), new Tidspunkt(Long.parseLong(resSvar[4])))) {
                        showMessageDialog(null, "Vellykket");
                    } else showMessageDialog(null, "Noe gikk galt");
                    break;
                case (3)://fin rom
                    String romSvar = flervalg.getAnswer("Velg rom", navn);
                    showMessageDialog(null, konfsenter.getRomNr(Integer.parseInt(romSvar)).printRes());
                    break;
                case (4): //skriv ut registeret
                    showMessageDialog(null, konfsenter);
                    break;
            }
        }
    }
}