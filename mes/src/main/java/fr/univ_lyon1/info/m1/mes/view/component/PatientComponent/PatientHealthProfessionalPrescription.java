package fr.univ_lyon1.info.m1.mes.view.component.PatientComponent;

import java.net.URL;
import fr.univ_lyon1.info.m1.mes.controller.PatientController;
import fr.univ_lyon1.info.m1.mes.model.Prescription;
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
import fr.univ_lyon1.info.m1.mes.utils.EasyAlert;
import io.github.palexdev.materialfx.builders.layout.BorderBuilder;
import io.github.palexdev.materialfx.controls.MFXButton;

public class PatientHealthProfessionalPrescription {

    private final PatientController controller;
    private final Prescription prescription;
    private final GridPane prescriptionPane = new GridPane();

    /**
     * Contructor PatientHealthProfessionalPrescription.
     * @param c PatientController
     * @param p Prescription
     */
    public PatientHealthProfessionalPrescription(final PatientController c, final Prescription p) {
        this.prescription = p;
        this.controller = c;
        getPrescriptions();
    }

    /**
     * Display Content.
     */
    void getPrescriptions() {

        //Content
        final Label content = new Label(this.prescription.getContent());
        final MFXButton delButton = new MFXButton("");
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
        delButton.setGraphic(deleteImage);

        this.prescriptionPane.add(delButton, 0, 0);
        this.prescriptionPane.add(content, 1, 0);
        

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
        this.prescriptionPane.setPadding(new Insets(10, 10, 10, 10));
        GridPane.setMargin(content, new Insets(0, 0, 0, 30));
        
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
     * @return Pane
     */
    public Pane asPane() {
        return prescriptionPane;
    }
}
