package fr.univ_lyon1.info.m1.mes.controller;

import fr.univ_lyon1.info.m1.mes.model.MES;
import fr.univ_lyon1.info.m1.mes.model.Patient;
import fr.univ_lyon1.info.m1.mes.view.PatientView;

public class PatientController {
    private final MES model;
    private final PatientView view;

    public PatientController(final MES mes) {
        this.model = mes;
        this.view = new PatientView(this, this.model);
        this.model.addPropertyChangeListener("patientList", this.view);
    }

    public Patient createPatient(final String name, final String ssid) {
        return model.createPatient(name, ssid);
    }

    public PatientView getView() {
        return this.view;
    }
}