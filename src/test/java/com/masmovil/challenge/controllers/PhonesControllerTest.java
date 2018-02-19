package com.masmovil.challenge.controllers;

import com.masmovil.challenge.DbUtilTest;
import com.masmovil.challenge.domain.Phone;
import com.masmovil.challenge.services.PhonesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

/**
 * Created by jcorredera on 17/02/18.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = PhonesController.class, secure = false)
public class PhonesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PhonesService service;

    @Test
    public void findAll() throws Exception {
        when(service.findAll()).thenReturn(createPhoneList());
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/mm/v1/phones/")
                .contentType(MediaType.APPLICATION_JSON);

        assertThat(HttpStatus.OK.value(),is(equalTo(mockMvc.perform(requestBuilder).andReturn().getResponse().getStatus())));
    }

    @Test
    public void findOne() throws Exception {
        when(service.findOne(anyInt())).thenReturn(createPhone());
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/mm/v1/phones/1")
                .contentType(MediaType.APPLICATION_JSON);

        assertThat(HttpStatus.OK.value(),is(equalTo(mockMvc.perform(requestBuilder).andReturn().getResponse().getStatus())));
    }

    private List<Phone> createPhoneList() {
        List<Phone> phones=new ArrayList<>();
        for(int i=0; i<10;i++) {
            Phone phone=new Phone(i+1,"model-"+i,"description for model-"+i,"./image"+i+".png",600.35 + i);
            phones.add(phone);
        }
        return phones;
    }

    private Phone createPhone() {
        return new Phone(1,"model-1","description for model-1","./image1.png",600.35);
    }

}