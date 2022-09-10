package fr.univ_lyon1.info.m1.mes.model;

public class Prescription {

    /*
     * Attributes
     */
    private final HealthProfessional hp;
    private final String content;

    /*
     * Constructor
     * 
     * @param hp
     * @param content
     */
    public Prescription(final HealthProfessional hp, final String content) {
        this.hp = hp;
        this.content = content;
    }

    /**
     * @return String
     */
    public String getContent() {
        return content;
    }

    /**
     * @return HealthProfessional
     */
    public HealthProfessional getHealthProfessional() {
        return hp;
    }

}
