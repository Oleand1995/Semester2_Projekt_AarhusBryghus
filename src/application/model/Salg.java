package application.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Salg {

    private LocalDateTime salgsTidspunkt;
    private double samletPris;
    private int samletKlip;
    private RabatBeregning rabat;
    private ArrayList<OrdreLinje> ordrelinjer;


    public Salg(LocalDateTime salgsTidspunkt){
        this.salgsTidspunkt = salgsTidspunkt;
        this.ordrelinjer = new ArrayList<>();
    }

    public LocalDateTime getSalgsTidspunkt() {
        return salgsTidspunkt;
    }

    public int getSamletKlip() {
        return samletKlip;
    }


    public ArrayList<OrdreLinje> getOrdreliner(){
        return new ArrayList<>(ordrelinjer);
    }

    public OrdreLinje createOrdrelinje(Pris pris){
        OrdreLinje ordreLinje = new OrdreLinje(pris);
        ordrelinjer.add(ordreLinje);
        return ordreLinje;
    }

    public void removeOrdrelinje(OrdreLinje ordreLinje){
        if (ordrelinjer.contains(ordreLinje)){
            ordrelinjer.remove(ordreLinje);
        }
    }

    public double getSamletPris(){
        double samletPris = 0;
        for (OrdreLinje o : ordrelinjer){
            samletPris += o.getPris().getPris() * o.getAntal();
        }
        return samletPris;
    }



}