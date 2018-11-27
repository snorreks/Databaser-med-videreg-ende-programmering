package Meny;

import javax.swing.*;
import java.util.ArrayList;

public class FlerBokser extends JApplet {
    private int antBokser;
    private int size = 10;
    private JPanel myPanel = new JPanel();
    private int counter = 0;

    private ArrayList<JTextField> bokser = new ArrayList<JTextField>();

    public FlerBokser(int antBokser) {
        this.antBokser = antBokser;
        init();
    }

    public void init() {
        for (int i = 0; i < antBokser; i++) {
            bokser.add(new JTextField(size));
        }
    }

    public boolean fill(String navn) {
        if (counter >= antBokser) return false;
        myPanel.add(new JLabel(navn));
        myPanel.add(bokser.get(counter));
        myPanel.add(Box.createVerticalStrut(15));
        counter++;
        return true;
    }

    public String[] show(String message) {
        int result = JOptionPane.showConfirmDialog(null, myPanel, message, JOptionPane.OK_CANCEL_OPTION);
        if (result != JOptionPane.OK_OPTION) return null;
        String[] tab = new String[antBokser];
        for (int i = 0; i < antBokser; i++) {
            tab[i] = bokser.get(i).getText();
        }
        return tab;
    }
/*
Test
    public static void main(String[] args) {

        FlerBokser f = new FlerBokser(3);
        f.fill("x: ");
        f.fill("y: ");
        f.fill("z: ");
        String[] p = f.show("Skriv inn resultatet");

        JOptionPane.showMessageDialog(null,Arrays.toString(p));

    }
*/
}