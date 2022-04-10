package application.controller;

import application.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    Controller controller;

    ProduktGruppe fadoel;

    Produkt klosterbryg;
    Produkt blondie;
    Prisliste fredagsBar;

    Pris pris;
    Pris prisOgklip;

    OrdreLinje ordreLinjeUdenKlip;
    OrdreLinje ordreLinjeMedKlip;

    ArrayList<OrdreLinje> ordreLinjerUdenKlip;
    ArrayList<OrdreLinje> ordreLinjerMedKlip;

    ObservableList<OrdreLinje> ordrelinjerObsUdenKlip;
    ObservableList<OrdreLinje> ordrelinjerObsMedKlip;

    Double ordrelinjerSamletPris;
    int ordrelinjerSamletKlip;

    RabatBeregning procentRabat;
    RabatBeregning fastRabat;


    @BeforeEach
    void setUp() {

        controller = Controller.getController().getTestController();
        

        //Opretter produktgruppe
        fadoel = controller.createproduktGruppe("Fadoel");

        //Opretter produkter
        klosterbryg = controller.createProdukt("Klosterbryg",fadoel);
        blondie = controller.createProdukt("Blondie",fadoel);

        //Opretter prisliste
        fredagsBar = controller.createPrisliste("Bar");

        //Opretter priser
        pris = controller.createPris(38,klosterbryg,fredagsBar);
        prisOgklip = controller.createPrisOgKlip(38,blondie,2,fredagsBar);

        //Opretter orderelinjer
        ordreLinjeUdenKlip = controller.createOrdreLinje(pris);
        ordreLinjeMedKlip = controller.createOrdreLinje(prisOgklip);

        //Opretter indkøbsliste/array med ordrelinjer
        ordreLinjerUdenKlip = new ArrayList<>();
        ordreLinjerUdenKlip.add(ordreLinjeUdenKlip);
        ordreLinjerMedKlip = new ArrayList<>();
        ordreLinjerMedKlip.add(ordreLinjeMedKlip);

        //Laver ordreliner array om til obserble liste så den kan bruges.
        ordrelinjerObsUdenKlip = FXCollections.observableArrayList(ordreLinjerUdenKlip);
        ordrelinjerObsMedKlip = FXCollections.observableArrayList(ordreLinjerMedKlip);

        //Udregner samlet pris og klip.
        ordrelinjerSamletPris = controller.getSamletPris(ordrelinjerObsUdenKlip);
        ordrelinjerSamletKlip = controller.getSamletKlip(ordrelinjerObsMedKlip);

    }

    @Test
    void createSalg() {
        //Tester salg med en pris
        assertEquals(0, controller.getSalg().size());
        Salg salgMedPris = controller.createSalg(LocalDateTime.of(LocalDate.of(2022,4 ,8), LocalTime.of(12,0)),ordreLinjerUdenKlip,0,ordrelinjerSamletPris);
        assertNotNull(salgMedPris);
        assertEquals(1, controller.getSalg().size());
        assertEquals(LocalDateTime.of(LocalDate.of(2022,4 ,8), LocalTime.of(12,0)),salgMedPris.getSalgsTidspunkt());
        assertEquals(ordreLinjerUdenKlip,salgMedPris.getOrdrelinjer());
        assertEquals(38,salgMedPris.getSamletPris());

        //Tester salg med klip
        assertEquals(1, controller.getSalg().size());
        Salg salgMedKlip = controller.createSalg(LocalDateTime.of(LocalDate.of(2022,4 ,10), LocalTime.of(9,0)),ordreLinjerMedKlip,ordrelinjerSamletKlip,0);
        assertNotNull(salgMedKlip);
        assertEquals(2, controller.getSalg().size());
        assertEquals(LocalDateTime.of(LocalDate.of(2022,4 ,10), LocalTime.of(9,0)),salgMedKlip.getSalgsTidspunkt());
        assertEquals(ordreLinjerMedKlip,salgMedKlip.getOrdrelinjer());
        assertEquals(2,salgMedKlip.getSamletKlip());

    }

    @Test
    void getSalg(){
        assertEquals(0,controller.getSalg().size());
        Salg salgMedPris = controller.createSalg(LocalDateTime.of(LocalDate.of(2022,4 ,8), LocalTime.of(12,0)),ordreLinjerUdenKlip,0,ordrelinjerSamletPris);
        assertEquals(1,controller.getSalg().size());
        ArrayList<Salg> salgArrayliste = new ArrayList<>();
        salgArrayliste.add(salgMedPris);
        assertEquals(salgArrayliste,controller.getSalg());
    }

    @Test
    void removeSalg(){
        assertEquals(0,controller.getSalg().size());
        Salg salgMedPris = controller.createSalg(LocalDateTime.of(LocalDate.of(2022,4 ,8), LocalTime.of(12,0)),ordreLinjerUdenKlip,0,ordrelinjerSamletPris);
        assertEquals(1,controller.getSalg().size());
        controller.removeSalg(salgMedPris);
        assertEquals(0,controller.getSalg().size());
    }

    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    @Test
    void createUdlejning() {
        assertEquals(0,controller.getUdlejninger().size());
        Udlejning udlejning = controller.createUdlejning(LocalDateTime.of(LocalDate.of(2022,4 ,8), LocalTime.of(12,30)),ordrelinjerSamletPris,"Mads Nørskov",ordreLinjerUdenKlip);
        assertNotNull(udlejning);
        assertEquals(1,controller.getUdlejninger().size());
        assertEquals(LocalDateTime.of(LocalDate.of(2022,4 ,8), LocalTime.of(12,30)),udlejning.getUdlejningsTidspunkt());
        assertEquals("Mads Nørskov",udlejning.getLejersNavn());
        assertEquals(ordreLinjerUdenKlip,udlejning.getOrdrelinjer());
        assertEquals(38,udlejning.getSamletPris());
    }

    @Test
    void removeUdlejning(){
        assertEquals(0,controller.getUdlejninger().size());
        Udlejning udlejning = controller.createUdlejning(LocalDateTime.of(LocalDate.of(2022,4 ,8), LocalTime.of(12,30)),ordrelinjerSamletPris,"Mads Nørskov",ordreLinjerUdenKlip);
        assertEquals(1,controller.getUdlejninger().size());
        controller.removeUdlejning(udlejning);
        assertEquals(0,controller.getUdlejninger().size());
    }

    @Test
    void getUdleninger(){
        assertEquals(0,controller.getUdlejninger().size());
        Udlejning udlejning = controller.createUdlejning(LocalDateTime.of(LocalDate.of(2022,4 ,8), LocalTime.of(12,30)),ordrelinjerSamletPris,"Mads Nørskov",ordreLinjerUdenKlip);
        assertEquals(1,controller.getUdlejninger().size());
        ArrayList<Udlejning> udlejningArrayliste = new ArrayList<>();
        udlejningArrayliste.add(udlejning);
        assertEquals(udlejningArrayliste,controller.getUdlejninger());
    }

    @Test
    void setAfrejningstidspunkt(){
        Udlejning udlejning = controller.createUdlejning(LocalDateTime.of(LocalDate.of(2022,4 ,8), LocalTime.of(12,30)),ordrelinjerSamletPris,"Mads Nørskov",ordreLinjerUdenKlip);
        assertNull(udlejning.getAfregningsTidspunkt());
        controller.setAfrejningstidpunkt(udlejning);
        assertNotNull(udlejning.getAfregningsTidspunkt());
    }

    @Test
    void setSamletPrisPåUdlejning(){
        OrdreLinje ordreLinjeNy = controller.createOrdreLinje(pris);
        ordrelinjerObsUdenKlip.add(ordreLinjeNy);
        assertEquals(76,controller.getSamletPris(ordrelinjerObsUdenKlip));
        Udlejning udlejning = controller.createUdlejning(LocalDateTime.of(LocalDate.of(2022,4 ,8), LocalTime.of(12,30)),ordrelinjerSamletPris,"Mads Nørskov",ordreLinjerUdenKlip);
        assertEquals(38,udlejning.getSamletPris());
        controller.setSamletPrisPåUdlejning(udlejning,ordrelinjerObsUdenKlip);
        assertEquals(76,udlejning.getSamletPris());

    }

    @Test
    void getAktiveUdlejninger(){
        Udlejning udlejning = controller.createUdlejning(LocalDateTime.of(LocalDate.of(2022,4 ,8), LocalTime.of(12,30)),ordrelinjerSamletPris,"Mads Nørskov",ordreLinjerUdenKlip);
        Udlejning udlejning2 = controller.createUdlejning(LocalDateTime.of(LocalDate.of(2022,4 ,8), LocalTime.of(13,30)),ordrelinjerSamletPris,"Mads Nørskov",ordreLinjerUdenKlip);
        Udlejning udlejning3 = controller.createUdlejning(LocalDateTime.of(LocalDate.of(2022,4 ,9), LocalTime.of(14,30)),ordrelinjerSamletPris,"Mads Nørskov",ordreLinjerUdenKlip);
        Udlejning udlejning4 = controller.createUdlejning(LocalDateTime.of(LocalDate.of(2022,4 ,10), LocalTime.of(15,30)),ordrelinjerSamletPris,"Mads Nørskov",ordreLinjerUdenKlip);
        Udlejning udlejning5 = controller.createUdlejning(LocalDateTime.of(LocalDate.of(2022,4 ,11), LocalTime.of(16,30)),ordrelinjerSamletPris,"Mads Nørskov",ordreLinjerUdenKlip);
        controller.setAfrejningstidpunkt(udlejning);
        controller.setAfrejningstidpunkt(udlejning2);
        controller.setAfrejningstidpunkt(udlejning3);
        assertEquals(5,controller.getUdlejninger().size());
        assertEquals(2,controller.getAktiveUdlejninger().size());
    }

    @Test
    void getAfsluttedeUdlejninger(){
        Udlejning udlejning = controller.createUdlejning(LocalDateTime.of(LocalDate.of(2022,4 ,8), LocalTime.of(12,30)),ordrelinjerSamletPris,"Mads Nørskov",ordreLinjerUdenKlip);
        Udlejning udlejning2 = controller.createUdlejning(LocalDateTime.of(LocalDate.of(2022,4 ,8), LocalTime.of(13,30)),ordrelinjerSamletPris,"Mads Nørskov",ordreLinjerUdenKlip);
        Udlejning udlejning3 = controller.createUdlejning(LocalDateTime.of(LocalDate.of(2022,4 ,9), LocalTime.of(14,30)),ordrelinjerSamletPris,"Mads Nørskov",ordreLinjerUdenKlip);
        Udlejning udlejning4 = controller.createUdlejning(LocalDateTime.of(LocalDate.of(2022,4 ,10), LocalTime.of(15,30)),ordrelinjerSamletPris,"Mads Nørskov",ordreLinjerUdenKlip);
        Udlejning udlejning5 = controller.createUdlejning(LocalDateTime.of(LocalDate.of(2022,4 ,11), LocalTime.of(16,30)),ordrelinjerSamletPris,"Mads Nørskov",ordreLinjerUdenKlip);
        controller.setAfrejningstidpunkt(udlejning);
        controller.setAfrejningstidpunkt(udlejning2);
        controller.setAfrejningstidpunkt(udlejning3);
        assertEquals(5,controller.getUdlejninger().size());
        assertEquals(3,controller.getAfsluttedeUdlejninger().size());
    }

    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    @Test
    void createOrdreLinje() {
        OrdreLinje ordreLinje3 = controller.createOrdreLinje(prisOgklip);
        assertNotNull(ordreLinje3);
        assertEquals(prisOgklip,ordreLinje3.getPris());
        assertEquals(1,ordreLinje3.getAntal());
        assertEquals(null,ordreLinje3.getRabatBeregning());
    }


    @Test
    void setAntalPåOrdreLinje() {
        assertEquals(1, ordreLinjeUdenKlip.getAntal());
        controller.setAntalPåOrdreLinje(ordreLinjeUdenKlip,5);
        assertEquals(5, ordreLinjeUdenKlip.getAntal());
    }

    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    @Test
    void createproduktGruppe() {
        assertEquals(1,controller.getProduktGrupper().size());
        ProduktGruppe flaskeØl = controller.createproduktGruppe("Flaske øl");
        assertNotNull(flaskeØl);
        assertEquals(2,controller.getProduktGrupper().size());
        assertEquals("Flaske øl",flaskeØl.getProduktType());
    }

    @Test
    void getProduktGrupper(){
        assertEquals(1,controller.getProduktGrupper().size());
        ProduktGruppe produktgruppeTest = controller.createproduktGruppe("Test produktgruppe");
        assertEquals(2,controller.getProduktGrupper().size());
        ArrayList<ProduktGruppe> produktGruppeArray = new ArrayList<>();
        produktGruppeArray.add(fadoel);
        produktGruppeArray.add(produktgruppeTest);
        assertEquals(produktGruppeArray,controller.getProduktGrupper());
    }

    @Test
    void sletProduktgruppe() {
        assertEquals(1,controller.getProduktGrupper().size());
        controller.sletProduktgruppe(fadoel);
        assertEquals(0,controller.getProduktGrupper().size());
    }

    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    @Test
    void createProdukt() {
        assertEquals(2,fadoel.getProdukter().size());
        Produkt hyggeØl = controller.createProdukt("Hygge øl",fadoel);
        assertNotNull(hyggeØl);
        assertEquals(3,fadoel.getProdukter().size());
        assertEquals("Hygge øl",hyggeØl.getBeskrivelse());
        assertEquals(fadoel,hyggeØl.getProduktgruppe());
    }


    @Test
    void getProdukter() {
        assertEquals(2,controller.getProdukter().size());
        ProduktGruppe produktGruppeTest = controller.createproduktGruppe("Produkt gruppe test");
        Produkt produktTest = controller.createProdukt("Test produkt",produktGruppeTest);
        assertEquals(3,controller.getProdukter().size());
        ArrayList<Produkt> produktArray = new ArrayList<>();
        produktArray.add(klosterbryg);
        produktArray.add(blondie);
        produktArray.add(produktTest);
        assertEquals(produktArray,controller.getProdukter());
    }

    @Test
    void sletProdukt() {
        assertEquals(2,controller.getProdukter().size());
        controller.sletProdukt(fadoel,blondie);
        assertEquals(1,controller.getProdukter().size());
    }

    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    @Test
    void createPrisliste() {
        assertEquals(1,controller.getPrislister().size());
        Prisliste familieFest = controller.createPrisliste("Familie fest");
        assertNotNull(familieFest);
        assertEquals(2,controller.getPrislister().size());
        assertEquals("Familie fest",familieFest.getSituation());
        assertEquals(0,familieFest.getPriser().size());
    }

    @Test
    void getPrislister() {
        assertEquals(1,controller.getPrislister().size());
        Prisliste prislisteTest = controller.createPrisliste("pristliste test");
        assertEquals(2,controller.getPrislister().size());
        ArrayList<Prisliste> prislisteArray = new ArrayList<>();
        prislisteArray.add(fredagsBar);
        prislisteArray.add(prislisteTest);
        assertEquals(prislisteArray,controller.getPrislister());
    }

    @Test
    void sletPrisliste() {
        assertEquals(1,controller.getPrislister().size());
        controller.sletPrisliste(fredagsBar);
        assertEquals(0,controller.getPrislister().size());
    }

    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    @Test
    void getSamletPris() {
        assertEquals(38,controller.getSamletPris(ordrelinjerObsUdenKlip));
        OrdreLinje ordrelinjeTest = controller.createOrdreLinje(pris);
        controller.setAntalPåOrdreLinje(ordrelinjeTest,2);
        ordrelinjerObsUdenKlip.add(ordrelinjeTest);
        assertEquals(38*3,controller.getSamletPris(ordrelinjerObsUdenKlip));
    }

    @Test
    void getSamletKlip() {
        assertEquals(2,controller.getSamletKlip(ordrelinjerObsMedKlip));
        OrdreLinje ordrelinjetest = controller.createOrdreLinje(prisOgklip);
        controller.setAntalPåOrdreLinje(ordrelinjetest,2);
        ordrelinjerObsMedKlip.add(ordrelinjetest);
        assertEquals(2*3,controller.getSamletKlip(ordrelinjerObsMedKlip));
    }

    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    @Test
    void createPrisOgKlip() {
        assertEquals(2,fredagsBar.getPriser().size());
        Pris blondiePrisOgKlip = controller.createPrisOgKlip(70,blondie ,4,fredagsBar);
        assertNotNull(blondiePrisOgKlip);
        assertEquals(3,fredagsBar.getPriser().size());
        assertEquals(4,blondiePrisOgKlip.getKlip());
        assertEquals(blondie,blondiePrisOgKlip.getProdukt());
        assertEquals(70,blondiePrisOgKlip.getPris());
    }

    @Test
    void createPris() {
        assertEquals(2,fredagsBar.getPriser().size());
        Pris blondiePris = controller.createPris(70,blondie,fredagsBar);
        assertNotNull(blondiePris);
        assertEquals(3,fredagsBar.getPriser().size());
        assertEquals(blondie,blondiePris.getProdukt());
        assertEquals(70,blondiePris.getPris());
    }

    @Test
    void sletPrisEllerPrisOgKlip() {
        assertEquals(2,fredagsBar.getPriser().size());
        //Tester om metoden vil slette pris.
        controller.sletPrisEllerPrisOgKlip(fredagsBar,pris);
        assertEquals(1,fredagsBar.getPriser().size());
        //Tester om metoden vil slette prisMedKlip.
        controller.sletPrisEllerPrisOgKlip(fredagsBar, prisOgklip);
        assertEquals(0,fredagsBar.getPriser().size());
    }

    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    @Test
    void tilføjProcentRabatTilOrdrelinje() {
        assertEquals(38,controller.getSamletPris(ordrelinjerObsUdenKlip));
        assertNull(ordreLinjeUdenKlip.getRabatBeregning());
        controller.tilføjProcentRabatTilOrdrelinje(ordreLinjeUdenKlip,30);
        assertNotNull(ordreLinjeUdenKlip.getRabatBeregning());
        assertEquals(26.6,controller.getSamletPris(ordrelinjerObsUdenKlip));
    }

    @Test
    void tilføjFastRabatTilOrdrelinje() {
        assertEquals(38,controller.getSamletPris(ordrelinjerObsUdenKlip));
        assertNull(ordreLinjeUdenKlip.getRabatBeregning());
        controller.tilføjFastRabatTilOrdrelinje(ordreLinjeUdenKlip,8);
        assertNotNull(ordreLinjeUdenKlip.getRabatBeregning());
        assertEquals(30,controller.getSamletPris(ordrelinjerObsUdenKlip));
    }

    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    @Test
    void getSalgFromDato(){
        LocalDate dato1 = LocalDate.of(2022,4 ,5);
        LocalDate dato2 = LocalDate.of(2022,4 ,8);
        LocalDate dato3 = LocalDate.of(2022,4 ,9);
        LocalDate dato4 = LocalDate.of(2022,4 , 10);
        controller.createSalg(LocalDateTime.of(LocalDate.of(2022,4 ,8), LocalTime.of(12,0)),ordreLinjerUdenKlip,0,ordrelinjerSamletPris);
        controller.createSalg(LocalDateTime.of(LocalDate.of(2022,4 ,9), LocalTime.of(15,0)),ordreLinjerUdenKlip,0,ordrelinjerSamletPris);
        controller.createSalg(LocalDateTime.of(LocalDate.of(2022,4 ,10), LocalTime.of(20,0)),ordreLinjerUdenKlip,0,ordrelinjerSamletPris);
        assertEquals(1,controller.getSalgFromDato(dato1,dato2).size());
        assertEquals(2,controller.getSalgFromDato(dato1,dato3).size());
        assertEquals(3,controller.getSalgFromDato(dato1,dato4).size());
        assertEquals(0,controller.getSalgFromDato(dato1,dato1).size());
    }
}