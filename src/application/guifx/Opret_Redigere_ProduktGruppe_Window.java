package application.guifx;

import application.controller.Controller;
import application.model.ProduktGruppe;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Opret_Redigere_ProduktGruppe_Window extends Stage {

	private ProduktGruppe produktGruppe;

    public Opret_Redigere_ProduktGruppe_Window(String title, ProduktGruppe produktGruppe) {
		this.initStyle(StageStyle.UTILITY);
		this.initModality(Modality.APPLICATION_MODAL);
		this.setResizable(false);

		this.produktGruppe = produktGruppe;

		this.setTitle(title);
		GridPane pane = new GridPane();
		this.initContent(pane);

		Scene scene = new Scene(pane);
		this.setScene(scene);
	}

	public Opret_Redigere_ProduktGruppe_Window(String title) {
        this(title, null);
    }


	// -------------------------------------------------------------------------
	private TextField txfProdukttype;
	private Label lblError;


	private void initContent(GridPane pane) {
		pane.setPadding(new Insets(10));
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setGridLinesVisible(false);

		Label lblName = new Label("Produkt Type");
		pane.add(lblName, 0, 0);

		txfProdukttype = new TextField();
		pane.add(txfProdukttype, 0, 1);
		txfProdukttype.setPrefWidth(200);

		Button btnAnnuller = new Button("Annuller");
		pane.add(btnAnnuller, 0, 2);
		GridPane.setHalignment(btnAnnuller, HPos.LEFT);
		btnAnnuller.setOnAction(event -> annullerAction());

		Button btnOK = new Button("OK");
		pane.add(btnOK, 1, 2);
		GridPane.setHalignment(btnOK, HPos.RIGHT);
		btnOK.setOnAction(event -> okAction());

		lblError = new Label();
		pane.add(lblError, 0, 3);
		lblError.setStyle("-fx-text-fill: red");

		initControls();

	}

	private void initControls() {
		if (produktGruppe != null) {
			txfProdukttype.setText(produktGruppe.getProduktType());
		}
	}

	private void annullerAction() {
		hide();
	}


	private void okAction(){
		String produktType = txfProdukttype.getText().trim();
		if (produktType.length() == 0){
			lblError.setText("Produkttype skal være udfyldt");
		}
		else if (produktGruppe != null){
			produktGruppe.setProduktType(produktType);
			hide();
		}
		else {
			Controller.createproduktGruppe(produktType);
			hide();
			}
		}



	}





