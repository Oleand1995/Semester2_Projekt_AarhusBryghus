package application.guifx;

import application.controller.Controller;
import application.model.Prisliste;
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

public class Opret_Redigere_Prisliste_Window extends Stage {

    private Prisliste prisliste;

    public Opret_Redigere_Prisliste_Window(String title, Prisliste prisliste) {
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

    public Opret_Redigere_Prisliste_Window(String title) {
        this(title,null);
    }


    // -------------------------------------------------------------------------
    private TextField txfPrislisteNavn;
    private Label lblError;


    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        Label lblName = new Label("Prisliste navn");
        pane.add(lblName, 0, 0);

        txfPrislisteNavn = new TextField();
        pane.add(txfPrislisteNavn, 0, 1);
        txfPrislisteNavn.setPrefWidth(200);

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
     if (prisliste != null) {
            txfPrislisteNavn.setText(prisliste.getNavn());
        }
    }

    private void annullerAction() {
        hide();
    }


    private void okAction(){
        String prisListeNavn = txfPrislisteNavn.getText().trim();
        if (prisListeNavn.length() == 0){
            lblError.setText("Produktbeskrivelse skal være udfyldt");
        }
        else if (prisliste != null) {
            prisliste.setNavn(prisListeNavn);
            hide();
        }
        else {
            Controller.createPrisliste(prisListeNavn);
            hide();
        }
    }






}
