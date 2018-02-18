package com.masmovil.challenge.component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * Created by jcorredera on 17/02/18.
 */
@Component
@Primary
public class MMCustomObjectMapper extends ObjectMapper {

    public MMCustomObjectMapper() {
        setSerializationInclusion(JsonInclude.Include.NON_NULL);
        configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        enable(SerializationFeature.INDENT_OUTPUT);
    }
}
