package com.masmovil.challenge.controllers;

import com.masmovil.challenge.domain.ErrorInfo;
import com.masmovil.challenge.exceptions.DataFormatException;
import com.masmovil.challenge.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by jcorredera on 17/02/18.
 */
public abstract class AbstractControllerHandler {

    protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataFormatException.class)
    public @ResponseBody
    ErrorInfo handleDataStoreException(DataFormatException ex, WebRequest request, HttpServletResponse response) {
        LOG.error("Problems in data request transformation : {}", ex.getMessage());
        return new ErrorInfo(ex, "problems in data request transformation");
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public @ResponseBody
    ErrorInfo handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request, HttpServletResponse response) {
        LOG.error("Resource not found: {}",ex.getMessage());
        return new ErrorInfo(ex, "resource not found");
    }

}
