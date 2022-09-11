package fr.univ_lyon1.info.m1.mes.controller;

import fr.univ_lyon1.info.m1.mes.model.MES;
import fr.univ_lyon1.info.m1.mes.view.JfxView;
import javafx.stage.Stage;

public class MainController {
    private final MES model;
    private final HealthProfessionalController healthController;
    private final PatientController patientController;

    public MainController(final MES mes) {
        this.model = mes;
        this.healthController = new HealthProfessionalController();
        this.patientController = new PatientController();

        // Init Views
        model.createExampleConfiguration();
        new JfxView(model, new Stage(),
                this, this.healthController, this.patientController,
                600, 600);
    }

    public void onClickButton() {
        System.out.println("Hey");
    }
}
