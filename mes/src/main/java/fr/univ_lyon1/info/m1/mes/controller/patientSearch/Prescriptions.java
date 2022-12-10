package fr.univ_lyon1.info.m1.mes.controller.patientSearch;

import java.util.ArrayList;
import java.util.List;

import fr.univ_lyon1.info.m1.mes.model.Patient;
import fr.univ_lyon1.info.m1.mes.model.Prescription;

public class Prescriptions implements BaseStrategy {
    public Prescriptions() {
        super();
    }

    @Override
    public String toString() {
        return "Prescription";
    }
    
    /**
     * Search for a patient by prescription.
     * @param e The list of patients.
     * @param search The prescription to search.
     * @return The list of patients matching the search.
     */
    @Override
    public List<Patient> search(final List<Patient> e, final String search) {
        List<Patient> output = new ArrayList<Patient>();
        for (Patient p : e) {
            for (Prescription pr : p.getPrescriptions()) {
                if (pr.getContent().contains(search)) {
                    output.add(p);
                }
            }
        }
        return output;
    }
}
