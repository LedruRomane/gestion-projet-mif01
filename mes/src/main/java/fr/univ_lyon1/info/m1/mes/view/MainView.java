package fr.univ_lyon1.info.m1.mes.view;

import fr.univ_lyon1.info.m1.mes.controller.MainController;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.stage.Stage;

public class MainView {
    
    private Tab patientTab = new Tab("Patient");
    private Tab healthProTab = new Tab("HealthProfessional");

    private final MainController mainController;

    /**
     * Create the main view of the application.
     */
    public MainView(final Stage stage,
            final MainController mainController, 
            final HealthProfessionalView healthProfessionalView,
            final PatientView patientView,
            final int width, final int height) {

        this.mainController = mainController;
        // Name of window
        stage.setTitle("Mon Espace Santé");

        patientTab.setContent(patientView.asPane());
        healthProTab.setContent(healthProfessionalView.asPane());

        final TabPane root = new TabPane(patientTab, healthProTab);
        root.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

        // Everything's ready: add it to the scene and display it
        final Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();
    }
}
