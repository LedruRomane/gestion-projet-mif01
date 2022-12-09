package fr.univ_lyon1.info.m1.mes.model;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import fr.univ_lyon1.info.m1.mes.types.HealthProfessionalType;

public class HealthProfessionalTest {

    @Test
    public void healthProfessionalFactoryTest() {
        // Given
        HealthProfessional hpNeurosurgeon =  HealthProfessionalFactory.createHealthProfessional(
            HealthProfessionalType.NEUROSURGEON, 
            "Bob");
        HealthProfessional hpDentist =  HealthProfessionalFactory.createHealthProfessional(
            HealthProfessionalType.DENTIST, 
            "Alice");
        HealthProfessional hpPediatrician =  HealthProfessionalFactory.createHealthProfessional(
            HealthProfessionalType.PEDIATRICIAN, 
            "John");
        HealthProfessional hpPulmonologist =  HealthProfessionalFactory.createHealthProfessional(
            HealthProfessionalType.PULMONOLOGIST, 
            "Jane");
        HealthProfessional hpHomeopath =  HealthProfessionalFactory.createHealthProfessional(
            HealthProfessionalType.HOMEOPATH, 
            "Jack");

        // When
        String hpNeuroClass = hpNeurosurgeon.getClass().getSimpleName();
        String hpDentistClass = hpDentist.getClass().getSimpleName();
        String hpPediatricianClass = hpPediatrician.getClass().getSimpleName();
        String hpPulmonologistClass = hpPulmonologist.getClass().getSimpleName();
        String hpHomeopathClass = hpHomeopath.getClass().getSimpleName();

        // Then
        assertThat(hpNeuroClass, is("Neurosurgeon"));
        assertThat(hpDentistClass, is("Dentist"));
        assertThat(hpPediatricianClass, is("Pediatrician"));
        assertThat(hpPulmonologistClass, is("Pulmonologist"));
        assertThat(hpHomeopathClass, is("Homeopath"));
    }

    @Test
    public void healthProfessionalFactoryTestFail() {
        NullPointerException thrown = Assertions.assertThrows(NullPointerException.class, () -> {
            HealthProfessionalFactory.createHealthProfessional(
                    (HealthProfessionalType) null, 
                    "Test");
        });
        assertThat(thrown.getMessage(), is("HealthProfessionalType is null"));
    }

    @Test
    public void healthProfessionalNameTest() {
        // Given
        HealthProfessional hp =  HealthProfessionalFactory.createHealthProfessional(
            HealthProfessionalType.DENTIST, 
            "Un dentiste");

        // When
        String name = hp.getName();

        // Then
        assertThat(name, is("Un dentiste"));
    }

    @Test
    public void healthProfessionalPrefPrescriptionTest() {
        // Given
        HealthProfessional hp =  HealthProfessionalFactory.createHealthProfessional(
            HealthProfessionalType.DENTIST, 
            "Un dentiste");

        // When
        List<Prescription> prefPrescription = hp.getPrefPrescription();

        // Then
        assertThat(prefPrescription.get(0).getContent(), is("Paracetamol"));
        assertThat(prefPrescription.get(1).getContent(), is("Less sugar & soda"));
    }

    @Test
    public void healthProfessionalPropertyChangeListener() {
        // Given
        HealthProfessional hp =  HealthProfessionalFactory.createHealthProfessional(
            HealthProfessionalType.DENTIST, 
            "Un dentiste");

        // When
        hp.addPropertyChangeListener("prescription", (evt) -> {
            assertThat(evt.getPropertyName(), is("prescription"));
            // Verification dernier ajout
            List<Prescription> pP = (List<Prescription>) evt.getNewValue();
            assertThat(((Prescription) pP.get(pP.size() - 1)).getContent(), is("Paracetamol"));
        });
        hp.addPrefPrescription(new Prescription(hp, "Paracetamol"));
    }
}
