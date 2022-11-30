package fr.univ_lyon1.info.m1.mes.view.component.HealthProfessionalComponent;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import fr.univ_lyon1.info.m1.mes.controller.HealthProfessionalController;
import fr.univ_lyon1.info.m1.mes.model.HealthProfessional;
import fr.univ_lyon1.info.m1.mes.model.Patient;
import fr.univ_lyon1.info.m1.mes.model.Prescription;
import fr.univ_lyon1.info.m1.mes.utils.EasyAlert;
import io.github.palexdev.materialfx.builders.layout.BorderBuilder;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.collections.FXCollections;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.util.StringConverter;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

public class HealthProfessionalBox implements PropertyChangeListener {

    /**
     * Attributes.
     */
    private final HealthProfessionalController controller;
    private HealthProfessional professional;
    private Patient currentPatient;
    private final Pane healthProfessionalPane = new VBox();
    private final Pane optionSearchPane = new HBox();
    private final Pane searchResultPane = new VBox();
    private final Pane patientPane = new VBox();
    private Pane prescriptionPane = new VBox();
    private ScrollPane prescriptionsScroll = new ScrollPane();
    private MFXComboBox<Patient> patientComboBox = new MFXComboBox<Patient>();
    private HealthProfessionalPrescriptions prescriptionsToolPane;
    private HealthProfessionalPatientPrescription patientPrescriptionPane;

    /**
     * Constructor HealthProfessionalBox component.
     * @param healthProfessionalController HealthProfessionalController
     * @param p HealthProfessional
     */
    public HealthProfessionalBox(
        final HealthProfessionalController healthProfessionalController,
        final HealthProfessional p) {
        this.controller = healthProfessionalController;
        this.professional = p;
        this.currentPatient = null;
        refresh();
    }
    
