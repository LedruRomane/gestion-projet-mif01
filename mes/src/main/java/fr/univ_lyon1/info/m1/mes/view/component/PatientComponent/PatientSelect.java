package fr.univ_lyon1.info.m1.mes.view.component.PatientComponent;

import java.util.List;

import fr.univ_lyon1.info.m1.mes.controller.PatientController;
import fr.univ_lyon1.info.m1.mes.model.Patient;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

public class PatientSelect {
    
    private final PatientController controller;
    private final Pane patientSelectBox = new VBox();
    private final Pane nomFormBox = new HBox();
    private final Pane ssidFormBox = new HBox();
    private final ComboBox<Patient> patientComboBox;

    public PatientSelect(final PatientController c) {

        this.controller = c;

        this.patientComboBox = new ComboBox<Patient>();
        this.patientComboBox.setPromptText("[Aucun]");
        this.patientComboBox.setConverter(new StringConverter<Patient>() {
            @Override
            public String toString(final Patient object) {
                return "[" + object.getClass().getSimpleName() + "] " + object.getName();
            }

            @Override
            public Patient fromString(final String string) {
                return null;
            }
        });

        final Label prescriptionTitleLabel = new Label("Nouveau Patient : ");
        final Label nameLabel = new Label("Nom : ");
        final TextField nameTextField = new TextField();
        final Label typeLabel = new Label("SSID : ");
        final TextField patientSsidTextField = new TextField();
        final Button createButton = new Button("Créer");

        this.nomFormBox.getChildren().addAll(
            nameLabel,
            nameTextField
        );
        this.ssidFormBox.getChildren().addAll(
            typeLabel,
            patientSsidTextField
        );

        this.patientSelectBox.getChildren().addAll(
            patientComboBox,
            prescriptionTitleLabel,
            nomFormBox,
            ssidFormBox,
            createButton
        );

        // Buttons
        createButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                // Vérifier les champs non remplis
                if (nameTextField.getText() == "" || patientSsidTextField.getText() == "") {
                    Alert alert = new Alert(AlertType.ERROR,
                            "Veuillez remplir tout les champs!", ButtonType.OK);
                    alert.showAndWait();
                    return;
                }
                Patient newPatient = controller.createPatient(
                        nameTextField.getText(),
                        patientSsidTextField.getText());
                patientComboBox.getSelectionModel().select(newPatient);
            }
        });

        patientComboBox.getSelectionModel().selectedItemProperty().addListener(
            (options, oldValue, newValue) -> {
            controller.selectPatient(newValue);
         }); 
    }

    public void updatePatient(final List<Patient> patientList) {
        Patient oldPatient = this.patientComboBox.getSelectionModel().getSelectedItem();
        this.patientComboBox.getItems().clear();
        this.patientComboBox.setItems(FXCollections.observableArrayList(patientList));
        this.patientComboBox.getSelectionModel().select(oldPatient);
    }

    public Pane asPane() {
        return this.patientSelectBox;
    }
}
