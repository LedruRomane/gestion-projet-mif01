package fr.univ_lyon1.info.m1.mes;

import fr.univ_lyon1.info.m1.mes.controller.MainController;
import fr.univ_lyon1.info.m1.mes.model.MES;
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
        final MES app = MES.getInstance();
        new MainController(app);
        new MainController(app);
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
