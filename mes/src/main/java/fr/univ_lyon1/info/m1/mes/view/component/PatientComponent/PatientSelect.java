package fr.univ_lyon1.info.m1.mes.view.component.PatientComponent;

import java.net.URL;
import java.util.List;
import fr.univ_lyon1.info.m1.mes.controller.PatientController;
import fr.univ_lyon1.info.m1.mes.model.Patient;
import io.github.palexdev.materialfx.builders.layout.BorderBuilder;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.util.StringConverter;

public class PatientSelect {
    
    private final PatientController controller;
    private final GridPane patientSelectBox = new GridPane();
    private final GridPane createPatient = new GridPane();
    private final MFXComboBox<Patient> patientComboBox;

    /**
     * Constructor PatientSelect component.
     * @param c PatientController
     */
    public PatientSelect(final PatientController c) {

        //Datas
        this.controller = c;
        final URL imageURL = this.getClass().getResource("/img/logo.png");
        if (imageURL == null) {
            System.out.println("Resource not found. Aborting. Img ressource null");
        }

        //Content
        this.patientComboBox = new MFXComboBox<Patient>();
        this.patientComboBox.setPromptText("[Aucun] -  Selectionnez un Patient");
        this.patientComboBox.setConverter(new StringConverter<Patient>() {
            @Override
            public String toString(final Patient object) {
                if (object == null) { 
                    return ""; 
                }
                return "[" + object.getClass().getSimpleName() + "] " + object.getName();
            }

            @Override
            public Patient fromString(final String string) {
                return null;
            }
        });
        final Image image = new Image(imageURL.toExternalForm(),
        400,
        270,
        false,
        true);
        final ImageView imageView = new ImageView(image);
        final Label createPatientTitleLabel = new Label("Nouveau Patient");
        final Label nameLabel = new Label("Nom : ");
        final MFXTextField nameTextField = new MFXTextField();
        final Label ssidLabel = new Label("SSID : ");
        final MFXTextField patientSsidTextField = new MFXTextField();
        final MFXButton createButton = new MFXButton("Créer");

        //Import
        this.createPatient.add(createPatientTitleLabel, 0, 0);
        this.createPatient.add(nameLabel, 0, 1);
        this.createPatient.add(nameTextField, 1, 1);
        this.createPatient.add(ssidLabel, 0, 2);
        this.createPatient.add(patientSsidTextField, 1, 2);
        
        this.patientSelectBox.add(patientComboBox, 0, 0);
        this.patientSelectBox.add(imageView, 0, 1);
        this.patientSelectBox.add(createPatient, 0, 2);
        this.patientSelectBox.add(createButton, 1, 3);

        // Buttons & listeners
        createButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                // Vérifier les champs non remplis
                if (nameTextField.getText().equals("")
                || patientSsidTextField.getText().equals("")) {
                    Alert alert = new Alert(AlertType.ERROR,
                            "Veuillez remplir tous les champs!", ButtonType.OK);
                    alert.showAndWait();
                    return;
                }
                Patient newPatient = controller.createPatient(
                        nameTextField.getText(),
                        patientSsidTextField.getText());
                patientComboBox.getSelectionModel().selectItem(newPatient);
            }
        });

        //Combobox events
        patientComboBox.getSelectionModel().selectedItemProperty().addListener(
            (options, oldValue, newValue) -> {
            controller.selectPatient(newValue);
        });


        //CSS Stuff
        this.patientComboBox.setMaxSize(800, 25);
        this.patientComboBox.setMinSize(100, 25);

        nameTextField.setMinSize(200, 27);
        patientSsidTextField.setMinSize(200, 27);
        createPatientTitleLabel.setFont(new Font("Arial", 18));

        this.patientSelectBox.setPadding(new Insets(10, 10, 10, 10));
        this.patientSelectBox.setAlignment(Pos.TOP_CENTER);
        this.createPatient.setPadding(new Insets(20, 0, 0, 0));

        Insets margin = new Insets(10, 0, 10, 0);
        GridPane.setMargin(createPatientTitleLabel, margin);
        GridPane.setMargin(nameLabel, margin);
        GridPane.setMargin(ssidLabel, margin);

        BorderBuilder border = new BorderBuilder();
        border.addFill(
            Paint.valueOf("#dc0f6c"),
            BorderStrokeStyle.SOLID,
            new CornerRadii(5),
            new BorderWidths(1, 1, 1, 1),
            new Insets(0, 0, 0, 0)
        );
        createButton.setBorder(border.get());
    }

    /**
     * Update Patient when a Patient is added.
     * @param patientList List Patient
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
     * Return Pane patientSelectBox.
     * @return this Pane.
     */
    public Pane asPane() {
        return this.patientSelectBox;
    }
}
