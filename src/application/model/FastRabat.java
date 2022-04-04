package application.model;

public class FastRabat implements RabatBeregning {

    private double fastRabat;

    public FastRabat(double fastRabat){

        this.fastRabat = fastRabat;
    }

    @Override
    public double getRabat(double pris) {
        pris = pris - fastRabat;
        return pris;
    }
}
