package application.model;

import java.time.LocalDate;

public class Rundvisning extends Oevrige {

    private LocalDate startTidspunkt;
    private LocalDate slutTidspunkt;


    public Rundvisning(String navn, int antal, double barPris, double butiksPris) {
        super(navn, antal, barPris, butiksPris);

        this.startTidspunkt = startTidspunkt;
        this.slutTidspunkt = slutTidspunkt;
    }
}
