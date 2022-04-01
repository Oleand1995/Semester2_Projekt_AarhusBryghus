package application.guifx;

import application.controller.Controller;
import application.model.Produkt;
import application.model.Salg;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class Statistik_Pane extends GridPane {

    private ListView<Salg> lvwSalg;
    private ListView<Produkt> lvwProduter;

    public Statistik_Pane(){
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        Label lblAfsluttedeSalg = new Label("Afsluttede Salg:");
        this.add(lblAfsluttedeSalg, 0, 1);

        lvwSalg = new ListView<>();
        this.add(lvwSalg, 0, 2, 1, 1);
        lvwSalg.getItems().setAll(Controller.getSalg());

        Label lblProdukter = new Label("Solgte Produkter:");
        this.add(lblProdukter, 1, 1);

        lvwProduter = new ListView<>();
        this.add(lvwProduter, 1, 2, 1, 1);

        TextField txfIndtjening = new TextField();
        this.add(txfIndtjening, 0, 4);
        txfIndtjening.setEditable(false);

        Label lblIndtjening = new Label("Indtjent");
        this.add(lblIndtjening, 0, 3);

    }

//    private void updateprodukt(){
//        Produkt produkt = lvwProdukter.getSelectionModel().getSelectedItem();
//        if (produkt != null) {
//            Opret_Redigere_Produkt_Window dia = new Opret_Redigere_Produkt_Window("Update produktgruppe", null,produkt);
//            dia.showAndWait();
//
//            int selectIndex = lvwProduktgrupper.getSelectionModel().getSelectedIndex();
//            lvwProduktgrupper.getItems().setAll(Controller.getProduktGrupper());
//            lvwProduktgrupper.getSelectionModel().select(selectIndex);
//
//        }
}
