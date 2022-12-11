package fr.univ_lyon1.info.m1.mes.types;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import fr.univ_lyon1.info.m1.mes.controller.patientSearch.BaseStrategy;

public enum PatientSearchStrategyType {
    NAME("fr.univ_lyon1.info.m1.mes.controller.patientSearch.Name"),
    SSID("fr.univ_lyon1.info.m1.mes.controller.patientSearch.Ssid"),
    PRESCRIPTIONS("fr.univ_lyon1.info.m1.mes.controller.patientSearch.Prescriptions");

    private BaseStrategy baseStrategy;

    PatientSearchStrategyType(final String strategyName) {
        try {
            Class<?> clazz = Class.forName(strategyName);
            Constructor<?> constructor = clazz.getConstructor();
            Object instance = constructor.newInstance();
            baseStrategy = (BaseStrategy) instance;
        } catch (InstantiationException | IllegalAccessException 
        | ClassNotFoundException | NoSuchMethodException 
        | SecurityException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * Return the strategy class.
     * @return BaseStrategy
     */
    public BaseStrategy getStrategyClass() {
        return baseStrategy;
    }

    /**
     * Return the String of the strategy.
     * @return String
     */
    public String toString() {
        return baseStrategy.toString();
    }
}
