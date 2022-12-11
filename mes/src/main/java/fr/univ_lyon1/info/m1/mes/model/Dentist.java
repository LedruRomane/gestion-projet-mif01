package fr.univ_lyon1.info.m1.mes.model;

/**
 * Dentist class type of HealthProfessional.
 */
public class Dentist extends HealthProfessional {

    /**
     * Dentist Constructor.
     * @param name String
     */
    public Dentist(final String name) {
        super(name);
        this.addPrefPrescription(new Prescription(this, "Paracetamol"));
        this.addPrefPrescription(new Prescription(this, "Less sugar & soda"));
    }
}
