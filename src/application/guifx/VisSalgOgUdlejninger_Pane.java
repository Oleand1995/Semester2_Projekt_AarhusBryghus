package application.guifx;

import application.controller.Controller;
import application.model.Salg;
import application.model.*;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;



public class VisSalgOgUdlejninger_Pane extends GridPane {

    private ListView<Salg> lvwSalg;
    private ListView<Udlejning> lvwUdlejninger;

    public VisSalgOgUdlejninger_Pane(){
        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        Label lblSalg = new Label("Salg: ");
        this.add(lblSalg,0,0);

        lvwSalg = new ListView<>();
        lvwSalg.getItems().setAll(Controller.getSalg());
        this.add(lvwSalg,0,1);

        Label lblUdlejninger = new Label("Udlejninger: ");
        this.add(lblUdlejninger,1,0);

        lvwUdlejninger = new ListView<>();
        lvwUdlejninger.getItems().setAll(Controller.getUdlejninger());
        this.add(lvwUdlejninger,1,1);

    }

    public void updateControls(){
        lvwSalg.getItems().setAll(Controller.getSalg());
        lvwUdlejninger.getItems().setAll(Controller.getUdlejninger());
    }
}
