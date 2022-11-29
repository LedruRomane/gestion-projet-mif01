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
     * @param p
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
     * @param hp
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
     * Find Patient by SSID.
     * @param ssID String
     * @return Patient or null.
     */
    public Patient findPatientBySSID(final String ssID) {
        Patient resultat = null;
        if (this.patientsList.containsKey(ssID)) {
            resultat = this.patientsList.get(ssID);
        }
        return resultat;
    }

    /**
     * Find Patient by starting Name.
     * @param nom String
     * @return List Patient
     */
    public List<Patient> findPatientByName(final String nom) {
        List<Patient> resultat = new ArrayList<Patient>();
        for (Patient p : this.patientsList.values()) {
            if (p.getName().startsWith(nom)) {
                resultat.add(p);
            }
        }
        return resultat;
    }

    /**
     * Find Patient by a Prescription (keyword or part of it).
     * @param prescription String
     * @return List Patient
     */
    public List<Patient> findPatientsByPrescription(final String prescription) {
        List<Patient> resultat = new ArrayList<Patient>();
        for (Patient p : this.patientsList.values()) {
            for (Prescription e : p.getPrescriptions()) {
                if (e.getContent().contains(prescription)) {
                    resultat.add(p);
                    break;
                }
            }
        }
        return resultat;
    }

    /**
     * Create an example configuration for the current instance.
     * 
     */

    public void createExampleConfiguration() {
        final Patient a = createPatient("Alice Foo", "299010212345678");
        final Patient b = createPatient("Bob Bar", "199010212345678");
        final Patient c = createPatient("Charles Boz", "102020212345678");

        final HealthProfessional w = createHealthProfessional(
                HealthProfessionalType.PEDIATRICIAN,
                "Dr. Who");
        final HealthProfessional s = createHealthProfessional(
                HealthProfessionalType.DENTIST,
                "Dr. Strange");
        final HealthProfessional p = createHealthProfessional(
                HealthProfessionalType.PULMONOLOGIST,
                "Dr. Epstein");
        createHealthProfessional(HealthProfessionalType.HOMEOPATH, "Dr. Hahnemann");

        a.addPrescription(new Prescription(w, "One apple a day"));
        b.addPrescription(new Prescription(w, "One apple a day"));
        a.addPrescription(new Prescription(w, "Sport twice a week"));
        b.addPrescription(new Prescription(w, "Whatever placebo, you're not sick"));
        b.addPrescription(new Prescription(s, "Snake oil"));
        b.addPrescription(new Prescription(p, "Snake oil"));
        c.addPrescription(new Prescription(p, "apple ddd"));
    }

    /**
     * Ajout d'un object d'écoute sur MES.
     * @param name
     * @param l
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
