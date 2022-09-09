package fr.univ_lyon1.info.m1.mes.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MES {

    /**
     * Attributes
     */
    private final List<HealthProfessional> healthProfessionals = new ArrayList<>();
    private final Map<String, Patient> registry = new HashMap<>();

    /**
     * Constructor
     * 
     * @return
     */
    public List<HealthProfessional> getHealthProfessional() {
        return healthProfessionals;
    }

    /**
     * @param ssID
     * @return Patient
     */
    public Patient getPatient(final String ssID) {
        return registry.get(ssID);
    }

    /**
     * @param name
     * @param ssID
     * @return Patient
     */
    public Patient createPatient(final String name, final String ssID) {
        final Patient p = new Patient(name, ssID);
        registry.put(ssID, p);
        return p;
    }

    /**
     * @param hp
     */
    public void addHealthProfessional(final HealthProfessional hp) {
        healthProfessionals.add(hp);
    };

    /**
     * @return List<Patient>
     */
    public List<Patient> getPatients() {
        return new ArrayList<>(registry.values());
    }

    /**
     * @return List<HealthProfessional>
     */

    public void createExampleConfiguration() {
        final Patient a = createPatient("Alice Foo", "299010212345678");
        final Patient b = createPatient("Bob Bar", "199010212345678");
        createPatient("Charles Boz", "102020212345678");
        final HealthProfessional w = new HealthProfessional("Dr. Who", this);
        final HealthProfessional s = new Dentist("Dr. Strange", this);
        final Pediatrician p = new Pediatrician("Dr. Epstein", this);
        new Homeopath("Dr. Hahnemann", this);
        a.addPrescription(w, "One apple a day");
        a.addPrescription(w, "Sport twice a week");
        b.addPrescription(w, "Whatever placebo, you're not sick");
        b.addPrescription(s, "Snake oil");

    }
}
