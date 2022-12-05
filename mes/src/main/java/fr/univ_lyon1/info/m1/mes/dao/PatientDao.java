package fr.univ_lyon1.info.m1.mes.dao;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import fr.univ_lyon1.info.m1.mes.model.Patient;

public class PatientDao extends Dao<Patient> {

    public Patient find(final String id) {
        Map<String, Patient> map = this.findAllPatients();
        Patient p = map.get(id);
        return p;
    }

    public Map<String, Patient> findAllPatients() {
        final Yaml yaml = new Yaml();
        final InputStream inputStream = this.getClass()
        .getClassLoader()
        .getResourceAsStream("data/patient.yml");

        Map<String, Patient> map = new HashMap<String, Patient>();

        for (Object object : yaml.loadAll(inputStream)) {
            
            String nameString = null;
            String ssidString = null;
            

            if (object instanceof Map<?, ?>) {
                Map<?, ?> mapObject = (Map<?, ?>) object;
                if (mapObject.containsKey("ssid") && mapObject.get("ssid") instanceof String) {
                    ssidString = (String) mapObject.get("ssid");
                }
                if (mapObject.containsKey("name") && mapObject.get("name") instanceof String) {
                    nameString = (String) mapObject.get("name");
                }
            }
            if (ssidString != null && nameString != null) {
                Patient p = new Patient(nameString, ssidString);
                map.put(ssidString, p);
            }
        }
        return map;
    }
}
