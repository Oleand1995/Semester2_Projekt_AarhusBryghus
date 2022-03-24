package application.model;

public class Fadøl extends Drikkervare {

    private int klipPris;
    private String størrelse;

    public Fadøl(int klippris, String størrelse){

     this.klipPris = klippris;
     this.størrelse = størrelse;
    }

    public int getKlipPris() {
        return klipPris;
    }

    public void setKlipPris(int klipPris) {
        this.klipPris = klipPris;
    }

    public String getStørrelse() {
        return størrelse;
    }

    public void setStørrelse(String størrelse) {
        this.størrelse = størrelse;
    }
}
