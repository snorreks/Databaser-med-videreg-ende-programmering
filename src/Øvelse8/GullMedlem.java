package Øvelse8;

import java.time.LocalDate;

public class GullMedlem extends BonusMedlem {

    public GullMedlem(int medlNr, Personalia pers, LocalDate innmeldtDato, int poeng) {
        super(medlNr, pers, innmeldtDato);
        super.registrerPoeng(poeng);

    }

    @Override
    public void registrerPoeng(int nyPoeng) {
        super.registrerPoeng((int) (nyPoeng * 1.5));
    }
}
