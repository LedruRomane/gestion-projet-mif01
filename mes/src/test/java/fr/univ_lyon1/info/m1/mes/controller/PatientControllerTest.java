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
import fr.univ_lyon1.info.m1.mes.view.PatientView;

public class PatientControllerTest {

    @Test
    public void instantiatePatientController() throws Exception {
        MES model = MES.getInstance();
        PatientController patientController = new PatientController(model);
        assertThat(patientController, is(patientController));
    }

    @Test
    public void selectAndDeletePrescriptionPatient() throws Exception {
        // Given
        MES model = MES.getInstance();
        PatientController patientController = new PatientController(model);

        Patient patient = new Patient("test", "1234");
        HealthProfessional d = HealthProfessionalFactory.createHealthProfessional(HealthProfessionalType.DENTIST, "test");
        Prescription prescription = new Prescription(d, "test");

        PatientView patientView = new PatientView(patientController);
        patientController.setView(patientView);

        model.addHealthProfessional(d);
        model.addPatient(patient);
        patient.addPrescription(prescription);
        // When
        patientController.selectPatient(patient);
        patientController.deletePrescription(prescription);
        // Then
        assertThat(patient.getPrescriptions().size(), is(0));
    }

    @Test
    public void selectAndDeletePrescriptionNullPatient() throws Exception {
        // Given
        MES model = MES.getInstance();
        PatientController patientController = new PatientController(model);
        Patient patient = new Patient("test", "1234");
        HealthProfessional d = HealthProfessionalFactory.createHealthProfessional(HealthProfessionalType.DENTIST, "test");
        Prescription prescription = new Prescription(d, "test");
        model.addHealthProfessional(d);
        model.addPatient(patient);
        patient.addPrescription(prescription);
        // When
        patientController.selectPatient(null);
        try {
            patientController.deletePrescription(prescription);
        } catch (NullPointerException e) {
            // Then
            assertThat(patient.getPrescriptions().size(), is(1));
            assertThat(patient.getPrescriptions().get(0), is(prescription));
        }
    }

    @Test
    public void patientCreation() throws Exception {
        // Given
        MES model = MES.getInstance();
        PatientController patientController = new PatientController(model);
        Integer size = model.getPatients().size();
        // When
        patientController.createPatient("patientTestCreation", "2222");
        // Then
        assertThat(model.getPatients().size(), is(size + 1));
        assertThat(model.getPatients().get(size).getName(), is("patientTestCreation"));
        assertThat(model.getPatients().get(size).getSsid(), is("2222"));
    }

    @Test
    public void patientDuplicateCreation() throws Exception {
        // Given
        MES model = MES.getInstance();
        PatientController patientController = new PatientController(model);
        Integer size = model.getPatients().size();
        // When
        patientController.createPatient("patientTestCreationDuplicate", "3333");
        patientController.createPatient("patientTestCreationDuplicate", "3333");
        
        // Then
        assertThat(model.getPatients().size(), is(size + 1));

    }

    @Test
    public void getViewTest() throws Exception {
        // Given
        MES model = MES.getInstance();
        PatientController patientController = new PatientController(model);
        // Then
        assertThat(patientController.getView(), is(patientController.getView()));
    }
}
