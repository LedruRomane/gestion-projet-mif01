package fr.univ_lyon1.info.m1.mes.controller.patientSearch;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fr.univ_lyon1.info.m1.mes.model.Patient;

public class Name extends BaseStrategy {
    public Name() {
        super();
    }

    @Override
    public String toString() {
        return "Starting Name";
    }
    
    /**
     * Search for a patient by starting name.
     * @param e The list of patients.
     * @param search The name to search.
     * @return The list of patients matching the search.
     */
    @Override
    public List<Patient> search(final List<Patient> e, final String search) {
        List<Patient> output = new ArrayList<Patient>();
        for (Patient p : e) {
            if (p.getName().startsWith(search)) {
                output.add(p);
            }
        }
        return output;
    }
}
