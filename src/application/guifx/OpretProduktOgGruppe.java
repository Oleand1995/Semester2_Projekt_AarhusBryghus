package application.guifx;

import application.model.Produkt;
import application.model.ProduktGruppe;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class OpretProduktOgGruppe extends GridPane {

    private TextField txfProduktNavn, txfAntalPåLager;
    private TextArea txa;
    private ListView<ProduktGruppe> lvwProduktgrupper;
    private ListView<Produkt> lvwProdukter;
    private Button btnOpretProduktGruppe,btnSletProduktGruppe,btnÆndreProduktgruppe,btnOpretProdukt,btnSletProdukt,btnÆndreProdukt;


    public OpretProduktOgGruppe() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);


        Label lblProduktgrupper = new Label("Produktgrupper");
        this.add(lblProduktgrupper, 0, 1);

        lvwProduktgrupper = new ListView<>();
        this.add(lvwProduktgrupper,0 ,2,1,1);


        btnOpretProduktGruppe = new Button("Opret produktgruppe");
        this.add(btnOpretProduktGruppe,0 ,3);


        btnSletProduktGruppe = new Button("Slet produktgruppe");
        this.add(btnSletProduktGruppe,0 ,4);


        btnÆndreProduktgruppe = new Button("Ændre produktgruppe");
        this.add(btnÆndreProduktgruppe,0 ,5 );



        Label lblprodukter = new Label("produkter");
        this.add(lblprodukter, 1, 1);

        lvwProdukter = new ListView<>();
        this.add(lvwProdukter,1 ,2,1,1);

        btnOpretProdukt = new Button("Opret produkt");
        this.add(btnOpretProdukt,1 ,3);


        btnSletProdukt = new Button("Slet produkt");
        this.add(btnSletProdukt,1 ,4);


        btnÆndreProdukt = new Button("Ændre produkt");
        this.add(btnÆndreProdukt,1 ,5 );




    }

    // -------------------------------------------------------------------------

    private void createAction() {
        //	Vindue1Window dia = new Vindue1Window("Create X");
        //	dia.showAndWait();

        //se note, under akitektur.


    }

    private void updateAction() {

    }

    private void deleteAction() {

    }

    // -------------------------------------------------------------------------

    public void updateControls() {

    }

}
