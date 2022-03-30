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
import org.w3c.dom.Text;

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
	private ListView<Double> lvwPriser;
	private TextField txfpris;


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

		Button addButton = new Button("-->");
		this.add(addButton, 3, 4);
		addButton.setOnAction(event -> this.addVareToIndkøbsliste());

		Label lblIndkoeb = new Label("Indkøbsliste:");
		this.add(lblIndkoeb,4,1);

		lvwIndkøbsliste = new ListView<>();
		this.add(lvwIndkøbsliste,4,2,2,5);
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
	private void selectedProduktChanged(){this.updateControlsProdukter();}
	private void selectedIndkøbsProduktChanged(){this.updateControlsIndkøbsProdukt();}


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

	private void addVareToIndkøbsliste() {


	}

	private void updateControlsIndkøbsProdukt(){
	}


}
