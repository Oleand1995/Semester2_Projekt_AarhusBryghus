package application.guifx;

import application.controller.Controller;
import application.model.*;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
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
	private ComboBox<Integer> cbbAntal;
	private VBox serviceBoxNavn, serviceBoxCounter;
	private ListView<Double> lvwPriser;
	private TextField txfpris,txfPrisIndkøbsliste,txfAntal;
	private Salg salg;
	private Button addButton, btnOpretUdlejning;
	private CheckBox chbUdlejning;


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


		Label lblProdukter = new Label("Produkter:");
		this.add(lblProdukter,0,1);

		lvwProdukter = new ListView<>();
		ChangeListener<Pris> listenerProdukt = (ov, oldProdukt, newProdukt) -> this.selectedProduktChanged();
		lvwProdukter.getSelectionModel().selectedItemProperty().addListener(listenerProdukt);
		this.add(lvwProdukter,0,2, 2, 4);


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


		Label lblIndkøbslistePris = new Label("Indkøbsliste samlet Pris: ");
		this.add(lblIndkøbslistePris,3,6);
		txfPrisIndkøbsliste= new TextField();
		this.add(txfPrisIndkøbsliste,4,6);


		HBox hbxAntal = new HBox(40);
		this.add(hbxAntal, 3, 7, 3, 1);
		hbxAntal.setPadding(new Insets(10, 0, 0, 0));
		hbxAntal.setAlignment(Pos.BASELINE_CENTER);

		Button antalPlusKnap = new Button("+");
		hbxAntal.getChildren().add(antalPlusKnap);
		antalPlusKnap.setOnAction(event -> this.antalPlusKnap());

		Button antalMinusKnap = new Button("-");
		hbxAntal.getChildren().add(antalMinusKnap);
		antalMinusKnap.setOnAction(event -> this.antalMinusKnap());

		Button antalGodkend = new Button("✔");
		hbxAntal.getChildren().add(antalGodkend);
		antalGodkend.setOnAction(event -> this.godkendAntal());

		Label lblAntal = new Label("Antal: ");
		hbxAntal.getChildren().add(lblAntal);

		txfAntal= new TextField();
		hbxAntal.getChildren().add(txfAntal);

		HBox hbxSalg = new HBox(70);
		this.add(hbxSalg, 5, 5, 3, 1);
		hbxSalg.setPadding(new Insets(10, 0, 0, 0));
		hbxSalg.setAlignment(Pos.CENTER_LEFT);

		Label lblSalg = new Label("Salg: ");
		hbxSalg.getChildren().add(lblSalg);

		Button opretSalg = new Button("Opret Salg");
		hbxSalg.getChildren().add(opretSalg);
		opretSalg.setOnAction(event -> this.opretSalg());


		VBox vbxUdlejning = new VBox(20);
		this.add(vbxUdlejning, 5, 4, 3, 1);
		vbxUdlejning.setPadding(new Insets(10, 0, 0, 0));
		vbxUdlejning.setAlignment(Pos.BOTTOM_LEFT);

		HBox hbxUdlejning = new HBox(20);
		vbxUdlejning.getChildren().add(hbxUdlejning);
		hbxUdlejning.setPadding(new Insets(10, 0, 0, 0));
		hbxUdlejning.setAlignment(Pos.BOTTOM_LEFT);

		Label lblUdlejning = new Label("Udlejning: ");
		hbxUdlejning.getChildren().add(lblUdlejning);

		btnOpretUdlejning = new Button("Opret Udlejning");
		hbxUdlejning.getChildren().add(btnOpretUdlejning);
		btnOpretUdlejning.setOnAction(event -> this.opretUdlejning());
		btnOpretUdlejning.setDisable(true);


		HBox hbxLejersNavn = new HBox(20);
		vbxUdlejning.getChildren().add(hbxLejersNavn);
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
			txfPrisIndkøbsliste.setText(Controller.getSamletPris(lvwIndkøbsliste.getItems()) + "");
		}
	}

	private void fjernFraIndkoebskurv(){
		OrdreLinje ordreLinje = lvwIndkøbsliste.getSelectionModel().getSelectedItem();
		if (ordreLinje != null){
			lvwIndkøbsliste.getItems().remove(ordreLinje);
			lvwProdukter.getItems().add(ordreLinje.getPris());
			txfPrisIndkøbsliste.setText(Controller.getSamletPris(lvwIndkøbsliste.getItems()) + "");
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
		if (ordreLinje != null){
			Controller.setAntalPåOrdreLinje(ordreLinje,Integer.parseInt(txfAntal.getText()));
			txfPrisIndkøbsliste.setText(Controller.getSamletPris(lvwIndkøbsliste.getItems()) + "");
			ArrayList<OrdreLinje> ordreLinjer = new ArrayList<OrdreLinje>(lvwIndkøbsliste.getItems());
			lvwIndkøbsliste.getItems().setAll(ordreLinjer);
			txfAntal.clear();
		}
	}

	private void checkUdlejningsNavn(){
		if (txfLejersNavn.getText().length() != 0){
			btnOpretUdlejning.setDisable(false);
		}
		else{
			btnOpretUdlejning.setDisable(true);
		}
	}

	private void opretSalg(){
		ArrayList<OrdreLinje> ordrelinjer = new ArrayList<>();
		ordrelinjer.addAll(lvwIndkøbsliste.getItems());
		Controller.createSalg(LocalDateTime.now(),ordrelinjer, -1, Double.parseDouble(txfPrisIndkøbsliste.getText()));
		clearAll();
	}

	private void opretUdlejning(){
		ArrayList<OrdreLinje> ordrelinjer = new ArrayList<>();
		ordrelinjer.addAll(lvwIndkøbsliste.getItems());
		Controller.createUdlejning(LocalDateTime.now(),Double.parseDouble(txfPrisIndkøbsliste.getText()),txfLejersNavn.getText(),ordrelinjer);
		clearAll();
	}

	private void clearAll(){
		lvwIndkøbsliste.getItems().clear();
		txfPrisIndkøbsliste.clear();
		txfLejersNavn.clear();
		txfAntal.clear();
		lvwProdukter.getItems().clear();
		cbbPrisListe.getSelectionModel().clearSelection();
	}


	// -------------------------------------------------------------------------

	private void selectedPrislisteChanged(){this.updateControlsPrisliste();}
	private void selectedProduktChanged(){this.updateControlsProdukter();}

	public void updateControlsPrisliste(){
		Prisliste prisliste = cbbPrisListe.getSelectionModel().getSelectedItem();
		if (prisliste != null){
			lvwProdukter.getItems().setAll(prisliste.getPriser());
			lvwIndkøbsliste.getItems().clear();
			txfPrisIndkøbsliste.clear();
		}

	}

	public void updateControlsProdukter(){
		Prisliste pl = cbbPrisListe.getSelectionModel().getSelectedItem();
		Pris pris = lvwProdukter.getSelectionModel().getSelectedItem();
		if (pris != null && pl != null){
			double test = pris.getPris();
		}

	}

	public void updateControls() {
		cbbPrisListe.getItems().setAll(Controller.getPrislister());
	}
}
