package fr.univ_lyon1.info.m1.mes.view.component.PatientComponent;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import fr.univ_lyon1.info.m1.mes.controller.PatientController;
import fr.univ_lyon1.info.m1.mes.model.Patient;
import fr.univ_lyon1.info.m1.mes.utils.EasyClipboard;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class PatientBox implements PropertyChangeListener {
    
    private final PatientController controller;
    private Patient patient = null;
    private final Pane patientPane = new VBox();
    private PatientHealthProfessionalPrescription prescriptionPane = null;
    private Pane descriptionPatientPane = new HBox();
    

    public PatientBox(final PatientController c, final Patient p) {

        this.controller = c;
        this.patient = p;

        refresh();
    }

    /**
     * 
     */
    private void refresh() {
        if (this.patient != null) {
            final Label patientNameLabel = new Label(patient.getName());
            final Label sSIDLabel = new Label(patient.getSSID());
            final Button sSIDButton = new Button("📋");
            descriptionPatientPane.getChildren().setAll(patientNameLabel, sSIDLabel, sSIDButton);
            
            showPrescriptions();

            final Label prescriptionTitleLabel = new Label("Mes prescriptions : ");
            
            patientPane.getChildren().setAll(descriptionPatientPane, prescriptionTitleLabel);
            if (this.prescriptionPane != null) {
                patientPane.getChildren().add(this.prescriptionPane.asPane());
            }
            
            sSIDButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent event) {
                    EasyClipboard.copy(patient.getSSID());
                }
            });
        }
    }

    /**
     * 
     * @param p
     */
    public void setPatient(final Patient p) {
        this.patient = p;
        refresh();
    }

    /**
     * 
     */
    private void showPrescriptions() {
        this.prescriptionPane = new PatientHealthProfessionalPrescription(
            this.controller,
            this.patient
            );
        this.patientPane.getChildren().add(prescriptionPane.asPane());
    }

    /**
     * Mise à jour de la vue lors d'évènements sur des objets écoutés.
     */
    public void propertyChange(final PropertyChangeEvent evt) {
        this.refresh();
    }

    /** 
     * @return Pane
     */
    public Pane asPane() {
        return patientPane;
    }

}
