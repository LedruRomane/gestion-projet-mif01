package fr.univ_lyon1.info.m1.mes;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.InputStream;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import fr.univ_lyon1.info.m1.mes.model.HealthProfessional;
import fr.univ_lyon1.info.m1.mes.model.HealthProfessionalFactory;
import fr.univ_lyon1.info.m1.mes.model.Patient;
import fr.univ_lyon1.info.m1.mes.model.Prescription;
import fr.univ_lyon1.info.m1.mes.types.HealthProfessionalType;

public class HealthProTest {
    @Test
    /*
     * A simple test, purposely broken so that students can see what happens for
     * test failures.
     */
    public void healthProfessionalNameTest() {
        // Given
        HealthProfessional hp = new HealthProfessional("Dr. Smith");

        // When
        String name = hp.getName();

        // Then
        assertThat(name, is("Dr. Smith"));
    }

    @Test
    /*
     * Test addPrescription, and demonstrate advanced Hamcrest assertions.
     */
    public void getPrescriptionTest() {
        // Given
        HealthProfessional hp =  HealthProfessionalFactory.createHealthProfessional(
            HealthProfessionalType.PULMONOLOGIST, 
            "Dr. Smith");
        Patient p = new Patient("Alice", "20123456789012");
        p.addPrescription(new Prescription(hp, "Do some sport"));

        // When
        List<Prescription> prescriptions = p.getPrescriptions();

        // Then
        assertThat(prescriptions, hasItem(
            hasProperty("content", equalTo("Do some sport"))));
    }

    @Test
    /*
     * Not-so-relevant test, mostly another example of advanced assertion. More
     * relevant things to test: play with several Patients, check that a
     * prescription made for one patient doesn't apply to the other, etc.
     */
    public void getNotPrescriptionTest() {
        // Given
        HealthProfessional hp = HealthProfessionalFactory.createHealthProfessional(
            HealthProfessionalType.DENTIST, 
            "Dr. Smith");
        Patient p = new Patient("Alice", "20123456789012");
        p.addPrescription(new Prescription(hp, "Eat fruits"));

        // When
        List<Prescription> prescriptions = p.getPrescriptions();

        // Then
        assertThat(prescriptions, not(
            hasItem(
                hasProperty("content", equalTo("Do some sport")))));
    }

    @Test
    public void whenLoadMultipleYAMLDocumentsThenLoadCorrectJavaObjects() {
        Yaml yaml = new Yaml(new Constructor(Patient.class));
        InputStream inputStream = this.getClass()
        .getClassLoader()
        .getResourceAsStream("data/patient.yml");

        int count = 0;
        for (Object object : yaml.loadAll(inputStream)) {
            count++;
            assertTrue(object instanceof Patient);
        }
        assertEquals(6, count);
    }

}
