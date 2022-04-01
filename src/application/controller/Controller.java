package application.controller;

import application.model.*;
import javafx.collections.ObservableList;
import storage.Storage;

import javax.naming.ldap.Control;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Controller {


    public static Salg createSalg(LocalDateTime salgsTidspunkt, ArrayList<OrdreLinje> ordreLinjer, int samletKlip, double samletPris){
        Salg salg = new Salg(salgsTidspunkt, ordreLinjer, samletPris, samletKlip);
        Storage.addSalg(salg);
        return salg;
    }

    public static ArrayList<Salg> getSalg(){return new ArrayList<>(Storage.getSalg());}

    public static Udlejning createUdlejning(LocalDateTime udlejningsTidspunkt, double samletPris, String lejersNavn, ArrayList<OrdreLinje> ordrelinjer){
        Udlejning udlejning = new Udlejning(udlejningsTidspunkt,null,samletPris,lejersNavn,ordrelinjer);
        Storage.addUdlejning(udlejning);
        return udlejning;
    }

    public static ArrayList<Udlejning> getUdlejninger(){return new ArrayList<>(Storage.getUdlejninger());}

    public static OrdreLinje createOrdreLinje(Pris pris){
        return new OrdreLinje(pris);
    }

    public static void setAntalPåOrdreLinje(OrdreLinje ordreLinje, int antal){
        ordreLinje.setAntal(antal);
    }


    public static ProduktGruppe createproduktGruppe(String produktType){
        ProduktGruppe produktGruppe = new ProduktGruppe(produktType);
        Storage.addProduktGruppe(produktGruppe);
        return produktGruppe;
    }

    public static ArrayList<ProduktGruppe> getProduktGrupper(){return Storage.getProduktGrupper();}

    public static Produkt createProdukt(String beskrivelse, ProduktGruppe produktGruppe){
        Produkt produkt = produktGruppe.createProdukt(beskrivelse);
        Storage.addProdukt(produkt);
        return produkt;
    }

    public static void sletProduktgruppe(ProduktGruppe produktGruppe){
        if (Storage.getProduktGrupper().contains(produktGruppe)){
            Storage.removeProduktGruppe(produktGruppe);
        }
    }

    public static ArrayList<Produkt> getProdukter(){
        ArrayList<Produkt> produkter = new ArrayList<>();
        for (ProduktGruppe pG : Storage.getProduktGrupper()){
            for (Produkt p : pG.getProdukter()){
                produkter.add(p);
            }
        }
        return produkter;
    }

    public static void sletProdukt(ProduktGruppe produktGruppe, Produkt produkt){
        produktGruppe.removeProdukt(produkt);
        Storage.removeProdukt(produkt);
    }

    public static Prisliste createPrisliste(String navn){
        Prisliste prisliste = new Prisliste(navn);
        Storage.addPrisliste(prisliste);
        return prisliste;
    }

    public static ArrayList<Prisliste> getPrislister(){return Storage.getPrislister();}



    public static void sletPrisliste(Prisliste prisliste){
        if (Storage.getPrislister().contains(prisliste)){
            Storage.removePrisliste(prisliste);
        }
    }

    public static double getSamletPris(ObservableList<OrdreLinje> ordreLinjer){
        double samletPris = 0;
        for (OrdreLinje o : ordreLinjer){
            samletPris += o.getPris().getPris() * o.getAntal();
        }
        return samletPris;
    }

    public static Pris createPrisOgKlip(double pris, Produkt produkt, int klipPris,Prisliste prisliste){
        Pris prisClass = prisliste.createPrisOgKlip(pris,produkt ,klipPris);
        return prisClass;
    }

    public static Pris createPris (double pris, Produkt produkt,Prisliste prisliste){
        Pris prisClass = prisliste.createPris(pris,produkt);
        return prisClass;
    }



    public static void initStorage(){
        ProduktGruppe fadoel = Controller.createproduktGruppe("Fadøl");
        Produkt storOel = Controller.createProdukt("Stor fed øl",fadoel);
        Produkt lilleOel = Controller.createProdukt("Lille bitte øl",fadoel);
        Produkt mellemOel = Controller.createProdukt("Semi øl",fadoel);

        Prisliste bar = Controller.createPrisliste("Bar");
        bar.createPris(20,lilleOel);
        bar.createPris(40,mellemOel);
        bar.createPris(60,storOel);

        Prisliste butik = Controller.createPrisliste("Butik");
        butik.createPris(55,storOel);

    }


}
