package application.model;

import java.io.Serializable;

public class ProcentRabat implements RabatBeregning, Serializable {

    private double procentRabat;

    public ProcentRabat(double procentRabat){
        this.procentRabat = procentRabat;
    }


    @Override
    public double getRabat(double pris) {
        double tal = procentRabat / 100;
        double rabatPris = pris * tal;
        pris = pris - rabatPris;
        return pris;
    }

    @Override
    public String getTypeAfRabat(){
        return "Procent rabat på " + this.procentRabat + "%";
    }

}

