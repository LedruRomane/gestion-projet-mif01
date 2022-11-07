package fr.univ_lyon1.info.m1.mes.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.List;

import fr.univ_lyon1.info.m1.mes.controller.PatientController;
import fr.univ_lyon1.info.m1.mes.model.MES;
import fr.univ_lyon1.info.m1.mes.model.Patient;
import fr.univ_lyon1.info.m1.mes.view.component.PatientComponent.PatientBox;
import fr.univ_lyon1.info.m1.mes.view.component.PatientComponent.PatientSelect;
import javafx.scene.control.SplitPane;

public class PatientView implements PropertyChangeListener {

    private final PatientController controller;
    private final PatientBox patientBox;
    private final PatientSelect patientSelect;
    private final SplitPane pane = new SplitPane();

    /**
     * Constructor PatientView.
     * @param patientController PatientController
     * @param mes MES
     */
    public PatientView(final PatientController patientController, final MES mes) {

        this.controller = patientController;
        this.patientBox = new PatientBox(controller, null);
        this.patientSelect = new PatientSelect(controller);
        this.patientSelect.updatePatient(mes.getPatients());

        loadCss();
        this.pane.getItems().addAll(patientBox.asPane(), patientSelect.asPane());

    }

    /**
     * Update View on Listended objects.
     * @param evt PropertyChangeEvent
     */
    // TODO: envoyer mail prof
    public void propertyChange(final PropertyChangeEvent evt) {
        this.patientSelect.updatePatient((List<Patient>) evt.getNewValue());
    }

    /**
     * Set to patientBox component a Patient selected.
     * @param p Patient
     */
    public void selectPatient(final Patient p) {
        this.patientBox.setPatient(p);
    }

    /**
     * get PatientBox component.
     * @return PatientBox
     */
    public PatientBox getPatientBox() {
        return this.patientBox;
    }

    /**
     * get PatientController.
     * @return PatientController
     */
    public PatientController getController() {
        return this.controller;
    }
    
    /**
     * Display CSS on this pane.
     */
    public void loadCss() {
        URL url = this.getClass().getResource("/css/main.css");
        if (url == null) {
            System.out.println("Resource not found. Aborting.");
            System.exit(-1);
        }
        String css = url.toExternalForm();
        pane.getStylesheets().add(css);
        pane.setDividerPosition(0, 1 / (double) 2);
        pane.getStyleClass().add("colorBackground");
    }

    /**
     * Return PatientView Pane.
     * @return SplitPane
     */
    public SplitPane asPane() {
        return pane;
    }

}
