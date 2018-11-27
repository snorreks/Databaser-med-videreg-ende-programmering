package Øvelse1;

import static javax.swing.JOptionPane.*;

public class Klient {
    public static void main(String[] args) {

        String restaurantNavn = showInputDialog(null, "Skriv inn navnet på restauranten ", "Restaurant", QUESTION_MESSAGE);
        String yearLest = showInputDialog(null, "Skriv inn etableringsår", "Restaurant " + restaurantNavn, QUESTION_MESSAGE);
        int year = Integer.parseInt(yearLest);
        String bordLest = showInputDialog(null, "Skriv inn antall bord", "Restaurant " + restaurantNavn, QUESTION_MESSAGE);
        int bord = Integer.parseInt(bordLest);

        Restaurant restaurant = new Restaurant(restaurantNavn, year, bord);

        boolean run = true;

        do {
            String[] options = new String[]{"Reserver bord", "Finn bordnr", "Frigi bord", "Skriv ut info", "Avslutt"};
            int valg = showOptionDialog(null, "Velg oppgave:", "Valuta kalkulator", DEFAULT_OPTION, PLAIN_MESSAGE, null, options, options[0]);
            if (valg == 4 || valg == -1) run = false;

            switch (valg) {

                case 0:  // reservere et antall bord på et bestemt navn
                    String navn = showInputDialog(null, "Skriv inn navnet på personen ", "Reserver bord", QUESTION_MESSAGE);
                    String antallBordLest = showInputDialog(null, "Skriv inn bord nummerene uten mellomrom", "Eks: 25-7-1: bord nr 25, 7 og 1", QUESTION_MESSAGE);
                    int[] antBord = restaurant.inputTilTabell(antallBordLest);
                    if (restaurant.reserver(navn, antBord)) showMessageDialog(null, "Vellykket");
                    else showMessageDialog(null, "Ugylidg bordnr eller bordet er tatt fra før");
                    break;

                case 1:  // finne alle bordene som er reservert p� et bestemt navn
                    String navnP = showInputDialog(null, "Skriv inn navnet på personen ", "Reserver bord", QUESTION_MESSAGE);
                    int[] tab = restaurant.bordPerson(navnP);

                    String print = navnP + " har reservert bordene: \n";
                    //print += tab[1];

                    for (int i = 0; i < tab.length; i++) {
                        print += (tab[i] + 1) + ", ";
                    }

                    showMessageDialog(null, print);
                    break;

                case 2:  // frigi en rekke bord, bordnummer er gitt
                    String bordNr = showInputDialog(null, "Skriv inn bordnummerene som skal frigis", "Eks: 524", QUESTION_MESSAGE);
                    int[] tab1 = restaurant.inputTilTabell(bordNr);
                    restaurant.frigiBord(tab1);
                    break;

                case 3:
                    showMessageDialog(null, restaurant.toString());
                    break;
            }

        } while (run);
    }
}
