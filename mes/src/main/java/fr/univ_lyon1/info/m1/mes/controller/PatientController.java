package fr.univ_lyon1.info.m1.mes.controller;

import java.util.List;

import fr.univ_lyon1.info.m1.mes.model.MES;
import fr.univ_lyon1.info.m1.mes.model.Patient;
import fr.univ_lyon1.info.m1.mes.model.Prescription;
import fr.univ_lyon1.info.m1.mes.view.PatientView;

public class PatientController {

    private final MES model;
    private PatientView view;
    private Patient currentPatient;

    /**
     * Constructor Patient Controller.
     * @param mes MES
     */
    public PatientController(final MES mes) {
        this.currentPatient = null;
        this.model = mes;
        this.view = null;
    }

    public void setView(final PatientView view) {
        this.view = view;
        this.model.addPropertyChangeListener("patientList", this.view);
    }

    /**
     * Give selected patient to the view.
     * @param patient Patient
     */
    public void selectPatient(final Patient patient) {
        if (patient == null) { 
            return; 
        }
        this.currentPatient = patient;
        this.view.selectPatient(patient);
        this.currentPatient.addPropertyChangeListener("prescription", this.view.getPatientBox());
    }

    public List<Patient> getPatients() {
        return this.model.getPatients();
    }

    /**
     * Create a new Patient.
     * @param name String
     * @param ssid String
     * @return Patient
     */
    public Patient createPatient(final String name, final String ssid) {
        Patient newPatient = new Patient(name, ssid);
        if (model.addPatient(newPatient)) {
            return newPatient;
        }
        return null;
    }

    /**
     * Delete a prescription from the current Patient.
     * @param p Prescription
     */
    public void deletePrescription(final Prescription p) {
        this.currentPatient.removePrescription(p);
    }

    /**
     * Return Patient view.
     * @return PatientView
     */
    public PatientView getView() {
        return this.view;
    }
}
