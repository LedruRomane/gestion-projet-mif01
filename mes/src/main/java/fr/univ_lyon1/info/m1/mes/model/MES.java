package fr.univ_lyon1.info.m1.mes.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public final class MES {

    private PropertyChangeSupport changes = new PropertyChangeSupport(this);
    private final List<HealthProfessional> healthProfessionalsList = new ArrayList<>();
    private final Map<String, Patient> patientsList = new HashMap<>();

    private static MES singleInstance = null;

    /**
     * Constructor.
     */
    private MES() {
    }

    /**
     * Singleton.
     * @return the instance of MES.
     */
    public static MES getInstance() {
        if (singleInstance == null) {
            singleInstance = new MES();
        }
        return singleInstance;
    }

    /**
     * Return HealthProfessionals list within MES.
     * @return List HealthProfessional
     */
    public List<HealthProfessional> getHealthProfessionals() {
        return healthProfessionalsList;
    }

    /**
     * Return patients list within MES.
     * @return List Patient
     */
    public List<Patient> getPatients() {
        return new ArrayList<>(patientsList.values());
    }

    /**
     * Add a new patient to MES.
     * 
     * @param p Patient
     * @return boolean
     */
    public boolean addPatient(final Patient p) {
        if (patientsList.containsKey(p.getSsid())) {
            return false;
        }
        patientsList.put(p.getSsid(), p);
        changes.firePropertyChange("patientList", null, this.getPatients());
        return true;
    }

    /**
     * Add a new health professional to MES.
     * 
     * @param hp HealthProfessional
     * @return boolean
     */
    public boolean addHealthProfessional(final HealthProfessional hp) {
        if (healthProfessionalsList.contains(hp)) {
            return false;
        }
        healthProfessionalsList.add(hp);
        changes.firePropertyChange("healthList", null, this.getHealthProfessionals());
        return true;
    }

    /**
     * Ajout d'un object d'écoute sur MES.
     * @param name String
     * @param l PropertyChangeListener
     */
    public void addPropertyChangeListener(final String name, final PropertyChangeListener l) {
        changes.addPropertyChangeListener(name, l);
    }

    /**
     * Delete object's listenner on MES.
     * @param name String
     * @param l PropertyChangeListener
     */
    public void removePropertyChangeListener(final String name, final PropertyChangeListener l) {
        changes.removePropertyChangeListener(name, l);
    }
}
