package fr.univ_lyon1.info.m1.mes.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import java.util.List;
import org.junit.jupiter.api.Test;
import fr.univ_lyon1.info.m1.mes.types.HealthProfessionalType;

public class PatientTest {
    @Test
    void constructorTest() {
        // Given
        Patient patient = new Patient();
        // Test instance
        assertThat(patient, hasProperty("name", equalTo(null)));
    }

    @Test
    void setNameTest() {
        // Given
        Patient patient = new Patient();
        patient.setName("Patient Test");

        // When
        String name = patient.getName();

        // Then
        assertThat(name, is("Patient Test"));
    }

    @Test
    void setSsidTest() {
        // Given
        Patient patient = new Patient();
        patient.setSsid("1234567890");

        // When
        String ssid = patient.getSsid();

        // Then
        assertThat(ssid, is("1234567890"));
    }
    
    @Test
    void addPrescriptionTest() {
         // Given
         HealthProfessional hp = HealthProfessionalFactory.createHealthProfessional(
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
    void getNameTest() {
        // Given
        Patient patient = new Patient("Patient Test", "1234567890");

        // When
        String name = patient.getName();

        // Then
        assertThat(name, is("Patient Test"));
    }

    @Test
    void getSSIDTest() {
         // Given
         Patient patient = new Patient("Patient Test", "1234567890");

         // When
         String ssid = patient.getSsid();
 
         // Then
         assertThat(ssid, is("1234567890"));
    }

    @Test
    void getPrescriptionsTest() {
        // Given
        HealthProfessional hp = HealthProfessionalFactory.createHealthProfessional(
            HealthProfessionalType.DENTIST, 
            "Dr. Smith");
        Patient p = new Patient("Alice", "20123456789012");
        p.addPrescription(new Prescription(hp, "Eat fruits"));

        // When
        List<Prescription> prescriptions = p.getPrescriptions();

        // Then
        assertThat(prescriptions, hasItem(
                hasProperty("content", equalTo("Eat fruits"))));
    }

    @Test
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
    public void getNotPrescriptionForAnotherPatientTest() {
        // Given
        HealthProfessional hp = HealthProfessionalFactory.createHealthProfessional(
            HealthProfessionalType.DENTIST, 
            "Dr. Smith");
        Patient p = new Patient("Alice", "20123456789012");
        Patient p2 = new Patient("Toto", "442426517645254");

        p.addPrescription(new Prescription(hp, "Eat fruits"));

        // When
        List<Prescription> prescriptions = p2.getPrescriptions();

        // Then
        assertThat(prescriptions, not(
            hasItem(
                hasProperty("content", equalTo("Eat fruits")))));
    }

    @Test
    void removePrescriptionTest() {
        // Given
        HealthProfessional hp = HealthProfessionalFactory.createHealthProfessional(
            HealthProfessionalType.DENTIST, 
            "Dr. Smith");
        Patient p = new Patient("Alice", "20123456789012");
        Prescription pr = new Prescription(hp, "Eat fruits");
        p.addPrescription(pr);

        List<Prescription> prescriptions = p.getPrescriptions();

        assertThat(prescriptions, hasItem(
                hasProperty("content", equalTo("Eat fruits"))));

        p.removePrescription(pr);

        List<Prescription> prescriptionsAfterDeleted = p.getPrescriptions();

        assertThat(prescriptionsAfterDeleted, not(
            hasItem(
                hasProperty("content", equalTo("Eat fruits")))));
    }

    @Test
    void removePrescriptionForOnlyOnePatientTest() {
        // Given
        HealthProfessional hp = HealthProfessionalFactory.createHealthProfessional(
            HealthProfessionalType.DENTIST, 
            "Dr. Smith");
        Patient p = new Patient("Alice", "20123456789012");
        Patient p2 = new Patient("Toto", "442426517645254");
        Prescription pr = new Prescription(hp, "Eat fruits");
        p.addPrescription(pr);
        p2.addPrescription(pr);
        p.removePrescription(pr);

        List<Prescription> prescriptionsAfterDeleted = p2.getPrescriptions();

        assertThat(prescriptionsAfterDeleted, hasItem(
                hasProperty("content", equalTo("Eat fruits"))));
    }

    @Test
    void addPropertyChangeListenerTest() {
        // Given
        Patient p = new Patient("Alice", "20123456789012");

        // When
        p.addPropertyChangeListener("prescription", (evt) -> {
            assertThat(evt.getPropertyName(), is("prescription"));
            // Verification dernier ajout
            List<Prescription> pP = (List<Prescription>) evt.getNewValue();
            assertThat(((Prescription) pP.get(pP.size() - 1)).getContent(), is("Paracetamol"));
        });
        p.addPrescription(new Prescription(null, "Paracetamol"));
    }
}
