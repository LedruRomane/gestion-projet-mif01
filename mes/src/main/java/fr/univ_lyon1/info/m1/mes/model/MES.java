package fr.univ_lyon1.info.m1.mes.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.univ_lyon1.info.m1.mes.dao.PatientDao;
import fr.univ_lyon1.info.m1.mes.types.HealthProfessionalType;

public class MES {

    /**
     * Attributes.
     */
    private PropertyChangeSupport changes = new PropertyChangeSupport(this);
    private final List<HealthProfessional> healthProfessionalsList = new ArrayList<>();
    private final Map<String, Patient> patientsList = new HashMap<>();


    /**
     * Constructor.
     */
    public MES() {
        this.createExampleConfiguration();
    }

    /**
     * Return HealthProfessionals list within MES.
     * 
     * @return List<HealthProfessional>
     */
    public List<HealthProfessional> getHealthProfessionals() {
        return healthProfessionalsList;
    }

    /**
     * Return patients list within MES.
     * 
     * @return List<Patient>
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
        changes.firePropertyChange("healthProfessionalList", null, this.getHealthProfessionals());
        return true;
    }

    /**
     * Find Patient by SSID.
     * @param ssID
     * @return 1 Patient or null.
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
     * @param nom
     * @return List<Patient>.
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
     * @param prescription
     * @return List<Patient>.
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
        PatientDao patientDao = new PatientDao();
        Map<Integer, Patient> map = patientDao.findAll();

        final HealthProfessional w = HealthProfessionalFactory.createHealthProfessional(
                HealthProfessionalType.PEDIATRICIAN,
                "Dr. Who");
        final HealthProfessional s = HealthProfessionalFactory.createHealthProfessional(
                HealthProfessionalType.DENTIST,
                "Dr. Strange");
        final HealthProfessional p = HealthProfessionalFactory.createHealthProfessional(
                HealthProfessionalType.PULMONOLOGIST,
                "Dr. Epstein");
        this.addHealthProfessional(w);
        this.addHealthProfessional(s);
        this.addHealthProfessional(p);

        for (Patient pa : map.values()) {
            this.addPatient(pa);
        }
        
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
     * Suppression d'un object d'écoute sur MES.
     * @param name
     * @param l
     */
    public void removePropertyChangeListener(final String name, final PropertyChangeListener l) {
        changes.removePropertyChangeListener(name, l);
    }
}
