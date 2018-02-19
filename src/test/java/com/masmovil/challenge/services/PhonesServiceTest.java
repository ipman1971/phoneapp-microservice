package com.masmovil.challenge.services;

import com.masmovil.challenge.DbUtilTest;
import com.masmovil.challenge.domain.Phone;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by jcorredera on 17/02/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PhonesServiceTest extends DbUtilTest {

    @Autowired
    private PhonesService phonesService;

    @Before
    public void setup() {
        removePhones();
    }

    @Test
    public void loadData() throws Exception {
        List<Phone> phones = createPhoneList(10,200.30);
        phonesService.loadData(phones);
        List<Phone> otherPhones = phonesService.findAll();
        assertThat(phones.size(),is(equalTo(otherPhones.size())));
        assertThat(phones.get(0),is(equalTo(otherPhones.get(0))));
    }

    @Test
    public void findAll() throws Exception {
        List<Phone> phones = createPhoneList(10, 345.70);
        phonesService.loadData(phones);
        List<Phone> otherPhones = phonesService.findAll();
        assertThat(phones.size(),is(equalTo(otherPhones.size())));
        assertThat(phones.get(0),is(equalTo(otherPhones.get(0))));
    }

    @Test
    public void findOne() throws  Exception {
        List<Phone> phones = createPhoneList(10, 180.90);
        phonesService.loadData(phones);
        Phone phone = phonesService.findOne(5);
        assertThat(phone.getReference(),is(equalTo(5)));
    }

}