package fr.univ_lyon1.info.m1.mes.types;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import fr.univ_lyon1.info.m1.mes.controller.patientSearch.BaseStrategy;

public enum PatientSearchStrategyType {
    NAME("fr.univ_lyon1.info.m1.mes.controller.patientSearch.Name"),
    SSID("fr.univ_lyon1.info.m1.mes.controller.patientSearch.Ssid"),
    PRESCRIPTIONS("fr.univ_lyon1.info.m1.mes.controller.patientSearch.Prescriptions");

    private BaseStrategy c;

    PatientSearchStrategyType(final String strategyName) {
        try {
            Class<?> clazz = Class.forName(strategyName);
            Constructor<?> constructor = clazz.getConstructor();
            Object instance = constructor.newInstance();
            c = (BaseStrategy) instance;
        } catch (InstantiationException | IllegalAccessException 
        | ClassNotFoundException | NoSuchMethodException 
        | SecurityException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public BaseStrategy getStrategyClass() {
        return c;
    }

    public String toString() {
        return c.toString();
    }
}
