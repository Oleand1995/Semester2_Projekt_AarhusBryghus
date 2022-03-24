package application.model;

import java.time.LocalDate;

public class Rundvisning {

    private LocalDate startTidspunkt;
    private LocalDate slutTidspunkt;

    public Rundvisning (LocalDate startTidspunkt, LocalDate slutTidspunkt){
        this.startTidspunkt = startTidspunkt;
        this.slutTidspunkt = slutTidspunkt;
    }
}
