package fr.univ_lyon1.info.m1.mes.controller.patientSearch;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fr.univ_lyon1.info.m1.mes.model.Patient;

public class Name extends BaseStrategy {
    public Name(final Map<String, Patient> e) {
        super(e);
    }

    @Override
    public String toString() {
        return "Starting Name";
    }
    
    @Override
    public List<Patient> search(final String search) {
        List<Patient> output = new ArrayList<Patient>();
        for (Patient p : this.getPatientsList().values()) {
            if (p.getName().startsWith(search)) {
                output.add(p);
            }
        }
        return output;
    }
}
