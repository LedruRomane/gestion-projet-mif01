package fr.univ_lyon1.info.m1.mes.dao;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import fr.univ_lyon1.info.m1.mes.model.Prescription;

public class PrescriptionDao extends Dao<Prescription> {

    public Prescription find(final Integer id) {
        Map<Integer, Prescription> map = this.findAllPrescription();
        Prescription p = map.get(id);
        return p;
    }

    public Map<Integer, Prescription> findAllPrescription() {
        final Yaml yaml = new Yaml();
        final InputStream inputStream = this.getClass()
        .getClassLoader()
        .getResourceAsStream("data/prescription.yml");
        Map<Integer, Prescription> map = new HashMap<Integer, Prescription>();
        Integer i = 0;
        for (Object object : yaml.loadAll(inputStream)) {
            i++;
            Prescription p = (Prescription) object;
            map.put(i, p);
        }
        return map;
    }
}
