package com.klymenko.proxyservice.controller;

import org.apache.thrift.transport.TTransportException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ControllerExceptionHandler {

    @ExceptionHandler(value = {NumberFormatException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, String> handleBadRequest(NumberFormatException e) {
        return new HashMap<String, String>() {
            {
                this.put("error", e.getClass().getName());
                this.put("message", "Please provide a correct value, 64 bit signed int as an input argument!");
            }
        };
    }

    @ExceptionHandler(value = {TTransportException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, String> handleConnectException(TTransportException e) {
        return new HashMap<String, String>() {
            {
                this.put("error", e.getClass().getName());
                this.put("message", "It seems like fibonacciserver can't answer right now. We're on it. Please try in a few minutes!");
            }
        };
    }

}
