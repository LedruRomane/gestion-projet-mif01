package fr.univ_lyon1.info.m1.mes.view;

import fr.univ_lyon1.info.m1.mes.model.Patient;
import fr.univ_lyon1.info.m1.mes.model.Prescription;
import fr.univ_lyon1.info.m1.mes.utils.EasyClipboard;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class PatientView {
    
    private final Pane pane = new VBox();
    private Pane prescriptionPane = new VBox();
    private final Patient patient;

    public PatientView(final Patient p) {

        this.patient = p;
        final HBox nameBox = new HBox();

        pane.setStyle("-fx-border-color: gray;\n"
                + "-fx-border-insets: 5;\n"
                + "-fx-padding: 5;\n"
                + "-fx-border-width: 1;\n");

        final Label patientNameLabel = new Label(patient.getName());
        final Button bSSID = new Button("📋");
        Button bReload = new Button("🗘");
        
        nameBox.getChildren().addAll(patientNameLabel, bSSID, bReload);
        pane.getChildren().addAll(nameBox, prescriptionPane);

        bSSID.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                EasyClipboard.copy(patient.getSSID());
            }
        });
        bReload.setOnAction(ActionEvent -> showPrescriptions());       
        showPrescriptions();
    }

    void showPrescriptions() {
        prescriptionPane.getChildren().clear();
        prescriptionPane.getChildren().add(new Label("Prescriptions:\n"));
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
        return pane;
    }

}
