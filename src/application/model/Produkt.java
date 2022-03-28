package application.model;

public class Produkt {

    private String beskrivelse;
    private ProduktGruppe produktgruppe;
    private Prisliste prisliste;

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

    public void setPrisliste(Prisliste prisliste){
        this.prisliste = prisliste;
    }

    public int getPris(){
       return prisliste.getPris(this);
    }

    //Virker ikke i, opret klassen. hvis der er pris med.
    //public String toString(){return this.beskrivelse + "\t\t\t" + this.getPris() + ",-";}
    public String toString(){return this.beskrivelse;}

}
