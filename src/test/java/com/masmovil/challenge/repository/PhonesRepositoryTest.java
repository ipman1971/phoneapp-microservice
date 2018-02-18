package com.masmovil.challenge.repository;

import com.masmovil.challenge.domain.Phone;
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
public class PhonesRepositoryTest {

    @Autowired
    private PhonesRepository repository;

    @Test
    public void create() throws Exception {
        clearTable();
        Phone phone = createPhone();
        repository.create(phone.getReference(), phone);

        Phone onePhone = (Phone) repository.findOne(phone.getReference());

        assertThat(phone, is(equalTo(onePhone)));
    }

    @Test
    public void findOne() throws Exception {
        clearTable();
        Phone phone = createPhone();
        repository.create(phone.getReference(), phone);

        Phone onePhone = (Phone) repository.findOne(phone.getReference());

        assertThat(phone, is(equalTo(onePhone)));
    }

    @Test
    public void notFindOne() throws Exception {
        clearTable();
        Phone phone = createPhone();
        repository.create(phone.getReference(), phone);

        Phone onePhone =(Phone)repository.findOne(10);

        assertThat(onePhone,is(nullValue()));
    }

    @Test
    public void findAll() throws Exception {
        clearTable();
        Phone phone = createPhone();
        repository.create(phone.getReference(), phone);

        assertThat(repository.findAll().size(), is(equalTo(1)));
    }

    @Test
    public void update() throws Exception {
        clearTable();
        Phone phone = createPhone();
        repository.create(phone.getReference(), phone);


        phone.setDescription("bad phone");
        repository.update(phone.getReference(), phone);

        Phone onePhone = (Phone) repository.findOne(phone.getReference());

        assertThat(onePhone.getDescription(), is(equalTo("bad phone")));
    }

    @Test
    public void delete() throws Exception {
        clearTable();
        Phone phone = createPhone();
        repository.create(phone.getReference(), phone);

        repository.delete(phone.getReference());

        assertThat(repository.findAll().size(), is(equalTo(0)));

    }

    private Phone createPhone() {
        return new Phone(1,"iphone-7","good phone","./iphone7.png",600.35);
    }

    private void clearTable() {
        List<Phone> phoneList = repository.findAll();
        phoneList.stream().forEach(phone -> repository.delete(phone.getReference()));
    }

}
