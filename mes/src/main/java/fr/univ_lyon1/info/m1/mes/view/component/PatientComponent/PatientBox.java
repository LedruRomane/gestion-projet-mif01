package fr.univ_lyon1.info.m1.mes.view.component.PatientComponent;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import fr.univ_lyon1.info.m1.mes.controller.PatientController;
import fr.univ_lyon1.info.m1.mes.model.HealthProfessional;
import fr.univ_lyon1.info.m1.mes.model.Patient;
import fr.univ_lyon1.info.m1.mes.model.Prescription;
import fr.univ_lyon1.info.m1.mes.utils.EasyClipboard;
import io.github.palexdev.materialfx.builders.layout.BorderBuilder;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class PatientBox implements PropertyChangeListener {
    
    private final PatientController controller;
    private Patient patient;
    private Pane patientPane = new VBox();
    private Pane prescriptionsPane = new VBox();
    private Pane descriptionPatientPane = new HBox();
    private ScrollPane prescriptionsScroll = new ScrollPane();
    private PatientHealthProfessionalPrescription prescriptionPane;
   
    /**
     * Constructor PatientBox component.
     * @param c PatientController
     * @param p Patient
     */
    public PatientBox(final PatientController c, final Patient p) {

        this.controller = c;
        this.patient = p;
        refresh();
    }

    /**
     * Dynamic refresh when a new Patient is selected.
     */
    private void refresh() {
        if (this.patient != null) {

            //Data
            final URL imageURL = this.getClass().getResource("/img/copier.png");
            if (imageURL == null) {
                System.out.println("Resource not found. Aborting. Img ressource null");
            }

            //Content
            final Image image = new Image(imageURL.toExternalForm(),
                15,
                15,
                false,
                true
            );
            final ImageView copie = new ImageView(image);
            final Label patientNameLabel = new Label(patient.getName());
            final Label sSIDLabel = new Label(patient.getSSID());
            final MFXButton sSIDButton = new MFXButton("");
            final Label prescriptionTitleLabel = new Label("Mes prescriptions : ");

            //Load & import
            showPrescriptions();
            sSIDButton.setGraphic(copie);
            this.descriptionPatientPane.getChildren().setAll(
                patientNameLabel,
                sSIDLabel,
                sSIDButton
            );
            this.patientPane.getChildren().setAll(
                this.descriptionPatientPane,
                prescriptionTitleLabel,
                this.prescriptionsScroll
            );
            
            //refresh
            if (this.prescriptionsPane != null) {
                showPrescriptions();
            }

            //Css Stuff
            HBox.setMargin(patientNameLabel, new Insets(20, 10, 0, 10));
            HBox.setMargin(sSIDLabel, new Insets(20, 10, 30, 10));
            HBox.setMargin(sSIDButton, new Insets(15, 10, 30, 10));
            VBox.setMargin(prescriptionsScroll, new Insets(10));
            this.prescriptionsPane.setPadding(new Insets(10));
            this.patientPane.setPadding(new Insets(0, 10, 0, 10));
            
            patientNameLabel.setFont(new Font("Arial", 18));
            sSIDLabel.setFont(new Font("Arial", 18));
            prescriptionTitleLabel.setFont(new Font("Arial", 18));

            BorderBuilder border = new BorderBuilder();
            border.addFill(
                Paint.valueOf("#dc0f6c"),
                BorderStrokeStyle.SOLID,
                new CornerRadii(5),
                new BorderWidths(1, 1, 1, 1),
                new Insets(0, 0, 0, 0)
            );
            sSIDButton.setBorder(border.get());
            this.prescriptionsScroll.setStyle("-fx-background-color:transparent;");
            this.prescriptionsScroll.setBorder(border.get());
            this.prescriptionsScroll.setPannable(true);
           
            //Buttons & Events
            sSIDButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent event) {
                    EasyClipboard.copy(patient.getSSID());
                }
            });
        }
    }

    /**
     * Set a Patient to the view.
     * @param p Patient
     */
    public void setPatient(final Patient p) {
        this.patient = p;
        refresh();
    }

    /**
     * Dynamic display Prescriptions.
     */
    private void showPrescriptions() {
        this.prescriptionsPane.getChildren().setAll();
        //Sorting prescription by healthprofessional for display.
        HashMap<
            HealthProfessional,
            List<Prescription>> list = new HashMap<HealthProfessional, List<Prescription>>();
        for (final Prescription prescription : this.patient.getPrescriptions()) {
            if (list.get(prescription.getHealthProfessional()) == null) {
                list.put(prescription.getHealthProfessional(), new ArrayList<Prescription>());
            }
            List<Prescription> listPrescription = list.get(prescription.getHealthProfessional());
            listPrescription.add(prescription);
        }
        
        //Display prescriptions by healthprofessional.
        for (HealthProfessional hp : list.keySet()) {
            Label nameHealthPro = new Label(hp.getName());
            nameHealthPro.setFont(new Font("Arial", 16));
            this.prescriptionsPane.getChildren().add(nameHealthPro);
            for (Prescription prescription : list.get(hp)) {
                this.prescriptionPane = new PatientHealthProfessionalPrescription(
                    this.controller,
                    prescription
                );
                this.prescriptionsPane.getChildren().add(this.prescriptionPane.asPane());
            }
        }
        this.prescriptionsScroll.setContent(this.prescriptionsPane);
    }

    /**
     * Update View on Listended objects.
     * @param evt PropertyChangeEvent
     */
    public void propertyChange(final PropertyChangeEvent evt) {
        this.refresh();
    }

    /** 
     * Return PatientBox pane.
     * @return Pane
     */
    public Pane asPane() {
        return patientPane;
    }

}
