package application.model;

public class Beklaedning extends Oevrige {

    private String størrelse;
    private String mærke;

    public Beklaedning(String navn, int antal) {
        super(navn, antal);

        this.størrelse = størrelse;
        this.mærke = mærke;
    }
}
