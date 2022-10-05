package fr.univ_lyon1.info.m1.mes.model;

public class Pediatrician extends HealthProfessional {

    private final Prescription[] prefPediatricianPrescriptions = {
        new Prescription(this, "Bonbon"),
        new Prescription(this, "Less Fastfood"),
        new Prescription(this, "More sport"),
        new Prescription(this, "Paracetamol 100")
    };

    /**
     * Pediatrician Constructor.
     * 
     * @param name
     */
    public Pediatrician(final String name) {
        super(name);
    }
}
