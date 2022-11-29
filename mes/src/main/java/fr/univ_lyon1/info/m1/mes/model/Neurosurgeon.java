package fr.univ_lyon1.info.m1.mes.model;

public class Neurosurgeon extends HealthProfessional {

    /**
     * Neurosurgeon Constructor.
     * @param name String
     */
    public Neurosurgeon(final String name) {
        super(name);
        this.addPrefPrescription(new Prescription(this, "Paracetamol"));
        this.addPrefPrescription(new Prescription(this, "Head magic healer"));
        this.addPrefPrescription(new Prescription(this, "Paillettes à cerveau"));

    }

}

