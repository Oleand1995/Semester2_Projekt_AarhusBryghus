package application.model;

import java.util.ArrayList;

public class OrdreLinje {

    private int antal;
    private Pris pris;
    private RabatBeregning rabatBeregning;

    public OrdreLinje(Pris pris){
        this.antal = 1;
        this.pris = pris;
        this.rabatBeregning = null;
    }

    public void setRabatBeregning(RabatBeregning rabatBeregning){
        this.rabatBeregning = rabatBeregning;
    }

    public RabatBeregning getRabatBeregning(){
        return this.rabatBeregning;
    }

    public int getAntal() {
        return antal;
    }

    public void setAntal(int antal) {
        this.antal = antal;
    }

    public void countDown(){
        this.antal--;
    }

    public void setPris(Pris pris){
        this.pris = pris;
    }

    public Pris getPris(){return this.pris;}


    @Override
    public String toString(){return pris + " | " + antal + " stk";}



}
