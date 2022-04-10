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
        Salg salgMedPris = controller.createSalg(LocalDateTime.of(LocalDate.of(2022,4 ,8), LocalTime.of(12,0)),ordreLinjerUdenKlip,0,ordrelinjerSamletPris, Betalingsmåder.Dankort);
        assertNotNull(salgMedPris);
        assertEquals(1, controller.getSalg().size());
        assertEquals(LocalDateTime.of(LocalDate.of(2022,4 ,8), LocalTime.of(12,0)),salgMedPris.getSalgsTidspunkt());
        assertEquals(ordreLinjerUdenKlip,salgMedPris.getOrdrelinjer());
        assertEquals(38,salgMedPris.getSamletPris());

        //Tester salg med klip
        assertEquals(1, controller.getSalg().size());
        Salg salgMedKlip = controller.createSalg(LocalDateTime.of(LocalDate.of(2022,4 ,10), LocalTime.of(9,0)),ordreLinjerMedKlip,ordrelinjerSamletKlip,0, Betalingsmåder.Klippekort);
        assertNotNull(salgMedKlip);
        assertEquals(2, controller.getSalg().size());
        assertEquals(LocalDateTime.of(LocalDate.of(2022,4 ,10), LocalTime.of(9,0)),salgMedKlip.getSalgsTidspunkt());
        assertEquals(ordreLinjerMedKlip,salgMedKlip.getOrdrelinjer());
        assertEquals(2,salgMedKlip.getSamletKlip());

    }

    @Test
    void getSalg(){
        assertEquals(1,2 );

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
    void getUdleninger(){

    }

    @Test
    void getAktiveUdlejninger(){

    }

    @Test
    void getAfsluttedeUdlejninger(){

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
    }

    @Test
    void getSamletKlip() {
        assertEquals(2,controller.getSamletKlip(ordrelinjerObsMedKlip));
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

    }
}