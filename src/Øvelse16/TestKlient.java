package Øvelse16;

import Øvelse16.dyrehage.Rovdyrfabrikk;
import Øvelse16.dyrehage.SkandinaviskeRovdyr;

import java.util.ArrayList;

public class TestKlient {
    public static void main(String[] args) {

        ArrayList<SkandinaviskeRovdyr> dyr = new ArrayList<>();
        Rovdyrfabrikk rovdyr = new Rovdyrfabrikk();


        dyr.add(rovdyr.nyBinne("Bjørnar", 2015, 2009, "Bjørnegate 2", 2));
        dyr.add(rovdyr.nyUlvehann("Ulvensen", 2017, 2016, "Ulvegata 4"));


        //test alle metodene:
        System.out.println("Bjørnar:    " + dyr.get(0).getNavn());
        System.out.println("2016:           " + dyr.get(1).getFdato());
        System.out.println("9:              " + dyr.get(0).getAlder());
        System.out.println("Ulvegata 4:     " + dyr.get(1).getAdresse());
        dyr.get(1).flytt("Ulvegata 5");
        System.out.println("Ulvegata 5:     " + dyr.get(1).getAdresse());

        //test kull:
        System.out.println("\nTest av kull:\n");
        System.out.println("2:              " + dyr.get(0).getAntKull());
        dyr.get(0).leggTilKull(2);
        System.out.println("4:              " + dyr.get(0).getAntKull());
        dyr.get(0).leggTilNyttKull();
        System.out.println("5:              " + dyr.get(0).getAntKull());

        System.out.println(dyr.get(1).skrivUtInfo());

        System.out.println(dyr.get(0).skrivUtInfo());
    }
}
