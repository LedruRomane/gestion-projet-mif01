package fr.univ_lyon1.info.m1.mes.view.component.HealthProfessionalComponent;

import fr.univ_lyon1.info.m1.mes.controller.HealthProfessionalController;
import fr.univ_lyon1.info.m1.mes.model.HealthProfessional;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class HealthProfessionalPatientPrescription {

    private final HealthProfessionalController controller;
    private final HealthProfessional healthProfessional;
    private final Pane prescriptionPatientPane = new VBox();
    
    public HealthProfessionalPatientPrescription(
        final HealthProfessionalController hpController,
        final HealthProfessional hp) {
            this.healthProfessional = hp;
            this.controller = hpController;
    }

    private void getPatientPrescriptions() {
        /*
         * TODO ajoute au pane le patient et ses prescriptions et le bouton
         * TODO supprimer par prescription.
         */
    }



    public Pane asPane() {
        return this.prescriptionPatientPane;
    }
}
