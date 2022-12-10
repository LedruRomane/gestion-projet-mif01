package fr.univ_lyon1.info.m1.mes.dao;

/**
 * class abstract for implement DAO in project.
 * @param <T> any Type
 */
public abstract class Dao<T> {
    
    /**
     * find an object with his id.
     * @param id String
     * @return T
     */
    public abstract T find(String id);
    
}

