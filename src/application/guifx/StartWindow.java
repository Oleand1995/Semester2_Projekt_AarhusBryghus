package application.guifx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class StartWindow extends Application {

	
	@Override
	public void init() {
	//	Controller.initStorage(); ----Gør denne aktiv
	}

	@Override
	public void start(Stage stage) {
		stage.setTitle("Aarhus Bryghus salgssystem");
		BorderPane pane = new BorderPane();
		this.initContent(pane);

		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.show();
	}

	// -------------------------------------------------------------------------

	private void initContent(BorderPane pane) {
		TabPane tabPane = new TabPane();
		this.initTabPane(tabPane);
		pane.setCenter(tabPane);
	}

	private void initTabPane(TabPane tabPane) {
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

		Tab tab1 = new Tab("Opret produkt");
		tabPane.getTabs().add(tab1);

		OpretProduktVindue opretProduktVindue = new OpretProduktVindue();
		tab1.setContent(opretProduktVindue);
		tab1.setOnSelectionChanged(event -> opretProduktVindue.updateControls());








	}

}