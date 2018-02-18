package com.masmovil.challenge.repository.impl;

import com.masmovil.challenge.domain.Phone;
import com.masmovil.challenge.repository.AbstractBaseRepository;
import com.masmovil.challenge.repository.PhonesRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jcorredera on 15/02/18.
 */
@Repository
public class PhonesRepositoryImpl extends AbstractBaseRepository<Integer, Phone> implements PhonesRepository<Integer, Phone> {
}
