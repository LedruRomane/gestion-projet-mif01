package fr.univ_lyon1.info.m1.mes.view;

import fr.univ_lyon1.info.m1.mes.controller.MainController;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainView {
    
    private Pane patients = new VBox();
    private Pane healthPro = new VBox();

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

        final HBox root = new HBox(10);

        root.getChildren().add(patientView.asPane());

        root.getChildren().add(healthProfessionalView.asPane());

        HBox.setHgrow(patients, Priority.SOMETIMES);
        HBox.setHgrow(healthPro, Priority.ALWAYS);

        // Everything's ready: add it to the scene and display it
        final Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();
    }
}
