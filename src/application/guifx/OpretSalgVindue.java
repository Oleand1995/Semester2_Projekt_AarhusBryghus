package application.guifx;

import application.controller.Controller;
import application.model.OrdreLinje;
import application.model.Prisliste;
import application.model.Produkt;
import application.model.Salg;
import javafx.beans.value.ChangeListener;
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

	private TextField txfProduktNavn, txfAntalPåLager;
	private TextArea txa;
	private ListView<OrdreLinje> lvwIndkøbsliste;
	private ListView<Produkt> lvwProdukter;
	private ComboBox<Prisliste> cbbPrisListe;
	private ComboBox<Integer> cbbAntal;
	private VBox serviceBoxNavn, serviceBoxCounter;
	private ListView<Double> lvwPriser;
	private TextField txfpris,txfPrisIndkøbsliste,txfAntal;
	private Salg salg;
	private Button addButton;


	public OpretSalgVindue() {
		this.setPadding(new Insets(20));
		this.setHgap(10);
		this.setVgap(10);
		this.setGridLinesVisible(false);


		cbbPrisListe= new ComboBox<>();
		cbbPrisListe.getItems().setAll(Controller.getPrislister());
		ChangeListener<Prisliste> listenerPrisliste = (ov, oldPrisliste, newPrisliste) -> this.selectedPrislisteChanged();
		cbbPrisListe.getSelectionModel().selectedItemProperty().addListener(listenerPrisliste);
		this.add(cbbPrisListe,0 ,0);

		Label lblProdukter = new Label("Produkter:");
		this.add(lblProdukter,0,1);

		lvwProdukter = new ListView<>();
		ChangeListener<Produkt> listenerProdukt = (ov, oldProdukt, newProdukt) -> this.selectedProduktChanged();
		lvwProdukter.getSelectionModel().selectedItemProperty().addListener(listenerProdukt);
		this.add(lvwProdukter,0,2, 2, 5);

		Label lblPris = new Label("Pris: ");
		this.add(lblPris,0,7);
		txfpris = new TextField();
		this.add(txfpris,1,7);

		addButton = new Button("-->");
		this.add(addButton, 3, 4);
		addButton.setOnAction(event -> this.TilføjTilIndkoebskurv());
		addButton.setDisable(true);

		Button opretOrdre = new Button("OpretOrdre");
		this.add(opretOrdre, 3, 5);
		opretOrdre.setOnAction(event -> this.opretOrdre());


		Label lblIndkoeb = new Label("Indkøbsliste:");
		this.add(lblIndkoeb,4,1,1,2);

		lvwIndkøbsliste = new ListView<>();
		this.add(lvwIndkøbsliste,4,2,2,5);
		lvwIndkøbsliste.setDisable(true);
		ChangeListener<OrdreLinje> listenerordrelinje = (ov, oldOrdreLinje, newOrdreLinje) -> this.antalPåvalgtProdukt();
		lvwIndkøbsliste.getSelectionModel().selectedItemProperty().addListener(listenerordrelinje);


		Label lblIndkøbslistePris = new Label("Indkøbsliste samlet Pris: ");
		this.add(lblIndkøbslistePris,4,7);
		txfPrisIndkøbsliste= new TextField();
		this.add(txfPrisIndkøbsliste,5,7);


		HBox hbxButtons = new HBox(40);
		this.add(hbxButtons, 4, 8, 3, 1);
		hbxButtons.setPadding(new Insets(10, 0, 0, 0));
		hbxButtons.setAlignment(Pos.BASELINE_CENTER);

		Button antalPlusKnap = new Button("+");
		hbxButtons.getChildren().add(antalPlusKnap);
		antalPlusKnap.setOnAction(event -> this.antalPlusKnap());

		Button antalMinusKnap = new Button("-");
		hbxButtons.getChildren().add(antalMinusKnap);
		antalMinusKnap.setOnAction(event -> this.antalMinusKnap());

		Button antalGodkend = new Button("✔");
		hbxButtons.getChildren().add(antalGodkend);
		antalGodkend.setOnAction(event -> this.godkendAntal());


		Label lblAntal = new Label("Antal: ");
		hbxButtons.getChildren().add(lblAntal);

		txfAntal= new TextField();
		hbxButtons.getChildren().add(txfAntal);



	}



	// -------------------------------------------------------------------------

	private void opretOrdre() {
		this.salg  = Controller.createSalg(LocalDateTime.now());
		addButton.setDisable(false);
		lvwIndkøbsliste.setDisable(false);

	}

	private void TilføjTilIndkoebskurv() {
		Produkt produkt = lvwProdukter.getSelectionModel().getSelectedItem();
		Controller.createOrdreLinje(salg,produkt,1 ,cbbPrisListe.getSelectionModel().getSelectedItem().getPris(produkt));
		lvwIndkøbsliste.getItems().setAll(salg.getOrdreliner());
		txfPrisIndkøbsliste.setText(salg.getSamletPris() + "");

	}


	public void antalPåvalgtProdukt(){
		OrdreLinje ordreLinje = lvwIndkøbsliste.getSelectionModel().getSelectedItem();
		txfAntal.setText(ordreLinje.getAntal() + "");
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
		Controller.setAntalPåOrdreLinje(ordreLinje,Integer.parseInt(txfAntal.getText()));
		lvwIndkøbsliste.getItems().setAll(salg.getOrdreliner());
		txfPrisIndkøbsliste.setText(salg.getSamletPris() + "");
		txfAntal.clear();
	}





	// -------------------------------------------------------------------------

	private void selectedPrislisteChanged(){this.updateControlsPrisliste();}
	private void selectedProduktChanged(){this.updateControlsProdukter();}

	public void updateControlsPrisliste(){
		Prisliste prisliste = cbbPrisListe.getSelectionModel().getSelectedItem();
		lvwProdukter.getItems().setAll(prisliste.getProdukter());
		txfpris.clear();
	}

	public void updateControlsProdukter(){
		Prisliste pl = cbbPrisListe.getSelectionModel().getSelectedItem();
		Produkt p = lvwProdukter.getSelectionModel().getSelectedItem();
		if (p != null && pl != null){
			double test = pl.getPris(p);
			txfpris.setText(test + "");
		}

	}

	public void updateControls() {
		cbbPrisListe.getItems().setAll(Controller.getPrislister());
	}



}
