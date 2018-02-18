package com.masmovil.challenge.component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.masmovil.challenge.domain.Phone;
import com.masmovil.challenge.services.PhonesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by jcorredera on 17/02/18.
 */
@Component
public class MMPhoneAppStartupRunner implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(MMPhoneAppStartupRunner.class);

    @Autowired
    PhonesService phonesService;

    @Override
    public void run(String... args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Phone>> typeReference = new TypeReference<List<Phone>>(){};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/json/phones.json");
        try {
            List<Phone> phonesList = mapper.readValue(inputStream,typeReference);
            phonesService.loadData(phonesList);
            if(LOGGER.isInfoEnabled()) {
                LOGGER.info("phones file loaded in database");
            }
        } catch (IOException e){
            LOGGER.error("Unable to load phones file: {}",e.getMessage());
        }
    }


}
