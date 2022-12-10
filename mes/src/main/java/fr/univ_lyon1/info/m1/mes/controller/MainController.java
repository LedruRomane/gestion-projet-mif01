package fr.univ_lyon1.info.m1.mes.controller;
import java.util.Map;

import fr.univ_lyon1.info.m1.mes.dao.HealthProfessionalDao;
import fr.univ_lyon1.info.m1.mes.dao.PatientDao;
import fr.univ_lyon1.info.m1.mes.dao.PrescriptionDao;
import fr.univ_lyon1.info.m1.mes.model.HealthProfessional;
import fr.univ_lyon1.info.m1.mes.model.MES;
import fr.univ_lyon1.info.m1.mes.model.Patient;

public class MainController {
    private final MES model;

    public MainController(final MES mes) {
        this.model = mes;
        createExampleConfiguration();
    }
    
    /**
     * Create an example configuration for the current instance.
     */
    public void createExampleConfiguration() {
        PatientDao patientDao = new PatientDao();
        HealthProfessionalDao healthProfessionalDao = new HealthProfessionalDao();
        PrescriptionDao prescriptionDao = new PrescriptionDao();

        Map<String, Patient> mapPatient = patientDao.findAllPatients();
        Map<String, HealthProfessional> mapHp = healthProfessionalDao.findAllHealthProfessional();
        prescriptionDao.findAllPrescription(mapHp, mapPatient);

        for (Patient pa : mapPatient.values()) {
            this.model.addPatient(pa);
        }
       
        for (HealthProfessional hp : mapHp.values()) {
            this.model.addHealthProfessional(hp);
        }
    }
}
