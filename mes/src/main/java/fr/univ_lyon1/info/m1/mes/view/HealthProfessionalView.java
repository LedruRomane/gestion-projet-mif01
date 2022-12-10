package fr.univ_lyon1.info.m1.mes.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.List;

import fr.univ_lyon1.info.m1.mes.controller.HealthProfessionalController;
import fr.univ_lyon1.info.m1.mes.model.Patient;
import fr.univ_lyon1.info.m1.mes.model.HealthProfessional;
import fr.univ_lyon1.info.m1.mes.view.component.HealthProfessionalComponent.HealthProfessionalBox;
import fr.univ_lyon1.info.m1.mes.view.component.HealthProfessionalComponent.HealthProfessionalSelect;
import javafx.scene.control.SplitPane;

public class HealthProfessionalView implements PropertyChangeListener {

    private final HealthProfessionalController controller;
    private final HealthProfessionalBox healthProfessionalBox;
    private final HealthProfessionalSelect healthProfessionalSelect;
    private final SplitPane pane = new SplitPane();

    /**
     * Constructor HealthProfessionalView.
     * @param healthProfessionalController HealthProfessionalController
     */
    public HealthProfessionalView(
            final HealthProfessionalController healthProfessionalController) {
        
        this.controller = healthProfessionalController;
        this.healthProfessionalBox = new HealthProfessionalBox(healthProfessionalController, null);
        this.healthProfessionalSelect = new HealthProfessionalSelect(healthProfessionalController);
        this.healthProfessionalSelect.updateHealthProfessional(
                controller.getHealthProfessionals());
               
        loadCss();
        this.pane.getItems().addAll(
                healthProfessionalBox.asPane(),
                healthProfessionalSelect.asPane());
    }

    /**
     * Update View on Listended objects.
     * @param evt PropertyChangeEvent
     */
    @SuppressWarnings("unchecked")
    public void propertyChange(final PropertyChangeEvent evt) {
        if (evt.getNewValue() instanceof List<?>) {
            for (Object o : (List<?>) evt.getNewValue()) {
                if (!(o instanceof HealthProfessional)) {
                    return;
                }
            }
        }
        this.healthProfessionalSelect.updateHealthProfessional(
            (List<HealthProfessional>) evt.getNewValue());
    }

    /**
     * Set a HealthProfessional to healthProfessionalBox component when one is selected.
     * @param hp HealthProfessional
     */
    public void selectHealthProfessionnal(final HealthProfessional hp) {
        this.healthProfessionalBox.setHealthProfessional(hp);
    }

    /**
     * Set a Patient to healthProfessionalBox component when one is selected.
     * @param p Patient
     */
    public void selectPatient(final Patient p) {
        this.healthProfessionalBox.setPatient(p);
    }

    /**
     * @return HealthProfessionalBox component.
     */
    public HealthProfessionalBox getHealthProfessionalBox() {
        return this.healthProfessionalBox;
    }

    /**
     * @return HealthProfessionalController
     */
    public HealthProfessionalController getController() {
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
     * Return HealthProfessionalView Pane.
     * @return SplitPane
     */
    public SplitPane asPane() {
        return pane;
    }

}
