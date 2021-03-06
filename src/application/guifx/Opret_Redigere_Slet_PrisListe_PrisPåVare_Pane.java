package application.guifx;

import application.controller.Controller;
import application.model.Pris;
import application.model.Prisliste;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class Opret_Redigere_Slet_PrisListe_PrisPåVare_Pane extends GridPane {

    private ListView<Prisliste> lvwPrislister;
    private ListView<Pris> lvwPrispåProdukter;
    private Button btnOpretPrisliste,btnSletPrisliste,btnÆndrePrisliste,btnTilføjProdukt,btnSletProdukt;
    private Controller controller;

    public Opret_Redigere_Slet_PrisListe_PrisPåVare_Pane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);
        controller = Controller.getController();


        Label lblPrislister = new Label("Prislister");
        this.add(lblPrislister, 0, 1);

        lvwPrislister = new ListView<>();
        this.add(lvwPrislister,0 ,2,1,1);
        lvwPrislister.getItems().setAll(controller.getPrislister());
        ChangeListener<Prisliste> listener = (ov, oldPrisliste, newPrisliste) -> this.valgtPrisliste();
        lvwPrislister.getSelectionModel().selectedItemProperty().addListener(listener);


        btnOpretPrisliste = new Button("Opret prisliste");
        this.add(btnOpretPrisliste,0 ,3);
        btnOpretPrisliste.setOnAction(event -> opretPrisliste());


        btnSletPrisliste = new Button("Slet Prisliste");
        this.add(btnSletPrisliste,0 ,4);
        btnSletPrisliste.setOnAction(event -> sletPrisliste());


        btnÆndrePrisliste = new Button("Ændre prisliste");
        this.add(btnÆndrePrisliste,0 ,5 );
        btnÆndrePrisliste.setOnAction(event -> updatePrisliste());


        Label lblPrislisteProdukter = new Label("Prisliste produktor");
        this.add(lblPrislisteProdukter, 1, 1);


        lvwPrispåProdukter = new ListView<>();
        this.add(lvwPrispåProdukter,1 ,2,1,1);


        btnTilføjProdukt = new Button("Tilføj Pris");
        this.add(btnTilføjProdukt,1 ,3);
        btnTilføjProdukt.setOnAction(event -> tilføjProduktTilPrisliste());


        btnSletProdukt = new Button("Slet Pris");
        this.add(btnSletProdukt,1 ,4);
        btnSletProdukt.setOnAction(event -> sletProdukt());

    }

    // -------------------------------------------------------------------------


    private void opretPrisliste() {
        Opret_Redigere_Prisliste_Window dia = new Opret_Redigere_Prisliste_Window("Opret prisliste");
        dia.showAndWait();

        lvwPrislister.getItems().setAll(controller.getPrislister());
    }

    private void valgtPrisliste() {
        Prisliste prisliste = lvwPrislister.getSelectionModel().getSelectedItem();
        if (prisliste != null) {
            lvwPrispåProdukter.getItems().setAll(prisliste.getPriser());
        }
    }

    private void sletPrisliste() {
        Prisliste prisliste = lvwPrislister.getSelectionModel().getSelectedItem();
        if (prisliste != null){
            controller.sletPrisliste(prisliste);
            lvwPrislister.getItems().setAll(controller.getPrislister());

        }
    }


    private void updatePrisliste(){
        Prisliste prisliste = lvwPrislister.getSelectionModel().getSelectedItem();
        if (prisliste != null) {
            Opret_Redigere_Prisliste_Window dia = new Opret_Redigere_Prisliste_Window("Update produktgruppe", prisliste);
            dia.showAndWait();

            int selectIndex = lvwPrislister.getSelectionModel().getSelectedIndex();
            lvwPrislister.getItems().setAll(controller.getPrislister());
            lvwPrislister.getSelectionModel().select(selectIndex);
        }
    }


    private void tilføjProduktTilPrisliste() {
        Prisliste prisliste = lvwPrislister.getSelectionModel().getSelectedItem();
        if (prisliste != null) {
            Opret_Redigere_PrisPåVare_Window dia = new Opret_Redigere_PrisPåVare_Window("Tilføj",prisliste);
            dia.showAndWait();

            //venter til vinduet lukker
            lvwPrispåProdukter.getItems().setAll(prisliste.getPriser());
        }
    }

    private void sletProdukt(){
        Prisliste prisliste = lvwPrislister.getSelectionModel().getSelectedItem();
        Pris pris = lvwPrispåProdukter.getSelectionModel().getSelectedItem();
        if (pris != null) {
            controller.sletPrisEllerPrisOgKlip(prisliste,pris);
            lvwPrispåProdukter.getItems().setAll(prisliste.getPriser());
        }
    }

    // -------------------------------------------------------------------------

    public void updateControls() {
        lvwPrislister.getItems().setAll(controller.getPrislister());
    }





}
