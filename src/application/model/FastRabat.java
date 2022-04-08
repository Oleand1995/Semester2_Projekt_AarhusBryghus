package application.model;

import java.io.Serializable;

public class FastRabat implements RabatBeregning, Serializable {

    private double fastRabat;

    public FastRabat(double fastRabat){

        this.fastRabat = fastRabat;
    }


    @Override
    public double getRabat(double pris) {
        pris = pris - fastRabat;
        return pris;
    }

    @Override
    public String getTypeAfRabat(){
        return "Fast rabat på " + this.fastRabat + ".-" ;
    }


}
