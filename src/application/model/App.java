package application.model;

public class App {

    public static void main(String[] args) {

        //Opretter produkt gruppe
        ProduktGruppe fadØl_Gruppe = new ProduktGruppe("Fad øl gruppe");

        //Opretter produkter
        Produkt rød_fadbamse = fadØl_Gruppe.createProdukt("Rød fadbamse");
        Produkt gul_fadbamse = fadØl_Gruppe.createProdukt("Gul fadbamse");

        //Opretter prisliste
        Prisliste prisListe_Fredagsbar = new Prisliste("Fredags bar prisliste");

        //Opretter pris til et produkt
        Pris pris = prisListe_Fredagsbar.createPris(50.0,rød_fadbamse);

        //Opretter pris med klip til et produkt
        Pris prisMedKlip = prisListe_Fredagsbar.createPrisOgKlip(50.0,gul_fadbamse,2);






    }

}
