package fr.univ_lyon1.info.m1.mes.types;

import fr.univ_lyon1.info.m1.mes.controller.patientSearch.BaseStrategy;
import fr.univ_lyon1.info.m1.mes.controller.patientSearch.Name;
import fr.univ_lyon1.info.m1.mes.controller.patientSearch.Ssid;

public enum PatientSearchStrategyType {
    NAME(Name.class),
    SSID(Ssid.class);

    private BaseStrategy c;

    PatientSearchStrategyType(final Class<?> c) {
        // this.c = c;
    }

    public BaseStrategy getStrategyClass() {
        return c;
    }

    public String toString() {
        return this.c.toString();
    }
}
