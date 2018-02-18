package com.masmovil.challenge.services;

import com.masmovil.challenge.domain.Phone;
import com.masmovil.challenge.repository.PhonesRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by jcorredera on 17/02/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PhonesServiceTest {

    @Autowired
    PhonesService phonesService;
    @Autowired
    PhonesRepository phonesRepository;

    @Test
    public void loadData() throws Exception {
        clearTable();
        List<Phone> phones = createPhoneList();
        phonesService.loadData(phones);
        List<Phone> otherPhones = phonesService.findAll();
        assertThat(phones.size(),is(equalTo(otherPhones.size())));
        assertThat(phones.get(0),is(equalTo(otherPhones.get(0))));
    }

    @Test
    public void findAll() throws Exception {
        clearTable();
        List<Phone> phones = createPhoneList();
        phonesService.loadData(phones);
        List<Phone> otherPhones = phonesService.findAll();
        assertThat(phones.size(),is(equalTo(otherPhones.size())));
        assertThat(phones.get(0),is(equalTo(otherPhones.get(0))));
    }

    @Test
    public void findOne() throws  Exception {
        clearTable();
        List<Phone> phones = createPhoneList();
        phonesService.loadData(phones);
        Phone phone = phonesService.findOne(5);
        assertThat(phone.getReference(),is(equalTo(5)));
    }

    private List<Phone> createPhoneList() {
        List<Phone> phones=new ArrayList<>();
        for(int i=0; i<10;i++) {
            Phone phone=new Phone(i+1,"model-"+i,"description for model-"+i,"./image"+i+".png",600.35 + i);
            phones.add(phone);
        }
        return phones;
    }

    private void clearTable() {
        List<Phone> phoneList = phonesService.findAll();
        phoneList.stream().forEach(phone -> phonesRepository.delete(phone.getReference()));
    }

}