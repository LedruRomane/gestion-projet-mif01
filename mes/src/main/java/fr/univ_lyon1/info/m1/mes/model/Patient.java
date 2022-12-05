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
    private String name;
    private String ssid;

    /**
     * Patient Constructor.
     * @param name
     * @param ssid
     */
    public Patient(final String name, final String ssid) {
        this.name = name;
        this.ssid = ssid;
    }

    public Patient() {

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
     * Get Patient ssid.
     * 
     * @return String
     */
    public String getSsid() {
        return ssid;
    }

    /**
     * Set Patient name.
     * @param name String
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Set Patient ssid.
     * @param ssid String
     */
    public void setSsid(final String ssid) {
        this.ssid = ssid;
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
     * @param p
     */
    public void addPrescription(final Prescription p) {
        prescriptions.add(p);
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
