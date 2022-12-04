package fr.univ_lyon1.info.m1.mes.controller.patientSearch;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fr.univ_lyon1.info.m1.mes.model.Patient;
import fr.univ_lyon1.info.m1.mes.model.Prescription;

public class Prescriptions extends BaseStrategy {
    public Prescriptions(final Map<String, Patient> e) {
        super(e);
    }

    @Override
    public String toString() {
        return "Prescription";
    }
    
    @Override
    public List<Patient> search(final String search) {
        List<Patient> output = new ArrayList<Patient>();
        for (Patient p : this.getPatientsList().values()) {
            for (Prescription e : p.getPrescriptions()) {
                if (e.getContent().contains(search)) {
                    output.add(p);
                    break;
                }
            }
        }
        return output;
    }
}
