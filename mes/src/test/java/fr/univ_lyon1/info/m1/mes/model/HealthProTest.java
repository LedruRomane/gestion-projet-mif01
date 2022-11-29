package fr.univ_lyon1.info.m1.mes.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import java.util.List;
import org.junit.jupiter.api.Test;
import fr.univ_lyon1.info.m1.mes.types.HealthProfessionalType;

public class HealthProTest {
    private MES model = new MES();

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
    public void addPrefPrescriptionTest() {
        // Given
        HealthProfessional hp = model.createHealthProfessional(
            HealthProfessionalType.PEDIATRICIAN, 
            "Dr. Test");
        Prescription prefPrescription = new Prescription(hp, "pref prescription");
        hp.addPrefPrescription(prefPrescription);

        // When
        List<Prescription> prescriptions = hp.getPrefPrescription();

        // Then
        assertThat(prescriptions, hasItem(
                hasProperty("content", equalTo("pref prescription"))));
    }

    @Test
    public void getPrefPrescriptionTest() {
        // Given
        HealthProfessional hp = model.createHealthProfessional(
            HealthProfessionalType.NEUROSURGEON, 
            "Dr. Test");

        // When
        List<Prescription> prescriptions = hp.getPrefPrescription();

        // Then
        assertThat(prescriptions, hasItems(
                hasProperty("content", equalTo("Paracetamol")),
                hasProperty("content", equalTo("Head magic healer")),
                hasProperty("content", equalTo("Paillettes à cerveau"))));
    }

    @Test
    public void getNotPrefPrescriptionTest() {
        // Given
        HealthProfessional hp = model.createHealthProfessional(
            HealthProfessionalType.DENTIST, 
            "Dr. Test");

        // When
        List<Prescription> prescriptions = hp.getPrefPrescription();

        // Then
        assertThat(prescriptions, not(
            hasItem(
                hasProperty("content", equalTo("Do some sport")))));
    }

}
