package application.guifx;

import application.controller.Controller;
import application.model.Betalingsmåder;
import application.model.OrdreLinje;
import application.model.Udlejning;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Opret_Salg_From_Udlejning_Window extends Stage {

    private Udlejning udlejning;
    private Controller controller;

    public Opret_Salg_From_Udlejning_Window(String title, Udlejning udlejning){
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);
        controller = Controller.getController();
        this.udlejning = udlejning;


        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    private ListView<OrdreLinje> lvwOrdrelinjer;
    private TextField txfReturBeloeb;
    private double returBeloeb = 0;
    private Button btnAfslutUdlejning;
    private CheckBox chbTilbagebetalt;
    private ComboBox<Betalingsmåder> cbbBetalingsmaader;

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);


        lvwOrdrelinjer = new ListView<>();
        lvwOrdrelinjer.getItems().setAll(udlejning.getOrdrelinjer());
        pane.add(lvwOrdrelinjer,0,0);

        Button btnFjernOrdreLinje = new Button("Returner vare");
        pane.add(btnFjernOrdreLinje,0,1);
        btnFjernOrdreLinje.setPrefSize(250,20);
        btnFjernOrdreLinje.setOnAction(event -> this.returnerVare());

        Label lblReturBeloeb = new Label("Returbeløb: ");
        pane.add(lblReturBeloeb,0,2);

        txfReturBeloeb = new TextField();
        pane.add(txfReturBeloeb,0,3);
        txfReturBeloeb.setPrefSize(250,20);
        txfReturBeloeb.setEditable(false);

        chbTilbagebetalt = new CheckBox("Tilbagebetalt");
        pane.add(chbTilbagebetalt,0,4);
        chbTilbagebetalt.setOnAction(event -> this.tilbagebetaltChecked());
        chbTilbagebetalt.setAlignment(Pos.CENTER_RIGHT);

        cbbBetalingsmaader = new ComboBox<>();
        cbbBetalingsmaader.getItems().setAll(Betalingsmåder.values());
        pane.add(cbbBetalingsmaader,0,5);

        btnAfslutUdlejning = new Button("Afslut Udlejning");
        pane.add(btnAfslutUdlejning,0,6);
        btnAfslutUdlejning.setPrefSize(250,20);
        btnAfslutUdlejning.setOnAction(event -> this.afslutUdlejning());
        btnAfslutUdlejning.setDisable(true);


    }

    private void returnerVare(){
        OrdreLinje ordreLinje = lvwOrdrelinjer.getSelectionModel().getSelectedItem();
        if (ordreLinje != null) {
            if (ordreLinje.getAntal() > 1) {
                ordreLinje.countDown();
                lvwOrdrelinjer.getItems().setAll(udlejning.getOrdrelinjer());
                lvwOrdrelinjer.getSelectionModel().select(ordreLinje);
                returBeloeb += ordreLinje.getPris().getPris();
                txfReturBeloeb.setText(returBeloeb + "");
            } else {
                lvwOrdrelinjer.getItems().remove(ordreLinje);
                returBeloeb += ordreLinje.getPris().getPris();
                txfReturBeloeb.setText(returBeloeb + "");
            }
        }
    }

    private void afslutUdlejning(){
        Betalingsmåder betalingsmåder = cbbBetalingsmaader.getSelectionModel().getSelectedItem();
        if (betalingsmåder != null){
            controller.setAfrejningstidpunkt(udlejning);
            controller.setSamletPrisPåUdlejning(udlejning,lvwOrdrelinjer.getItems());
            ArrayList<OrdreLinje> ordreLinjer = new ArrayList<>();
            ordreLinjer.addAll(lvwOrdrelinjer.getItems());
            controller.createSalg(LocalDateTime.now(),ordreLinjer,0,controller.getSamletPris(lvwOrdrelinjer.getItems()),betalingsmåder);
            close();
        }
    }

    private void tilbagebetaltChecked(){
        if (chbTilbagebetalt.isSelected()){
            btnAfslutUdlejning.setDisable(false);
        }
        else{
            btnAfslutUdlejning.setDisable(true);
        }
    }


}
