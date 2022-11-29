package fr.univ_lyon1.info.m1.mes.model;

public class Pulmonologist extends HealthProfessional {

    /**
     * Pulmonologist Constructor.
     * @param name String
     */
    public Pulmonologist(final String name) {
        super(name);
        this.addPrefPrescription(new Prescription(this, "More sport"));
        this.addPrefPrescription(new Prescription(this, "Paracetamol 500"));
        this.addPrefPrescription(new Prescription(this, "Breath test"));
    }
}
