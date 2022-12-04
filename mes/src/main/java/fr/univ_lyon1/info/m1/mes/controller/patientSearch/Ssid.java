package fr.univ_lyon1.info.m1.mes.controller.patientSearch;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fr.univ_lyon1.info.m1.mes.model.Patient;

public class Ssid extends BaseStrategy {
    public Ssid(final Map<String, Patient> e) {
        super(e);
    }
    
    @Override
    public String toString() {
        return "SSID";
    }
    
    @Override
    public List<Patient> search(final String search) {
        List<Patient> output = new ArrayList<Patient>();
        if (this.getPatientsList().containsKey(search)) {
            output.add(this.getPatientsList().get(search));
        }
        return output;
    }
}
