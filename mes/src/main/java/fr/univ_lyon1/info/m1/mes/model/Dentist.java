package fr.univ_lyon1.info.m1.mes.model;

public class Dentist extends HealthProfessional {

    private final Prescription[] prefPediatricianPrescriptions = {
        new Prescription(this, "Paracetamol"),
        new Prescription(this, "Less sugar & soda"),
    };

    /**
     * Dentist Constructor.
     * 
     * @param name
     */
    public Dentist(final String name) {
        super(name);
    }
}
