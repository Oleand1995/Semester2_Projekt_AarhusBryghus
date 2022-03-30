package application.model;

import java.util.ArrayList;

public class Produkt {

    private String beskrivelse;
    private ProduktGruppe produktgruppe;

    Produkt(String beskrivelse,ProduktGruppe produktGruppe){
        this.beskrivelse = beskrivelse;
        this.produktgruppe = produktGruppe;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    //Virker ikke i, opret klassen. hvis der er pris med.
//    public String toString(Prisliste prisliste){return this.beskrivelse + "\t\t\t" + this.getPris(prisliste) + ",-";}
    public String toString(){return this.beskrivelse;}


}
