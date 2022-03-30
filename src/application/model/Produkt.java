package application.model;

import java.util.ArrayList;

public class Produkt {

    private String beskrivelse;
    private ProduktGruppe produktgruppe;
    private ArrayList<Prisliste> prislister;

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

    public void addPrisliste(Prisliste prisliste){
        if (!prislister.contains(prisliste)){
            prislister.add(prisliste);
        }
    }

    public void removePrisliste(Prisliste prisliste){
        if (prislister.contains(prisliste)){
            prislister.remove(prisliste);
        }
    }
    public double getPris(Prisliste prisliste){
       return prisliste.getPris(this);
    }

    //Virker ikke i, opret klassen. hvis der er pris med.
    //public String toString(){return this.beskrivelse + "\t\t\t" + this.getPris() + ",-";}
    public String toString(){return this.beskrivelse;}


}
