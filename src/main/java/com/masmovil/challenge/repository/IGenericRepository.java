package com.masmovil.challenge.repository;

import java.util.List;

/**
 * Created by jcorredera on 15/02/18.
 */
public interface IGenericRepository<KEY, ENTITY> {

    ENTITY findOne(final KEY key);

    List<ENTITY> findAll();

    void create(final KEY key, final ENTITY entity);

    ENTITY update(final KEY key, final ENTITY entity);

    void delete(final KEY key);

}
