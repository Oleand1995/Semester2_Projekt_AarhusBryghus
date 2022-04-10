package application.guifx;

import application.controller.Controller;
import application.model.*;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import storage.Storage;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class OpretSalgVindue extends GridPane {

    private TextField txfLejersNavn;
    private ListView<OrdreLinje> lvwIndkøbsliste;
    private ListView<Pris> lvwProdukter;
    private ComboBox<Prisliste> cbbPrisListe;
    private ComboBox<Betalingsmåder> cbbBetalingsMåder;
    private TextField txfPrisIndkøbsliste, txfAntal, txfFastRabatEllerProcent;
    private Button addButton, btnOpretUdlejning, godkendRabat;
    private CheckBox chbProcent, chbFastPris;
    private HBox hbxRabat;
    private Label lblError;
    private Controller controller;

    public OpretSalgVindue() {
        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(10);
        this.setGridLinesVisible(false);
        controller = Controller.getController();

        cbbPrisListe = new ComboBox<>();
        this.add(cbbPrisListe, 0, 0);
        cbbPrisListe.getItems().setAll(controller.getPrislister());
        ChangeListener<Prisliste> listenerPrisliste = (ov, oldPrisliste, newPrisliste) -> this.selectedPrislisteChanged();
        cbbPrisListe.getSelectionModel().selectedItemProperty().addListener(listenerPrisliste);

        cbbBetalingsMåder = new ComboBox<>();
        cbbBetalingsMåder.getItems().setAll(Betalingsmåder.values());
        ChangeListener<Betalingsmåder> listenerBetalingsmåde = (ov, oldBetalingsmaade, newBetalingsmaade) -> this.selectedBetalingsmpdeChanged();
        cbbBetalingsMåder.getSelectionModel().selectedItemProperty().addListener(listenerBetalingsmåde);
        this.add(cbbBetalingsMåder, 1, 0);
        cbbBetalingsMåder.setDisable(true);

        Button btnKlippekort = new Button("Tilføj Klippekort");
        this.add(btnKlippekort, 2, 0);

        Label lblProdukter = new Label("Produkter:");
        this.add(lblProdukter, 0, 1);

        lvwProdukter = new ListView<>();
        ChangeListener<Pris> listenerProdukt = (ov, oldProdukt, newProdukt) -> this.selectedProduktChanged();
        lvwProdukter.getSelectionModel().selectedItemProperty().addListener(listenerProdukt);
        this.add(lvwProdukter, 0, 2, 2, 4);
        lvwProdukter.setDisable(true);


        VBox vbxButtons = new VBox(20);
        this.add(vbxButtons, 2, 4);
        vbxButtons.setPadding(new Insets(10, 0, 0, 0));
        vbxButtons.setAlignment(Pos.BASELINE_CENTER);

        addButton = new Button("→");
        vbxButtons.getChildren().add(addButton);
        addButton.setOnAction(event -> this.TilføjTilIndkoebskurv());

        Button btnRemove = new Button("←");
        vbxButtons.getChildren().add(btnRemove);
        btnRemove.setOnAction(event -> this.fjernFraIndkoebskurv());

        Label lblIndkoeb = new Label("Indkøbsliste:");
        this.add(lblIndkoeb, 3, 1, 1, 2);

        lvwIndkøbsliste = new ListView<>();
        this.add(lvwIndkøbsliste, 3, 2, 2, 4);
        ChangeListener<OrdreLinje> listenerordrelinje = (ov, oldOrdreLinje, newOrdreLinje) -> this.valgtOrdrelinje();
        lvwIndkøbsliste.getSelectionModel().selectedItemProperty().addListener(listenerordrelinje);


        HBox indkøbsListePris = new HBox(20);
        this.add(indkøbsListePris, 3, 6);
        indkøbsListePris.setPadding(new Insets(10, 0, 0, 50));
        indkøbsListePris.setAlignment(Pos.BOTTOM_LEFT);

        Label lblIndkøbslistePris = new Label("Indkøbsliste samlet pris: ");
        indkøbsListePris.getChildren().add(lblIndkøbslistePris);

        txfPrisIndkøbsliste = new TextField();
        indkøbsListePris.getChildren().add(txfPrisIndkøbsliste);
        txfPrisIndkøbsliste.setEditable(false);


        VBox vbxAntal = new VBox(10);
        this.add(vbxAntal, 5, 3);
        vbxAntal.setPadding(new Insets(10, 0, 0, 0));
        vbxAntal.setAlignment(Pos.BOTTOM_LEFT);

        HBox hbxAntal = new HBox(20);
        vbxAntal.getChildren().add(hbxAntal);
        hbxAntal.setPadding(new Insets(10, 0, 0, 50));
        hbxAntal.setAlignment(Pos.BASELINE_CENTER);

        Button antalPlusKnap = new Button("+");
        hbxAntal.getChildren().add(antalPlusKnap);
        antalPlusKnap.setOnAction(event -> this.antalPlusKnap());
        antalPlusKnap.setPrefSize(30, 30);

        Button antalMinusKnap = new Button("-");
        hbxAntal.getChildren().add(antalMinusKnap);
        antalMinusKnap.setOnAction(event -> this.antalMinusKnap());
        antalMinusKnap.setPrefSize(30, 30);

        Button antalGodkend = new Button("✔");
        hbxAntal.getChildren().add(antalGodkend);
        antalGodkend.setOnAction(event -> this.godkendAntal());
        antalGodkend.setPrefSize(30, 30);

        HBox hbxAntal1 = new HBox(20);
        vbxAntal.getChildren().add(hbxAntal1);
        hbxAntal1.setPadding(new Insets(10, 0, 0, 0));
        hbxAntal1.setAlignment(Pos.BASELINE_CENTER);

        Label lblAntal = new Label("Antal: ");
        hbxAntal1.getChildren().add(lblAntal);

        txfAntal = new TextField();
        hbxAntal1.getChildren().add(txfAntal);
        txfAntal.setEditable(false);


        VBox vbxUdlejningOgSalg = new VBox(20);
        this.add(vbxUdlejningOgSalg, 5, 5, 3, 1);
        vbxUdlejningOgSalg.setPadding(new Insets(10, 0, 0, 0));
        vbxUdlejningOgSalg.setAlignment(Pos.BOTTOM_LEFT);

        HBox hbxSalg = new HBox(56);
        vbxUdlejningOgSalg.getChildren().add(hbxSalg);
        hbxSalg.setPadding(new Insets(10, 0, 0, 0));
        hbxSalg.setAlignment(Pos.CENTER_LEFT);

        Label lblSalg = new Label("Salg: ");
        hbxSalg.getChildren().add(lblSalg);

        Button opretSalg = new Button("Opret Salg");
        hbxSalg.getChildren().add(opretSalg);
        opretSalg.setOnAction(event -> this.opretSalg());


        HBox hbxUdlejning = new HBox(28);
        vbxUdlejningOgSalg.getChildren().add(hbxUdlejning);
        hbxUdlejning.setPadding(new Insets(10, 0, 0, 0));
        hbxUdlejning.setAlignment(Pos.BOTTOM_LEFT);

        Label lblUdlejning = new Label("Udlejning: ");
        hbxUdlejning.getChildren().add(lblUdlejning);

        btnOpretUdlejning = new Button("Opret Udlejning");
        hbxUdlejning.getChildren().add(btnOpretUdlejning);
        btnOpretUdlejning.setOnAction(event -> this.opretUdlejning());
        btnOpretUdlejning.setDisable(true);

        HBox hbxLejersNavn = new HBox(20);
        vbxUdlejningOgSalg.getChildren().add(hbxLejersNavn);
        hbxLejersNavn.setPadding(new Insets(10, 0, 0, 0));
        hbxLejersNavn.setAlignment(Pos.BOTTOM_LEFT);

        Label lblLejersNavn = new Label("Lejers navn: ");
        hbxLejersNavn.getChildren().add(lblLejersNavn);

        txfLejersNavn = new TextField();
        hbxLejersNavn.getChildren().add(txfLejersNavn);
        ChangeListener<String> listenerTxfLejersNavn = (ov, oldOrdreLinje, newOrdreLinje) -> this.checkUdlejningsNavn();
        txfLejersNavn.textProperty().addListener(listenerTxfLejersNavn);


        hbxRabat = new HBox(20);
        this.add(hbxRabat, 3, 7);
        hbxRabat.setPadding(new Insets(10, 0, 0, 0));
        hbxRabat.setAlignment(Pos.BOTTOM_LEFT);

        Label lblFastRabatEllerProcent = new Label("Pris eller procent");
        hbxRabat.getChildren().add(lblFastRabatEllerProcent);

        txfFastRabatEllerProcent = new TextField();
        hbxRabat.getChildren().add(txfFastRabatEllerProcent);
        txfFastRabatEllerProcent.setDisable(true);

        Label lblFastRabat = new Label("Fast rabat");
        hbxRabat.getChildren().add(lblFastRabat);

        chbFastPris = new CheckBox();
        hbxRabat.getChildren().add(chbFastPris);
        chbFastPris.setOnAction(event -> this.valgtFastRabat());

        Label lblchbProcent = new Label("Procent rabat");
        hbxRabat.getChildren().add(lblchbProcent);

        chbProcent = new CheckBox();
        hbxRabat.getChildren().add(chbProcent);
        chbProcent.setOnAction(event -> ValgtProcentRabat());


        godkendRabat = new Button("✔");
        hbxRabat.getChildren().add(godkendRabat);
        godkendRabat.setOnAction(event -> tilføjRabat());
        godkendRabat.setDisable(true);

        lblError = new Label();
        this.add(lblError, 3, 8);

    }

    // -------------------------------------------------------------------------

    private void TilføjTilIndkoebskurv() {
        Pris pris = lvwProdukter.getSelectionModel().getSelectedItem();
        if (pris != null) {
            OrdreLinje ordreLinje = controller.createOrdreLinje(pris);
            lvwProdukter.getItems().remove(pris);
            lvwIndkøbsliste.getItems().add(ordreLinje);
            setSamletPris();
            lblError.setText("");
        }
    }

    private void fjernFraIndkoebskurv() {
        OrdreLinje ordreLinje = lvwIndkøbsliste.getSelectionModel().getSelectedItem();
        if (ordreLinje != null) {
            lvwIndkøbsliste.getItems().remove(ordreLinje);
            lvwProdukter.getItems().add(ordreLinje.getPris());
            setSamletPris();
        }
    }


    public void valgtOrdrelinje() {
        OrdreLinje ordreLinje = lvwIndkøbsliste.getSelectionModel().getSelectedItem();
        if (ordreLinje != null) {
            txfAntal.setText(ordreLinje.getAntal() + "");
            lblError.setText("");
        }


    }

    public void tilføjRabat() {
        OrdreLinje ordreLinje = lvwIndkøbsliste.getSelectionModel().getSelectedItem();

        if (ordreLinje != null && txfFastRabatEllerProcent.getText().trim().length() != 0) {

            if (chbProcent.isSelected()) {
                double rabatProcent = Double.parseDouble(txfFastRabatEllerProcent.getText().trim());
                if (rabatProcent <= 100.0 && rabatProcent >= 0.0) {
                    controller.tilføjProcentRabatTilOrdrelinje(ordreLinje, rabatProcent);
                    lblError.setStyle("-fx-text-fill: green");
                    lblError.setText("procent rabat på " + rabatProcent + "% er tilføjet til ordreLinje");
                } else {
                    lblError.setStyle("-fx-text-fill: red");
                    lblError.setText("Procent skal være mellem 0 og 100");
                }
            } else if (chbFastPris.isSelected()) {
                double fastRabat = Double.parseDouble(txfFastRabatEllerProcent.getText().trim());
                if (fastRabat <= ordreLinje.getPris().getPris() * ordreLinje.getAntal()) {
                    controller.tilføjFastRabatTilOrdrelinje(ordreLinje, fastRabat);
                    lblError.setStyle("-fx-text-fill: green");
                    lblError.setText("Fast rabat på " + fastRabat + "kr er tilføjet til ordrelinje");
                } else {
                    lblError.setStyle("-fx-text-fill: red");
                    lblError.setText("Rabaten kan ikke være større end ordre linjens fulde pris");
                }

            }
            txfPrisIndkøbsliste.setText(controller.getSamletPris(lvwIndkøbsliste.getItems()) + "");
            opdaterIndkøbsliste();
            clearRabat();
        }
    }


    private void antalPlusKnap() {
        if (!txfAntal.getText().isEmpty()) {
            int antal = Integer.parseInt(txfAntal.getText().trim());
            antal++;
            txfAntal.setText(antal + "");
        }

    }

    private void antalMinusKnap() {
        if (!txfAntal.getText().isEmpty()) {
            int antal = Integer.parseInt(txfAntal.getText().trim());
            antal--;
            txfAntal.setText(antal + "");
        }
    }

    private void godkendAntal() {
        OrdreLinje ordreLinje = lvwIndkøbsliste.getSelectionModel().getSelectedItem();
        if (ordreLinje != null) {
            if (Integer.parseInt(txfAntal.getText()) > 0){
                controller.setAntalPåOrdreLinje(ordreLinje, Integer.parseInt(txfAntal.getText()));
                if (ordreLinje.getRabatBeregning() instanceof FastRabat) {
                    ordreLinje.setRabatBeregning(null);
                    lblError.setStyle("-fx-text-fill: red");
                    lblError.setText("Ups: Rabat fjernet fra ordrelinje, skal tilføjes igen hvis ønskes");
                }
                opdaterIndkøbsliste();
                txfAntal.clear();
                setSamletPris();
            }
        }
    }

    private void opdaterIndkøbsliste() {
        ArrayList<OrdreLinje> ordreLinjer = new ArrayList<OrdreLinje>(lvwIndkøbsliste.getItems());
        lvwIndkøbsliste.getItems().setAll(ordreLinjer);
    }


    private void checkUdlejningsNavn() {
        if (txfLejersNavn.getText().length() != 0) {
            btnOpretUdlejning.setDisable(false);
        } else {
            btnOpretUdlejning.setDisable(true);
        }
    }

    private void opretSalg() {
        Betalingsmåder betalingsmåde = cbbBetalingsMåder.getSelectionModel().getSelectedItem();
        if (!lvwIndkøbsliste.getItems().isEmpty()) {
            ArrayList<OrdreLinje> ordrelinjer = new ArrayList<>();
            ordrelinjer.addAll(lvwIndkøbsliste.getItems());

            if (betalingsmåde == Betalingsmåder.Klippekort) {
                controller.createSalg(LocalDateTime.now(), ordrelinjer, Integer.parseInt(txfPrisIndkøbsliste.getText()), 0);
                clearAll();
            } else {
                controller.createSalg(LocalDateTime.now(), ordrelinjer, 0, Double.parseDouble(txfPrisIndkøbsliste.getText()));
                clearAll();
            }
        } else {
            lblError.setStyle("-fx-text-fill: red");
            lblError.setText("Ups: Du kan ikke oprette et salg, før du har lavet en indkøbsliste");
        }
    }

    private void opretUdlejning() {
        if (!lvwIndkøbsliste.getItems().isEmpty()) {
            Betalingsmåder betalingsmåde = cbbBetalingsMåder.getSelectionModel().getSelectedItem();
            ArrayList<OrdreLinje> ordrelinjer = new ArrayList<>();
            ordrelinjer.addAll(lvwIndkøbsliste.getItems());
            controller.createUdlejning(LocalDateTime.now(), Double.parseDouble(txfPrisIndkøbsliste.getText()), txfLejersNavn.getText(), ordrelinjer);
            clearAll();
        } else {
            lblError.setStyle("-fx-text-fill: red");
            lblError.setText("Ups: Du kan ikke oprette en udlejning, før du har lavet en indkøbsliste");
        }
    }

    private void clearAll() {
        lvwIndkøbsliste.getItems().clear();
        txfPrisIndkøbsliste.clear();
        txfLejersNavn.clear();
        txfAntal.clear();
        lvwProdukter.getItems().clear();
        //cbbPrisListe.getSelectionModel().clearSelection();
        cbbBetalingsMåder.getSelectionModel().clearSelection();
        lvwProdukter.setDisable(true);
        lblError.setText("");
        clearRabat();
    }

    private void clearRabat() {
        txfFastRabatEllerProcent.clear();
        txfFastRabatEllerProcent.setDisable(true);
        godkendRabat.setDisable(true);
        chbProcent.setSelected(false);
        chbFastPris.setSelected(false);
    }

    private void setSamletPris() {
        if (cbbBetalingsMåder.getSelectionModel().getSelectedItem() == Betalingsmåder.Klippekort) {
            txfPrisIndkøbsliste.setText(controller.getSamletKlip(lvwIndkøbsliste.getItems()) + "");
        } else {
            txfPrisIndkøbsliste.setText(controller.getSamletPris(lvwIndkøbsliste.getItems()) + "");
        }
    }

    public void valgtFastRabat() {
        chbProcent.setSelected(false);
        Chancevisit();
    }

    public void ValgtProcentRabat() {
        chbFastPris.setSelected(false);
        Chancevisit();
    }

    public void Chancevisit() {
        if (!chbFastPris.isSelected() && !chbProcent.isSelected()) {
            txfFastRabatEllerProcent.setDisable(true);
            godkendRabat.setDisable(true);
            txfFastRabatEllerProcent.clear();
        } else {
            txfFastRabatEllerProcent.setDisable(false);
            godkendRabat.setDisable(false);
        }
    }

    // -------------------------------------------------------------------------

    private void selectedPrislisteChanged() {
        this.updateControlsPrisliste();
    }

    private void selectedProduktChanged() {
        this.updateControlsProdukter();
    }

    private void selectedBetalingsmpdeChanged() {
        this.updateControlsBetalingsmaader();
    }

    public void updateControlsPrisliste() {
        Prisliste prisliste = cbbPrisListe.getSelectionModel().getSelectedItem();
        if (prisliste != null) {
            cbbBetalingsMåder.getSelectionModel().clearSelection();
            cbbBetalingsMåder.setDisable(false);
            lvwIndkøbsliste.getItems().clear();
            txfPrisIndkøbsliste.clear();
        }
        lvwProdukter.getItems().clear();
    }

    public void updateControlsProdukter() {
    }

    public void updateControls() {
        cbbPrisListe.getItems().setAll(controller.getPrislister());
        cbbBetalingsMåder.setDisable(true);
        clearAll();
    }

    public void updateControlsBetalingsmaader() {
        Prisliste prisliste = cbbPrisListe.getSelectionModel().getSelectedItem();
        Betalingsmåder betalingsmåde = cbbBetalingsMåder.getSelectionModel().getSelectedItem();
        if (betalingsmåde != null) {
            if (betalingsmåde == Betalingsmåder.Klippekort) {
                lvwProdukter.setDisable(false);
                lvwProdukter.getItems().setAll(prisliste.getKlipPriser());
                txfLejersNavn.setEditable(false);
                hbxRabat.setDisable(true);

            } else {
                lvwProdukter.setDisable(false);
                lvwProdukter.getItems().setAll(prisliste.getPriser());
                txfLejersNavn.setEditable(true);
                hbxRabat.setDisable(false);
            }
        }
        lvwIndkøbsliste.getItems().clear();
    }

}

