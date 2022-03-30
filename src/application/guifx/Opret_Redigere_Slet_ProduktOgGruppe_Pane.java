package application.guifx;

import application.controller.Controller;
import application.model.Produkt;
import application.model.ProduktGruppe;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class Opret_Redigere_Slet_ProduktOgGruppe_Pane extends GridPane {

    private TextField txfProduktNavn, txfAntalPåLager;
    private TextArea txa;
    private ListView<ProduktGruppe> lvwProduktgrupper;
    private ListView<Produkt> lvwProdukter;
    private Button btnOpretProduktGruppe,btnSletProduktGruppe,btnÆndreProduktgruppe,btnOpretProdukt,btnSletProdukt,btnÆndreProdukt;


    public Opret_Redigere_Slet_ProduktOgGruppe_Pane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);


        Label lblProduktgrupper = new Label("Produktgrupper");
        this.add(lblProduktgrupper, 0, 1);

        lvwProduktgrupper = new ListView<>();
        this.add(lvwProduktgrupper,0 ,2,1,1);
        lvwProduktgrupper.getItems().setAll(Controller.getProduktGrupper());
        ChangeListener<ProduktGruppe> listener = (ov, oldProduktGruppe, newproduktgruppe) -> this.valgtProduktgruppe();
        lvwProduktgrupper.getSelectionModel().selectedItemProperty().addListener(listener);


        btnOpretProduktGruppe = new Button("Opret produktgruppe");
        this.add(btnOpretProduktGruppe,0 ,3);
        btnOpretProduktGruppe.setOnAction(event -> opretProduktGruppe());


        btnSletProduktGruppe = new Button("Slet produktgruppe");
        this.add(btnSletProduktGruppe,0 ,4);
        btnSletProduktGruppe.setOnAction(event -> sletProduktGruppe());


        btnÆndreProduktgruppe = new Button("Ændre produktgruppe");
        this.add(btnÆndreProduktgruppe,0 ,5 );
        btnÆndreProduktgruppe.setOnAction(event -> updateproduktGruppe());



        Label lblprodukter = new Label("produkter");
        this.add(lblprodukter, 1, 1);

        lvwProdukter = new ListView<>();
        this.add(lvwProdukter,1 ,2,1,1);

        btnOpretProdukt = new Button("Opret produkt");
        this.add(btnOpretProdukt,1 ,3);
        btnOpretProdukt.setOnAction(event -> opretProdukt());


        btnSletProdukt = new Button("Slet produkt");
        this.add(btnSletProdukt,1 ,4);
        btnSletProdukt.setOnAction(event -> sletProdukt());


        btnÆndreProdukt = new Button("Ændre produkt");
        this.add(btnÆndreProdukt,1 ,5 );
        btnÆndreProdukt.setOnAction(event -> updateprodukt());




    }

    // -------------------------------------------------------------------------

    private void opretProduktGruppe() {
        Opret_Redigere_ProduktGruppe_Window dia = new Opret_Redigere_ProduktGruppe_Window("Opret Produktgruppe");
        dia.showAndWait();

        //venter til vinduet lukker
        lvwProduktgrupper.getItems().setAll(Controller.getProduktGrupper());
    }

    private void valgtProduktgruppe() {
        ProduktGruppe produktGruppe = lvwProduktgrupper.getSelectionModel().getSelectedItem();
        if (produktGruppe != null) {
            lvwProdukter.getItems().setAll(produktGruppe.getProdukter());
        }
    }

    private void sletProduktGruppe() {
        ProduktGruppe produktGruppe = lvwProduktgrupper.getSelectionModel().getSelectedItem();
        if (produktGruppe != null){
            Controller.sletProduktgruppe(produktGruppe);
            lvwProduktgrupper.getItems().setAll(Controller.getProduktGrupper());
            lvwProdukter.getItems().clear();

        }
    }

    private void updateproduktGruppe(){
        ProduktGruppe produktGruppe = lvwProduktgrupper.getSelectionModel().getSelectedItem();
        if (produktGruppe != null) {
            Opret_Redigere_ProduktGruppe_Window dia = new Opret_Redigere_ProduktGruppe_Window("Update produktgruppe", produktGruppe);
            dia.showAndWait();

            int selectIndex = lvwProduktgrupper.getSelectionModel().getSelectedIndex();
            lvwProduktgrupper.getItems().setAll(Controller.getProduktGrupper());
            lvwProduktgrupper.getSelectionModel().select(selectIndex);

        }
    }

    private void opretProdukt() {
        ProduktGruppe produktGruppe = lvwProduktgrupper.getSelectionModel().getSelectedItem();
        if (produktGruppe != null) {
            Opret_Redigere_Produkt_Window dia = new Opret_Redigere_Produkt_Window("Opret Produkt",produktGruppe);
            dia.showAndWait();

            //venter til vinduet lukker
            lvwProdukter.getItems().setAll(produktGruppe.getProdukter());
        }
    }

    private void sletProdukt(){
        ProduktGruppe produktGruppe = lvwProduktgrupper.getSelectionModel().getSelectedItem();
        Produkt produkt = lvwProdukter.getSelectionModel().getSelectedItem();
        if (produkt != null) {
            Controller.sletProdukt(produktGruppe,produkt);
            lvwProdukter.getItems().setAll(produktGruppe.getProdukter());

        }

    }

    private void updateprodukt(){
        Produkt produkt = lvwProdukter.getSelectionModel().getSelectedItem();
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

}
