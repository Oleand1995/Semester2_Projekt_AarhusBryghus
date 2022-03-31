package application.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Prisliste {

    private String situation;
    private ArrayList<Pris> priser = new ArrayList<>();

    public Prisliste(String situation){
        this.situation = situation;
    }

    public void addPris(Pris pris){
        if (!priser.contains(pris)){
            priser.add(pris);
        }
    }

    public void removePris(Pris pris){
        priser.remove(pris);
    }

    public ArrayList<Pris> getPriser(){return new ArrayList<>(priser);}

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }
}
