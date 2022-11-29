package fr.univ_lyon1.info.m1.mes.model;

import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PrescriptionTest {
    @Test
    void getContentTest() {
        // Given
        HealthProfessional hp = new HealthProfessional("Dr. Smith");
        Prescription prescription = new Prescription(hp, "content prescription");

        // When
        String content = prescription.getContent();

        // Then
        assertThat(content, is("content prescription"));

    }

    @Test
    void getHealthProfessionalTest() {
        // Given
        HealthProfessional hp = new HealthProfessional("Dr. Smith");
        Prescription prescription = new Prescription(hp, "content prescription");

        // When
        HealthProfessional hpTest = prescription.getHealthProfessional();

        // Then
        assertThat(hpTest, is(hp));
    }
}
