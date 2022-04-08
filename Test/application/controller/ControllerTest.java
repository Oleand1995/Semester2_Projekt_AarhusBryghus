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

    ProduktGruppe fadØl;
    Produkt klosterBryg,blondie;
    Prisliste fredagsBar;
    Pris pris,prisMedKlip;
    OrdreLinje ordreLinje1,ordreLinje2;
    ArrayList<OrdreLinje> ordreLinjer;
    double ordrelinjerSamletPris;
    int ordrelinjerSamletKlip;
    ObservableList<OrdreLinje> ordrelinjerObs;

    @BeforeEach
    void setUp() {

        //Opretter produktgruppe
        fadØl = new ProduktGruppe("Fadøl");

        //Opretter produkter
        klosterBryg = fadØl.createProdukt("Klosterbryg");
        blondie = fadØl.createProdukt("Blondie");

        //Opretter prisliste
        fredagsBar = new Prisliste("Fredagsbar");

        //Opretter priser
        pris = new Pris(38,klosterBryg);
        prisMedKlip = new PrisOgKlip(42,klosterBryg ,2);

        //Opretter orderelinjer
        ordreLinje1 = new OrdreLinje(pris);
        ordreLinje2 = new OrdreLinje(prisMedKlip);

        //Opretter indkøbsliste/array med ordrelinjer
        ordreLinjer = new ArrayList<>();
        ordreLinjer.add(ordreLinje1);
        ordreLinjer.add(ordreLinje2);

        //Laver ordreliner array om til obserble liste så den kan bruges.
        ordrelinjerObs = FXCollections.observableArrayList(ordreLinjer);

        //Udregner samlet pris og klip.
        ordrelinjerSamletPris = Controller.getSamletPris(ordrelinjerObs);
        ordrelinjerSamletKlip = Controller.getSamletKlip(ordrelinjerObs);

    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void createSalg() {
        assertEquals(0, Storage.getSalg().size());
        Salg salg = Controller.createSalg(LocalDateTime.of(LocalDate.of(2022,4 ,8), LocalTime.of(12,0)),ordreLinjer,ordrelinjerSamletKlip ,ordrelinjerSamletPris);
        assertNotNull(salg);
        assertEquals(1, Storage.getSalg().size());
        assertEquals(LocalDateTime.of(LocalDate.of(2022,4 ,8), LocalTime.of(12,0)),salg.getSalgsTidspunkt());
        assertEquals(ordreLinjer,salg.getOrdrelinjer());
        assertEquals(2,Controller.getSamletKlip(ordrelinjerObs));
        assertEquals(80,Controller.getSamletPris(ordrelinjerObs));

    }

    @Test
    void createUdlejning() {
        assertEquals(0,Storage.getUdlejninger().size());
        Udlejning udlejning = Controller.createUdlejning(LocalDateTime.of(LocalDate.of(2022,4 ,8), LocalTime.of(12,30)),ordrelinjerSamletPris,"Mads Nørskov",ordreLinjer);
        assertNotNull(udlejning);
        assertEquals(1,Storage.getUdlejninger().size());
        assertEquals(LocalDateTime.of(LocalDate.of(2022,4 ,8), LocalTime.of(12,30)),udlejning.getUdlejningsTidspunkt());
        assertEquals(80,ordrelinjerSamletPris);
        assertEquals("Mads Nørskov",udlejning.getLejersNavn());
        assertEquals(ordreLinjer,udlejning.getOrdrelinjer());
        assertEquals(80,Controller.getSamletPris(ordrelinjerObs));
    }

    @Test
    void createOrdreLinje() {
        OrdreLinje ordreLinje3 = Controller.createOrdreLinje(prisMedKlip);
        assertNotNull(ordreLinje3);
        assertEquals(prisMedKlip,ordreLinje3.getPris());
        assertEquals(1,ordreLinje3.getAntal());
        assertEquals(null,ordreLinje3.getRabatBeregning());
    }

    @Test
    void setAntalPåOrdreLinje() {
        assertEquals(1,ordreLinje1.getAntal());
        Controller.setAntalPåOrdreLinje(ordreLinje1,5);
        assertEquals(5,ordreLinje1.getAntal());
    }

    @Test
    void createproduktGruppe() {
        assertEquals(0,Storage.getProduktGrupper().size());
        ProduktGruppe flaskeØl = Controller.createproduktGruppe("Flaske øl");
        assertNotNull(flaskeØl);
        assertEquals(1,Storage.getProduktGrupper().size());
        assertEquals("Flaske øl",flaskeØl.getProduktType());
        assertEquals(0,flaskeØl.getProdukter().size());

    }

    @Test
    void createProdukt() {
        assertEquals(2,fadØl.getProdukter().size());
        Produkt hyggeØl = Controller.createProdukt("Hygge øl",fadØl);
        assertNotNull(hyggeØl);
        assertEquals(3,fadØl.getProdukter().size());
        assertEquals("Hygge øl",hyggeØl.getBeskrivelse());
        assertEquals(fadØl,hyggeØl.getProduktgruppe());
    }

    @Test
    void sletProduktgruppe() {
        //storage vil være 1, da tidligere test, har lagt en produktgruppe i storage.
        assertEquals(1,Storage.getProduktGrupper().size());
        ProduktGruppe pg = Controller.createproduktGruppe("Mams");
        assertEquals(2,Storage.getProduktGrupper().size());
        Controller.sletProduktgruppe(pg);
        assertEquals(1,Storage.getProduktGrupper().size());
    }

    @Test
    void getProdukter() {
        assertEquals(0,Controller.getProdukter().size());

        assertEquals(1,Controller.getProdukter().size());



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