package application.controller;

import application.model.Fadøl;
import application.model.Produkt;
import storage.Storage;

public class Controller {

    public static Produkt createFadoel(String navn, int antal, int klipPris, String størrelse){
        Produkt produkt = new Fadøl(navn,antal,klipPris,størrelse);
        Storage.addProdukt(produkt);
        return produkt;
    }

    public static void initStorage(){
        Controller.createFadoel("Forårsbryg",  1, 2, "Flaske");

    }


}
