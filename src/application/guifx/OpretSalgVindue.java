package application.guifx;

import application.controller.Controller;
import application.model.Prisliste;
import application.model.Produkt;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.sql.Array;
import java.util.ArrayList;

public class OpretSalgVindue extends GridPane {

	private TextField txfProduktNavn, txfAntalPåLager;
	private TextArea txa;
	private ListView<String> lvwIndkøbsliste;
	private ListView<Produkt> lvwProdukter;
	private ComboBox<Prisliste> cbbPrisListe;
	private ComboBox<Integer> cbbAntal;


	public OpretSalgVindue() {
		this.setPadding(new Insets(20));
		this.setHgap(10);
		this.setVgap(10);
		this.setGridLinesVisible(false);


		cbbPrisListe= new ComboBox<>();
		cbbPrisListe.getItems().setAll(Controller.getPrislister());
		ChangeListener<Prisliste> listenerPrisliste = (ov, oldPrisliste, newPrisliste) -> this.selectedPrislisteChanged();
		this.add(cbbPrisListe,0 ,0);

		Label lblProdukter = new Label("Produkter:");
		this.add(lblProdukter,0,1);

		lvwProdukter = new ListView<>();
		this.add(lvwProdukter,0,2, 2, 5);

		Label lblIndkoeb = new Label("Indkøbsliste:");
		this.add(lblIndkoeb,2,1);

		lvwIndkøbsliste = new ListView<>();
		this.add(lvwIndkøbsliste,2,2,2,5);

		cbbAntal = new ComboBox<>();
		this.add(cbbAntal,4,3);







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
	public void updateControlsPrisliste(){
		Prisliste prisliste = cbbPrisListe.getSelectionModel().getSelectedItem();
		lvwProdukter.getItems().setAll(prisliste.getProdukter());
	}
	public void updateControls() {
	}



}
