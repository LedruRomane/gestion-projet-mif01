package fr.univ_lyon1.info.m1.mes.model;

import fr.univ_lyon1.info.m1.mes.types.HealthProfessionalType;

public interface HealthProfessionalFactory {
    /**
     * Create a new Health Professional and return it.
     * 
     * @param name
     * @return HealthProfessional
     */
    static HealthProfessional createHealthProfessional(
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
        return p;
    };
}
