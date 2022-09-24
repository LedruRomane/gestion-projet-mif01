package fr.univ_lyon1.info.m1.mes.view.component.PatientComponent;

import fr.univ_lyon1.info.m1.mes.controller.PatientController;
import fr.univ_lyon1.info.m1.mes.model.Patient;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class PatientSelect {
    
    private final PatientController controller;
    private final Pane patientSelectBox = new VBox();
    private final Pane nomFormBox = new HBox();
    private final Pane ssidFormBox = new HBox();
    private ObservableList<Patient> optionSearchPatient;
    private ObservableList<String> optionSearchSsid;
    private final ComboBox patientComboBox;
    private final ComboBox patientSsidComboBox;

    public PatientSelect(final PatientController c) {

        this.controller = c;

        this.patientComboBox = new ComboBox<Patient>(optionSearchPatient);
        final Label prescriptionTitleLabel = new Label("Nouveau Patient : ");
        final Label nameLabel = new Label("Nom : ");
        final TextField namTextField = new TextField();
        final Label typeLabel = new Label("SSID : ");
        this.patientSsidComboBox = new ComboBox<String>(optionSearchSsid);
        final Button searchButton = new Button("Créer");

        this.nomFormBox.getChildren().addAll(
            nameLabel,
            namTextField
        );
        this.ssidFormBox.getChildren().addAll(
            typeLabel,
            patientSsidComboBox
        );

        this.patientSelectBox.getChildren().addAll(
            patientComboBox,
            prescriptionTitleLabel,
            nomFormBox,
            ssidFormBox,
            searchButton
        );
    }

    public Pane asPane() {
        return this.patientSelectBox;
    }
}
