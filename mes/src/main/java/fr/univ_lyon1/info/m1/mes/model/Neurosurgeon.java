package fr.univ_lyon1.info.m1.mes.model;

public class Neurosurgeon extends HealthProfessional {

    private final Prescription[] prefNeuroPrescriptions = {
        new Prescription(this, "Paracétamol"),
        new Prescription(this, "Head magic healer"),
        new Prescription(this, "Paillettes à cerveau")
    };

    /**
     * Neurosurgeon Constructor.
     * 
     * @param name
     */
    public Neurosurgeon(final String name) {
        super(name);
    }

}

