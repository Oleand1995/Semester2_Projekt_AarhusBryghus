package application.guifx;

import application.controller.Controller;
import application.model.*;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import org.w3c.dom.Text;
import storage.Storage;

import java.beans.EventHandler;
import java.sql.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class OpretSalgVindue extends GridPane {

	private TextField txfProduktNavn, txfAntalPåLager, txfLejersNavn;
	private TextArea txa;
	private ListView<OrdreLinje> lvwIndkøbsliste;
	private ListView<Pris> lvwProdukter;
	private ComboBox<Prisliste> cbbPrisListe;
	private ComboBox<Betalingsmåder> cbbBetalingsMåder;
	private ComboBox<Integer> cbbAntal;
	private VBox serviceBoxNavn, serviceBoxCounter;
	private ListView<Double> lvwPriser;
	private TextField txfpris,txfPrisIndkøbsliste,txfAntal;
	private Salg salg;
	private Button addButton, btnOpretUdlejning;
	private CheckBox chbUdlejning;
	private boolean isKlippekort;


	public OpretSalgVindue() {
		this.setPadding(new Insets(20));
		this.setHgap(10);
		this.setVgap(10);
		this.setGridLinesVisible(false);


		cbbPrisListe = new ComboBox<>();
		cbbPrisListe.getItems().setAll(Controller.getPrislister());
		ChangeListener<Prisliste> listenerPrisliste = (ov, oldPrisliste, newPrisliste) -> this.selectedPrislisteChanged();
		cbbPrisListe.getSelectionModel().selectedItemProperty().addListener(listenerPrisliste);
		this.add(cbbPrisListe,0 ,0);

		cbbBetalingsMåder = new ComboBox<>();
		cbbBetalingsMåder.getItems().setAll(Betalingsmåder.values());
		ChangeListener<Betalingsmåder> listenerBetalingsmåde = (ov, oldBetalingsmaade, newBetalingsmaade) -> this.selectedBetalingsmpdeChanged();
		cbbBetalingsMåder.getSelectionModel().selectedItemProperty().addListener(listenerBetalingsmåde);
		this.add(cbbBetalingsMåder,1,0);
		cbbBetalingsMåder.setDisable(true);



		Label lblProdukter = new Label("Produkter:");
		this.add(lblProdukter,0,1);

		lvwProdukter = new ListView<>();
		ChangeListener<Pris> listenerProdukt = (ov, oldProdukt, newProdukt) -> this.selectedProduktChanged();
		lvwProdukter.getSelectionModel().selectedItemProperty().addListener(listenerProdukt);
		this.add(lvwProdukter,0,2, 2, 4);
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
		this.add(lblIndkoeb,3,1,1,2);

		lvwIndkøbsliste = new ListView<>();
		this.add(lvwIndkøbsliste,3,2,2,4);
		ChangeListener<OrdreLinje> listenerordrelinje = (ov, oldOrdreLinje, newOrdreLinje) -> this.antalPåvalgtProdukt();
		lvwIndkøbsliste.getSelectionModel().selectedItemProperty().addListener(listenerordrelinje);


		Label lblIndkøbslistePris = new Label("Indkøbsliste samlet pris: ");
		this.add(lblIndkøbslistePris,3,6);
		txfPrisIndkøbsliste= new TextField();
		this.add(txfPrisIndkøbsliste,4,6);



		VBox vbxAntal = new VBox(10);
		this.add(vbxAntal,5,3);
		vbxAntal.setPadding(new Insets(10, 0, 0, 0));
		vbxAntal.setAlignment(Pos.BOTTOM_LEFT);

		HBox hbxAntal = new HBox(20);
		vbxAntal.getChildren().add(hbxAntal);
		hbxAntal.setPadding(new Insets(10, 0, 0, 50));
		hbxAntal.setAlignment(Pos.BASELINE_CENTER);

		Button antalPlusKnap = new Button("+");
		hbxAntal.getChildren().add(antalPlusKnap);
		antalPlusKnap.setOnAction(event -> this.antalPlusKnap());
		antalPlusKnap.setPrefSize(30,30);

		Button antalMinusKnap = new Button("-");
		hbxAntal.getChildren().add(antalMinusKnap);
		antalMinusKnap.setOnAction(event -> this.antalMinusKnap());
		antalMinusKnap.setPrefSize(30,30);

		Button antalGodkend = new Button("✔");
		hbxAntal.getChildren().add(antalGodkend);
		antalGodkend.setOnAction(event -> this.godkendAntal());
		antalGodkend.setPrefSize(30,30);

		HBox hbxAntal1 = new HBox(20);
		vbxAntal.getChildren().add(hbxAntal1);
		hbxAntal1.setPadding(new Insets(10, 0, 0, 0));
		hbxAntal1.setAlignment(Pos.BASELINE_CENTER);

		Label lblAntal = new Label("Antal: ");
		hbxAntal1.getChildren().add(lblAntal);

		txfAntal= new TextField();
		hbxAntal1.getChildren().add(txfAntal);



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
	}

	// -------------------------------------------------------------------------

	private void TilføjTilIndkoebskurv() {
		Pris pris = lvwProdukter.getSelectionModel().getSelectedItem();
		if (pris != null){
			OrdreLinje ordreLinje = Controller.createOrdreLinje(pris);
			lvwProdukter.getItems().remove(pris);
			lvwIndkøbsliste.getItems().add(ordreLinje);
			setSamletPris();
		}
	}

	private void fjernFraIndkoebskurv(){
		OrdreLinje ordreLinje = lvwIndkøbsliste.getSelectionModel().getSelectedItem();
		if (ordreLinje != null){
			lvwIndkøbsliste.getItems().remove(ordreLinje);
			lvwProdukter.getItems().add(ordreLinje.getPris());
			setSamletPris();
		}
	}


	public void antalPåvalgtProdukt(){
		OrdreLinje ordreLinje = lvwIndkøbsliste.getSelectionModel().getSelectedItem();
		if (ordreLinje != null){
			txfAntal.setText(ordreLinje.getAntal() + "");
		}

	}


	private void antalPlusKnap() {
		int antal = Integer.parseInt(txfAntal.getText().trim());
		antal++;
		txfAntal.setText(antal + "");
	}

	private void antalMinusKnap() {
		int antal = Integer.parseInt(txfAntal.getText().trim());
		antal--;
		txfAntal.setText(antal + "");
	}

	private void godkendAntal() {
		OrdreLinje ordreLinje = lvwIndkøbsliste.getSelectionModel().getSelectedItem();
		if (ordreLinje != null) {
			Controller.setAntalPåOrdreLinje(ordreLinje, Integer.parseInt(txfAntal.getText()));
			ArrayList<OrdreLinje> ordreLinjer = new ArrayList<OrdreLinje>(lvwIndkøbsliste.getItems());
			lvwIndkøbsliste.getItems().setAll(ordreLinjer);
			txfAntal.clear();
			setSamletPris();

		}
	}

		private void checkUdlejningsNavn () {
			if (txfLejersNavn.getText().length() != 0) {
				btnOpretUdlejning.setDisable(false);
			} else {
				btnOpretUdlejning.setDisable(true);
			}
		}

		private void opretSalg () {
		Betalingsmåder betalingsmåde = cbbBetalingsMåder.getSelectionModel().getSelectedItem();
		if (betalingsmåde == Betalingsmåder.Klippekort){
			ArrayList<OrdreLinje> ordrelinjer = new ArrayList<>();
			ordrelinjer.addAll(lvwIndkøbsliste.getItems());
			Salg salg = Controller.createSalg(LocalDateTime.now(), ordrelinjer, Integer.parseInt(txfPrisIndkøbsliste.getText()), 0);
			clearAll();
		}
		else{
			ArrayList<OrdreLinje> ordrelinjer = new ArrayList<>();
			ordrelinjer.addAll(lvwIndkøbsliste.getItems());
			Salg salg = Controller.createSalg(LocalDateTime.now(), ordrelinjer, 0, Double.parseDouble(txfPrisIndkøbsliste.getText()));
			clearAll();
			}
		}

		private void opretUdlejning () {
		Betalingsmåder betalingsmåde = cbbBetalingsMåder.getSelectionModel().getSelectedItem();
			ArrayList<OrdreLinje> ordrelinjer = new ArrayList<>();
			ordrelinjer.addAll(lvwIndkøbsliste.getItems());
			Controller.createUdlejning(LocalDateTime.now(), Double.parseDouble(txfPrisIndkøbsliste.getText()), txfLejersNavn.getText(), ordrelinjer);
			clearAll();
		}

		private void clearAll () {
			lvwIndkøbsliste.getItems().clear();
			txfPrisIndkøbsliste.clear();
			txfLejersNavn.clear();
			txfAntal.clear();
			lvwProdukter.getItems().clear();
//			cbbPrisListe.getSelectionModel().clearSelection();
			cbbBetalingsMåder.getSelectionModel().clearSelection();
			lvwProdukter.setDisable(true);
		}

		private void setSamletPris(){
			if (cbbBetalingsMåder.getSelectionModel().getSelectedItem() == Betalingsmåder.Klippekort) {
				txfPrisIndkøbsliste.setText(Controller.getSamletKlip(lvwIndkøbsliste.getItems()) + "");
			} else {
				txfPrisIndkøbsliste.setText(Controller.getSamletPris(lvwIndkøbsliste.getItems()) + "");
			}
		}

		// -------------------------------------------------------------------------

		private void selectedPrislisteChanged () {
			this.updateControlsPrisliste();
		}
		private void selectedProduktChanged () {
			this.updateControlsProdukter();
		}
		private void selectedBetalingsmpdeChanged () {
			this.updateControlsBetalingsmaader();
		}

		public void updateControlsPrisliste () {
			Prisliste prisliste = cbbPrisListe.getSelectionModel().getSelectedItem();
			if (prisliste != null) {
				cbbBetalingsMåder.getSelectionModel().clearSelection();
				cbbBetalingsMåder.setDisable(false);
				lvwIndkøbsliste.getItems().clear();
				txfPrisIndkøbsliste.clear();
			}
			lvwProdukter.getItems().clear();


		}

		public void updateControlsProdukter () {
		}

		public void updateControls () {
			cbbPrisListe.getItems().setAll(Controller.getPrislister());
			cbbBetalingsMåder.setDisable(true);
			clearAll();
		}

		public void updateControlsBetalingsmaader () {
			Prisliste prisliste = cbbPrisListe.getSelectionModel().getSelectedItem();
			Betalingsmåder betalingsmåde = cbbBetalingsMåder.getSelectionModel().getSelectedItem();
			if (betalingsmåde != null) {
				if (betalingsmåde == Betalingsmåder.Klippekort){
					lvwProdukter.setDisable(false);
					lvwProdukter.getItems().setAll(prisliste.getKlipPriser());
					txfLejersNavn.setEditable(false);
				}else{
					lvwProdukter.setDisable(false);
					lvwProdukter.getItems().setAll(prisliste.getPriser());
					txfLejersNavn.setEditable(true);
				}
			}
			lvwIndkøbsliste.getItems().clear();
		}

	}

