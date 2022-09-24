package fr.univ_lyon1.info.m1.mes.model;

public class HealthProfessional {

    /*
     * Attributes
     */
    private final String name;

    /*
     * Constructor
     * 
     * @param name
     * @param mes
     */
    public HealthProfessional(final String name) {
        this.name = name;
    }

    /**
     * @return String
     */
    public String getName() {
        return name;
    }
}
