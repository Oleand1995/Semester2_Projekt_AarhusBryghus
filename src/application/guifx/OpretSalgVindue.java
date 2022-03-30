package application.guifx;

import application.controller.Controller;
import application.model.Ordrelinje;
import application.model.Prisliste;
import application.model.Produkt;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.beans.EventHandler;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class OpretSalgVindue extends GridPane {

	private TextField txfProduktNavn, txfAntalPåLager;
	private TextArea txa;
	private ListView<HashMap<Produkt,Integer>> lvwIndkøbsliste;
	private ListView<Produkt> lvwProdukter;
	private ComboBox<Prisliste> cbbPrisListe;
	private ComboBox<Integer> cbbAntal;
	private VBox serviceBoxNavn, serviceBoxCounter;
	private Ordrelinje ordrelinje = new Ordrelinje();


	public OpretSalgVindue() {
		this.setPadding(new Insets(20));
		this.setHgap(10);
		this.setVgap(0);
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
		this.add(lvwProdukter,0,2, 2, 5);

		Button addButton = new Button("-->");
		this.add(addButton, 2, 4);
		addButton.setOnAction(event -> this.addVareToIndkøbsliste());

		Label lblIndkoeb = new Label("Indkøbsliste:");
		this.add(lblIndkoeb,3,1);

		lvwIndkøbsliste = new ListView<>();
		this.add(lvwIndkøbsliste,3,2,2,5);
		ChangeListener<HashMap<Produkt,Integer>> listenerIndkøbsliste = (ov, oldProdukt, newProdukt) -> this.selectedIndkøbsProduktChanged();


	}



	// -------------------------------------------------------------------------

	private void createAction() {
	//	Vindue1Window dia = new Vindue1Window("Create X");
	//	dia.showAndWait();

		//se note, under akitektur.


	}

	private void updateAction() {

	}

	private void deleteAction() {

	}

	// -------------------------------------------------------------------------

	private void selectedPrislisteChanged(){this.updateControlsPrisliste();}
	private void selectedProduktChanged(){this.updateControls();}
	private void selectedIndkøbsProduktChanged(){this.updateControlsIndkøbsProdukt();}


	public void updateControlsPrisliste(){
		Prisliste prisliste = cbbPrisListe.getSelectionModel().getSelectedItem();
		if (prisliste != null){
			Controller.setPrisLister(prisliste);
			lvwProdukter.getItems().setAll(prisliste.getProdukter());
		}

	}
	public void updateControls() {
		cbbPrisListe.getItems().setAll(Controller.getPrislister());
	}

	private void addVareToIndkøbsliste() {
		Produkt p = lvwProdukter.getSelectionModel().getSelectedItem();
		if (p != null){
			ordrelinje.addOrdre(p);
//			lvwIndkøbsliste.getItems().add(p);
//			lvwProdukter.getItems().remove(p);
//			int i = lvwIndkøbsliste.getItems().indexOf(p);
//			TextField txfAntal = new TextField();
//			txfAntal.setPrefWidth(50);
//			HBox antalHBox = new HBox(txfAntal);
//			antalHBox.setAlignment(Pos.CENTER_RIGHT);
//			antalHBox.setPadding(new Insets(0));
//			this.add(antalHBox,4,2+i);
		}
		lvwIndkøbsliste.getItems().add(ordrelinje.getOrdrer());
	}

	private void updateControlsIndkøbsProdukt(){
	}


}
