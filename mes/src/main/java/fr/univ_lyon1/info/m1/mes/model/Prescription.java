package fr.univ_lyon1.info.m1.mes.model;

public class Prescription {

    private final HealthProfessional hp;
    private final String content;

    /**
     * Prescription constructor.
     * @param hp HealthProfessional
     * @param content String
     */
    public Prescription(final HealthProfessional hp, final String content) {
        this.hp = hp;
        this.content = content;
    }

    /**
     * Return prescription content.
     * @return String
     */
    public String getContent() {
        return content;
    }

    /**
     * Return the HealthProfessional.
     * @return HealthProfessional
     */
    public HealthProfessional getHealthProfessional() {
        return hp;
    }

}
