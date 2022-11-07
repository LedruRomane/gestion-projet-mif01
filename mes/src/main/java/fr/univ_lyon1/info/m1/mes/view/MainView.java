package fr.univ_lyon1.info.m1.mes.view;

import fr.univ_lyon1.info.m1.mes.controller.MainController;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.stage.Stage;


public class MainView {
    
    private final Tab patientTab;
    private final Tab healthProTab;
    private final TabPane root;

    private final MainController mainController;

    /**
     * Constructor for the main view of the application.
     * @param stage Stage
     * @param mainController MainController
     * @param healthProfessionalView HealthProfessionalView
     * @param patientView PatientView
     * @param width int
     * @param height int
     */
    public MainView(
        final Stage stage,
        final MainController mainController, 
        final HealthProfessionalView healthProfessionalView,
        final PatientView patientView,
        final int width, final int height) {

        this.mainController = mainController;
        
        this.patientTab = new Tab("Patient");
        this.healthProTab = new Tab("HealthProfessional");
        this.patientTab.setContent(patientView.asPane());
        this.healthProTab.setContent(healthProfessionalView.asPane());
        
        this.root = new TabPane(patientTab, healthProTab);
        this.root.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

        final Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.setTitle("Mon espace santé");
        stage.show();
    }

    /**
     * Return mainController.
     * @return MainController
     */
    public MainController getController() {
        return this.mainController;
    }
    
}
