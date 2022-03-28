package storage;

import application.model.*;

import java.util.ArrayList;

public class Storage {

    private static ArrayList<Salg> salgs = new ArrayList<>();
    private static ArrayList<Produkt> produkter = new ArrayList<>();
    private static ArrayList<Udlejning> udlejninger = new ArrayList<>();
    private static ArrayList<ProduktGruppe> produktGruppes = new ArrayList<>();
    private static ArrayList<Prisliste> prislister = new ArrayList<>();

    //------------------------------------------------------------------------------------------------------------------
    public static ArrayList<Salg> getSalg(){
        return new ArrayList<Salg>(salgs);
    }
    public static void addSalg(Salg salg){salgs.add(salg);}
    public static void removeSalg(Salg salg){salgs.remove(salg);}

    //------------------------------------------------------------------------------------------------------------------
    public static ArrayList<Produkt> getProdukter(){return new ArrayList<Produkt>(produkter);}
    public static void addProdukt(Produkt produkt){produkter.add(produkt);}
    public static void removeProdukt(Produkt produkt){produkter.remove(produkt);}

    //------------------------------------------------------------------------------------------------------------------
    public static ArrayList<Udlejning> getUdlejninger(){
        return new ArrayList<Udlejning>(udlejninger);
    }
    public static void addUdlejning(Udlejning udlejning){udlejninger.add(udlejning);}
    public static void removeUdlejning(Udlejning udlejning){udlejninger.remove(udlejning);}

    //------------------------------------------------------------------------------------------------------------------
    public static ArrayList<ProduktGruppe> getProduktGrupper(){return new ArrayList<ProduktGruppe>(produktGruppes);}
    public static void addProduktGruppe(ProduktGruppe produktGruppe){produktGruppes.add(produktGruppe);}
    public static void removeProduktGruppe(ProduktGruppe produktGruppe){produkter.remove(produktGruppe);}

    //------------------------------------------------------------------------------------------------------------------
    public static ArrayList<Prisliste> getPrislister(){return new ArrayList<Prisliste>(prislister);}
    public static void addPrisliste(Prisliste prisliste){prislister.add(prisliste);}
    public static void removePrisliste(Prisliste prisliste){prislister.remove(prisliste);}



}
