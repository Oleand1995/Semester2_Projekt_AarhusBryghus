package application.model;

import java.io.Serializable;

public class Produkt implements Serializable {

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

    public String toString(){return this.beskrivelse;}

    public ProduktGruppe getProduktgruppe() {
        return produktgruppe;
    }
}
