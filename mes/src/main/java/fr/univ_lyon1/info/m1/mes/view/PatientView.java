package fr.univ_lyon1.info.m1.mes.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import fr.univ_lyon1.info.m1.mes.controller.PatientController;
import fr.univ_lyon1.info.m1.mes.model.MES;
import fr.univ_lyon1.info.m1.mes.model.Patient;
import fr.univ_lyon1.info.m1.mes.view.component.PatientComponent.PatientBox;
import fr.univ_lyon1.info.m1.mes.view.component.PatientComponent.PatientSelect;
import javafx.scene.control.SplitPane;

public class PatientView implements PropertyChangeListener {

    private final PatientController controller;
    private PatientBox patientBox;
    private final PatientSelect patientSelect;
    private final SplitPane pane = new SplitPane();

    public PatientView(final PatientController patientController, final MES mes) {

        this.controller = patientController;
        
        this.patientBox = new PatientBox(controller, null);
        this.patientSelect = new PatientSelect(controller);
        this.pane.getItems().addAll(patientBox.asPane(), patientSelect.asPane());

        pane.setDividerPosition(0, 1 / (double) 2);

        pane.setStyle("-fx-border-color: gray;\n"
                + "-fx-border-insets: 5;\n"
                + "-fx-padding: 5;\n"
                + "-fx-border-width: 1;\n"
                + "-fx-border-radius: 10");
        this.patientSelect.updatePatient(
                mes.getPatients());
    }

    /**
     * Mise à jour de la vue lors d'évènements sur des objets écoutés.
     */
    public void propertyChange(final PropertyChangeEvent evt) {
        this.patientSelect.updatePatient((List<Patient>) evt.getNewValue());
    }

    /**
     * 
     * @return
     */
    public void selectPatient(final Patient p) {
        this.patientBox.setPatient(p);
    }

    /**
     * @return PatientBox
     */
    public PatientBox getPatientBox() {
        return this.patientBox;
    }

    /**
     * @return Pane
     */
    public SplitPane asPane() {
        return pane;
    }

}
