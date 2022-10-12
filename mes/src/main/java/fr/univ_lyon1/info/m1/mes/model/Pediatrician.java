package fr.univ_lyon1.info.m1.mes.model;

public class Pediatrician extends HealthProfessional {

    /**
     * Pediatrician Constructor.
     * 
     * @param name
     */
    public Pediatrician(final String name) {
        super(name);
        this.addPrefPrescription(new Prescription(this, "More sport"));
        this.addPrefPrescription(new Prescription(this, "Less Fastfood"));
        this.addPrefPrescription(new Prescription(this, "Bonbon"));
        this.addPrefPrescription(new Prescription(this, "Paracetamol 100"));

    }
}
