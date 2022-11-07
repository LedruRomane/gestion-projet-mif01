package fr.univ_lyon1.info.m1.mes.view.component.PatientComponent;

import fr.univ_lyon1.info.m1.mes.controller.PatientController;
import fr.univ_lyon1.info.m1.mes.model.Patient;
import fr.univ_lyon1.info.m1.mes.model.Prescription;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import fr.univ_lyon1.info.m1.mes.utils.EasyAlert;

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

    /**
     * 
     */
    void getPrescriptions() {
        for (final Prescription pr : patient.getPrescriptions()) {
            prescriptionPane.getChildren().add(new Label("- From "
                    + pr.getHealthProfessional().getName()
                    + ": " + pr.getContent()));
            Button delButton = new Button("X");
            prescriptionPane.getChildren().add(delButton);
            delButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent event) {
                    EasyAlert.alertPromptYesNo("Attention !",
                    "Êtes-vous sûr de vouloir supprimer cette prescription ? ",
                     new Runnable() {
                        @Override
                        public void run() {
                            controller.deletePrescription(pr);
                        }
                    });
                }
            });
        }
    }

    /**
     * @return Pane
     */
    public Pane asPane() {
        return prescriptionPane;
    }
}
