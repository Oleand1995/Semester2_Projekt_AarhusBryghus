package application.controller;

import application.model.*;
import javafx.collections.ObservableList;
import storage.Storage;

import javax.naming.ldap.Control;
import java.net.PortUnreachableException;
import java.net.SocketImpl;
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

    public static void removeUdlejning(Udlejning udlejning){
        Storage.removeUdlejning(udlejning);
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

    public static int getSamletKlip(ObservableList<OrdreLinje> ordreLinjer){
        int samletKlip = 0;
        for (OrdreLinje o : ordreLinjer){
            samletKlip += o.getPris().getKlip() * o.getAntal();
        }
        return samletKlip;
    }

    public static Pris createPrisOgKlip(double pris, Produkt produkt, int klipPris,Prisliste prisliste){
        Pris prisClass = prisliste.createPrisOgKlip(pris,produkt ,klipPris);
        return prisClass;
    }

    public static Pris createPris (double pris, Produkt produkt,Prisliste prisliste){
        Pris prisClass = prisliste.createPris(pris,produkt);
        return prisClass;
    }

    public static void sletPrisEllerPrisOgKlip(Prisliste prisliste, Pris pris){
        prisliste.removePris(pris);
    }

    public static void setPrisOgKlipForProdukt(Pris pris,double varePris,int klipPris){
        pris.setPris(varePris);
        pris.setKlip(klipPris);
    }

    public static void ændreKlasseFraPrisTilPrisOgKlip(Prisliste prisliste,Pris pris,double prisPåvare,int klipPris,Produkt produkt){
        sletPrisEllerPrisOgKlip(prisliste,pris);
        prisliste.createPrisOgKlip(prisPåvare,produkt,klipPris);
    }



    public static void initStorage(){

        // Produktgrupper:

        ProduktGruppe flaske = Controller.createproduktGruppe("Flaske");
        ProduktGruppe fadoel = Controller.createproduktGruppe("Fadøl");
        ProduktGruppe spiritus = Controller.createproduktGruppe("Spiritus");
        ProduktGruppe fustage = Controller.createproduktGruppe("Fustage");
        ProduktGruppe kulsyre = Controller.createproduktGruppe("Kulsyre");
        ProduktGruppe malt = Controller.createproduktGruppe("Malt");
        ProduktGruppe beklaedning = Controller.createproduktGruppe("Beklædning");
        ProduktGruppe anlaeg = Controller.createproduktGruppe("Anlæg");
        ProduktGruppe glas = Controller.createproduktGruppe("Glas");
        ProduktGruppe sampakning = Controller.createproduktGruppe("Sampakning");
        ProduktGruppe sodavand = Controller.createproduktGruppe("Sodavand");
        ProduktGruppe snacks = Controller.createproduktGruppe("Snacks");
        ProduktGruppe rundvisning = Controller.createproduktGruppe("Rundvisning");

        // Produkter under flaske:

        Produkt klosterbryg = Controller.createProdukt("Klosterbryg", flaske);
        Produkt sweetGB = Controller.createProdukt("Sweet Georgia Brown", flaske);
        Produkt extraPilsner = Controller.createProdukt("Extra Pilsner", flaske);
        Produkt celebration = Controller.createProdukt("Celebration", flaske);
        Produkt blondie = Controller.createProdukt("Blondie", flaske);
        Produkt foraarsbryg = Controller.createProdukt("Forårsbryg", flaske);
        Produkt indianPA = Controller.createProdukt("Indian Pale Ale", flaske);
        Produkt julebryg = Controller.createProdukt("Julebryg", flaske);
        Produkt juletoenden = Controller.createProdukt("Juletønden", flaske);
        Produkt oldSA = Controller.createProdukt("Old Strong Ale", flaske);
        Produkt fregattenJylland = Controller.createProdukt("Fregatten Jylland", flaske);
        Produkt imperialStout = Controller.createProdukt("Imperial Stout", flaske);
        Produkt tribute = Controller.createProdukt("Tribute", flaske);
        Produkt blackMonster = Controller.createProdukt("Black Monster", flaske);

        // Produkter under fadøl:


        Produkt klosterbrygFadoel = Controller.createProdukt("Klosterbryg, 40 cl", fadoel);
        Produkt jazzClassicFadoel = Controller.createProdukt("Jazz Classic, 40 cl", fadoel);
        Produkt extraPilsnerFadoel = Controller.createProdukt("Extra Pilsner, 40 cl", fadoel);
        Produkt celebrationFadoel = Controller.createProdukt("Celebration, 40 cl", fadoel);
        Produkt blondieFadoel = Controller.createProdukt("Blondie, 40 cl", fadoel);
        Produkt foraarsbrygFadoel = Controller.createProdukt("Forårsbryg, 40 cl", fadoel);
        Produkt indianPAFadoel = Controller.createProdukt("Indian Pale Ale, 40 cl", fadoel);
        Produkt julebrygFadoel = Controller.createProdukt("Julebryg, 40 cl", fadoel);
        Produkt imperialStoutFadoel = Controller.createProdukt("Imperial Stout, 40 cl", fadoel);
        Produkt specialFadoel = Controller.createProdukt("Special, 40 cl", fadoel);

        // Produkter under sodavand:

        Produkt aeblebrus = Controller.createProdukt("Æblebrus", sodavand);
        Produkt cola = Controller.createProdukt("Cola", sodavand);
        Produkt nikoline = Controller.createProdukt("Nikoline", sodavand);
        Produkt sevenUp = Controller.createProdukt("7-Up", sodavand);
        Produkt vand = Controller.createProdukt("Vand", sodavand);

        // Produkter under snacks:

        Produkt chips = Controller.createProdukt("Chips", snacks);
        Produkt peanuts = Controller.createProdukt("Peanuts", snacks);
        Produkt oelpoelser = Controller.createProdukt("Ølpølser", snacks);

        // Produkter under spiritus:

        Produkt whisky = Controller.createProdukt("Whisky 45%, 50 cl rør", spiritus);
        Produkt whiskyFireCL = Controller.createProdukt("Whisky, 4 cl", spiritus);
        Produkt whiskyCL = Controller.createProdukt("Whisky 43%, 50 cl rør", spiritus);
        Produkt egesplint = Controller.createProdukt("u/ egesplint", spiritus);
        Produkt egesplintM = Controller.createProdukt("m/ egesplint", spiritus);
        Produkt wGB = Controller.createProdukt("2 gange Whisky, Glas + Brikker", spiritus);
        Produkt liqourOA = Controller.createProdukt("Liquor of Aarhus", spiritus);
        Produkt lyngGin = Controller.createProdukt("Lyng Gin, 50 cl", spiritus);
        Produkt lynGinFireCl = Controller.createProdukt("Lyng Gin, 4 cl", spiritus);

        // Produkter under fustage:

        Produkt klosterbrygFustage = Controller.createProdukt("Klosterbryg, 20 liter", fustage);
        Produkt jazzClassicFustage = Controller.createProdukt("Jazz Classic, 25 liter", fustage);
        Produkt extraPilsnerFustage = Controller.createProdukt("Extra Pilsner, 25 liter", fustage);
        Produkt celebrationFustage = Controller.createProdukt("Celebration, 20 liter", fustage);
        Produkt blondieFustage = Controller.createProdukt("Blondie, 25 liter", fustage);
        Produkt foraarsbrygFustage = Controller.createProdukt("Forårsbryg, 20 liter", fustage);
        Produkt indianPAFustage = Controller.createProdukt("Indian Pale Ale, 20 liter", fustage);
        Produkt julebrygFustage = Controller.createProdukt("Julebryg, 20 liter", fustage);
        Produkt imperialStountFustage = Controller.createProdukt("Imperial Stout, 20 liter", fustage);
        Produkt pant = Controller.createProdukt("Pant", fustage);

        // Produkter under Kulsyre

        Produkt kulsyreSeksKg = Controller.createProdukt("Kulsyre, 6 kg", kulsyre);
        Produkt kulsyrePant = Controller.createProdukt("Pant", kulsyre);
        Produkt kulsyreFireKg = Controller.createProdukt("Kulsyre, 4 kg", kulsyre);
        Produkt kulsyreTiKg = Controller.createProdukt("Kulsyre, 10 kg", kulsyre);

        // Produkter under Malt:

        Produkt maltSaek = Controller.createProdukt("Malt, 25 kg sæk", malt);

        // Produkter under Beklædning:

        Produkt tshirt = Controller.createProdukt("T-Shirt", beklaedning);
        Produkt polo = Controller.createProdukt("Polo", beklaedning);
        Produkt cap = Controller.createProdukt("Cap", beklaedning);

        // Produkter under Anlæg:

        Produkt enHane = Controller.createProdukt("1-hane", anlaeg);
        Produkt toHane = Controller.createProdukt("2-hane", anlaeg);
        Produkt flereHaner = Controller.createProdukt("Bar med flere haner", anlaeg);
        Produkt levering = Controller.createProdukt("Levering", anlaeg);
        Produkt krus = Controller.createProdukt("Krus", anlaeg);

        // Produkter under Glas:

        Produkt uanset = Controller.createProdukt("Uanset Størrelse", glas);

        // Produkter under sampakning:

        Produkt gaveEt = Controller.createProdukt("Gaveæske, 2 øl, 2 glas", sampakning);
        Produkt gaveTo = Controller.createProdukt("Gaveæske, 4 øl", sampakning);
        Produkt traekasse = Controller.createProdukt("Trækasse, 6 øl", sampakning);
        Produkt gavekurv = Controller.createProdukt("Gavekurv, 6 øl, 2 glas", sampakning);
        Produkt traekasseTo = Controller.createProdukt("Trækasse, 6 øl, 6 glas", sampakning);
        Produkt traekasseTre = Controller.createProdukt("Trækasse, 12 øl", sampakning);
        Produkt papkasse = Controller.createProdukt("Papkasse, 12 øl", sampakning);

        // Produkt under Rundvisning:

        Produkt rundvisninger = Controller.createProdukt("Pr. Person dag", rundvisning);

        // Prisliste i Bar:

        Prisliste bar = Controller.createPrisliste("Bar");

        // Flaske i Bar:

        bar.createPrisOgKlip(70, klosterbryg, 2);
        bar.createPrisOgKlip(70, sweetGB, 2);
        bar.createPrisOgKlip(70, extraPilsner, 2);
        bar.createPrisOgKlip(70, celebration, 2);
        bar.createPrisOgKlip(70, blondie, 2);
        bar.createPrisOgKlip(70, foraarsbryg, 2);
        bar.createPrisOgKlip(70, indianPA, 2);
        bar.createPrisOgKlip(70, julebryg, 2);
        bar.createPrisOgKlip(70, juletoenden, 2);
        bar.createPrisOgKlip(70, oldSA, 2);
        bar.createPrisOgKlip(70, fregattenJylland, 2);
        bar.createPrisOgKlip(70, imperialStout, 2);
        bar.createPrisOgKlip(70, tribute, 2);
        bar.createPrisOgKlip(100, blackMonster, 3);

        // Fadøl i Bar:

        bar.createPrisOgKlip(38, klosterbrygFadoel, 1);
        bar.createPrisOgKlip(38, jazzClassicFadoel, 1);
        bar.createPrisOgKlip(38, extraPilsnerFadoel, 1);
        bar.createPrisOgKlip(38, celebrationFadoel, 1);
        bar.createPrisOgKlip(38, blondieFadoel, 1);
        bar.createPrisOgKlip(38, foraarsbrygFadoel, 1);
        bar.createPrisOgKlip(38, indianPAFadoel, 1);
        bar.createPrisOgKlip(38, julebrygFadoel, 1);
        bar.createPrisOgKlip(38, imperialStoutFadoel, 1);
        bar.createPrisOgKlip(38, specialFadoel, 1);

        // Sodavand i Bar:

        bar.createPris(15, aeblebrus);
        bar.createPris(15, cola);
        bar.createPris(15, nikoline);
        bar.createPris(15, sevenUp);
        bar.createPris(10, vand);

        // Snacks i Bar:

        bar.createPris(10, chips);
        bar.createPris(15, peanuts);
        bar.createPrisOgKlip(30, oelpoelser, 1);

        // Spiritus i Bar:

        bar.createPris(599, whisky);
        bar.createPris(50, whiskyFireCL);
        bar.createPris(499, whiskyCL);
        bar.createPris(300, egesplint);
        bar.createPris(350, egesplintM);
        bar.createPris(80, wGB);
        bar.createPris(175, liqourOA);
        bar.createPris(350, lyngGin);
        bar.createPris(40, lynGinFireCl);

        // Kulsyre i Bar:

        bar.createPris(400, kulsyreSeksKg);
        bar.createPris(1000, kulsyrePant);

        // Beklædning i Bar:

        bar.createPris(70, tshirt);
        bar.createPris(100, polo);
        bar.createPris(30, cap);

        // Sampakninger i Bar

        bar.createPris(110, gaveEt);
        bar.createPris(140,gaveTo);
        bar.createPris(260, traekasse);
        bar.createPris(260, gavekurv);
        bar.createPris(350, traekasseTo);
        bar.createPris(410, traekasseTre);
        bar.createPris(370, papkasse);

        // Prisliste i Butik:

        Prisliste butik = Controller.createPrisliste("Butik");

        // Flaske i Butik:

        butik.createPris(36, klosterbryg);
        butik.createPris(36, sweetGB);
        butik.createPris(36, extraPilsner);
        butik.createPris(36, celebration);
        butik.createPris(36, blondie);
        butik.createPris(36, foraarsbryg);
        butik.createPris(36, indianPA);
        butik.createPris(36, julebryg);
        butik.createPris(36, juletoenden);
        butik.createPris(36, oldSA);
        butik.createPris(36, fregattenJylland);
        butik.createPris(36, imperialStout);
        butik.createPris(36, tribute);
        butik.createPris(60, blackMonster);

        // Spiritus i Butik:

        butik.createPris(599, whisky);
        butik.createPris(499, whiskyCL);
        butik.createPris(300, egesplint);
        butik.createPris(350, egesplintM);
        butik.createPris(80, wGB);
        butik.createPris(175, liqourOA);
        butik.createPris(350, lyngGin);

        // Fustage i Butik:

        butik.createPris(775, klosterbrygFustage);
        butik.createPris(625, jazzClassicFustage);
        butik.createPris(575, extraPilsnerFustage);
        butik.createPris(775, celebrationFustage);
        butik.createPris(700, blondieFustage);
        butik.createPris(775, foraarsbrygFustage);
        butik.createPris(775, indianPAFustage);
        butik.createPris(775, julebrygFustage);
        butik.createPris(775, imperialStountFustage);
        butik.createPris(200, pant);

        // Kulsyre i Butik:

        butik.createPris(400, kulsyreSeksKg);
        butik.createPris(1000, kulsyrePant);

        // Malt i Butik:

        butik.createPris(300, maltSaek);

        // Beklædning i Butik:

        butik.createPris(70, tshirt);
        butik.createPris(100, polo);
        butik.createPris(30, cap);

        // Anlæg i Butik:

        butik.createPris(250, enHane);
        butik.createPris(400, toHane);
        butik.createPris(500, flereHaner);
        butik.createPris(500, levering);
        butik.createPris(60, krus);

        // Glas i Butik:

        butik.createPris(15, uanset);

        // Sampakning i Butik:

        butik.createPris(110, gaveEt);
        butik.createPris(140, gaveTo);
        butik.createPris(260, traekasse);
        butik.createPris(260, gavekurv);
        butik.createPris(350, traekasseTo);
        butik.createPris(410, traekasseTre);
        butik.createPris(370, papkasse);

        // Rundvisning i Butik:

        butik.createPris(100, rundvisninger);

    }

}
