package fr.univ_lyon1.info.m1.mes.view.component.HealthProfessionalComponent;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import fr.univ_lyon1.info.m1.mes.controller.HealthProfessionalController;
import fr.univ_lyon1.info.m1.mes.model.HealthProfessional;
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
import javafx.util.StringConverter;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class HealthProfessionalPrescriptions implements PropertyChangeListener {

    private final HealthProfessionalController controller;
    private final HealthProfessional healthProfessional;
    private final GridPane prescriptionToolPane = new GridPane();
    private MFXComboBox<Prescription> prefPrescriptionComboBox;

    /**
     * Contructor HealthProfessionalPrescription component.
     * @param hpController HealthProfessionalController
     * @param hp HealthProfessional
     */
    public HealthProfessionalPrescriptions(
        final HealthProfessionalController hpController,
        final HealthProfessional hp) {
        
        //Load
        this.controller = hpController;
        this.healthProfessional = hp;
        this.prefPrescriptionComboBox = new MFXComboBox<Prescription>();
        final List<Prescription> listPrefPrescription = new ArrayList<Prescription>();
        listPrefPrescription.addAll(this.healthProfessional.getPrefPrescription());
       
        //Content
        this.prefPrescriptionComboBox.setPromptText("[Choisir prescription]");
        this.prefPrescriptionComboBox.setConverter(new StringConverter<Prescription>() {
            @Override
            public String toString(final Prescription object) {
                if (object == null) { 
                    return ""; 
                }
                return object.getContent();
            }

            @Override
            public Prescription fromString(final String string) {
                return null;
            }
        });
        this.prefPrescriptionComboBox.setItems(
            FXCollections.observableArrayList(listPrefPrescription));
        
        final Label descriptionLabel = new Label("Prescription ");
        final Label favoritePrescriptionLabel = new Label("Prescription favorites :");  
        final Label customPrescriptionLabel = new Label("Prescription personnalisé : ");
        final MFXTextField createTextField = new MFXTextField();
        final MFXButton prescribeButton = new MFXButton("Prescrire");
        
        this.prescriptionToolPane.add(descriptionLabel, 0, 0);
        this.prescriptionToolPane.add(favoritePrescriptionLabel, 0, 1);
        this.prescriptionToolPane.add(this.prefPrescriptionComboBox, 1, 1);
        this.prescriptionToolPane.add(customPrescriptionLabel, 0, 2);
        this.prescriptionToolPane.add(createTextField, 1, 2);
        this.prescriptionToolPane.add(prescribeButton, 2, 3);

        //Css Stuff
        this.prefPrescriptionComboBox.setMinSize(200, 27);
        createTextField.setMinSize(200, 27);

        descriptionLabel.setFont(new Font("Arial", 18));

        Insets margin = new Insets(10, 10, 10, 0);
        GridPane.setMargin(descriptionLabel, margin);
        GridPane.setMargin(favoritePrescriptionLabel, margin);
        GridPane.setMargin(customPrescriptionLabel, margin);
        GridPane.setMargin(prescribeButton, new Insets(15, 0, 0, 20));
        this.prescriptionToolPane.setPadding(new Insets(20, 0, 0, 0));
        
        BorderBuilder border = new BorderBuilder();
        border.addFill(
            Paint.valueOf("#dc0f6c"),
            BorderStrokeStyle.SOLID,
            new CornerRadii(5),
            new BorderWidths(1, 1, 1, 1),
            new Insets(0, 0, 0, 0)
        );
        prescribeButton.setBorder(border.get());

        //Buttons & Events
        prescribeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                if (createTextField.getText().length() != 0 
                && prefPrescriptionComboBox.getSelectedItem() != null) {
                    EasyAlert.alertPromptComboBoxOrTextFieldOrBoth(
                        "Selection",
                        "Attention, plusieurs choix possibles !",
                        "Quelle prescription souhaitez-vous prescrire ? ",
                        new Runnable() {
                            @Override
                            public void run() {
                                addPrescription(
                                    prefPrescriptionComboBox.getSelectedItem().getContent());
                            }
                        },
                        new Runnable() {
                            @Override
                            public void run() {
                                addPrescription(createTextField.getText());
                            }
                        },
                        new Runnable() {
                            @Override
                            public void run() {
                                addPrescription(
                                    prefPrescriptionComboBox.getSelectedItem().getContent());
                                addPrescription(createTextField.getText());
                            }
                        }
                    );
                }
                if (createTextField.getText().length() != 0 
                && prefPrescriptionComboBox.getSelectedItem() == null) {
                    EasyAlert.alertPromptYesNo(
                        "Prescription",
                        "Attention ! [Custom] Voulez-vous prescrire : " 
                        + createTextField.getText() + " ?",
                        new Runnable() {
                            @Override
                            public void run() {
                                addPrescription(createTextField.getText());
                            }
                        }
                    );
                }
                if (createTextField.getText().isBlank() 
                && prefPrescriptionComboBox.getSelectedItem() != null) {
                    EasyAlert.alertPromptYesNo(
                        "Prescription",
                        "Attention ! [Selected] Voulez-vous prescrire : " 
                        + prefPrescriptionComboBox.getSelectedItem().getContent() + " ?",
                        new Runnable() {
                            @Override
                            public void run() {
                                addPrescription(
                                    prefPrescriptionComboBox.getSelectedItem().getContent());
                            }
                        }
                    );
                }
                if (createTextField.getText().isBlank() 
                && prefPrescriptionComboBox.getSelectedItem() == null) {
                    EasyAlert.alert("Erreur!", 
                        "Veuillez selectionner ou remplir une prescription !");
                    return;
                }
            }
        });
    }

    /**
     * Add a prescription to Patient.
     * @param pString String
     * @return Prescription
     */
    private Prescription addPrescription(final String pString) {
        Prescription prescription = controller.addPrescription(pString);
        return prescription;
    }
    
    /**
     * Update View on Listended objects.
     * @param evt PropertyChangeEvent
     */
    public void propertyChange(final PropertyChangeEvent evt) {
        
    }

    /**
     * Return this Pane.
     * @return Pane
     */
    public Pane asPane() {
        return this.prescriptionToolPane;
    }
}
