package fr.univ_lyon1.info.m1.mes.controller;

import fr.univ_lyon1.info.m1.mes.model.HealthProfessional;
import fr.univ_lyon1.info.m1.mes.model.MES;
import fr.univ_lyon1.info.m1.mes.types.HealthProfessionalType;
import fr.univ_lyon1.info.m1.mes.view.HealthProfessionalView;

public class HealthProfessionalController {
    private final MES model;
    private final HealthProfessionalView view;

    private HealthProfessional currentPro;

    public HealthProfessionalController(final MES mes) {
        this.currentPro = null;
        this.model = mes;
        this.view = new HealthProfessionalView(this, model);
        this.model.addPropertyChangeListener("healthList", this.view);
    }

    public HealthProfessional createHealthProfessional(
            final HealthProfessionalType model,
            final String name) {
        return this.model.createHealthProfessional(model, name);
    }

    public void selectHealthProfessional(final HealthProfessional pro) {
        this.currentPro = pro;
        System.out.println(this.currentPro);
    }

    public HealthProfessionalView getView() {
        return this.view;
    }
}
