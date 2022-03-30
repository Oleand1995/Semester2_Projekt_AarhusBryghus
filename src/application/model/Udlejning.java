package application.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Udlejning {

    private LocalDateTime udlejningsTidspunkt;
    private LocalDateTime afleveringsTidspunkt;
    private double samletPris;
    private String lejersNavn;
    private ArrayList<Produkt> produkter;
    private Salg salg;

    public Udlejning(LocalDateTime udlejningsTidspunkt, LocalDateTime afleveringsTidspunkt, double samletPris, String lejersNavn) {
        this.udlejningsTidspunkt = udlejningsTidspunkt;
        this.afleveringsTidspunkt = afleveringsTidspunkt;
        this.samletPris = samletPris;
        this.lejersNavn = lejersNavn;
        produkter = new ArrayList<>();
        this.salg = null;
    }

    public LocalDateTime getUdlejningsTidspunkt() {
        return udlejningsTidspunkt;
    }

    public void setUdlejningsTidspunkt(LocalDateTime udlejningsTidspunkt) {
        this.udlejningsTidspunkt = udlejningsTidspunkt;
    }

    public LocalDateTime getAfleveringsTidspunkt() {
        return afleveringsTidspunkt;
    }

    public void setAfleveringsTidspunkt(LocalDateTime afleveringsTidspunkt) {
        this.afleveringsTidspunkt = afleveringsTidspunkt;
    }

    public double getSamletPris() {
        return samletPris;
    }

    public String getLejersNavn() {
        return lejersNavn;
    }

    public void setLejersNavn(String lejersNavn) {
        this.lejersNavn = lejersNavn;
    }


    public void addProduktTilUdlejning(Produkt produkt){
        produkter.add(produkt);
    }

    public void removeProduktFraUdlejning(Produkt produkt){
        produkter.remove(produkt);
    }


    public Salg createSalg(LocalDateTime salgsTidspunkt, Prisliste prisliste){
            salg = new Salg(udlejningsTidspunkt,prisliste);

        return salg;
    }













}
