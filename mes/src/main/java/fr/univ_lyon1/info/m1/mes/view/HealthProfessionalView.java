package fr.univ_lyon1.info.m1.mes.view;

import java.util.ArrayList;
import java.util.List;
import fr.univ_lyon1.info.m1.mes.model.Dentist;
import fr.univ_lyon1.info.m1.mes.model.Homeopath;
import fr.univ_lyon1.info.m1.mes.model.Pediatrician;
import fr.univ_lyon1.info.m1.mes.model.HealthProfessional;
import fr.univ_lyon1.info.m1.mes.model.Patient;
import fr.univ_lyon1.info.m1.mes.utils.EasyAlert;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class HealthProfessionalView {
    private final VBox pane = new VBox();
    private final VBox prescriptions = new VBox();
    private HealthProfessional healthProfessional;
    private String selectedPatientSSID;

    public HealthProfessionalView(final HealthProfessional hp) {

        this.healthProfessional = hp;
        final HealthProfessionalView parent = this;

        pane.setStyle("-fx-border-color: gray;\n"
                + "-fx-border-insets: 5;\n"
                + "-fx-padding: 5;\n"
                + "-fx-border-width: 1;\n");

        final HBox searchBox = new HBox();
        final HBox addPrescriptionBox = new HBox();

        final Label hpNameLabel = new Label(hp.getName());
        final Label prescriveLabel = new Label("Prescibe");
        final TextField t = new TextField();
        final TextField prescriptionTextField = new TextField();
        final Button searchButton = new Button("Search");
        final Button addButton = new Button("Add");
        
        searchBox.getChildren().addAll(t, searchButton);
        addPrescriptionBox.getChildren().addAll(prescriptionTextField, addButton);
        pane.getChildren().addAll(hpNameLabel,
                                prescriveLabel, 
                                searchBox, 
                                prescriptions,
                                addPrescriptionBox);

        

        final EventHandler<ActionEvent> searchPatientHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                final String text = t.getText().trim();
                if (text.equals("")) {
                    return; // Do nothing
                }
                selectedPatientSSID = text;
                showPrescriptions();
                t.setText("");
                t.requestFocus();
            }
        };
        addButton.setOnAction(searchPatientHandler);
        t.setOnAction(searchPatientHandler);

        final EventHandler<ActionEvent> prescriptionHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                final String text = prescriptionTextField.getText().trim();
                if (text.equals("")) {
                    return; // Do nothing
                }
                prescriptionTextField.setText("");
                prescriptionTextField.requestFocus();
                parent.prescribe(text);
            }
        };

        // TODO: someone wrote some business logic within the view :-\
        List<String> predefPrescr = new ArrayList<>();
        predefPrescr.add("Paracetamol");
        if (hp instanceof Dentist) {
            predefPrescr.add("Don't eat for one hour");
        } else if (hp instanceof Homeopath) {
            predefPrescr.add("Natrum Muriaticum 30CH");
            predefPrescr.add("Sucre 200K");
        } else if (hp instanceof Pediatrician) {
            predefPrescr.add("Less fastfood");
        }
        for (final String p : predefPrescr) {
            final Button predefPrescrB = new Button(p);
            predefPrescrB.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent event) {
                    parent.prescribe(p);
                }
            });
            pane.getChildren().add(predefPrescrB);
        }
        prescriptionTextField.setOnAction(prescriptionHandler);
        addButton.setOnAction(prescriptionHandler);
    }

    
    /**
     * @param prescription
     */
    void prescribe(final String prescription) {
        if (selectedPatientSSID == null) {
            EasyAlert.alert("Please select a patient first");
            return;
        }
      /*   healthProfessional
                .getPatient(selectedPatientSSID)
                .addPrescription(healthProfessional, prescription);
                */
        showPrescriptions();
    }

    /*
     * Show patient list prescription
     */
    void showPrescriptions() {
        prescriptions.getChildren().clear();
        Patient p = healthProfessional.getPatient(selectedPatientSSID);

        if (p == null) {
            prescriptions.getChildren().add(new Label(
                    "Use search above to see prescriptions"));
            return;
        }

        prescriptions.getChildren().add(new Label(
                "Prescriptions for " + p.getName()));

        for (final Prescription pr : p.getPrescriptions(healthProfessional)) {

            final HBox pView = new HBox();
            final Label content = new Label("- " + pr.getContent());
            final Button removeBtn = new Button("x");
            removeBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent event) {
                    p.removePrescription(pr);
                    pView.getChildren().remove(content);
                    pView.getChildren().remove(removeBtn);
                }

            });
            pView.getChildren().addAll(content, removeBtn);
            prescriptions.getChildren().add(pView);
        } */
    }

    
    /** 
     * @return Pane
     */
    public Pane asPane() {
        return pane;
    }

}
