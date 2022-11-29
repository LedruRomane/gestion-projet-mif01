package fr.univ_lyon1.info.m1.mes.controller;

import java.util.ArrayList;
import java.util.List;

import fr.univ_lyon1.info.m1.mes.model.HealthProfessional;
import fr.univ_lyon1.info.m1.mes.model.HealthProfessionalFactory;
import fr.univ_lyon1.info.m1.mes.model.MES;
import fr.univ_lyon1.info.m1.mes.model.Patient;
import fr.univ_lyon1.info.m1.mes.model.Prescription;
import fr.univ_lyon1.info.m1.mes.types.HealthProfessionalType;
import fr.univ_lyon1.info.m1.mes.types.PatientSearchStrategyType;
import fr.univ_lyon1.info.m1.mes.view.HealthProfessionalView;

public class HealthProfessionalController {

    private final MES model;
    private HealthProfessionalView view;
    private HealthProfessional currentPro;
    private Patient currentPatient;

    /**
     * Constructeur.
     * Instancie la vue healthprofessional view avec comme param le controlleur (this) et le model.
     * Ajoute le listener au modèle pour tout ajout d'un healthpro.
     * @param mes de type MES
     */
    public HealthProfessionalController(final MES mes) {
        this.currentPro = null;
        this.currentPatient = null;
        this.model = mes;
        this.view = null;
    }

    public void setView(final HealthProfessionalView view) {
        this.view = view;
        this.model.addPropertyChangeListener("healthList", this.view);
    }

    /**
     * Méthode qui gère la selection d'un healthpro dans la combobox, 
     * le charge et l'affiche sur la bonne vue.
     * @param pro HealthProfessional
     */
    public void selectHealthProfessional(final HealthProfessional pro) {
        if (pro == null) { 
            return; 
        }
        this.currentPro = pro;
        this.currentPatient = null; // Suppression du choix d'utilisateur
        this.view.selectHealthProfessionnal(pro);
        this.currentPro.addPropertyChangeListener(
            "prescription", 
            this.view.getHealthProfessionalBox());
    }

    /**
     * Gère la selection d'un patient dans la combobox multiplechoice.
     * @param patient Patient
     */
    public void selectPatient(final Patient patient) {
        if (patient == null) { 
            return; 
        }
        this.currentPatient = patient;
        this.view.selectPatient(patient);
        this.currentPatient.addPropertyChangeListener(
            "prescription", 
            this.view.getHealthProfessionalBox());
    }

    /**
     * Create a HealthProfessional.
     * @param model HealthProfessionalType
     * @param name String
     * @return HealthProfessional
     */
    public HealthProfessional createHealthProfessional(
            final HealthProfessionalType model,
            final String name) {
        HealthProfessional newPro = HealthProfessionalFactory.createHealthProfessional(model, name);
        if (this.model.addHealthProfessional(newPro)) {
            return newPro;
        }
        return null;
    }

    /**
     * Méthode qui gère les différents type de recherches des patients sur la vue healthPro.
     * @param politique String
     * @param valeur String
     * @return List Patient
     */
    public List<Patient> getPatients(
        final PatientSearchStrategyType politique, final String valeur) {
        List<Patient> resultat = new ArrayList<Patient>();
        politique.getStrategyClass().search(this.model.getPatients(), valeur).forEach((patient) -> {
            resultat.add(patient);
        });
        return resultat;
    }

    public List<HealthProfessional> getHealthProfessionals() {
        return this.model.getHealthProfessionals();
    }

    /**
     * Return patient's list of prescription for the current healthpro.
     * @param patient Patient
     * @return List Prescription
     */
    public List<Prescription> getPrescriptionsByPatient(final Patient patient) {
        List<Prescription> resultat = new ArrayList<Prescription>();
        for (final Prescription prescription : patient.getPrescriptions()) {
            if (prescription.getHealthProfessional() == this.currentPro) {
                resultat.add(prescription);
            }
        }
        return resultat;
    }

    /**
     * Delete Prescription from current Patient.
     * @param p Prescription
     */
    public void deletePrescription(final Prescription p) {
        if (this.currentPatient == null) { 
            throw new Error("Missing selected patient"); 
        }
        this.currentPatient.removePrescription(p);
    }

    /**
     * Add a prescription to current patient.
     * @param prescription String
     * @return Prescription
     */
    public Prescription addPrescription(final String prescription) {
        if (this.currentPatient == null) { 
            throw new Error("Missing selected patient"); 
        }
        Prescription newPresc = new Prescription(currentPro, prescription);
        this.currentPatient.addPrescription(newPresc);
        return newPresc;
    }

    /**
     * Return main view.
     * @return HealthProfessionalView.
     */
    public HealthProfessionalView getView() {
        return this.view;
    }
}
