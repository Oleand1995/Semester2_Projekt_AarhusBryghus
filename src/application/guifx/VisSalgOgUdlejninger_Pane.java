package application.guifx;

import application.controller.Controller;
import application.model.Salg;
import application.model.*;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.control.*;

import java.time.LocalDateTime;


public class VisSalgOgUdlejninger_Pane extends GridPane {

    private ListView<Salg> lvwSalg;
    private ListView<Udlejning> lvwUdlejninger;
    private ListView<OrdreLinje> lvwSalgOrdreLinjer;

    public VisSalgOgUdlejninger_Pane(){
        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        Label lblSalg = new Label("Salg: ");
        this.add(lblSalg,0,0);

        lvwSalg = new ListView<>();
        lvwSalg.getItems().setAll(Controller.getSalg());
        ChangeListener<Salg> listenerSalg = (ov, oldSalg, newSalg) -> this.updateControlsSalg();
        lvwSalg.getSelectionModel().selectedItemProperty().addListener(listenerSalg);
        this.add(lvwSalg,0,1);

        lvwSalgOrdreLinjer = new ListView<>();
        this.add(lvwSalgOrdreLinjer,1,1);

        Label lblUdlejninger = new Label("Udlejninger: ");
        this.add(lblUdlejninger,2,0);

        lvwUdlejninger = new ListView<>();
        lvwUdlejninger.getItems().setAll(Controller.getUdlejninger());
        this.add(lvwUdlejninger,2,1);

        Button btnOpretSalg = new Button("Afslut Udlejning");
        this.add(btnOpretSalg,3,0);
        btnOpretSalg.setOnAction(event -> this.opretSalg());

    }

    public void updateControls(){
        lvwSalg.getItems().setAll(Controller.getSalg());
        lvwUdlejninger.getItems().setAll(Controller.getUdlejninger());
        lvwSalgOrdreLinjer.getItems().clear();
    }

    public void updateControlsSalg(){
        this.selectedSalgChanged();
    }

    private void opretSalg(){
        Udlejning udlejning = lvwUdlejninger.getSelectionModel().getSelectedItem();
        if (udlejning != null){
            Opret_Salg_From_Udlejning_Window dia = new Opret_Salg_From_Udlejning_Window("Afslut udlejning",udlejning);
            dia.showAndWait();

            lvwSalg.getItems().setAll(Controller.getSalg());
            lvwUdlejninger.getItems().setAll(Controller.getUdlejninger());
        }
    }

    public void selectedSalgChanged(){
        Salg salg = lvwSalg.getSelectionModel().getSelectedItem();
        if (salg != null){
            lvwSalgOrdreLinjer.getItems().setAll(salg.getOrdrelinjer());
        }
    }
}
