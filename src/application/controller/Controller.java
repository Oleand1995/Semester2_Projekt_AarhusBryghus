package application.controller;

import application.model.*;
import javafx.collections.ObservableList;
import storage.Storage;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Controller {


    private static Controller controller;
    private Storage storage;

    public static Controller getController() {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }

    private Controller() {
        storage = Storage.getStorage();
    }

    //     Denne metode kræver at Storage constructoren ikke er privat den er kun
    //     til JUnit test
    public Controller getTestController() {
        Controller controller = new Controller();
        controller.storage = new Storage();
        return controller;
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------

    /**
     *
     * @param salgsTidspunkt
     * @param ordreLinjer
     * @param samletKlip
     * @param samletPris
     * @param betalingsmåde
     * pre: salgstidspunkt og betalingsmåde er ikke null
     * pre: samletKlip, samletPris > 0
     * pre: ordrelinjer er ikke tom
     * @return opretter og returnere et salg.
     */
    public Salg createSalg(LocalDateTime salgsTidspunkt, ArrayList<OrdreLinje> ordreLinjer, int samletKlip, double samletPris, Betalingsmåder betalingsmåde){
        Salg salg = new Salg(salgsTidspunkt, ordreLinjer, samletPris, samletKlip, betalingsmåde);
        storage.addSalg(salg);
        return salg;
    }

    public ArrayList<Salg> getSalg(){return new ArrayList<>(storage.getSalg());}

    public void removeSalg(Salg salg){storage.removeSalg(salg);}
    //-------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * @param udlejningsTidspunkt
     * @param samletPris
     * @param lejersNavn
     * @param ordrelinjer
     * pre: udlejningstidspunkt, samletPris og lejersNavn er ikke null
     * pre: ordrelinjer er ikke tom.
     * @return opretter og returnere en udlejning
     */
    public Udlejning createUdlejning(LocalDateTime udlejningsTidspunkt, double samletPris, String lejersNavn, ArrayList<OrdreLinje> ordrelinjer){
        Udlejning udlejning = new Udlejning(udlejningsTidspunkt,null,samletPris,lejersNavn,ordrelinjer);
        storage.addUdlejning(udlejning);
        return udlejning;
    }

    public void removeUdlejning(Udlejning udlejning){
        storage.removeUdlejning(udlejning);
    }

    public ArrayList<Udlejning> getUdlejninger(){return new ArrayList<>(storage.getUdlejninger());}

    public void setAfrejningstidpunkt(Udlejning udlejning){
        udlejning.setAfregningsTidspunkt(LocalDateTime.now());
    }

    public void setSamletPrisPåUdlejning(Udlejning udlejning, ObservableList<OrdreLinje> ordreLinjer){
        udlejning.setSamletPris(getSamletPris(ordreLinjer));
    }

    /**
     * @return henter og returnere alle udlejninger som er igangværende fra storage.
     * Bestemmes på om udlejnings attributten 'afregningstidspunkt' er null.
     */
    public ArrayList<Udlejning> getAktiveUdlejninger(){
        ArrayList<Udlejning> udlejninger = new ArrayList<>();
        for (Udlejning u : storage.getUdlejninger()){
            if (u.getAfregningsTidspunkt() == null){
                udlejninger.add(u);
            }
        }
        return udlejninger;
    }

    /**
     * @return henter og returnere alle udlejninger som er afsluttede fra storage.
     * Bestemmes på om udlejnings attributten 'afregningstidspunkt' er null.
     */
    public ArrayList<Udlejning> getAfsluttedeUdlejninger(){
        ArrayList<Udlejning> udlejninger = new ArrayList<>();
        for (Udlejning u : storage.getUdlejninger()){
            if (u.getAfregningsTidspunkt() != null){
                udlejninger.add(u);
            }
        }
        return udlejninger;
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * @param pris
     * pre: pris er ikke null
     * @return returnere en ny ordrelinje
     */
    public OrdreLinje createOrdreLinje(Pris pris){
        return new OrdreLinje(pris);
    }

    /**
     * Opdatere ordrelinjens antal.
     * @param ordreLinje
     * @param antal
     * pre: ordrelinje er ikke null.
     * pre: antal > 0.
     */
    public void setAntalPåOrdreLinje(OrdreLinje ordreLinje, int antal){
        ordreLinje.setAntal(antal);
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * @param produktType
     * pre: produktType er ikke null
     * @return opretter og returnere produktGruppe
     */
    public ProduktGruppe createproduktGruppe(String produktType){
        ProduktGruppe produktGruppe = new ProduktGruppe(produktType);
        storage.addProduktGruppe(produktGruppe);
        return produktGruppe;
    }

    public ArrayList<ProduktGruppe> getProduktGrupper(){return storage.getProduktGrupper();}

    public void sletProduktgruppe(ProduktGruppe produktGruppe){
        if (storage.getProduktGrupper().contains(produktGruppe)){
            storage.removeProduktGruppe(produktGruppe);
        }
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------

    /**
     *
     * @param beskrivelse
     * @param produktGruppe
     * pre: beskrivelse og produktGruppe er ikke null
     * @return opretter og returnerer et produkt
     */
    public Produkt createProdukt(String beskrivelse, ProduktGruppe produktGruppe){
        Produkt produkt = produktGruppe.createProdukt(beskrivelse);
        return produkt;
    }

    /**
     *
     * @return returnere alle produkter, for alle produktgrupper i storage.
     */
    public ArrayList<Produkt> getProdukter(){
        ArrayList<Produkt> produkter = new ArrayList<>();
        for (ProduktGruppe pG : storage.getProduktGrupper()){
            for (Produkt p : pG.getProdukter()){
                produkter.add(p);
            }
        }
        return produkter;
    }

    public void sletProdukt(ProduktGruppe produktGruppe, Produkt produkt){
        produktGruppe.removeProdukt(produkt);
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * @param navn
     * pre: navn må ikke være null
     * @return opretter og returnere en prisliste.
     */
    public Prisliste createPrisliste(String navn){
        Prisliste prisliste = new Prisliste(navn);
        storage.addPrisliste(prisliste);
        return prisliste;
    }

    public ArrayList<Prisliste> getPrislister(){return storage.getPrislister();}


    public void sletPrisliste(Prisliste prisliste){
        if (storage.getPrislister().contains(prisliste)){
            storage.removePrisliste(prisliste);
        }
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Tager en observable list fra f.eks. et listview.
     * Den tjekker også om ordrelinjerne har tilknyttet rabat.
     * @param ordreLinjer
     * @return beregner og returnere en samlet pris.
     */
    public double getSamletPris(ObservableList<OrdreLinje> ordreLinjer){
        double samletPris = 0;
        for (OrdreLinje o : ordreLinjer){
            if (o.getRabatBeregning() != null){
                samletPris += o.getRabatBeregning().getRabat((o.getPris().getPris()) * o.getAntal());
            }else{
                samletPris += o.getPris().getPris() * o.getAntal();
            }
        }
        return samletPris;
    }

    /**
     * Tager en observable list fra f.eks. et listview.
     * Den tjekker også om ordrelinjerne har tilknyttet rabat.
     * @param ordreLinjer
     * @return beregner og returnere en samlet pris
     */
    public int getSamletKlip(ObservableList<OrdreLinje> ordreLinjer){
        int samletKlip = 0;

        for (OrdreLinje o : ordreLinjer){
            samletKlip += o.getPris().getKlip() * o.getAntal();

        }
        return samletKlip;
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * @param pris
     * @param produkt
     * @param klipPris
     * @param prisliste
     * pre: pris, klippris > 0.
     * pre: produkt og prisliste er ikke null
     * @return opretter og returnerer en prisOgKlip
     */
    public Pris createPrisOgKlip(double pris, Produkt produkt, int klipPris,Prisliste prisliste){
        Pris prisOgKlip = prisliste.createPrisOgKlip(pris,produkt ,klipPris);
        return prisOgKlip;
    }

    /**
     * @param pris
     * @param produkt
     * @param prisliste
     * pre: pris > 0
     * pre: produkt og prislite er ikke nullæ
     * @return opretter og returnerer en pris
     */
    public Pris createPris (double pris, Produkt produkt,Prisliste prisliste){
        Pris prisUdenKlip = prisliste.createPris(pris,produkt);
        return prisUdenKlip;
    }

    public void sletPrisEllerPrisOgKlip(Prisliste prisliste, Pris pris){
        prisliste.removePris(pris);
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * @param ordreLinje
     * @param rabatProcent
     * pre: ordrelinje er ikke null
     * pre: 0 <= rabatProcent <= 100
     * @return opretter, tilføjer til ordreLinje og returnerer en ProcentRabat
     */
    public RabatBeregning tilføjProcentRabatTilOrdrelinje(OrdreLinje ordreLinje,double rabatProcent){
        RabatBeregning procentRabat = new ProcentRabat(rabatProcent);
        ordreLinje.setRabatBeregning(procentRabat);
        return procentRabat;
    }

    /**
     * @param ordreLinje
     * @param fastRabatPris
     * pre: ordreLinje er ikke null
     * pre: fastRabatPris <= ordreLinje.getPris() * ordreLinje.getAntal()
     * @return opretter, tilføjer til ordrelinje og returnerer en FastRabat
     */
    public RabatBeregning tilføjFastRabatTilOrdrelinje(OrdreLinje ordreLinje,double fastRabatPris){
        RabatBeregning fastRabat = new FastRabat(fastRabatPris);
        ordreLinje.setRabatBeregning(fastRabat);
        return fastRabat;
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * @param start
     * @param slut
     * pre: slut er efter start
     * @return returnerer en ArrayList med salg, solgt imellem start og slut fra storage.
     */
    public ArrayList<Salg> getSalgFromDato(LocalDate start, LocalDate slut){
        ArrayList<Salg> salg = new ArrayList<>();
        if (start != null && slut != null){
            if (!storage.getSalg().isEmpty()){
                for (Salg s : storage.getSalg()){
                    if (s.getSalgsTidspunkt().isAfter(start.atStartOfDay()) && s.getSalgsTidspunkt().isBefore(slut.atTime(23,59))){
                        salg.add(s);
                    }
                }
            }
        }
        return salg;
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------
    public void saveStorage() {
        try (FileOutputStream fileOut = new FileOutputStream("storage.ser")) {
            try (ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                out.writeObject(storage);
                System.out.println("Storage saved in file storage.ser.");
            }
        } catch (IOException ex) {
            System.out.println("Error saving storage object.");
            throw new RuntimeException(ex);
        }
    }

    public void loadStorage() {
        try (FileInputStream fileIn = new FileInputStream("storage.ser")) {
            try (ObjectInputStream in = new ObjectInputStream(fileIn);) {
                storage = (Storage) in.readObject();

                System.out.println("Storage loaded from file storage.ser.");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error loading storage object.");
                throw new RuntimeException(ex);
            }
        } catch (IOException ex) {
            System.out.println("Error loading storage object.");
            throw new RuntimeException(ex);
        }

    }

}
