package fr.univ_lyon1.info.m1.mes.controller.patientSearch;

import java.util.ArrayList;
import java.util.List;

import fr.univ_lyon1.info.m1.mes.model.Patient;

public class Ssid extends BaseStrategy {
    public Ssid() {
        super();
    }
    
    @Override
    public String toString() {
        return "SSID";
    }
    
    /**
     * Search for a patient by SSID.
     * @param e The list of patients.
     * @param search The SSID to search.
     * @return The list of patients matching the search.
     */
    @Override
    public List<Patient> search(final List<Patient> e, final String search) {
        List<Patient> output = new ArrayList<Patient>();
        for (Patient p : e) {
            if (p.getSsid().equals(search)) {
                output.add(p);
            }
        }
        return output;
    }
}
