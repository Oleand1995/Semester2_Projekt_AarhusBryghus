package application.model;

public class procentRabat implements Rabat {

    @Override
    public double rabat(double pris) {
        double fradrag = pris * 0.3;
        return pris + ((fradrag >= 0.3) ? fradrag : 3);
    }
}
