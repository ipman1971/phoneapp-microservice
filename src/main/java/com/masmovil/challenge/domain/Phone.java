package com.masmovil.challenge.domain;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by jcorredera on 15/02/18.
 */
@ApiModel(description = "model representation for Phone")
public class Phone extends ResourceSupport {

    private Integer reference;
    private String model;
    private String description;
    private String image;
    private Double price;

    public Phone(Integer reference,String model, String description, String image, Double price) {
        this.reference = reference;
        this.model = model;
        this.description = description;
        this.image = image;
        this.price = price;
    }

    public Phone() {;}

    @ApiModelProperty(name = "reference", dataType = "integer", required = true)
    public Integer getReference() {
        return this.reference;
    }

    public void setReference(Integer reference) {
        this.reference = reference;
    }

    @ApiModelProperty(name = "model", dataType = "string", required = true)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @ApiModelProperty(name = "description", dataType = "string", required = true)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ApiModelProperty(name = "image", dataType = "string", required = true)
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @ApiModelProperty(name = "price", dataType = "decimal", required = true)
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Phone)) return false;
        Phone phone = (Phone) obj;
        return Objects.equal(reference, phone.reference) &&
                Objects.equal(model, phone.model) &&
                Objects.equal(description, phone.description) &&
                Objects.equal(image, phone.image) &&
                Objects.equal(price, phone.price);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(reference, model, description, image, price);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).
                add("reference", reference).
                add("model", model).
                add("description", description).
                add("image", image).
                add("price", price).
                omitNullValues().
                toString();
    }

}
