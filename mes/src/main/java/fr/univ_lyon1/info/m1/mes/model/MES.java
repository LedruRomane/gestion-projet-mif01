package fr.univ_lyon1.info.m1.mes.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.univ_lyon1.info.m1.mes.types.HealthProfessionalType;

public class MES {

    /**
     * Attributes.
     */
    private final List<HealthProfessional> healthProfessionalsList = new ArrayList<>();
    private final Map<String, Patient> patientsList = new HashMap<>();

    /**
     * Return HealthPofessionals list within MES.
     * 
     * @return List<HealthProfessional>
     */
    public List<HealthProfessional> getHealthProfessionals() {
        return healthProfessionalsList;
    }

    /**
     * Return patients list within MES.
     * 
     * @return List<Patient>
     */
    public List<Patient> getPatients() {
        return new ArrayList<>(patientsList.values());
    }

    /**
     * Create a new patient and return it.
     *
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
     * Create a new Health Professional and return it.
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

    /**
     * Create an example configuration for the current instance.
     * 
     */

    public void createExampleConfiguration() {
        final Patient a = createPatient("Alice Foo", "299010212345678");
        final Patient b = createPatient("Bob Bar", "199010212345678");
        createPatient("Charles Boz", "102020212345678");

        final HealthProfessional w = createHealthProfessional(
                HealthProfessionalType.PEDIATRICIAN,
                "Dr. Who");
        final HealthProfessional s = createHealthProfessional(
                HealthProfessionalType.DENTIST,
                "Dr. Strange");
        final HealthProfessional p = createHealthProfessional(
                HealthProfessionalType.PULMONOLOGIST,
                "Dr. Epstein");
        createHealthProfessional(HealthProfessionalType.HOMEOPATH, "Dr. Hahnemann");

        a.addPrescription(w, "One apple a day");
        a.addPrescription(w, "Sport twice a week");
        b.addPrescription(w, "Whatever placebo, you're not sick");
        b.addPrescription(s, "Snake oil");
        b.addPrescription(p, "Snake oil");

    }
}
