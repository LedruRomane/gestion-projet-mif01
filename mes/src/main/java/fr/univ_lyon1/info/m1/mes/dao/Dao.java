package fr.univ_lyon1.info.m1.mes.dao;

public abstract class Dao<T> {
    
    /**
     * find an object with his id.
     * @param id
     * @return
     */
    public abstract T find(Integer id);
    
}

