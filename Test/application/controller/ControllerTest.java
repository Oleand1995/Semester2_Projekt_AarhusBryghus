package application.controller;

import application.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeAll;
import storage.Storage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.SortedMap;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    Controller controller;

    ProduktGruppe fadoel;

    Produkt klosterbryg;
    Produkt blondie;
    Prisliste fredagsBar;

    Pris pris;
    Pris pris2;

    OrdreLinje ordreLinje1;
    OrdreLinje ordreLinje2;

    ArrayList<OrdreLinje> ordreLinjerUdenKlip;
    ArrayList<OrdreLinje> ordreLinjerMedKlip;

    ObservableList<OrdreLinje> ordrelinjerObsUdenKlip;
    ObservableList<OrdreLinje> ordrelinjerObsMedKlip;

    Double ordrelinjerSamletPris;
    int ordrelinjerSamletKlip;

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
        pris2 = controller.createPrisOgKlip(38,blondie,2,fredagsBar);

        //Opretter orderelinjer
        ordreLinje1 = controller.createOrdreLinje(pris);
        ordreLinje2 = controller.createOrdreLinje(pris2);

        //Opretter indkøbsliste/array med ordrelinjer
        ordreLinjerUdenKlip = new ArrayList<>();
        ordreLinjerUdenKlip.add(ordreLinje1);
        ordreLinjerMedKlip = new ArrayList<>();
        ordreLinjerMedKlip.add(ordreLinje2);

        //Laver ordreliner array om til obserble liste så den kan bruges.
        ordrelinjerObsUdenKlip = FXCollections.observableArrayList(ordreLinjerUdenKlip);
        ordrelinjerObsMedKlip = FXCollections.observableArrayList(ordreLinjerMedKlip);

        //Udregner samlet pris og klip.
        ordrelinjerSamletPris = controller.getSamletPris(ordrelinjerObsUdenKlip);
        ordrelinjerSamletKlip = controller.getSamletKlip(ordrelinjerObsMedKlip);

    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void createSalg() {
        assertEquals(0, controller.getSalg().size());
        Salg salg = controller.createSalg(LocalDateTime.of(LocalDate.of(2022,4 ,8), LocalTime.of(12,0)),ordreLinjerUdenKlip,0,ordrelinjerSamletPris);
        assertNotNull(salg);
        assertEquals(1, controller.getSalg().size());
        assertEquals(LocalDateTime.of(LocalDate.of(2022,4 ,8), LocalTime.of(12,0)),salg.getSalgsTidspunkt());
        assertEquals(ordreLinjerUdenKlip,salg.getOrdrelinjer());
        assertEquals(38,controller.getSamletPris(ordrelinjerObsUdenKlip));

    }

    @Test
    void createUdlejning() {
        assertEquals(0,controller.getUdlejninger().size());
        Udlejning udlejning = controller.createUdlejning(LocalDateTime.of(LocalDate.of(2022,4 ,8), LocalTime.of(12,30)),ordrelinjerSamletPris,"Mads Nørskov",ordreLinjerUdenKlip);
        assertNotNull(udlejning);
        assertEquals(1,controller.getUdlejninger().size());
        assertEquals(LocalDateTime.of(LocalDate.of(2022,4 ,8), LocalTime.of(12,30)),udlejning.getUdlejningsTidspunkt());
        assertEquals(38,ordrelinjerSamletPris);
        assertEquals("Mads Nørskov",udlejning.getLejersNavn());
        assertEquals(ordreLinjerUdenKlip,udlejning.getOrdrelinjer());
        assertEquals(38,controller.getSamletPris(ordrelinjerObsUdenKlip));
    }

    @Test
    void createOrdreLinje() {
        OrdreLinje ordreLinje3 = controller.createOrdreLinje(pris2);
        assertNotNull(ordreLinje3);
        assertEquals(pris2,ordreLinje3.getPris());
        assertEquals(1,ordreLinje3.getAntal());
        assertEquals(null,ordreLinje3.getRabatBeregning());
    }

    @Test
    void setAntalPåOrdreLinje() {
        assertEquals(1,ordreLinje1.getAntal());
        controller.setAntalPåOrdreLinje(ordreLinje1,5);
        assertEquals(5,ordreLinje1.getAntal());
    }

    @Test
    void createproduktGruppe() {
        assertEquals(1,controller.getProduktGrupper().size());
        ProduktGruppe flaskeØl = controller.createproduktGruppe("Flaske øl");
        assertNotNull(flaskeØl);
        assertEquals(2,controller.getProduktGrupper().size());
        assertEquals("Flaske øl",flaskeØl.getProduktType());

    }

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
    void sletProduktgruppe() {
        //controller.getTestController vil være 1, da tidligere test, har lagt en produktgruppe i controller.getTestController.
        assertEquals(1,controller.getProduktGrupper().size());
        ProduktGruppe pg = controller.createproduktGruppe("Mams");
        assertEquals(2,controller.getProduktGrupper().size());
        controller.sletProduktgruppe(pg);
        assertEquals(1,controller.getProduktGrupper().size());
    }

    @Test
    void getProdukter() {
        assertEquals(2,controller.getProdukter().size());
        controller.createProdukt("Lort",fadoel);
        assertEquals(3,controller.getProdukter().size());

    }

    @Test
    void sletProdukt() {
    }

    @Test
    void createPrisliste() {
    }

    @Test
    void getPrislister() {
    }

    @Test
    void sletPrisliste() {
    }

    @Test
    void getSamletPris() {
    }

    @Test
    void getSamletKlip() {
    }

    @Test
    void createPrisOgKlip() {
    }

    @Test
    void createPris() {
    }

    @Test
    void sletPrisEllerPrisOgKlip() {
    }

    @Test
    void tilføjProcentRabatTilOrdrelinje() {
    }

    @Test
    void tilføjFastRabatTilOrdrelinje() {
    }

    @Test
    void initStorage() {
    }
}