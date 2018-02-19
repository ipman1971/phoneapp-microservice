package com.masmovil.challenge.repository;

import com.masmovil.challenge.DbUtilTest;
import com.masmovil.challenge.domain.Phone;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by jcorredera on 15/02/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PhonesRepositoryTest extends DbUtilTest {

    @Autowired
    private PhonesRepository repository;

    @Before
    public void setup() {
        removePhones();
    }

    @Test
    public void create() throws Exception {
        Phone phone = createPhone(1,200.25);
        repository.create(phone.getReference(), phone);

        Phone onePhone = (Phone) repository.findOne(phone.getReference());

        assertThat(phone, is(equalTo(onePhone)));
    }

    @Test
    public void findOne() throws Exception {
        Phone phone = createPhone(1, 200.25);
        repository.create(phone.getReference(), phone);

        Phone onePhone = (Phone) repository.findOne(phone.getReference());

        assertThat(phone, is(equalTo(onePhone)));
    }

    @Test
    public void notFindOne() throws Exception {
        Phone phone = createPhone(1, 220.35);
        repository.create(phone.getReference(), phone);

        Phone onePhone =(Phone)repository.findOne(10);

        assertThat(onePhone,is(nullValue()));
    }

    @Test
    public void findAll() throws Exception {
        Phone phone = createPhone(1,345.56);
        repository.create(phone.getReference(), phone);

        assertThat(repository.findAll().size(), is(equalTo(1)));
    }

    @Test
    public void update() throws Exception {
        Phone phone = createPhone(1, 220.50);
        repository.create(phone.getReference(), phone);


        phone.setPrice(300.20);
        repository.update(phone.getReference(), phone);

        Phone onePhone = (Phone) repository.findOne(phone.getReference());

        assertThat(onePhone.getPrice(), is(equalTo(300.20)));
    }

    @Test
    public void delete() throws Exception {
        Phone phone = createPhone(2,500.70);
        repository.create(phone.getReference(), phone);

        repository.delete(phone.getReference());

        assertThat(repository.findAll().size(), is(equalTo(0)));
    }

}
