package fr.univ_lyon1.info.m1.mes.model;

public class Pulmonologist extends HealthProfessional {

    private final Prescription[] prefPediatricianPrescriptions = {
        new Prescription(this, "More sport"),
        new Prescription(this, "Paracetamol 500"),
        new Prescription(this, "Breath test"),
    };

    /**
     * Pulmonologist Constructor.
     * 
     * @param name
     */
    public Pulmonologist(final String name) {
        super(name);
    }
}
