package application.guifx;

import application.controller.Controller;
import application.model.Produkt;
import application.model.ProduktGruppe;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;

public class Opret_Redigere_Produkt_Window extends Stage {

    private ProduktGruppe produktGruppe;
    private Produkt produkt;

    public Opret_Redigere_Produkt_Window(String title, ProduktGruppe produktGruppe, Produkt produkt) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.produktGruppe = produktGruppe;
        this.produkt = produkt;


        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    public Opret_Redigere_Produkt_Window(String title,ProduktGruppe produktGruppe) {
        this(title,produktGruppe,null);
    }


    // -------------------------------------------------------------------------
    private TextField txfProduktBeskrivelse;
    private Label lblError;
    private Controller controller;


    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);
        controller = Controller.getController();

        Label lblName = new Label("Produkt beskrivelse");
        pane.add(lblName, 0, 0);

        txfProduktBeskrivelse = new TextField();
        pane.add(txfProduktBeskrivelse, 0, 1);
        txfProduktBeskrivelse.setPrefWidth(200);

        Button btnAnnuller = new Button("Annuller");
        pane.add(btnAnnuller, 0, 2);
        GridPane.setHalignment(btnAnnuller, HPos.LEFT);
        btnAnnuller.setOnAction(event -> annullerAction());

        Button btnOK = new Button("OK");
        pane.add(btnOK, 1, 2);
        GridPane.setHalignment(btnOK, HPos.RIGHT);
        btnOK.setOnAction(event -> okAction());

        lblError = new Label();
        pane.add(lblError, 0, 3);
        lblError.setStyle("-fx-text-fill: red");

        initControls();

    }

    private void initControls() {
       if (produkt != null) {
           txfProduktBeskrivelse.setText(produkt.getBeskrivelse());
        }
    }

    private void annullerAction() {
        hide();
    }


    private void okAction(){
        String produktbeskrivelse = txfProduktBeskrivelse.getText().trim();
        if (produktbeskrivelse.length() == 0){
            lblError.setText("Produktbeskrivelse skal være udfyldt");
        }
        else if (produkt != null){
            produkt.setBeskrivelse(produktbeskrivelse);
            hide();
        }
        else {
            controller.createProdukt(produktbeskrivelse,produktGruppe);
            hide();
        }
    }

}
