package fr.univ_lyon1.info.m1.mes.view.component.HealthProfessionalComponent;

import fr.univ_lyon1.info.m1.mes.controller.HealthProfessionalController;
import fr.univ_lyon1.info.m1.mes.model.HealthProfessional;
import fr.univ_lyon1.info.m1.mes.model.Prescription;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class HealthProfessionalPrescriptions {

    private final HealthProfessionalController controller;
    private final HealthProfessional healthProfessional;
    private final Pane prescriptionToolPane = new VBox();

    public HealthProfessionalPrescriptions(
        final HealthProfessionalController hpController,
        final HealthProfessional hp) {

            this.controller = hpController;
            this.healthProfessional = hp;

            prescriptionToolPane.setStyle("-fx-border-color: white;\n"
                + "-fx-border-insets: 5;\n"
                + "-fx-padding: 5;\n"
                + "-fx-border-width: 1;\n");
    }

    private void getPrescriptions() {
        //TODO affiche les bouttons prescriptions de base du médecin en cours.
    }

    private Prescription addPrescription(final String pString) {
        //TODO Ajoute une préscription au patient courant
        Prescription test = new Prescription(healthProfessional, "content");
        return test;
    }



    public Pane asPane() {
        return this.prescriptionToolPane;
    }
}
