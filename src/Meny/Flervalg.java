package Meny;

import javax.swing.*;
import java.util.ArrayList;

import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import static javax.swing.JOptionPane.showInputDialog;

public class Flervalg {

    private JFrame frame = new JFrame();
    private ArrayList<String> tab = new ArrayList<String>();
    private int counter = 0;

    public void add(String text) {
        tab.add(text);
    }

    public String getAnswer(String message, String title) {
        String[] nyTab = tab.toArray(new String[tab.size()]);
        return (String) showInputDialog(frame, message, title, QUESTION_MESSAGE, null, nyTab, nyTab[0]);
    }
}