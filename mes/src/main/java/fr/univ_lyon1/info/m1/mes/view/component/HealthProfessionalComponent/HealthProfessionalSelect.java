package fr.univ_lyon1.info.m1.mes.view.component.HealthProfessionalComponent;

import java.util.List;

import fr.univ_lyon1.info.m1.mes.controller.HealthProfessionalController;
import fr.univ_lyon1.info.m1.mes.model.HealthProfessional;
import fr.univ_lyon1.info.m1.mes.types.HealthProfessionalType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.collections.FXCollections;

public class HealthProfessionalSelect {

    private final HealthProfessionalController controller;
    private final Pane healthProfessionalSelectBox = new VBox();
    private final Pane nomFormBox = new HBox();
    private final Pane typeFormBox = new HBox();

    private final TextField nameTextField;
    private final ComboBox<HealthProfessional> healthProComboBox;

    public HealthProfessionalSelect(final HealthProfessionalController c) {

        this.controller = c;

        this.healthProComboBox = new ComboBox<HealthProfessional>();
        this.healthProComboBox.setPromptText("[Aucun]");
        this.healthProComboBox.setConverter(new StringConverter<HealthProfessional>() {
            @Override
            public String toString(final HealthProfessional object) {
                return "[" + object.getClass().getSimpleName() + "] " + object.getName();
            }

            @Override
            public HealthProfessional fromString(final String string) {
                return null;
            }
        });

        final Label prescriptionTitleLabel = new Label("Nouveau Médecin : ");
        final Label nameLabel = new Label("Nom : ");
        this.nameTextField = new TextField();
        final Label typeLabel = new Label("Profession : ");
        ComboBox<HealthProfessionalType> healthProTypeComboBox = new ComboBox<>();
        healthProTypeComboBox.setItems(
                FXCollections.observableArrayList(HealthProfessionalType.values()));
        final Button createButton = new Button("Créer");

        this.nomFormBox.getChildren().addAll(
                nameLabel,
                nameTextField);
        this.typeFormBox.getChildren().addAll(
                typeLabel,
                healthProTypeComboBox);

        this.healthProfessionalSelectBox.getChildren().addAll(
                healthProComboBox,
                prescriptionTitleLabel,
                nomFormBox,
                typeFormBox,
                createButton);

        // Buttons
        createButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                // Vérifier les champs non remplis
                if (healthProTypeComboBox.getValue() == null || nameTextField.getText() == "") {
                    Alert alert = new Alert(AlertType.ERROR,
                            "Veuillez remplir tout les champs!", ButtonType.OK);
                    alert.showAndWait();
                    return;
                }
                HealthProfessional newPro = controller.createHealthProfessional(
                        (HealthProfessionalType) healthProTypeComboBox.getValue(),
                        nameTextField.getText());
                healthProComboBox.getSelectionModel().select(newPro);
            }
        });

        // Combobox events
        healthProComboBox.getSelectionModel().selectedItemProperty().addListener(
            (options, oldValue, newValue) -> {
            System.out.println(newValue);
         }); 
    }

    public void updateHealthProfessional(final List<HealthProfessional> proList) {
        this.healthProComboBox.getItems().clear();
        this.healthProComboBox.setItems(FXCollections.observableArrayList(proList));
    }

    public Pane asPane() {
        return this.healthProfessionalSelectBox;
    }
}
