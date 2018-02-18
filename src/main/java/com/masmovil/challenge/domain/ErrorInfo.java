package com.masmovil.challenge.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by jcorredera on 17/02/18.
 */
@ApiModel(description = "model representation for Error Information")
public class ErrorInfo {

    public final String message;
    public final String detail;

    public ErrorInfo(Exception ex, String detail) {
        this.message = ex.getLocalizedMessage();
        this.detail = detail;
    }

    @ApiModelProperty(name = "message", dataType = "string", required = true)
    public String getMessage() {
        return message;
    }

    @ApiModelProperty(name = "detail", dataType = "string", required = true)
    public String getDetail() {
        return detail;
    }

}
