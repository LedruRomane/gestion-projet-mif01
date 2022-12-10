package fr.univ_lyon1.info.m1.mes.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.InputStream;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import fr.univ_lyon1.info.m1.mes.model.HealthProfessional;

public class healthProfessionalDaoTest {

    @Test
    public void findAllPatientsTest() {
        // Given
        HealthProfessionalDao d = new HealthProfessionalDao();
        Map<String, HealthProfessional> m = d.findAllHealthProfessional();
        // When
        int count = 0;
        for (HealthProfessional h : m.values()) {
            count++;
            assertTrue(h instanceof HealthProfessional);
        }
        // Then
        assertEquals(5, count);
    }

    @Test
    public void findOnePatientsTest() {
        // Given
        HealthProfessionalDao d = new HealthProfessionalDao();
        String id = "1";
        // When
        HealthProfessional p = d.find(id);
        // Then
        assertTrue(p instanceof HealthProfessional);
        assertEquals("Dr. Who", p.getName());
    }

}
