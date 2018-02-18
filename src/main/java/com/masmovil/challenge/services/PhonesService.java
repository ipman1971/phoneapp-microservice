package com.masmovil.challenge.services;

import com.masmovil.challenge.domain.Phone;

import java.util.List;

/**
 * Created by jcorredera on 17/02/18.
 */
public interface PhonesService {

    public void loadData(List<Phone> phoneList);

    public List<Phone> findAll();

    public Phone findOne(Integer reference);
}
