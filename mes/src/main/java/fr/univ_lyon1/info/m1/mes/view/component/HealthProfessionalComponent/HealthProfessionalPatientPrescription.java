package fr.univ_lyon1.info.m1.mes.view.component.HealthProfessionalComponent;

import java.net.URL;
import fr.univ_lyon1.info.m1.mes.controller.HealthProfessionalController;
import fr.univ_lyon1.info.m1.mes.model.Prescription;
import fr.univ_lyon1.info.m1.mes.utils.EasyAlert;
import io.github.palexdev.materialfx.builders.layout.BorderBuilder;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

public class HealthProfessionalPatientPrescription {

    private final HealthProfessionalController controller;
    private final GridPane prescriptionGridPane = new GridPane();
    private final Prescription prescription;
    
    /**
     * Constructor HealthProfessionalPatientPrescription Component.
     * @param hpController HealthProfessionalController
     * @param prescription Prescription
     */
    public HealthProfessionalPatientPrescription(
        final HealthProfessionalController hpController,
        final Prescription prescription) {
        
        this.controller = hpController;
        this.prescription = prescription;
        getPatientPrescriptions();
    }

    /**
     * Display Content.
     */
    private void getPatientPrescriptions() {

        //Content
        final URL imageURL = this.getClass().getResource("/img/delete.png");
        if (imageURL == null) {
            System.out.println("Resource not found. Aborting. Img ressource null");
        }
        final Image image = new Image(imageURL.toExternalForm(),
                15,
                15,
                false,
                true
            );
        final ImageView deleteImage = new ImageView(image);
        final Label content = new Label(this.prescription.getContent());
        MFXButton delButton = new MFXButton("");
        delButton.setGraphic(deleteImage);

        this.prescriptionGridPane.add(delButton, 0, 0);
        this.prescriptionGridPane.add(content, 1, 0);
        

        //Css Stuff
        BorderBuilder border = new BorderBuilder();
        border.addFill(
            Paint.valueOf("#dc0f6c"),
            BorderStrokeStyle.SOLID,
            new CornerRadii(5),
            new BorderWidths(1, 1, 1, 1),
            new Insets(0, 0, 0, 0)
        );
        delButton.setBorder(border.get());
        GridPane.setMargin(content, new Insets(0, 0, 0, 30));
        this.prescriptionGridPane.setPadding(new Insets(10, 10, 10, 10));

        //Buttons & Events
        delButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                EasyAlert.alertPromptYesNo("Attention !",
                "Êtes-vous sûr de vouloir supprimer cette prescription ? ",
                new Runnable() {
                    @Override
                    public void run() {
                        controller.deletePrescription(prescription);
                    }
                });
            }
        });
    }

    /**
     * Return HealthProfessionalPatientPrescription Pane.
     * @return Pane
     */
    public Pane asPane() {
        return this.prescriptionGridPane;
    }
}
