package fr.univ_lyon1.info.m1.mes.model;

import java.util.List;

public class HealthProfessional {

    /**
     * Attributes
     */
    private final String name;
    private final MES mes;

    /**
     * Constructor
     * 
     * @param name
     * @param mes
     */
    public HealthProfessional(final String name, final MES mes) {
        this.name = name;
        this.mes = mes;
        mes.addHealthProfessional(this);
    }

    /**
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * @param ssID
     * @return Patient
     */
    public Patient getPatient(final String ssID) {
        return mes.getPatient(ssID);
    }

    /**
     * @param ssID
     * @return List<Prescription>
     */
    public List<Prescription> getPrescriptions(final String ssID) {
        return mes.getPatient(ssID).getPrescriptions(this);
    }
}
