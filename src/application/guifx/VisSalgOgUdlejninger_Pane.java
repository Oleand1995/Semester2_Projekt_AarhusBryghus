package application.guifx;

import application.controller.Controller;
import application.model.Salg;
import application.model.*;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.time.temporal.ChronoUnit;


public class VisSalgOgUdlejninger_Pane extends GridPane {

    private ListView<Salg> lvwSalg;
    private ListView<Udlejning> lvwAktiveUdlejninger, lvwAfsluttedeUdlejninger;
    private ListView<OrdreLinje> lvwSalgOrdreLinjer;
    private DatePicker salgsDatoStart, salgsDatoSlut;
    private Controller controller;
    private Label lblSolgteKlip;
    private Label lblBrugteKlip;
    private int klipSolgt = 0;
    private int klippekortSolgt = 0;
    private int klipBrugt = 0;

    public VisSalgOgUdlejninger_Pane(){
        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(5);
        this.setGridLinesVisible(false);
        controller = Controller.getController();

        Label lblSalg = new Label("Salg: ");
        this.add(lblSalg,0,0);

        HBox hbxLblDatoer = new HBox(180);
        this.add(hbxLblDatoer,0,1);
        hbxLblDatoer.setPadding(new Insets(10, 0, 0, 0));
        hbxLblDatoer.setAlignment(Pos.BASELINE_CENTER);

        Label lblStart = new Label("StartDato: ");
        hbxLblDatoer.getChildren().add(lblStart);

        Label lblSlut = new Label("SlutDato: ");
        hbxLblDatoer.getChildren().add(lblSlut);

        HBox hbxDatoer = new HBox(50);
        this.add(hbxDatoer,0,2);
        hbxDatoer.setPadding(new Insets(0, 0, 0, 0));
        hbxDatoer.setAlignment(Pos.BASELINE_CENTER);

        salgsDatoStart = new DatePicker();
        hbxDatoer.getChildren().add(salgsDatoStart);
        salgsDatoStart.setOnAction(event -> this.DatoChanged());

        salgsDatoSlut = new DatePicker();
        hbxDatoer.getChildren().add(salgsDatoSlut);
        salgsDatoSlut.setOnAction(event -> this.DatoChanged());

        HBox hbxKlip = new HBox(100);
        this.add(hbxKlip,0,3);
        hbxKlip.setPadding(new Insets(0, 0, 0, 0));
        hbxKlip.setAlignment(Pos.BASELINE_CENTER);


        lblSolgteKlip = new Label("Solgte klip i perioden: " + klipSolgt + " på " + klippekortSolgt);
        hbxKlip.getChildren().add(lblSolgteKlip);

        lblBrugteKlip = new Label("Brugte klip i perioden: " + klipBrugt);
        hbxKlip.getChildren().add(lblBrugteKlip);

        lvwSalg = new ListView<>();
        lvwSalg.getItems().setAll(controller.getSalg());
        ChangeListener<Salg> listenerSalg = (ov, oldSalg, newSalg) -> this.updateControlsSalg();
        lvwSalg.getSelectionModel().selectedItemProperty().addListener(listenerSalg);
        this.add(lvwSalg,0,4,1,4);

        lvwSalgOrdreLinjer = new ListView<>();
        this.add(lvwSalgOrdreLinjer,1,4,1,4);
        lvwSalgOrdreLinjer.setPrefSize(300,300);

        Label lblAktUdlejninger = new Label("Aktive udlejninger ");
        this.add(lblAktUdlejninger,2,3);

        lvwAktiveUdlejninger = new ListView<>();
        lvwAktiveUdlejninger.getItems().setAll(controller.getAktiveUdlejninger());
        this.add(lvwAktiveUdlejninger,2,4,1,1);
        lvwAktiveUdlejninger.setPrefSize(300,200);

        Label lblAfsUdlejninger = new Label("Afsluttede udlejninger ");
        this.add(lblAfsUdlejninger,2,6);

        lvwAfsluttedeUdlejninger = new ListView<>();
        lvwAfsluttedeUdlejninger.getItems().setAll(controller.getAfsluttedeUdlejninger());
        this.add(lvwAfsluttedeUdlejninger,2,7,1,1);
        lvwAfsluttedeUdlejninger.setPrefSize(300,200);

        Button btnOpretSalg = new Button("Se/Afslut Udlejning");
        this.add(btnOpretSalg,2,5);
        btnOpretSalg.setOnAction(event -> this.opretSalg());

        Button btnSletSalg = new Button("Slet Salg");
        this.add(btnSletSalg, 0, 8);
        btnSletSalg.setOnAction(event -> this.sletSalg());

    }

