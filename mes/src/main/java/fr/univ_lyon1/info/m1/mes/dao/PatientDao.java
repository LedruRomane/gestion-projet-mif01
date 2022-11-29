package fr.univ_lyon1.info.m1.mes.dao;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import fr.univ_lyon1.info.m1.mes.model.Patient;

public class PatientDao extends Dao<Patient> {

    public Patient find(final Integer id) {
        Map<Integer, Patient> map = this.findAll();
        Patient p = map.get(id);
        return p;
    }

    public Map<Integer, Patient> findAll() {
        final Yaml yaml = new Yaml(new Constructor(Patient.class));
        final InputStream inputStream = this.getClass()
        .getClassLoader()
        .getResourceAsStream("data/patient.yml");
        Map<Integer, Patient> map = new HashMap<Integer, Patient>();
        Integer i = 0;
        for (Object object : yaml.loadAll(inputStream)) {
            i++;
            Patient p = (Patient) object;
            map.put(i, p);
        }
        return map;
    }
}
