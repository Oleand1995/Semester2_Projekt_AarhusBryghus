package application.model;

public abstract class Produkt {

    private String navn;
    private int antal;

    public Produkt(String navn, int antal){
        this.navn = navn;
        this.antal = antal;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public int getAntal() {
        return antal;
    }

    public void setAntal(int antal) {
        this.antal = antal;
    }
}
