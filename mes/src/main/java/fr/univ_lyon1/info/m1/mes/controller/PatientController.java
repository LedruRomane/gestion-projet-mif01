package fr.univ_lyon1.info.m1.mes.controller;

import fr.univ_lyon1.info.m1.mes.model.MES;
import fr.univ_lyon1.info.m1.mes.model.Patient;
import fr.univ_lyon1.info.m1.mes.model.Prescription;
import fr.univ_lyon1.info.m1.mes.view.PatientView;

public class PatientController {
    private final MES model;
    private final PatientView view;
    private Patient currentPatient;

    public PatientController(final MES mes) {
        this.currentPatient = null;
        this.model = mes;
        this.view = new PatientView(this, this.model);
        this.model.addPropertyChangeListener("patientList", this.view);
    }

    public void selectPatient(final Patient pro) {
        if (pro == null) { 
            return; 
        }
        this.currentPatient = pro;
        this.view.selectPatient(pro);
        this.currentPatient.addPropertyChangeListener("prescription", this.view.getPatientBox());
    }

    public Patient createPatient(final String name, final String ssid) {
        return model.createPatient(name, ssid);
    }

    public void deletePrescription(final Prescription p) {
        this.currentPatient.removePrescription(p);
    }

    public PatientView getView() {
        return this.view;
    }
}
