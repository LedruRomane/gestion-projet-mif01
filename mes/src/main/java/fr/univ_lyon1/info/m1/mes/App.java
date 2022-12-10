package fr.univ_lyon1.info.m1.mes;

import fr.univ_lyon1.info.m1.mes.controller.MainController;
import fr.univ_lyon1.info.m1.mes.controller.HealthProfessionalController;
import fr.univ_lyon1.info.m1.mes.controller.PatientController;
import fr.univ_lyon1.info.m1.mes.model.MES;
import fr.univ_lyon1.info.m1.mes.view.HealthProfessionalView;
import fr.univ_lyon1.info.m1.mes.view.MainView;
import fr.univ_lyon1.info.m1.mes.view.PatientView;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class for the application (structure imposed by JavaFX).
 */
public class App extends Application {

    /**
     * With javafx, start() is called when the application is launched.
     */
    @Override
    public void start(final Stage stage) throws Exception {
        startApp();
        startApp();
    }

    public void startApp() {
        final MES app = MES.getInstance();
        // Instanciate view and controller
    
        MainController mController = new MainController(app);
        HealthProfessionalController healthController = new HealthProfessionalController(app);
        PatientController patientController = new PatientController(app);

        HealthProfessionalView healthView = new HealthProfessionalView(healthController);
        PatientView patientView = new PatientView(patientController);

        healthController.setView(healthView);
        patientController.setView(patientView);

        MainView mainVue = new MainView(new Stage(),
        mController, healthController.getView(),
        patientController.getView(),
        1000, 600);
    }


    /**
     * A main method in case the user launches the application using
     * App as the main class.
     * @param args Arguments inline code.
     */
    public static void main(final String[] args) {
        Application.launch(args);
    }
}
