package fr.univ_lyon1.info.m1.mes.model;

import java.util.ArrayList;
import java.util.List;

import fr.univ_lyon1.info.m1.mes.types.PatientSearchStrategyType;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

import org.junit.jupiter.api.Test;

public class PatientSearchStrategyTest {
    @Test
    void StrategyToString() {
        // Given
        PatientSearchStrategyType ssid = PatientSearchStrategyType.SSID;
        PatientSearchStrategyType name = PatientSearchStrategyType.NAME;
        PatientSearchStrategyType prescriptions = PatientSearchStrategyType.PRESCRIPTIONS;

        // When
        String ssidString = ssid.toString();
        String nameString = name.toString();
        String prescriptionsString = prescriptions.toString();

        // Then
        assertThat(ssidString, equalTo("SSID"));
        assertThat(nameString, equalTo("Starting Name"));
        assertThat(prescriptionsString, equalTo("Prescription"));
    }

    @Test
    void searchSsidTest() {
        // Given
        List<Patient> patients = new ArrayList<Patient>();
        Patient p = new Patient("Alice", "20123456789012");
        Patient p2 = new Patient("Bob", "40123456789012");
        patients.add(p);
        patients.add(p2);
        PatientSearchStrategyType ssid = PatientSearchStrategyType.SSID;

        // When
        List<Patient> result = ssid.getStrategyClass().search(patients, "20123456789012");

        // Then
        assertThat(result.size(), equalTo(1));
        assertThat(result, hasItem(p));
    }

    @Test
    void searchNameTest() {
        // Given
        List<Patient> patients = new ArrayList<Patient>();
        Patient p = new Patient("Alice", "20123456789012");
        Patient p2 = new Patient("Bob", "40123456789012");
        patients.add(p);
        patients.add(p2);
        PatientSearchStrategyType name = PatientSearchStrategyType.NAME;

        // When
        List<Patient> result = name.getStrategyClass().search(patients, "Alice");

        // Then
        assertThat(result.size(), equalTo(1));
        assertThat(result, hasItem(p));
    }

    @Test
    void searchPrescriptionTest() {
        // Given
        List<Patient> patients = new ArrayList<Patient>();
        Patient p = new Patient("Alice", "20123456789012");
        Patient p2 = new Patient("Bob", "40123456789012");
        Prescription test = new Prescription(null, "test");
        p.addPrescription(test);
        patients.add(p);
        patients.add(p2);

        PatientSearchStrategyType prescription = PatientSearchStrategyType.PRESCRIPTIONS;

        // When
        List<Patient> result = prescription.getStrategyClass().search(patients, "test");

        // Then
        assertThat(result.size(), equalTo(1));
        assertThat(result, hasItem(p));
    }

}
