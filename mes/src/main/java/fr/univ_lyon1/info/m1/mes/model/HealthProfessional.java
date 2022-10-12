package fr.univ_lyon1.info.m1.mes.model;

import java.util.ArrayList;

public class HealthProfessional {

    /*
     * Attributes
     */
    private final String name;
    private final ArrayList<Prescription> prefPrescriptions = new ArrayList<>();

    /*
     * Constructor
     * 
     * @param name
     * @param mes
     */
    public HealthProfessional(final String name) {
        this.name = name;
    }

    /**
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * @return ArrayList<Prescription>
     */
    public ArrayList<Prescription> getPrefPrescription() {
        return this.prefPrescriptions;
    }

    /**
     * @param prescription prescription pref
     */
    public void addPrefPrescription(final Prescription prescription) {
        this.prefPrescriptions.add(prescription);
    }
}
