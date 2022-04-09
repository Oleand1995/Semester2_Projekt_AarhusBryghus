package application.guifx;

import application.controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class StartWindow extends Application {

	private Controller controller;

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
		controller = Controller.getController();
		controller.loadStorage();
	}

	@Override
	public void stop() {
		controller.saveStorage();
	}

	private void initTabPane(TabPane tabPane) {
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

		Tab tab1 = new Tab("Opret salg");
		tabPane.getTabs().add(tab1);

		OpretSalgVindue opretSalgVindue = new OpretSalgVindue();
		tab1.setContent(opretSalgVindue);
		tab1.setOnSelectionChanged(event -> opretSalgVindue.updateControls());

		Tab tab2 = new Tab("Opret produkt eller gruppe");
		tabPane.getTabs().add(tab2);

		Opret_Redigere_Slet_ProduktOgGruppe_Pane opretProduktOgGruppe = new Opret_Redigere_Slet_ProduktOgGruppe_Pane();
		tab2.setContent(opretProduktOgGruppe);
		tab2.setOnSelectionChanged(event -> opretProduktOgGruppe.updateControls());


		Tab tab3 = new Tab("Opret prisliste og priser på vare");
		tabPane.getTabs().add(tab3);

		Opret_Redigere_Slet_PrisListe_PrisPåVare_Pane opretPrislistePrisPåVare = new Opret_Redigere_Slet_PrisListe_PrisPåVare_Pane();
		tab3.setContent(opretPrislistePrisPåVare);
		tab3.setOnSelectionChanged(event -> opretPrislistePrisPåVare.updateControls());

		Tab tab4 = new Tab("Vis salg & udlejninger");
		tabPane.getTabs().add(tab4);

		VisSalgOgUdlejninger_Pane visSalgOgUdlejninger = new VisSalgOgUdlejninger_Pane();
		tab4.setContent(visSalgOgUdlejninger);
		tab4.setOnSelectionChanged(event -> visSalgOgUdlejninger.updateControls());





	}

}
