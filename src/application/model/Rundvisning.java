package application.model;

import java.time.LocalDate;

public class Rundvisning extends Oevrige {

    private LocalDate startTidspunkt;
    private LocalDate slutTidspunkt;


    public Rundvisning(String navn, int antal) {
        super(navn, antal);

        this.startTidspunkt = startTidspunkt;
        this.slutTidspunkt = slutTidspunkt;
    }
}
