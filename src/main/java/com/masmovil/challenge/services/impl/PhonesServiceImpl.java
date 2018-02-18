package com.masmovil.challenge.services.impl;

import com.masmovil.challenge.domain.Phone;
import com.masmovil.challenge.repository.PhonesRepository;
import com.masmovil.challenge.services.PhonesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jcorredera on 17/02/18.
 */
@Service
public class PhonesServiceImpl implements PhonesService {

    @Autowired
    private PhonesRepository phonesRepository;

    @Override
    public void loadData(List<Phone> phoneList) {
        phoneList.stream().forEach(phone -> phonesRepository.create(phone.getReference(),phone));
    }

    @Override
    public List<Phone> findAll() {
        return phonesRepository.findAll();
    }

    @Override
    public Phone findOne(Integer reference) {
        return (Phone) phonesRepository.findOne(reference);
    }

}
