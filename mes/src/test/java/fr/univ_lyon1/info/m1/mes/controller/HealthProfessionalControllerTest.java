package fr.univ_lyon1.info.m1.mes.controller;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

import fr.univ_lyon1.info.m1.mes.model.HealthProfessional;
import fr.univ_lyon1.info.m1.mes.model.HealthProfessionalFactory;
import fr.univ_lyon1.info.m1.mes.model.MES;
import fr.univ_lyon1.info.m1.mes.model.Patient;
import fr.univ_lyon1.info.m1.mes.model.Prescription;
import fr.univ_lyon1.info.m1.mes.types.HealthProfessionalType;
import fr.univ_lyon1.info.m1.mes.types.PatientSearchStrategyType;
import fr.univ_lyon1.info.m1.mes.view.HealthProfessionalView;

public class HealthProfessionalControllerTest {

    @Test
    public void instantiateHealthProfessionalControllerTest() throws Exception {
        MES model = MES.getInstance();
        HealthProfessionalController healthProfessionalController = new HealthProfessionalController(model);
        assertThat(healthProfessionalController, is(healthProfessionalController));
    }

    @Test
    public void getHealthProfessionalTest() throws Exception {
        MES model = MES.getInstance();
        HealthProfessionalController healthProfessionalController = new HealthProfessionalController(model);
        HealthProfessional d = HealthProfessionalFactory.createHealthProfessional(HealthProfessionalType.DENTIST, "testProControllerHP0");
        Integer size = model.getHealthProfessionals().size();
        model.addHealthProfessional(d);
        assertThat(healthProfessionalController.getHealthProfessionals().size(), is(size + 1));
    }

    @Test
    public void deletePrescriptionWithoutSelectTest() throws Exception {
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
    public void addPrescriptionWithoutSelectTest() throws Exception {
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
    public void getPrescriptionsByPatientTest() throws Exception {
        // Given
        MES model = MES.getInstance();
        HealthProfessionalController healthProfessionalController = new HealthProfessionalController(model);

        Patient patient = new Patient("testProControllerPatient66", "41157");
        HealthProfessional d = HealthProfessionalFactory.createHealthProfessional(HealthProfessionalType.DENTIST, "testProControllerHPZ");
        Prescription p = new Prescription(d, "testProControllerPrescription1");

        model.addHealthProfessional(d);
        model.addPatient(patient);
        patient.addPrescription(p);

        // When
        healthProfessionalController.selectHealthProfessional(d);

        // Then
        assertThat(healthProfessionalController.getPrescriptionsByPatient(patient).size(), is(1));
    }

    @Test
    public void selectAndDeletePrescriptionHealthProfessionalTest() throws Exception {
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
        healthProfessionalController.selectHealthProfessional(d);
        healthProfessionalController.selectPatient(patient);
        healthProfessionalController.deletePrescription(p);
        // Then
        assertThat(patient.getPrescriptions().size(), is(0));
    }

    @Test
    public void selectAndAddPrescriptionHealthProfessionalTest() throws Exception {
        // Given
        MES model = MES.getInstance();
        HealthProfessionalController healthProfessionalController = new HealthProfessionalController(model);

        Patient patient = new Patient("testProControllerPatient2", "4112");
        HealthProfessional d = HealthProfessionalFactory.createHealthProfessional(HealthProfessionalType.DENTIST, "testProControllerHP2");

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
    public void getPatientsTest() throws Exception {
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
    public void createHealthProfessionalTest() throws Exception {
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
