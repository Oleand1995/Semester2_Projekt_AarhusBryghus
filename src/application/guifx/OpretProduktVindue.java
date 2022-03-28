package application.guifx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class OpretProduktVindue extends GridPane {

	private TextField txfProduktNavn, txfAntalPåLager;
	private TextArea txa;
	private ListView<String> lvw; //Husk at ændre string
	private  ComboBox<String> cbbVareKategori,cbbtypeAfProdukt = new ComboBox<>();


	public OpretProduktVindue() {
		this.setPadding(new Insets(20));
		this.setHgap(20);
		this.setVgap(10);
		this.setGridLinesVisible(false);


		Label lblNavPåProdukt = new Label("Navn på produkt");
		this.add(lblNavPåProdukt, 0, 1);

		txfProduktNavn = new TextField();
		this.add(txfProduktNavn, 0, 2);
		txfProduktNavn.setEditable(true);


		Label lblAntalPåLager = new Label("Antal på lager");
		this.add(lblAntalPåLager, 0, 3);

		txfAntalPåLager = new TextField();
		this.add(txfAntalPåLager, 0, 4);
		txfAntalPåLager.setEditable(true);


		Label lblVareKategori = new Label("Vare kategori");
		this.add(lblVareKategori, 0, 5);

		cbbVareKategori = new ComboBox<>();
		this.add(cbbVareKategori,0 ,6);


		Label lblProduktType = new Label("Produkt type");
		this.add(lblProduktType, 0, 7);

		cbbtypeAfProdukt = new ComboBox<>();
		this.add(cbbtypeAfProdukt,0 ,8 );


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

	public void updateControls() {

	}


}
