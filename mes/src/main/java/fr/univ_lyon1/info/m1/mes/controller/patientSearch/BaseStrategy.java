package fr.univ_lyon1.info.m1.mes.controller.patientSearch;

import java.util.List;

import fr.univ_lyon1.info.m1.mes.model.Patient;

public interface BaseStrategy {
    default List<Patient> search(final List<Patient> e, final String search) {
        return null;
    };
}
