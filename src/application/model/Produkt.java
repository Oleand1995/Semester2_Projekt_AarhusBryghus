package application.model;

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

}
