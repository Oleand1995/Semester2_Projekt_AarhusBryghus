package application.controller;

import application.model.*;
import storage.Storage;

import javax.naming.ldap.Control;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Controller {


    public static Salg createSalg(LocalDateTime salgsTidspunkt){
        Salg salg = new Salg(salgsTidspunkt);
        Storage.addSalg(salg);
        return salg;
    }

    public static OrdreLinje createOrdreLinje(Salg salg,Produkt produkt, int antal, double pris){
        OrdreLinje ordreLinje = salg.createOrdrelinje(produkt,antal ,pris);
        return ordreLinje;
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



    public static void initStorage(){
        ProduktGruppe fadoel = Controller.createproduktGruppe("Fadøl");
        Produkt storOel = Controller.createProdukt("Stor fed øl",fadoel);
        Produkt lilleOel = Controller.createProdukt("Lille bitte øl",fadoel);
        Produkt mellemOel = Controller.createProdukt("Semi øl",fadoel);

        Prisliste bar = Controller.createPrisliste("Bar");
        bar.addPris(storOel, 60);
        bar.addPris(lilleOel, 20);
        bar.addPris(mellemOel,40);

        Prisliste butik = Controller.createPrisliste("Butik");
        butik.addPris(storOel, 55);

    }


}
