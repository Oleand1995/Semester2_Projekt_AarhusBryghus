package application.guifx;

import application.controller.Controller;
import application.model.Pris;
import application.model.Prisliste;
import application.model.Produkt;
import javafx.beans.value.ChangeListener;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Opret_Redigere_PrislisteProdukter_Window extends Stage {

    private Prisliste prisliste;
    private Produkt produkt;

    public Opret_Redigere_PrislisteProdukter_Window(String title,Prisliste prisliste) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.prisliste = prisliste;

        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }



    // -------------------------------------------------------------------------
    private TextField txfPrispåProdukt;
    private Label lblError;
    private ListView<Produkt> lvwprodukter;


    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        Label lblName = new Label("produkter");
        pane.add(lblName, 0, 0);

        lvwprodukter = new ListView<>();
        pane.add(lvwprodukter,0 ,1,1,1);
        lvwprodukter.getItems().setAll(Controller.getProdukter());


        Label lblPrisPåProdukt = new Label("Pris på produkt");
        pane.add(lblPrisPåProdukt, 0, 2);

        txfPrispåProdukt = new TextField();
        pane.add(txfPrispåProdukt,0 ,3);
        txfPrispåProdukt.setPrefWidth(200);

        Button btnAnnuller = new Button("Annuller");
        pane.add(btnAnnuller, 0, 4);
        GridPane.setHalignment(btnAnnuller, HPos.LEFT);
        btnAnnuller.setOnAction(event -> annullerAction());

        Button btnOK = new Button("OK");
        pane.add(btnOK, 1, 4);
        GridPane.setHalignment(btnOK, HPos.RIGHT);
        btnOK.setOnAction(event -> okAction());

        lblError = new Label();
        pane.add(lblError, 0, 5);
        lblError.setStyle("-fx-text-fill: red");


    }

    private void annullerAction() {
        hide();
    }


    private void okAction(){

        //Finder og tjekker om det der er indtastet er et tal.
        Produkt produkt = lvwprodukter.getSelectionModel().getSelectedItem();
        int prispåprodukt = -1;
        try {
            prispåprodukt = Integer.parseInt(txfPrispåProdukt.getText().trim());
        }catch (NumberFormatException ex){
        }

        //Tjekker om tallet er positivt.
        if (prispåprodukt < 0){
            lblError.setText("Tallet skal være positivt");
        }

        else if (produkt == null){
            lblError.setText("Der skal vælges et produkt");
        }
        else {
            prisliste.createPris(prispåprodukt,produkt);
            hide();
        }
    }




}
