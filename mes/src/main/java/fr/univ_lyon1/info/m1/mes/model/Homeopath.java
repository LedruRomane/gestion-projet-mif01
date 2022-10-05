package fr.univ_lyon1.info.m1.mes.model;

public class Homeopath extends HealthProfessional {

    private final Prescription[] prefPediatricianPrescriptions = {
        new Prescription(this, "Natrum Muriaticum 30CH"),
        new Prescription(this, "Sucre 200K"),
    };

    /**
     * Homeopath Constructor.
     * 
     * @param name
     */
    public Homeopath(final String name) {
        super(name);
    }
}
