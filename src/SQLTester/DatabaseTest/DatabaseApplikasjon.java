package SQLTester.DatabaseTest; /**
 * DatabaseApplikasjon.java - "Programmering i Java", 4.utgave - 2009-07-01
 * <p>
 * Applikasjonen gjoor det mulig aa vedlikeholde tabellen person.
 * Alle data vises fram, deretter kan brukeren endre, slette eller legge inn nye data.
 * Endringene utfoores i databasen, og dersom det gikk bra, i vinduet.
 * Valget "Gjenoppfrisk" henter alle dataene paa nytt fra databasen.
 */

import mittBibliotek.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Enum-klasse som inneholder de aktuelle knappene.
 */
enum Knapp {
    Slett, Ny, Endre, Gjenoppfrisk;
    private JButton knapp;

    Knapp() {
        knapp = new JButton(this.name());
    }

    public JButton finnKnapp() {
        return knapp;
    }
}

/**
 * Brukergrensesnittet, dvs. vinduet med liste over navene,
 * og knapper for menyvalg.
 */
class DatabaseGUI extends JFrame {
    private Database databasen;  // settes i klientprogrammet

    private DefaultListModel<Person> listeinnhold = new DefaultListModel<Person>();
    private JList<Person> liste = new JList<Person>(listeinnhold);
    private PersonDialog persondialog = new PersonDialog(this);

    public DatabaseGUI(Database databasen) {
        this.databasen = databasen;
        setTitle("Navneregister");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new Vinduslytter());
        add(new OverskriftPanel("Vedlikehold av navneregister"),
                BorderLayout.NORTH);
        add(new ListePanel(), BorderLayout.CENTER);
        add(new KnappePanel(), BorderLayout.SOUTH);
        fyllListeMedData();  // se nedenfor
        persondialog.setLocation(300, 400);
        setLocation(300, 300);
        pack();
    }

    /* Hjelpemetode: Henter data fra databasen og fyller opp listen p� skjermen */
    private void fyllListeMedData() {
        listeinnhold.clear();
        ArrayList<Person> alle = databasen.finnAlle();
        if (alle != null) {
            for (Person enPerson : alle) {
                listeinnhold.addElement(enPerson);
            }
            if (listeinnhold.size() > 0) {
                liste.setSelectedIndex(0); // standard valg
            } else { // ingen data, kan ikke trykke p� knappene Endre og Slett
                Knapp.Slett.finnKnapp().setEnabled(false);
                Knapp.Endre.finnKnapp().setEnabled(false);
            }
        } else {
            showMessageDialog(DatabaseGUI.this, "F�r ikke hentet data fra databasen.");
        }
    }

    /*
     * N� kommer de tre panelene som beskriver brukergrensesnittet.
     */
    private class OverskriftPanel extends JPanel {
        public OverskriftPanel(String overskrift) {
            Font stdSkrift = getFont();
            Font storeBokstaver = new Font(stdSkrift.getName(), stdSkrift.getStyle(), 18);
            JLabel tekst = new JLabel(overskrift);
            tekst.setFont(storeBokstaver);
            add(tekst);
        }
    }

    private class ListePanel extends JPanel {
        public ListePanel() {
            liste.setPreferredSize(
                    new Dimension(300, 300)); // FlowLayout tar hensyn til dette
            liste.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            JScrollPane rullefeltMedListe = new JScrollPane(liste);

            /* Lager tabelloverskrift */
            JViewport jvp = new JViewport(); // se online API-dokumentasjonen
            jvp.setView(new JLabel("Registrerte navn"));
            rullefeltMedListe.setColumnHeader(jvp);

            add(rullefeltMedListe);
        }
    }

    private class KnappePanel extends JPanel {
        public KnappePanel() {
            Knappelytter lytteren = new Knappelytter();
            for (Knapp enKnapp : Knapp.values()) {
                enKnapp.finnKnapp().addActionListener(lytteren);
                add(enKnapp.finnKnapp());
            }
        }
    }

    /*
     * F�lgende klasse h�ndterer alle knappetrykkene:
     */
    private class Knappelytter implements ActionListener {
        public void actionPerformed(ActionEvent hendelse) {
            String kommando = hendelse.getActionCommand();
            Knapp valg = Knapp.valueOf(kommando);

            switch (valg) {
                case Slett:
                    Person personen = liste.getSelectedValue();
                    boolean slettet = databasen.slettPerson(personen.getPersonNr());
                    if (slettet) {
                        listeinnhold.remove(liste.getSelectedIndex());
                        if (listeinnhold.size() == 0) {
                            Knapp.Slett.finnKnapp().setEnabled(false);
                            Knapp.Endre.finnKnapp().setEnabled(false);
                        } else {
                            liste.setSelectedIndex(0);
                        }
                    } else {
                        showMessageDialog(DatabaseGUI.this,
                                "F�r ikke slettet personen. Kan v�re slettet av andre.");
                    }
                    break;
                case Endre:  // endrer et navn
                    personen = liste.getSelectedValue();
                    int indeks = liste.getSelectedIndex();
                    if (persondialog.visDialog(personen)) {  // navnet skal endres
                        if (databasen.endreNavn(personen)) {
                            listeinnhold.set(indeks, personen);  // bytter ut personen
                            liste.setSelectedIndex(0);
                        } else {
                            showMessageDialog(DatabaseGUI.this,
                                    "Ingen data endret. Kan v�re slettet av andre.");
                        }
                    }
                    break;
                case Ny:  // legger inn en ny person
                    personen = new Person(-1, "", "");
                    if (persondialog.visDialog(personen)) {
                        personen = databasen.registrerNyPerson(
                                personen.getFornavn(), personen.getEtternavn());
                        showMessageDialog(DatabaseGUI.this,
                                "Personen f�r nummer " + personen.getPersonNr());
                        listeinnhold.addElement(personen);
                        if (listeinnhold.size() == 1) { // f�rste personen som er lagt inn
                            Knapp.Slett.finnKnapp().setEnabled(true);
                            Knapp.Endre.finnKnapp().setEnabled(true);
                            liste.setSelectedIndex(0);
                        }
                    }
                    break;
                case Gjenoppfrisk:  // henter "friske" data fra databasen
                    fyllListeMedData();
                    break;
                default:
                    System.out.println("Hit skal ikke programmet komme!");
                    break;
            }
        }
    }

    private class Vinduslytter extends WindowAdapter {
        public void windowClosing(WindowEvent hendelse) {
            databasen.kobleNedForbindelse();  // NB!
            dispose();
            System.exit(0);
        }
    }
}

class DatabaseApplikasjon {
    public static void main(String[] args) {
        try {
            //String dbDriver = "org.apache.derby.jdbc.ClientDriver";
            String dbDriver = "com.mysql.jdbc.Driver";
            String dbNavn
                    //= "jdbc:derby://localhost:1527/personer;user=vprg;password=vprg";
                    = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/xxxxxxx?user=xxxxxxx&password=xxxxxxx";
            Database databasekontakt = new Database(dbDriver, dbNavn);
            DatabaseGUI applikasjonen = new DatabaseGUI(databasekontakt);
            applikasjonen.setVisible(true);
        } catch (Exception e) {
            showMessageDialog(null,
                    "F�r ikke koblet opp mot databasen. \nFeilmelding: " + e);
        }
    }
}