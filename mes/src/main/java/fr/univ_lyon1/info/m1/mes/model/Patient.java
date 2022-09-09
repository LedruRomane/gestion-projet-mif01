package fr.univ_lyon1.info.m1.mes.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Patient {

    /**
     * Attributes
     */
    private final List<Prescription> prescriptions = new ArrayList<>();
    private final String name;
    private final String ssID;

    /**
     * Contructor
     * 
     * @param name
     * @param ssID
     */
    public Patient(final String name, final String ssID) {
        this.name = name;
        this.ssID = ssID;
    }

    /**
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * @return String
     */
    public String getSSID() {
        return ssID;
    }

    /**
     * @return List<Prescription>
     */
    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    /**
     * @param hp
     * @return List<Prescription>
     */
    public List<Prescription> getPrescriptions(final HealthProfessional hp) {
        return prescriptions.stream()
                .filter(p -> p.getHealthProfessional() == hp)
                .collect(Collectors.toList());
    }

    /**
     * @param hp
     * @param content
     */
    public void addPrescription(final HealthProfessional hp, final String content) {
        prescriptions.add(new Prescription(hp, content));
    }

    /**
     * @param p
     */
    public void removePrescription(final Prescription p) {
        prescriptions.remove(p);
    }

}
