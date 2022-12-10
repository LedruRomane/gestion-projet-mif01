package fr.univ_lyon1.info.m1.mes.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.InputStream;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import fr.univ_lyon1.info.m1.mes.model.Patient;

public class patientDaoTest {

    @Test
    public void whenLoadMultipleYAMLDocumentsThenLoadCorrectJavaObjects() {
        // Given
        final Yaml yaml = new Yaml(new Constructor(Patient.class));
        final InputStream inputStream = this.getClass()
        .getClassLoader()
        .getResourceAsStream("data/patient.yml");
        // When
        int count = 0;
        for (Object object : yaml.loadAll(inputStream)) {
        
            count++;
            assertTrue(object instanceof Patient);
        }
        // Then
        assertEquals(6, count);
    }

    @Test
    public void findAllPatientsTest() {
        // Given
        PatientDao d = new PatientDao();
        Map<String, Patient> m = d.findAllPatients();
        // When
        int count = 0;
        for (Patient p : m.values()) {
            count++;
            assertTrue(p instanceof Patient);
        }
        // Then
        assertEquals(6, count);
    }

    @Test
    public void findOnePatientsTest() {
        // Given
        PatientDao d = new PatientDao();
        String ssid = "299010212345678";
        // When
        Patient p = d.find(ssid);
        // Then
        assertTrue(p instanceof Patient);
        assertEquals(ssid, p.getSsid());
        assertEquals("Alice Foo", p.getName());
    }

    @Test
    public void objctYamlInstanceOfTest() {
        // Given
        final Yaml yaml = new Yaml();
        final InputStream inputStream = this.getClass()
        .getClassLoader()
        .getResourceAsStream("data/patient.yml");
        int count = 0;
        for (Object object : yaml.loadAll(inputStream)) {
            count++;
            assertTrue(object instanceof Map<?, ?>);
            Map<?, ?> mapObject = (Map<?, ?>) object;
            assertTrue(mapObject.containsKey("ssid"));
            assertTrue(mapObject.get("ssid") instanceof String);
            assertTrue(mapObject.containsKey("name"));
            assertTrue(mapObject.get("name") instanceof String);
            assertNotNull(mapObject.get("name"));
            assertNotNull(mapObject.get("ssid"));
        }
        assertEquals(6, count);
    }
    
}
