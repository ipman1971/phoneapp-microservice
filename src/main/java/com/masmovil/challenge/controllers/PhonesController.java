package com.masmovil.challenge.controllers;

import com.masmovil.challenge.domain.Phone;
import com.masmovil.challenge.services.PhonesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by jcorredera on 17/02/18.
 */
@RestController
@RequestMapping(value = "/mm/v1/phones")
@Api(value = "Phones", description = "API for Phones")
public class PhonesController extends AbstractControllerHandler {

    @Autowired
    PhonesService phonesService;

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get all phones availables in catalog",nickname = "phones-catalog")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @ResponseBody
    public List<Phone> findAll() {
        List<Phone> phones = phonesService.findAll();
        phones.stream().forEach(phone -> {
            phone.removeLinks();
            phone.add(linkTo(PhonesController.class).slash(phone.getReference()).withSelfRel());
        });
        return phones;
    }

    @RequestMapping(value = "/{reference}", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get all info for phone",nickname = "find-phone")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @ResponseBody
    public Phone findOne(@PathVariable Integer reference, HttpServletRequest request,
                            HttpServletResponse response) {
        Phone phone = phonesService.findOne(reference);
        phone.removeLinks();
        phone.add(linkTo(PhonesController.class).slash(phone.getReference()).withSelfRel());
        return phone;
    }

}
