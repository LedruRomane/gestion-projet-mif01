package fr.univ_lyon1.info.m1.mes.controller.patientSearch;

import java.util.List;
import java.util.Map;

import fr.univ_lyon1.info.m1.mes.model.Patient;

public class BaseStrategy {
    private final Map<String, Patient> patientsList;

    BaseStrategy(final Map<String, Patient> e) {
        this.patientsList = e;
    }

    protected Map<String, Patient> getPatientsList() {
        return patientsList;
    }
    
    public List<Patient> search(final String search) {
        return null;
    };
}
