package fr.univ_lyon1.info.m1.mes.view.component.PatientComponent;

import fr.univ_lyon1.info.m1.mes.controller.PatientController;
import fr.univ_lyon1.info.m1.mes.model.Patient;
import fr.univ_lyon1.info.m1.mes.model.Prescription;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class PatientHealthProfessionalPrescription {

    private final PatientController controller;
    private final Patient patient;   
    private final Pane prescriptionPane = new VBox();

    public PatientHealthProfessionalPrescription(final PatientController c, final Patient p) {
        this.patient = p;
        this.controller = c;

        prescriptionPane.setStyle("-fx-border-color: white;\n"
                + "-fx-border-insets: 5;\n"
                + "-fx-padding: 5;\n"
                + "-fx-border-width: 1;\n");

        getPrescriptions();
    }


    void getPrescriptions() {
        for (final Prescription pr : patient.getPrescriptions()) {
            prescriptionPane.getChildren().add(new Label("- From "
                    + pr.getHealthProfessional().getName()
                    + ": " + pr.getContent()));
        } 
    }

    /** 
     * @return Pane
     */
    public Pane asPane() {
        return prescriptionPane;
    }
}
