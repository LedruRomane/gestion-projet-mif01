package fr.univ_lyon1.info.m1.mes.controller;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javafx.application.Platform;
import fr.univ_lyon1.info.m1.mes.model.HealthProfessional;
import fr.univ_lyon1.info.m1.mes.model.HealthProfessionalFactory;
import fr.univ_lyon1.info.m1.mes.model.MES;
import fr.univ_lyon1.info.m1.mes.model.Patient;
import fr.univ_lyon1.info.m1.mes.model.Prescription;
import fr.univ_lyon1.info.m1.mes.types.HealthProfessionalType;
import fr.univ_lyon1.info.m1.mes.types.PatientSearchStrategyType;
import fr.univ_lyon1.info.m1.mes.view.HealthProfessionalView;

public class HealthProfessionalControllerTest {
    @BeforeAll
    public static void setUp() {
        Platform.startup(() -> {});
    }

    @Test
    public void instantiateHealthProfessionalController() throws Exception {
        MES model = MES.getInstance();
        HealthProfessionalController healthProfessionalController = new HealthProfessionalController(model);
        assertThat(healthProfessionalController, is(healthProfessionalController));
    }

    @Test
    public void deletePrescriptionWithoutSelect() throws Exception {
        // Given
        MES model = MES.getInstance();
        HealthProfessionalController healthProfessionalController = new HealthProfessionalController(model);

        Patient patient = new Patient("testProControllerPatient1", "4111");
        HealthProfessional d = HealthProfessionalFactory.createHealthProfessional(HealthProfessionalType.DENTIST, "testProControllerHP1");
        Prescription p = new Prescription(d, "testProControllerPrescription1");

        model.addHealthProfessional(d);
        model.addPatient(patient);
        patient.addPrescription(p);

        // When
        try {
            healthProfessionalController.deletePrescription(p);
        } catch (Error e) {
           // Then
            assertThat(e.getMessage(), is("Missing selected patient"));
        }
    }

    @Test
    public void addPrescriptionWithoutSelect() throws Exception {
        // Given
        MES model = MES.getInstance();
        HealthProfessionalController healthProfessionalController = new HealthProfessionalController(model);

        Patient patient = new Patient("testProControllerPatient1", "4111");
        HealthProfessional d = HealthProfessionalFactory.createHealthProfessional(HealthProfessionalType.DENTIST, "testProControllerHP1");

        model.addHealthProfessional(d);
        model.addPatient(patient);

        // When
        try {
            healthProfessionalController.addPrescription("testPresc");
        } catch (Error e) {
           // Then
            assertThat(e.getMessage(), is("Missing selected patient"));
        }
    }

    @Test
    public void selectAndDeletePrescriptionHealthProfessional() throws Exception {
        // Given
        MES model = MES.getInstance();
        HealthProfessionalController healthProfessionalController = new HealthProfessionalController(model);

        Patient patient = new Patient("testProControllerPatient1", "4111");
        HealthProfessional d = HealthProfessionalFactory.createHealthProfessional(HealthProfessionalType.DENTIST, "testProControllerHP1");
        Prescription p = new Prescription(d, "testProControllerPrescription1");
        HealthProfessionalView healthProfessionalView = new HealthProfessionalView(healthProfessionalController);
        healthProfessionalController.setView(healthProfessionalView);

        model.addHealthProfessional(d);
        model.addPatient(patient);
        patient.addPrescription(p);

        // When
        healthProfessionalController.selectHealthProfessional(d);
        healthProfessionalController.selectPatient(patient);
        healthProfessionalController.deletePrescription(p);
        // Then
        assertThat(patient.getPrescriptions().size(), is(0));
    }

    @Test
    public void selectAndAddPrescriptionHealthProfessional() throws Exception {
        // Given
        MES model = MES.getInstance();
        HealthProfessionalController healthProfessionalController = new HealthProfessionalController(model);

        Patient patient = new Patient("testProControllerPatient2", "4112");
        HealthProfessional d = HealthProfessionalFactory.createHealthProfessional(HealthProfessionalType.DENTIST, "testProControllerHP2");
        HealthProfessionalView healthProfessionalView = new HealthProfessionalView(healthProfessionalController);
        healthProfessionalController.setView(healthProfessionalView);

        model.addHealthProfessional(d);
        model.addPatient(patient);

        // When
        healthProfessionalController.selectHealthProfessional(d);
        healthProfessionalController.selectPatient(patient);
        healthProfessionalController.addPrescription("testProControllerPrescription2");
        // Then
        assertThat(patient.getPrescriptions().size(), is(1));
        assertThat(patient.getPrescriptions().get(0).getHealthProfessional(), is(d));
    }

    @Test
    public void getPatients() throws Exception {
        // Given
        MES model = MES.getInstance();
        HealthProfessionalController healthProfessionalController = new HealthProfessionalController(model);

        Patient patient = new Patient("testProControllerPatient3", "4113");

        // When
        model.addPatient(patient);

        // Then
        assertThat(healthProfessionalController.getPatients(PatientSearchStrategyType.NAME, "testProControllerPatient3").size(), is(1));
    }

    @Test
    public void createHealthProfessional() throws Exception {
        // Given
        MES model = MES.getInstance();
        HealthProfessionalController healthProfessionalController = new HealthProfessionalController(model);
        Integer size = model.getHealthProfessionals().size();
        // When
        healthProfessionalController.createHealthProfessional(HealthProfessionalType.DENTIST, "testProControllerHP3");

        // Then
        assertThat(model.getHealthProfessionals().size(), is(size + 1));
    }
}
