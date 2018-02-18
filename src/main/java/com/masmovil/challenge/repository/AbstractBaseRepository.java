package com.masmovil.challenge.repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Created by jcorredera on 15/02/18.
 */
public abstract class AbstractBaseRepository<KEY, ENTITY> implements IGenericRepository<KEY, ENTITY> {

    private Map<KEY, ENTITY> table;

    public AbstractBaseRepository() {
        table = new ConcurrentHashMap<KEY, ENTITY>();
    }

    @Override
    public ENTITY findOne(KEY key) {
        if(table.containsKey(key)) {
            return table.get(key);
        }
        return null;
    }

    @Override
    public List<ENTITY> findAll() {
        return Collections.unmodifiableList(table.values().stream().collect(Collectors.toList()));
    }

    @Override
    public void create(KEY key, ENTITY entity) {
        table.putIfAbsent(key, entity);
    }

    @Override
    public ENTITY update(KEY key, ENTITY entity) {
        if (table.containsKey(key)) {
            table.put(key, entity);
        }
        else {
            create(key,entity);
        }
        return entity;
    }

    @Override
    public void delete(KEY key) {
        if (table.containsKey(key)) {
            table.remove(key);
        }
    }

}
