package fr.univ_lyon1.info.m1.mes.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import fr.univ_lyon1.info.m1.mes.controller.HealthProfessionalController;
import fr.univ_lyon1.info.m1.mes.model.MES;
import fr.univ_lyon1.info.m1.mes.model.HealthProfessional;
import fr.univ_lyon1.info.m1.mes.view.component.HealthProfessionalComponent.HealthProfessionalBox;
import fr.univ_lyon1.info.m1.mes.view.component.HealthProfessionalComponent.HealthProfessionalSelect;
import javafx.scene.control.SplitPane;

public class HealthProfessionalView implements PropertyChangeListener {

    private final HealthProfessionalController controller;
    private final HealthProfessionalBox healthProfessionalBox;
    private final HealthProfessionalSelect healthProfessionalSelect;
    private final SplitPane pane = new SplitPane();

    public HealthProfessionalView(
            final HealthProfessionalController healthProfessionalController,
            final MES mes) {

        this.controller = healthProfessionalController;
        this.healthProfessionalBox = new HealthProfessionalBox(healthProfessionalController);
        this.healthProfessionalSelect = new HealthProfessionalSelect(healthProfessionalController);

        this.pane.getItems().addAll(
                healthProfessionalBox.asPane(),
                healthProfessionalSelect.asPane());

        pane.setDividerPosition(0, 1 / (double) 2);

        pane.setStyle("-fx-border-color: gray;\n"
                + "-fx-border-insets: 5;\n"
                + "-fx-padding: 5;\n"
                + "-fx-border-width: 1;\n"
                + "-fx-border-radius: 10");
                // Update Pro list
                this.healthProfessionalSelect.updateHealthProfessional(
                    mes.getHealthProfessionals());
    }

    /**
     * @param prescription
     */
    void prescribe(final String prescription) {
    }

    /**
     * Mise à jour de la vue lors d'évènements sur des objets écoutés.
     */
    public void propertyChange(final PropertyChangeEvent evt) {
        //TODO : check type evt.getNewValue()
        this.healthProfessionalSelect.updateHealthProfessional(
            (List<HealthProfessional>) evt.getNewValue());
    }

    /**
     * @return Pane
     */
    public SplitPane asPane() {
        return pane;
    }

}
