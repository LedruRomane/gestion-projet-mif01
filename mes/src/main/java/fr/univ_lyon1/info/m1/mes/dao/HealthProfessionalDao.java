package fr.univ_lyon1.info.m1.mes.dao;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import fr.univ_lyon1.info.m1.mes.model.HealthProfessional;
import fr.univ_lyon1.info.m1.mes.model.HealthProfessionalFactory;
import fr.univ_lyon1.info.m1.mes.types.HealthProfessionalType;

public class HealthProfessionalDao extends Dao<HealthProfessional> {

    public HealthProfessional find(final Integer id) {
        Map<Integer, HealthProfessional> map = this.findAllHealthProfessional();
        HealthProfessional p = map.get(id);
        return p;
    }

    
    public Map<Integer, HealthProfessional> findAllHealthProfessional() {
        final Yaml yaml = new Yaml();
        final InputStream inputStream = this.getClass()
        .getClassLoader()
        .getResourceAsStream("data/healthProfessional.yml");
        Map<Integer, HealthProfessional> map = new HashMap<Integer, HealthProfessional>();

        Integer i = 0;
        for (Object object : yaml.loadAll(inputStream)) {
            i++;
            String typeString = null;
            String nameString = null;

            // check cast safety of object
            if (object instanceof Map<?, ?>) {
                Map<?, ?> mapObject = (Map<?, ?>) object;
                if (mapObject.containsKey("type") && mapObject.get("type") instanceof String) {
                    typeString = (String) mapObject.get("type");
                }
                if (mapObject.containsKey("name") && mapObject.get("name") instanceof String) {
                    nameString = (String) mapObject.get("name");
                }
            }
            if (typeString != null && nameString != null) {
                HealthProfessionalType type = HealthProfessionalType.valueOf(typeString);
                HealthProfessional p = HealthProfessionalFactory.createHealthProfessional(
                    type, 
                    nameString);
                map.put(i, p);
            }
        }
        return map;
    }
}
