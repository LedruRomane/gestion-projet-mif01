package fr.univ_lyon1.info.m1.mes.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class Patient {

    /**
     * Attributes.
     */
    private PropertyChangeSupport changes = new PropertyChangeSupport(this);
    private final List<Prescription> prescriptions = new ArrayList<>();
    private final String name;
    private final String ssID;

    /**
     * Patient Constructor.
     * 
     * @param name
     * @param ssID
     */
    public Patient(final String name, final String ssID) {
        this.name = name;
        this.ssID = ssID;
    }

    /**
     * Get Patient name. 
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Get Patient SSID.
     * 
     * @return String
     */
    public String getSSID() {
        return ssID;
    }

    /**
     * Get Patient Prescriptions.
     * 
     * @return List<Prescription>
     */
    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    /**
     * Add a prescription to the Patient.
     * 
     * @param hp
     * @param content
     */
    public void addPrescription(final HealthProfessional hp, final String content) {
        prescriptions.add(new Prescription(hp, content));
        changes.firePropertyChange("prescription", null, this.getPrescriptions());
    }

    /**
     * Remove a prescription to the Patient.
     * 
     * @param p
     */
    public void removePrescription(final Prescription p) {
        prescriptions.remove(p);
        changes.firePropertyChange("prescription", null, this.getPrescriptions());
    }

    public void addPropertyChangeListener(final String name, final PropertyChangeListener l) {
        changes.addPropertyChangeListener(name, l);
    }

    public void removePropertyChangeListener(final String name, final PropertyChangeListener l) {
        changes.removePropertyChangeListener(name, l);
    }
}
