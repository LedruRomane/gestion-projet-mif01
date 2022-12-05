package fr.univ_lyon1.info.m1.mes.model;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

import fr.univ_lyon1.info.m1.mes.types.HealthProfessionalType;

public class HealthProfessionalTest {

    @Test
    public void healthProfessionalFactoryTest() {
        // Given
        HealthProfessional hp =  HealthProfessionalFactory.createHealthProfessional(
            HealthProfessionalType.NEUROSURGEON, 
            "Un Neurosurgeon");

        // When
        String name = hp.getClass().getSimpleName();

        // Then
        assertThat(name, is("Neurosurgeon"));
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
}
