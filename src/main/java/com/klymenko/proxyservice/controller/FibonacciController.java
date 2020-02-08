package com.klymenko.proxyservice.controller;

import com.klymenko.proxyservice.service.FibonacciService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FibonacciController {

    private FibonacciService fibonacciService;

    public FibonacciController(FibonacciService fibonacciService){
        this.fibonacciService = fibonacciService;
    }

    @GetMapping("fibonacci/{number}")
    public List<Long> fibonacci(@PathVariable("number") Long number) {
        return fibonacciService.getFibonacci(number);
    }
}
