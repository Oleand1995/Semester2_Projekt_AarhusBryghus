package application.model;

import application.controller.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Udlejning {

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

    public String toString(){return lejersNavn + ": " + ordrelinjer + " | Total: " + samletPris + ",-";}
}