    public void updateControls(){
        if (salgsDatoSlut.getValue() != null && salgsDatoSlut.getValue() != null){
            lvwSalg.getItems().setAll(controller.getSalgFromDato(salgsDatoStart.getValue(),salgsDatoSlut.getValue()));
        }

        lvwAktiveUdlejninger.getItems().setAll(controller.getAktiveUdlejninger());
        lvwSalgOrdreLinjer.getItems().clear();
        klipChanged();

    }

    public void updateControlsSalg(){
        this.selectedSalgChanged();
    }

    private void opretSalg(){
        Udlejning udlejning = lvwAktiveUdlejninger.getSelectionModel().getSelectedItem();
        if (udlejning != null){
            Opret_Salg_From_Udlejning_Window dia = new Opret_Salg_From_Udlejning_Window("Afslut udlejning",udlejning);
            dia.showAndWait();

            lvwSalg.getItems().setAll(controller.getSalgFromDato(salgsDatoStart.getValue(),salgsDatoSlut.getValue()));
            lvwAktiveUdlejninger.getItems().setAll(controller.getAktiveUdlejninger());
            lvwAfsluttedeUdlejninger.getItems().setAll(controller.getAfsluttedeUdlejninger());
        }
    }

    private void sletSalg(){
        Salg salg = lvwSalg.getSelectionModel().getSelectedItem();
        if(salg != null){
            for (Udlejning u : lvwAfsluttedeUdlejninger.getItems()){
                if (u.getAfregningsTidspunkt().truncatedTo(ChronoUnit.SECONDS).isEqual(salg.getSalgsTidspunkt().truncatedTo(ChronoUnit.SECONDS))){
                    controller.removeUdlejning(u);
                    controller.removeSalg(salg);
                }else{
                    controller.removeSalg(salg);
                }
            }
            lvwAfsluttedeUdlejninger.getItems().setAll(controller.getAfsluttedeUdlejninger());
            lvwSalg.getItems().setAll(controller.getSalg());
            lvwSalgOrdreLinjer.getItems().clear();
        }
    }

    public void selectedSalgChanged(){
        Salg salg = lvwSalg.getSelectionModel().getSelectedItem();
        if (salg != null){
            lvwSalgOrdreLinjer.getItems().setAll(salg.getOrdrelinjer());
        }
    }

    public void DatoChanged(){
        klipSolgt = 0;
        klippekortSolgt = 0;
        klipBrugt = 0;
        lblBrugteKlip.setText("Brugte klip i perioden: " + klipBrugt);
        lblSolgteKlip.setText("Solgte klip i perioden: " + klipSolgt + " på " + klippekortSolgt);
        if (salgsDatoSlut.getValue() != null && salgsDatoStart != null){
            if (salgsDatoStart.getValue().isBefore(salgsDatoSlut.getValue()) || salgsDatoStart.getValue().isEqual(salgsDatoSlut.getValue())){
                lvwSalg.getItems().setAll(controller.getSalgFromDato(salgsDatoStart.getValue(),salgsDatoSlut.getValue()));
                lvwSalgOrdreLinjer.getItems().clear();
                klipChanged();
            }
        }
    }

    public void klipChanged(){
        klipSolgt = 0;
        klippekortSolgt = 0;
        klipBrugt = 0;
                for (Salg s : lvwSalg.getItems()) {
                    for (OrdreLinje o : s.getOrdrelinjer()) {
                        if (o.getPris().getProdukt().getProduktgruppe().getProduktType().equalsIgnoreCase("klippekort")) {
                            klipSolgt += o.getAntal() * 4;
                            klippekortSolgt += o.getAntal();
                            lblSolgteKlip.setText("Solgte klip i perioden: " + klipSolgt + " på " + klippekortSolgt);
                        }
                    }
                    klipBrugt += s.getSamletKlip();
                    lblBrugteKlip.setText("Brugte klip i perioden: " + klipBrugt);
                }
            }
}
