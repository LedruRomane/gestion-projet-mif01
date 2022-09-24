package fr.univ_lyon1.info.m1.mes.controller;

import fr.univ_lyon1.info.m1.mes.model.MES;
import fr.univ_lyon1.info.m1.mes.view.MainView;
import javafx.stage.Stage;

public class MainController {
    private final MES model;
    private final HealthProfessionalController healthController;
    private final PatientController patientController;

    public MainController(final MES mes) {
        this.model = mes;
        this.healthController = new HealthProfessionalController(mes);
        this.patientController = new PatientController(mes);

        // Init Views
        model.createExampleConfiguration();
        
        new MainView(new Stage(),
                this, this.healthController.getView(),
                this.patientController.getView(),
                1000, 600);
    }

    public void onClickButton() {
        System.out.println("Hey");
    }
}
