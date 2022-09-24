package fr.univ_lyon1.info.m1.mes.view;

import fr.univ_lyon1.info.m1.mes.controller.PatientController;
import fr.univ_lyon1.info.m1.mes.view.component.PatientComponent.PatientBox;
import fr.univ_lyon1.info.m1.mes.view.component.PatientComponent.PatientSelect;
import javafx.scene.control.SplitPane;

public class PatientView {

    private final PatientController controller;
    private final PatientBox patientBox;
    private final PatientSelect patientSelect;
    private final SplitPane pane = new SplitPane();

    public PatientView(final PatientController patientController) {

        this.controller = patientController;

        this.patientBox = new PatientBox(controller);
        this.patientSelect = new PatientSelect(controller);
        this.pane.getItems().addAll(
            patientBox.asPane(),
            patientSelect.asPane()
            );
        
        pane.setDividerPosition(0, 1 / (double) 2);
    

        pane.setStyle("-fx-border-color: gray;\n"
                + "-fx-border-insets: 5;\n"
                + "-fx-padding: 5;\n"
                + "-fx-border-width: 1;\n"
                + "-fx-border-radius: 10");
    }
    
    /** 
     * @return Pane
     */
    public SplitPane asPane() {
        return pane;
    }

}
