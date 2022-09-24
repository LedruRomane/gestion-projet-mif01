package fr.univ_lyon1.info.m1.mes.view.component.HealthProfessionalComponent;

import fr.univ_lyon1.info.m1.mes.controller.HealthProfessionalController;
import fr.univ_lyon1.info.m1.mes.model.HealthProfessional;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HealthProfessionalBox {

    private final HealthProfessionalController controller;
    private final Pane healthProfessionalPane = new VBox();
    private final Pane optionSearchPane = new HBox();
    private HealthProfessionalPrescriptions prescriptionsToolPane;
    private HealthProfessionalPatientPrescription patientPrescriptionPane;
    private HealthProfessional healthProfessional = null;
    private ObservableList<String> optionSearch;

    public HealthProfessionalBox(final HealthProfessionalController healthProfessionalController) {
        this.controller = healthProfessionalController;
        refrech();
    }
    
    private void refrech() {
        if (this.healthProfessional != null) {

            final Label healthProfessionalNameLabel = new Label(healthProfessional.getName());
            final Label searchPatientLabel = new Label("Rechercher patient : ");
            final ComboBox patientComboBox = new ComboBox<String>(optionSearch);
            final TextField searchTextField = new TextField();
            final Button searchButton = new Button("Rechercher");

            this.optionSearchPane.getChildren().addAll(
                patientComboBox,
                searchTextField,
                searchButton);
            this.healthProfessionalPane.getChildren().addAll(
                healthProfessionalNameLabel,
                searchPatientLabel,
                optionSearchPane
                );

            /* 
            * TODO observable sur optionSearchPane pour afficher le bloc 6 patient. 
            * TODO search by prescription : proposer une liste des patients
            * qui ont cette prescription et pouvoir en selectionner 1 pour l'afficher et le gérer. 
            */ 
            showPatientPrescription(); //bloc 6 dynamique avec le patient selectionné du médecin.
            showPrescriptionTool(); //bloc 7 dynamique du médecin en cours. 
        }
    }

    private void setHealthProfessional(final HealthProfessional hp) {
        this.healthProfessional = hp;
    }

    private void showPatientPrescription() {
        this.patientPrescriptionPane = new HealthProfessionalPatientPrescription(
            this.controller,
            this.healthProfessional
        );
        this.healthProfessionalPane.getChildren().add(patientPrescriptionPane.asPane());
    }

    private void showPrescriptionTool() {
        this.prescriptionsToolPane = new HealthProfessionalPrescriptions(
            this.controller,
            this.healthProfessional
        );
        this.healthProfessionalPane.getChildren().add(prescriptionsToolPane.asPane());

    }

    public Pane asPane() {
        return this.healthProfessionalPane;
    }
    
}
