package fr.univ_lyon1.info.m1.mes.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasItem;

import java.util.List;

import org.junit.jupiter.api.Test;

import fr.univ_lyon1.info.m1.mes.types.HealthProfessionalType;

public class MESTest {
    @Test
    void constructorTest() {
        // Given
        MES mes = MES.getInstance();
        MES mes2 = MES.getInstance();
        // Test instance
        assertThat(mes, (equalTo(mes2)));
    }

    @Test
    void patientTests() {
        // Given
        MES mes = MES.getInstance();
        Patient p = new Patient("Alice", "20123456789012");
        mes.addPatient(p);

        // When
        List<Patient> patients = mes.getPatients();

        // Then
        assertThat(patients, hasItem(p));
    }
    @Test
    void addDuplicatePatientTest() {
        // Given
        MES mes = MES.getInstance();
        Patient p = new Patient("Alice2", "24123456789012");
        mes.addPatient(p);
        Patient p2 = new Patient("Bob", "24123456789012");
        mes.addPatient(p2);

        // When
        List<Patient> patients = mes.getPatients();

        // Then
        assertThat(patients, hasItem(p));
        assertThat(patients, not(hasItem(p2)));
    }

    @Test
    void HealthProfessionalTests() {
        // Given
        MES mes = MES.getInstance();
        HealthProfessional hp = HealthProfessionalFactory.createHealthProfessional(
            HealthProfessionalType.PULMONOLOGIST, 
            "Dr. Smith");
        mes.addHealthProfessional(hp);

        // When
        List<HealthProfessional> healthProfessionals = mes.getHealthProfessionals();

        // Then
        assertThat(healthProfessionals, hasItem(hp));
    }

    @Test
    void addDuplicateHealthProfessionalTest() {
        // Given
        MES mes = MES.getInstance();
        HealthProfessional hp = HealthProfessionalFactory.createHealthProfessional(
            HealthProfessionalType.DENTIST, 
            "Dr. Anderson");

        // When
        mes.addHealthProfessional(hp);
        mes.addHealthProfessional(hp);
        List<HealthProfessional> healthProfessionals = mes.getHealthProfessionals();

        // Then
        assertThat(healthProfessionals, hasItem(hp));
    }

    @Test
    void MESProPropertyChangeListener() {
        // Given
        MES mes = MES.getInstance();
        HealthProfessional hp =  HealthProfessionalFactory.createHealthProfessional(
            HealthProfessionalType.DENTIST, 
            "Un dentiste");
        
        // Then
        mes.addPropertyChangeListener("healthList", (evt) -> {
            assertThat(evt.getPropertyName(), is("healthList"));
            // Verification dernier ajout
            List<HealthProfessional> hP = (List<HealthProfessional>) evt.getNewValue();
            assertThat(hP, hasItem(hp));
        });
        
        // When
        mes.addHealthProfessional(hp);
    }
}
