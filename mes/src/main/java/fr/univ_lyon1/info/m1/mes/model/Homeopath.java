package fr.univ_lyon1.info.m1.mes.model;

public class Homeopath extends HealthProfessional {

    /**
     * Homeopath Constructor.
     * 
     * @param name
     */
    public Homeopath(final String name) {
        super(name);
        this.addPrefPrescription(new Prescription(this, "Natrum Muriaticum 30CH"));
        this.addPrefPrescription(new Prescription(this, "Sucre 200K"));
    }
}
