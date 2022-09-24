package fr.univ_lyon1.info.m1.mes.view;

import fr.univ_lyon1.info.m1.mes.controller.PatientController;
import fr.univ_lyon1.info.m1.mes.view.component.PatientComponent.PatientBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class PatientView {

    private final PatientController controller;
    private final PatientBox patientBox;
    private final Pane pane = new HBox();

    public PatientView(final PatientController patientController) {

        this.controller = patientController;
        this.patientBox = new PatientBox(patientController);
        
        this.pane.getChildren().add(patientBox.asPane());

        pane.setStyle("-fx-border-color: gray;\n"
                + "-fx-border-insets: 5;\n"
                + "-fx-padding: 5;\n"
                + "-fx-border-width: 1;\n");
    }
    
    /** 
     * @return Pane
     */
    public Pane asPane() {
        return pane;
    }

}
