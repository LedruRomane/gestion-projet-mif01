package fr.univ_lyon1.info.m1.mes.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.univ_lyon1.info.m1.mes.types.HealthProfessionalType;

public class MES {

    /*
     * Attributes
     */
    private final List<HealthProfessional> healthProfessionalsList = new ArrayList<>();
    private final Map<String, Patient> patientsList = new HashMap<>();

    /*
     * Constructor
     * 
     * @return
     */
    public List<HealthProfessional> getHealthProfessionals() {
        return healthProfessionalsList;
    }

    /**
     * @return List<Patient>
     */
    public List<Patient> getPatients() {
        return new ArrayList<>(patientsList.values());
    }

    /**
     * @param name
     * @param ssID
     * @return Patient
     */
    public Patient createPatient(final String name, final String ssID) {
        final Patient p = new Patient(name, ssID);
        patientsList.put(ssID, p);
        return p;
    }

    /**
     * Factory builder for HealthProfessional.
     * 
     * @param name
     * @return HealthProfessional
     */
    public HealthProfessional createHealthProfessional(
            final HealthProfessionalType model, final String name) {
        HealthProfessional p = null;
        switch (model) {
            case PEDIATRICIAN:
                p = new Pediatrician(name);
                break;
            case HOMEOPATH:
                p = new Homeopath(name);
                break;
            case DENTIST:
                p = new Dentist(name);
                break;
            case NEUROSURGEON:
                p = new Neurosurgeon(name);
                break;
            case PULMONOLOGIST:
                p = new Pulmonologist(name);
                break;
            default:
                throw new Error("Unknown professional type");
        }
        healthProfessionalsList.add(p);
        return p;
    };

    /*
     * @return List<HealthProfessional>
     */

    public void createExampleConfiguration() {
        final Patient a = createPatient("Alice Foo", "299010212345678");
        final Patient b = createPatient("Bob Bar", "199010212345678");
        createPatient("Charles Boz", "102020212345678");

        final HealthProfessional w = new HealthProfessional("Dr. Who");
        final HealthProfessional s = new Dentist("Dr. Strange");
        final HealthProfessional p = new Pediatrician("Dr. Epstein");
        new Homeopath("Dr. Hahnemann");

        a.addPrescription(w, "One apple a day");
        a.addPrescription(w, "Sport twice a week");
        b.addPrescription(w, "Whatever placebo, you're not sick");
        b.addPrescription(s, "Snake oil");
        b.addPrescription(p, "Snake oil");

    }
}
