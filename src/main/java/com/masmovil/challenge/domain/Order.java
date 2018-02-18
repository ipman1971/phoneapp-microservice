package com.masmovil.challenge.domain;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

/**
 * Created by jcorredera on 17/02/18.
 */
public class Order extends ResourceSupport {

    private Integer reference;
    private String name;
    private String surname;
    private String email;
    private List<Integer> productList;
    private Double amount;

    public Order(Integer reference, String name, String surname, String email, List<Integer> phonesReferenceList, Double amount) {
        this.reference = reference;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.productList = phonesReferenceList;
        this.amount = amount;
    }

    public Order(Integer reference, String name, String surname, String email, List<Integer> phonesReferenceList) {
        this(reference,name,surname,email,phonesReferenceList,0.0);
    }

    public Order() {;}

    public Integer getReference() {
        return reference;
    }

    public void setReference(Integer reference) {
        this.reference = reference;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Integer> getProductList() {
        return productList;
    }

    public void setProductList(List<Integer> productList) {
        this.productList = productList;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equal(reference, order.reference) &&
                Objects.equal(name, order.name) &&
                Objects.equal(surname, order.surname) &&
                Objects.equal(email, order.email) &&
                Objects.equal(productList, order.productList) &&
                Objects.equal(amount, order.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(reference, name, surname, email, productList);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).
                add("reference",reference).
                add("name", name).
                add("surname", surname).
                add("email", email).
                add("productList", productList).
                add("amount",amount).
                omitNullValues().
                toString();
    }

}
