package org.academiadecodigo.roothless.model.dao;

import java.util.List;

/**
 * Created by codecadet on 24/03/17.
 */
public interface DAO<T> {

    void create(T t);
    T read(String tID);
    void update(T t);
    void delete(T t);
    List<T> listAll();
}
