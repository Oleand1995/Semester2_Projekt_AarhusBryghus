package application.controller;

import application.model.Pris;
import application.model.Prisliste;
import application.model.Produkt;
import application.model.ProduktGruppe;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    @BeforeEach
    void setUP(){
        ProduktGruppe fadØl = Controller.createproduktGruppe("Fadøl");
        Produkt klosterbryg = Controller.createProdukt("Klosterbryg", fadØl);
        Prisliste fredagsBar = Controller.createPrisliste("Fredagsbar");
        Pris pris = Controller.createPris(38,klosterbryg ,fredagsBar);
        Controller.createOrdreLinje(pris);

    }

    @Test
    void createSalg() {

    }

    @Test
    void getSalg() {
    }

    @Test
    void createUdlejning() {
    }

    @Test
    void removeUdlejning() {
    }

    @Test
    void getUdlejninger() {
    }

    @Test
    void createOrdreLinje() {
    }

    @Test
    void setAntalPåOrdreLinje() {
    }

    @Test
    void createproduktGruppe() {
    }

    @Test
    void getProduktGrupper() {
    }

    @Test
    void createProdukt() {
    }

    @Test
    void sletProduktgruppe() {
    }

    @Test
    void getProdukter() {
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