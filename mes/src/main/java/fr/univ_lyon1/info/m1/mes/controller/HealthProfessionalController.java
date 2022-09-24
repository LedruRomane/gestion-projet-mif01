package fr.univ_lyon1.info.m1.mes.controller;

import fr.univ_lyon1.info.m1.mes.model.MES;
import fr.univ_lyon1.info.m1.mes.view.HealthProfessionalView;

public class HealthProfessionalController {
    private final MES model;
    private final HealthProfessionalView view;

    public HealthProfessionalController(final MES mes) {
        this.model = mes;
        this.view = new HealthProfessionalView(this);
    }

    public HealthProfessionalView getView() {
        return this.view;
    }
}
