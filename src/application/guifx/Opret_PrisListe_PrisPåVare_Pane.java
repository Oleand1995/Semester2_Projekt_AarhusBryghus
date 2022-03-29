package application.guifx;

import application.controller.Controller;
import application.model.Prisliste;
import application.model.Produkt;
import application.model.ProduktGruppe;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class Opret_PrisListe_PrisPåVare_Pane extends GridPane {

    private ListView<Prisliste> lvwPrislister;
    private ListView<Produkt> lvwPrislisteProdukter;
    private Button btnOpretPrisliste,btnSletPrisliste,btnÆndrePrisliste,btnTilføjProdukt,btnSletProdukt,btnÆndreProdukt;


    public Opret_PrisListe_PrisPåVare_Pane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);


        Label lblPrislister = new Label("Prislister");
        this.add(lblPrislister, 0, 1);

        lvwPrislister = new ListView<>();
        this.add(lvwPrislister,0 ,2,1,1);
        lvwPrislister.getItems().setAll(Controller.getPrislister());
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

        lvwPrislisteProdukter = new ListView<>();
        this.add(lvwPrislisteProdukter,1 ,2,1,1);

        btnTilføjProdukt = new Button("Tilføg produkt");
        this.add(btnTilføjProdukt,1 ,3);
        //btnTilføjProdukt.setOnAction(event -> tilføjProdukt());


        btnSletProdukt = new Button("Slet produkt");
        this.add(btnSletProdukt,1 ,4);


        btnÆndreProdukt = new Button("Ændre produkt");
        this.add(btnÆndreProdukt,1 ,5 );
        //btnÆndreProdukt.setOnAction(event -> updateprodukt());




    }

    // -------------------------------------------------------------------------


    private void opretPrisliste() {
        Opret_Prisliste_Window dia = new Opret_Prisliste_Window("Opret prisliste");
        dia.showAndWait();

        //venter til vinduet lukker
        lvwPrislister.getItems().setAll(Controller.getPrislister());
    }

    private void valgtPrisliste() {
        Prisliste prisliste = lvwPrislister.getSelectionModel().getSelectedItem();
        if (prisliste != null) {
            lvwPrislisteProdukter.getItems().setAll(prisliste.getProdukter());
        }
    }

    private void sletPrisliste() {
        Prisliste prisliste = lvwPrislister.getSelectionModel().getSelectedItem();
        if (prisliste != null){
            Controller.sletPrisliste(prisliste);
            lvwPrislister.getItems().setAll(Controller.getPrislister());

        }
    }


    private void updatePrisliste(){
        Prisliste prisliste = lvwPrislister.getSelectionModel().getSelectedItem();
        if (prisliste != null) {
            Opret_Prisliste_Window dia = new Opret_Prisliste_Window("Update produktgruppe", prisliste);
            dia.showAndWait();

            int selectIndex = lvwPrislister.getSelectionModel().getSelectedIndex();
            lvwPrislister.getItems().setAll(Controller.getPrislister());
            lvwPrislister.getSelectionModel().select(selectIndex);
        }
    }

/*
    private void tilføjProdukt() {
        ProduktGruppe produktGruppe = lvwProduktgrupper.getSelectionModel().getSelectedItem();
        if (produktGruppe != null) {
            Opret_Redigere_Produkt_Window dia = new Opret_Redigere_Produkt_Window("Opret Produkt",produktGruppe);
            dia.showAndWait();

            //venter til vinduet lukker
            lvwPrislisteProdukter.getItems().setAll(produktGruppe.getProdukter());
        }
    }

    private void updateprodukt(){
        Produkt produkt = lvwPrislisteProdukter.getSelectionModel().getSelectedItem();
        if (produkt != null) {
            Opret_Redigere_Produkt_Window dia = new Opret_Redigere_Produkt_Window("Update produktgruppe", null,produkt);
            dia.showAndWait();

            int selectIndex = lvwProduktgrupper.getSelectionModel().getSelectedIndex();
            lvwProduktgrupper.getItems().setAll(Controller.getProduktGrupper());
            lvwProduktgrupper.getSelectionModel().select(selectIndex);

        }
    }







    // -------------------------------------------------------------------------

    public void updateControls() {
        lvwProduktgrupper.getItems().setAll(Controller.getProduktGrupper());
    }

     */

}
