package application.model;

import java.util.ArrayList;

public class ProduktGruppe {

    private String produktType;
    private ArrayList<Produkt> produkter;

    public ProduktGruppe(String produktType){
        this.produktType = produktType;
        produkter = new ArrayList<>();
    }
    //--------------------------------------------------------------------------

    public ArrayList<Produkt> getProdukter(){
        return new ArrayList<>(produkter);
    }

    public Produkt createProdukt(String beskrivelse){
        Produkt produkt = new Produkt(beskrivelse,this);
        produkter.add(produkt);
        return produkt;
    }

    public void removeProdukt(Produkt produkt){
        if (produkter.contains(produkt)){
            produkter.remove(produkt);
        }
    }

    public String getProduktType(){
        return this.produktType;
    }

    public void setProduktType(String produktType){
        this.produktType = produktType;
    }

    @Override
    public String toString(){
        return produktType;
    }



}
