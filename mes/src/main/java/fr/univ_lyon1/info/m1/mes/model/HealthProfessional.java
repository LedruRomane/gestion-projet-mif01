package fr.univ_lyon1.info.m1.mes.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class HealthProfessional {
    private static Integer idCounter = 0;
    /*
     * Attributes
     */
    private PropertyChangeSupport changes = new PropertyChangeSupport(this);
    private final String name;
    private final ArrayList<Prescription> prefPrescriptions = new ArrayList<>();
    private final Integer id;

    /*
     * Constructor
     * 
     * @param name
     * @param id
     */
    public HealthProfessional(final String name) {
        this.name = name;
        this.id = idCounter++;
    }

    /**
     * Return HealthProfessional Name.
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * @return Integer
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return ArrayList<Prescription>
     */
    public ArrayList<Prescription> getPrefPrescription() {
        return this.prefPrescriptions;
    }

    /**
     * Add preference prescription.
     * @param prescription Prescription
     */
    public void addPrefPrescription(final Prescription prescription) {
        this.prefPrescriptions.add(prescription);
    }

    /**
     * Add object's listenner on HealthProfessional.
     * @param name String
     * @param l PropertyChangeListener
     */
    public void addPropertyChangeListener(final String name, final PropertyChangeListener l) {
        changes.addPropertyChangeListener(name, l);
    }

    /**
     * Delete object's listenner on HealthProfessional.
     * @param name String
     * @param l PropertyChangeListener
     */
    public void removePropertyChangeListener(final String name, final PropertyChangeListener l) {
        changes.removePropertyChangeListener(name, l);
    }
}
