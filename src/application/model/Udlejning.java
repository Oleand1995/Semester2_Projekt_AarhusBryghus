package application.model;

import application.controller.Controller;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Udlejning implements Serializable {

    private LocalDateTime udlejningsTidspunkt;
    private LocalDateTime afregningsTidspunkt;
    private double samletPris;
    private ArrayList<OrdreLinje> ordrelinjer;
    private String lejersNavn;
    private Salg salg;

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

    public void setUdlejningsTidspunkt(LocalDateTime udlejningsTidspunkt) {
        this.udlejningsTidspunkt = udlejningsTidspunkt;
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

    public void setLejersNavn(String lejersNavn) {
        this.lejersNavn = lejersNavn;
    }

    public Salg getSalg() {
        return salg;
    }

    public ArrayList<OrdreLinje> getOrdrelinjer(){return new ArrayList<>(ordrelinjer);}

    public String toString(){
        if (afregningsTidspunkt == null){
            return "Navn: " + lejersNavn + " | Udlejningstidspunkt: " + udlejningsTidspunkt.truncatedTo(ChronoUnit.MINUTES) + " | Pris: " + samletPris + ",-";
        }
        else{
            return "Navn: " + lejersNavn + " | Afregningstidspunkt: " + afregningsTidspunkt.truncatedTo(ChronoUnit.MINUTES) + " | Pris: " + samletPris + ",-";
        }

    }
}
