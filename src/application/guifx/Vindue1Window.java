package application.guifx;

import javafx.stage.Stage;


public class Vindue1Window extends Stage {

    /*   -------- Fjern denne

	private X x;

    public Vindue1Window(String title, X x) {
		this.initStyle(StageStyle.UTILITY);
		this.initModality(Modality.APPLICATION_MODAL);
		this.setResizable(false);

		this.x = x;

		this.setTitle(title);
		GridPane pane = new GridPane();
		this.initContent(pane);

		Scene scene = new Scene(pane);
		this.setScene(scene);
	}

	public Vindue1Window(String title) {
        this(title, null);
    }



	// -------------------------------------------------------------------------

	private TextField txf1, txf2;
	private Label lblError;

	private void initContent(GridPane pane) {
		pane.setPadding(new Insets(10));
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setGridLinesVisible(false);

		Label lbl1 = new Label("Label 1");
		pane.add(lbl1, 0, 0);

		txf1 = new TextField();
		pane.add(txf1, 0, 1);
		txf1.setPrefWidth(200);


		Label lbl2 = new Label("Label 2");
		pane.add(lbl2, 0, 2);

		txf2 = new TextField();
		pane.add(txf2, 0, 3);


		Button btnCancel = new Button("Cancel");
		pane.add(btnCancel, 0, 4);
		GridPane.setHalignment(btnCancel, HPos.LEFT);
		btnCancel.setOnAction(event -> this.cancelAction());

		Button btnOK = new Button("OK");
		pane.add(btnOK, 0, 4);
		GridPane.setHalignment(btnOK, HPos.RIGHT);
		btnOK.setOnAction(event -> this.okAction());


		lblError = new Label();
		pane.add(lblError, 0, 5);
		lblError.setStyle("-fx-text-fill: red");

		this.initControls();
	}

	private void initControls() {
		if (this.x != null) {
			txf1.setText(x.getText); //Dette kan være navn og andre ting
	    	txf2.setText("" + x.getText); // Dette kan være navn og andre ting.
		} else {
			txf1.clear();
			txf2.clear();
		}
	}

	// -------------------------------------------------------------------------

	private void cancelAction() {
		this.hide();
	}

	private void okAction() {
		String texst1 = txf1.getText().trim();
		if (texst1.length() == 0) {
			lblError.setText("Texst field is empty");
		} else {
			int tal1 = -1;
			try {
				tal1 = Integer.parseInt(txf2.getText().trim());
			} catch (NumberFormatException ex) {
				// do nothing
			}
			if (tal1 < 0) {
				lblError.setText("Not a positive number");
			} else {
				// Call application.controller methods
				if (this.x != null) {
				//	Controller.updateX(x,texst1, tal1); --Tjek op på denne
				} else {
				//	Controller.createX(texst1, tal1); ----Tjek op på denne
				}

				this.hide();
			}
		}
	}

     */// -----Fjern denne.


}


