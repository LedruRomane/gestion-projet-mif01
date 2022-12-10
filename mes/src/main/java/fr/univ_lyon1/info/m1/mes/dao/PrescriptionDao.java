package fr.univ_lyon1.info.m1.mes.dao;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import fr.univ_lyon1.info.m1.mes.model.HealthProfessional;
import fr.univ_lyon1.info.m1.mes.model.Patient;
import fr.univ_lyon1.info.m1.mes.model.Prescription;

public class PrescriptionDao extends Dao<Prescription> {

    /**
     * Find one prescription.
     * @return one Presription
     */
    public Prescription find(final String id) {
        //useless
        return new Prescription(null, id);
    }

    /**
     * load all Prescriptions.
     * @param mapHp Map HealthProfessional
     * @param mapPatient Map Patient
     * @return Map Presriptions
     */
    public Map<String, Prescription> findAllPrescription(
        final Map<String, HealthProfessional> mapHp,
        final Map<String, Patient> mapPatient) {

        final Yaml yaml = new Yaml();
        final InputStream inputStream = this.getClass()
        .getClassLoader()
        .getResourceAsStream("data/prescription.yml");
        
        Map<String, Prescription> map = new HashMap<String, Prescription>();

        for (Object object : yaml.loadAll(inputStream)) {

            String idMap = null;
            String hp = null;
            String patient = null;
            String content = null;

            // check cast safety of object
            if (object instanceof Map<?, ?>) {
                Map<?, ?> mapObject = (Map<?, ?>) object;
                if (mapObject.containsKey("id") && mapObject.get("id") instanceof String) {
                    idMap = (String) mapObject.get("id");
                }
                if (mapObject.containsKey("hp") && mapObject.get("hp") instanceof String) {
                    hp = (String) mapObject.get("hp");
                }
                if (mapObject.containsKey("patient") 
                && mapObject.get("patient") instanceof String) {
                    patient = (String) mapObject.get("patient");
                }
                if (mapObject.containsKey("content") 
                && mapObject.get("content") instanceof String) {
                    content = (String) mapObject.get("content");
                }
            }
            if (idMap != null
                && hp != null
                && patient != null 
                && content != null) {
                Prescription p = new Prescription(mapHp.get(hp), content);
                mapPatient.get(patient).addPrescription(p);
                map.put(idMap, p);
            }
        }
        return map;
    }
}
