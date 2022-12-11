package fr.univ_lyon1.info.m1.mes.view.component.HealthProfessionalComponent;

import java.net.URL;
import java.util.List;
import io.github.palexdev.materialfx.builders.layout.BorderBuilder;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.effects.DepthLevel;
import fr.univ_lyon1.info.m1.mes.controller.HealthProfessionalController;
import fr.univ_lyon1.info.m1.mes.model.HealthProfessional;
import fr.univ_lyon1.info.m1.mes.types.HealthProfessionalType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import javafx.collections.FXCollections;

public class HealthProfessionalSelect {

    private final HealthProfessionalController controller;
    private final MFXComboBox<HealthProfessional> healthProComboBox;
    private final MFXComboBox<HealthProfessionalType> healthProTypeComboBox;
    private final GridPane healthProSelectBox = new GridPane();
    private final GridPane createHealthPro = new GridPane();

    /**
     * Constructor HealthProfessionalSelect Component.
     * @param c HealthProfessionalController
     */
    public HealthProfessionalSelect(final HealthProfessionalController c) {

        //Load datas
        this.controller = c;
        final URL imageURL = this.getClass().getResource("/img/logo.png");
        if (imageURL == null) {
            System.out.println("Resource not found. Aborting. Img ressource null");
        }

        //Content
        this.healthProComboBox = new MFXComboBox<HealthProfessional>();
        this.healthProTypeComboBox = new MFXComboBox<HealthProfessionalType>();
        this.healthProComboBox.setPromptText("[Aucun]");
        this.healthProComboBox.setConverter(new StringConverter<HealthProfessional>() {
            @Override
            public String toString(final HealthProfessional object) {
                if (object == null) { 
                    return ""; 
                }
                return "[" + object.getClass().getSimpleName() + "] " + object.getName();
            }

            @Override
            public HealthProfessional fromString(final String string) {
                return null;
            }
        });

        Image image = new Image(imageURL.toExternalForm(),
        400,
        270,
        false,
        true);
        ImageView imageView = new ImageView(image);
        final Label createHealthProTitleLabel = new Label("Nouveau Médecin : ");
        final Label nameLabel = new Label("Nom : ");
        final MFXTextField nameTextField = new MFXTextField();
        final Label typeLabel = new Label("Profession : ");
        this.healthProTypeComboBox.setItems(
                FXCollections.observableArrayList(HealthProfessionalType.values()));
        final MFXButton createButton = new MFXButton("Créer");

        //Import
        this.createHealthPro.add(createHealthProTitleLabel, 0, 0);
        this.createHealthPro.add(nameLabel, 0, 1);
        this.createHealthPro.add(nameTextField, 1, 1);
        this.createHealthPro.add(typeLabel, 0, 2);
        this.createHealthPro.add(healthProTypeComboBox, 1, 2);

        this.healthProSelectBox.add(healthProComboBox, 0, 0);
        this.healthProSelectBox.add(imageView, 0, 1);
        this.healthProSelectBox.add(createHealthPro, 0, 2);
        this.healthProSelectBox.add(createButton, 1, 3);


        // Buttons & listeners
        createButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                // Vérifier les champs non remplis
                if (healthProTypeComboBox.getValue() == null 
                || nameTextField.getText().equals("")) {
                    Alert alert = new Alert(AlertType.ERROR,
                            "Veuillez remplir tous les champs!", ButtonType.OK);
                    alert.showAndWait();
                    return;
                }
                HealthProfessional newPro = controller.createHealthProfessional(
                        (HealthProfessionalType) healthProTypeComboBox.getValue(),
                        nameTextField.getText());
                healthProComboBox.getSelectionModel().selectItem(newPro);
            }
        });

        // Combobox events
        healthProComboBox.getSelectionModel().selectedItemProperty().addListener(
            (options, oldValue, newValue) -> {
            controller.selectHealthProfessional(newValue);
        });

        //CSS Stuff
        this.healthProComboBox.setMaxSize(800, 25);
        this.healthProComboBox.setMinSize(100, 25);
        this.healthProTypeComboBox.setMaxSize(200, 27);
        this.healthProTypeComboBox.setMinSize(200, 27);

        nameTextField.setMinSize(200, 27);
        createHealthProTitleLabel.setFont(new Font("Arial", 18));

        this.healthProSelectBox.setPadding(new Insets(10, 10, 10, 10));
        this.healthProSelectBox.setAlignment(Pos.TOP_CENTER);
        this.createHealthPro.setPadding(new Insets(20, 0, 0, 0));

        Insets margin = new Insets(10, 0, 10, 0);
        GridPane.setMargin(createHealthProTitleLabel, margin);
        GridPane.setMargin(nameLabel, margin);
        GridPane.setMargin(typeLabel, margin);

        createButton.setDepthLevel(DepthLevel.LEVEL5);
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
     * update HealthProfessional comboBox.
     * @param proList List HealthProfessional
     */
    public void updateHealthProfessional(final List<HealthProfessional> proList) {
        HealthProfessional oldPro = this.healthProComboBox.getSelectionModel().getSelectedItem();
        this.healthProComboBox.getItems().clear();
        this.healthProComboBox.setItems(FXCollections.observableArrayList(proList));
        if (this.healthProComboBox.getItems().contains(oldPro)) {
            this.healthProComboBox.getSelectionModel().selectItem(oldPro);
        }
    }

    /**
     * Return Pane healthProfessionalSelectBox.
     * @return this Pane
     */
    public Pane asPane() {
        return this.healthProSelectBox;
    }
    
}
