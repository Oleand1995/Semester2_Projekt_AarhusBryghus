package application.model;

public class FastRabat implements RabatBeregning {

    @Override
    public double rabat(double pris) {
        return pris * 0.90;
    }
}
