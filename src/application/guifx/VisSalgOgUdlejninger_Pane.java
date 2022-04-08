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

import java.time.LocalDate;
import java.util.Date;


public class VisSalgOgUdlejninger_Pane extends GridPane {

    private ListView<Salg> lvwSalg;
    private ListView<Udlejning> lvwAktiveUdlejninger, lvwAfsluttedeUdlejninger;
    private ListView<OrdreLinje> lvwSalgOrdreLinjer;
    private DatePicker salgsDatoStart, salgsDatoSlut;

    public VisSalgOgUdlejninger_Pane(){
        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(5);
        this.setGridLinesVisible(false);

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

        lvwSalg = new ListView<>();
        lvwSalg.getItems().setAll(Controller.getSalg());
        ChangeListener<Salg> listenerSalg = (ov, oldSalg, newSalg) -> this.updateControlsSalg();
        lvwSalg.getSelectionModel().selectedItemProperty().addListener(listenerSalg);
        this.add(lvwSalg,0,3,1,4);

        lvwSalgOrdreLinjer = new ListView<>();
        this.add(lvwSalgOrdreLinjer,1,3,1,4);
        lvwSalgOrdreLinjer.setPrefSize(300,300);

        Label lblAktUdlejninger = new Label("Aktive udlejninger ");
        this.add(lblAktUdlejninger,2,2);

        lvwAktiveUdlejninger = new ListView<>();
        lvwAktiveUdlejninger.getItems().setAll(Controller.getAktiveUdlejninger());
        this.add(lvwAktiveUdlejninger,2,3,1,1);
        lvwAktiveUdlejninger.setPrefSize(300,200);

        Label lblAfsUdlejninger = new Label("Afsluttede udlejninger ");
        this.add(lblAfsUdlejninger,2,5);

        lvwAfsluttedeUdlejninger = new ListView<>();
        lvwAfsluttedeUdlejninger.getItems().setAll(Controller.getAfsluttedeUdlejninger());
        this.add(lvwAfsluttedeUdlejninger,2,6,1,1);
        lvwAfsluttedeUdlejninger.setPrefSize(300,200);

        Button btnOpretSalg = new Button("Se/Afslut Udlejning");
        this.add(btnOpretSalg,2,4);
        btnOpretSalg.setOnAction(event -> this.opretSalg());

    }

    public void updateControls(){
        if (salgsDatoSlut.getValue() != null && salgsDatoSlut.getValue() != null){
            lvwSalg.getItems().setAll(Controller.getSalgFromDato(salgsDatoStart.getValue(),salgsDatoSlut.getValue()));
        }

        lvwAktiveUdlejninger.getItems().setAll(Controller.getAktiveUdlejninger());
        lvwSalgOrdreLinjer.getItems().clear();

    }

    public void updateControlsSalg(){
        this.selectedSalgChanged();
    }

    private void opretSalg(){
        Udlejning udlejning = lvwAktiveUdlejninger.getSelectionModel().getSelectedItem();
        if (udlejning != null){
            Opret_Salg_From_Udlejning_Window dia = new Opret_Salg_From_Udlejning_Window("Afslut udlejning",udlejning);
            dia.showAndWait();

            lvwSalg.getItems().setAll(Controller.getSalgFromDato(salgsDatoStart.getValue(),salgsDatoSlut.getValue()));
            lvwAktiveUdlejninger.getItems().setAll(Controller.getAktiveUdlejninger());
            lvwAfsluttedeUdlejninger.getItems().setAll(Controller.getAfsluttedeUdlejninger());
        }
    }

    public void selectedSalgChanged(){
        Salg salg = lvwSalg.getSelectionModel().getSelectedItem();
        if (salg != null){
            lvwSalgOrdreLinjer.getItems().setAll(salg.getOrdrelinjer());
        }
    }

    public void DatoChanged(){
        if (salgsDatoSlut.getValue() != null && salgsDatoStart != null){
            if (salgsDatoStart.getValue().isBefore(salgsDatoSlut.getValue()) || salgsDatoStart.getValue().isEqual(salgsDatoSlut.getValue())){
                lvwSalg.getItems().setAll(Controller.getSalgFromDato(salgsDatoStart.getValue(),salgsDatoSlut.getValue()));
                lvwSalgOrdreLinjer.getItems().clear();
            }
        }
    }
}