    /**
     * Refresh function for dynamic view.
     */
    private void refresh() {
        if (this.professional != null) {

            //Content
            final Label healthProfessionalNameLabel = new Label(this.professional.getName());
            final Label searchPatientLabel = new Label("Rechercher patient : ");
            final String[] listChoices = {"N°patient", "Nom patient", "Prescription"};
            final MFXComboBox<String> searchComboBox = new MFXComboBox<String>();
            searchComboBox.setItems(FXCollections.observableArrayList(listChoices));
            searchComboBox.setPromptText("Type recherche");
            final MFXTextField searchTextField = new MFXTextField();
            final MFXButton searchButton = new MFXButton("Rechercher");


            this.optionSearchPane.getChildren().setAll(
                searchComboBox,
                searchTextField,
                searchButton);
            this.healthProfessionalPane.getChildren().setAll(
                healthProfessionalNameLabel,
                searchPatientLabel,
                optionSearchPane,
                searchResultPane,
                patientPane
                );

            //Css Stuff
            this.prescriptionsScroll.setStyle("-fx-background-color:transparent;");
            this.prescriptionsScroll.setPannable(true);
            this.prescriptionsScroll.setMaxSize(450, 200);
            this.prescriptionsScroll.setMinSize(100, 100);
            this.healthProfessionalPane.setPadding(new Insets(10));
            VBox.setMargin(healthProfessionalNameLabel, new Insets(10));
            
            healthProfessionalNameLabel.setFont(new Font("Arial", 20));

            searchComboBox.setMinSize(150, 27);
            searchComboBox.setMaxSize(150, 27);
            searchTextField.setMinSize(200, 27);
            searchTextField.setMaxSize(200, 27);
            this.patientComboBox.setMinSize(150, 27);
            this.patientComboBox.setMaxSize(150, 27);


            BorderBuilder border = new BorderBuilder();
            border.addFill(
                Paint.valueOf("#dc0f6c"),
                BorderStrokeStyle.SOLID,
                new CornerRadii(5),
                new BorderWidths(1, 1, 1, 1),
                new Insets(0, 0, 0, 0)
            );
            searchButton.setBorder(border.get());
            this.prescriptionsScroll.setBorder(border.get());
            
            //Buttons & Events
            searchButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent event) {
                    searchResultPane.getChildren().clear();
                    patientPane.getChildren().clear();
                    if (searchComboBox.getValue() == null 
                    || searchTextField.getText().equals("")) {
                        EasyAlert.alert("Erreur!", "Veuillez remplir tout les champs!");
                        return;
                    }
                    // TODO: Better
                    List<Patient> list = controller.getPatients(
                        searchComboBox.getValue(),
                        searchTextField.getText());

                    if (list.isEmpty()) {
                        final Label noChoice = new Label(
                            "Aucun patient ne correspond à votre recherche");
                            searchResultPane.getChildren().setAll(noChoice);
                        return;
                    }
                    if (list.size() > 1) {
                        showMultiplePatientChoice(list);
                        return;
                    }
                    controller.selectPatient(list.get(0));
                    return;
                }

            });
        }
    }

     /**
     * Set new HealthProfessional select.
     * @param hp HealthProfessional.
     */
    public void setHealthProfessional(final HealthProfessional hp) {
        this.professional = hp;
        optionSearchPane.getChildren().clear();
        searchResultPane.getChildren().clear();
        patientPane.getChildren().clear();
        refresh();
    }

    /**
     * Show PatientPrescription when 1 Patient are load.
     */
    private void showPatientPrescription(final Patient patient) {
        this.prescriptionPane.getChildren().setAll();
        final Label name = new Label(patient.getName());
        name.setFont(new Font("Arial", 18));
        VBox.setMargin(name, new Insets(10));
        List<Prescription> prescriptions = this.controller.getPrescriptionsByPatient(patient);

        for (final Prescription prescription : prescriptions) {
             this.patientPrescriptionPane = new HealthProfessionalPatientPrescription(
            this.controller,
            prescription
            );
            this.prescriptionPane.getChildren().add(this.patientPrescriptionPane.asPane());
        }
        this.prescriptionsScroll.setContent(this.prescriptionPane);
        this.patientPane.getChildren().setAll(name, this.prescriptionsScroll);
       
    }

    /**
     * Show PrescriptionTool.
     */
    private void showPrescriptionTool() {
        this.prescriptionsToolPane = new HealthProfessionalPrescriptions(
            this.controller,
            this.professional
        );
        this.patientPane.getChildren().add(prescriptionsToolPane.asPane());
    }

    /**
     * Show comboBox selection if multiple patient choice with search.
     * @param list List<Patient>
     */
    private void showMultiplePatientChoice(final List<Patient> list) {
        final Label multipleChoice = new Label(
            "Vous avez " + list.size() + " patients qui correspond à votre recherche.");
        this.patientComboBox.setPromptText("[Aucun]");
        this.patientComboBox.setConverter(new StringConverter<Patient>() {

            @Override
            public String toString(final Patient object) {
                if (object == null) {
                    return null;
                }
                return object.getName();
            }

            @Override
            public Patient fromString(final String string) {
                return null;
            }
            
        });
        this.patientComboBox.getItems().setAll(list);
        patientComboBox.getSelectionModel().selectedItemProperty().addListener(
            (options, oldValue, newValue) -> {
                controller.selectPatient(newValue);
        });

        this.searchResultPane.getChildren().setAll(multipleChoice, patientComboBox);
    }

    /**
     * Update comboBox patient in HealthProfessionalBox View.
     * @param patientList List<Patient>
     */
    public void updatePatient(final List<Patient> patientList) {
        Patient oldPatient = this.patientComboBox.getSelectionModel().getSelectedItem();
        this.patientComboBox.getItems().clear();
        this.patientComboBox.setItems(FXCollections.observableArrayList(patientList));
        if (this.patientComboBox.getItems().contains(oldPatient)) {
            this.patientComboBox.getSelectionModel().selectItem(oldPatient);
        }
    }

    /**
     * Update View on Listended objects.
     * @param evt PropertyChangeEvent
     */
    public void propertyChange(final PropertyChangeEvent evt) {
        this.refresh();
        if (evt.getPropertyName().equals("prescription")) {
            patientPane.getChildren().clear();
            showPatientPrescription(currentPatient);
            showPrescriptionTool();
        }
    }

    /**
     * Set and display patient when selected.
     * @param p Patient.
     */
    public void setPatient(final Patient p) {
        this.currentPatient = p;
        refresh();
        showPatientPrescription(p);
        showPrescriptionTool();
    }

    /**
     * Return HealthProfessionalBox Pane.
     * @return Pane.
     */
    public Pane asPane() {
        return this.healthProfessionalPane;
    }
    
}
