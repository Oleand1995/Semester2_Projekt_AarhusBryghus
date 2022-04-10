package application.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Udlejning implements Serializable {

    private LocalDateTime udlejningsTidspunkt;
    private LocalDateTime afregningsTidspunkt;
    private double samletPris;
    private ArrayList<OrdreLinje> ordrelinjer;
    private String lejersNavn;

    public Udlejning(LocalDateTime udlejningsTidspunkt, LocalDateTime afregningsTidspunkt, double samletPris, String lejersNavn, ArrayList<OrdreLinje> ordrelinjer) {
        this.udlejningsTidspunkt = udlejningsTidspunkt;
        this.afregningsTidspunkt = afregningsTidspunkt;
        this.samletPris = samletPris;
        this.lejersNavn = lejersNavn;
        this.ordrelinjer = ordrelinjer;
    }

    public LocalDateTime getUdlejningsTidspunkt() {
        return udlejningsTidspunkt;
    }

    public LocalDateTime getAfregningsTidspunkt() {
        return afregningsTidspunkt;
    }

    public void setAfregningsTidspunkt(LocalDateTime afleveringsTidspunkt) {
        this.afregningsTidspunkt = afleveringsTidspunkt;
    }

    public double getSamletPris() {
        return samletPris;
    }

    public void setSamletPris(double samletPris) {
        this.samletPris = samletPris;
    }

    public String getLejersNavn() {
        return lejersNavn;
    }

    public ArrayList<OrdreLinje> getOrdrelinjer(){return new ArrayList<>(ordrelinjer);}

    public void removeOrdrelinje(OrdreLinje ordreLinje){
        if (ordrelinjer.contains(ordreLinje)){
            ordrelinjer.remove(ordreLinje);
        }
    }

    public String toString(){
        if (afregningsTidspunkt == null){
            return "Navn: " + lejersNavn + " | Udlejningstidspunkt: " + udlejningsTidspunkt.truncatedTo(ChronoUnit.MINUTES) + " | Pris: " + samletPris + ",-";
        }
        else{
            return "Navn: " + lejersNavn + " | Afregningstidspunkt: " + afregningsTidspunkt.truncatedTo(ChronoUnit.MINUTES) + " | Pris: " + samletPris + ",-";
        }

    }
}
