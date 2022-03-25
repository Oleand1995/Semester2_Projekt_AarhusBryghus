package storage;

import application.model.Produkt;
import application.model.Salg;
import application.model.Udlejning;

import java.util.ArrayList;

public class Storage {

    private static ArrayList<Salg> salg = new ArrayList<>();
    private static ArrayList<Produkt> produkter = new ArrayList<>();
    private static ArrayList<Udlejning> udlejninger = new ArrayList<>();

    public static ArrayList<Salg> getSalg(){
        return new ArrayList<Salg>(salg);
    }

    public static ArrayList<Produkt> getProdukter(){
        return new ArrayList<Produkt>(produkter);
    }

    public static ArrayList<Udlejning> getUdlejninger(){
        return new ArrayList<Udlejning>(udlejninger);
    }

}
