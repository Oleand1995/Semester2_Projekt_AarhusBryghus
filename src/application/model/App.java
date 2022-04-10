package application.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class App {

    public static void main(String[] args) {


//------------------------------------------------------------------------------------------------
//                  **Elaboration - Iteration 1**

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


//------------------------------------------------------------------------------------------------
//                  **Elaboration - Iteration 2*a*

        //Opretter ordrelinjer.
        OrdreLinje ordreLinje = new OrdreLinje(pris);
        OrdreLinje ordreLinje1 = new OrdreLinje(prisMedKlip);

        //Vores system fungere sådan at ordrelinjerne samles i guien,
        //Og pushes efterfølgende ind i enten salg eller udlejning for
        //at oprette enten salg eller udlejning.

        //Jeg illustrere det her med en ny arrayliste
        ArrayList<OrdreLinje> ordreLinjer = new ArrayList<>();
        ordreLinjer.add(ordreLinje);
        ordreLinjer.add(ordreLinje1);

        //Salget oprettes med ordrelinjerne.
        Salg salg = new Salg(LocalDateTime.now(),ordreLinjer,100,0, Betalingsmåder.Dankort);

        //Udlejning oprettes med ordrelinjerne.
        Udlejning udlejning = new Udlejning(LocalDateTime.now(),null,100,"Henrik",ordreLinjer);


//------------------------------------------------------------------------------------------------
//                  **Elaboration - Iteration 3**

        //Opretter rabatBeregnere af de 2 typer.
        RabatBeregning fastRabat = new FastRabat(50);
        RabatBeregning procentRabat = new ProcentRabat(20);

        //Vi tillader os at bruge ordrelinjer fra tidligere

    }

}
