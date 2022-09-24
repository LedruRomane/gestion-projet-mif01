package fr.univ_lyon1.info.m1.mes.view.component.HealthProfessionalComponent;

import fr.univ_lyon1.info.m1.mes.controller.HealthProfessionalController;
import fr.univ_lyon1.info.m1.mes.model.HealthProfessional;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class HealthProfessionalSelect {
    
    private final HealthProfessionalController controller;
    private final Pane healthProfessionalSelectBox = new VBox();
    private final Pane nomFormBox = new HBox();
    private final Pane typeFormBox = new HBox();
    private ObservableList<HealthProfessional> optionSearchHp;
    private ObservableList<String> optionSearchType;
    private final ComboBox healthProComboBox;
    private final ComboBox healthProTypeComboBox;

    public HealthProfessionalSelect(final HealthProfessionalController c) {

        this.controller = c;

        this.healthProComboBox = new ComboBox<HealthProfessional>(optionSearchHp);
        final Label prescriptionTitleLabel = new Label("Nouveau Médecin : ");
        final Label nameLabel = new Label("Nom : ");
        final TextField namTextField = new TextField();
        final Label typeLabel = new Label("Profession : ");
        this.healthProTypeComboBox = new ComboBox<String>(optionSearchType);
        final Button searchButton = new Button("Créer");

        this.nomFormBox.getChildren().addAll(
            nameLabel,
            namTextField
        );
        this.typeFormBox.getChildren().addAll(
            typeLabel,
            healthProTypeComboBox
        );

        this.healthProfessionalSelectBox.getChildren().addAll(
            healthProComboBox,
            prescriptionTitleLabel,
            nomFormBox,
            typeFormBox,
            searchButton
        );
    }

    public Pane asPane() {
        return this.healthProfessionalSelectBox;
    }
}
