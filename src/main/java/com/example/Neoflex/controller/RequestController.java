package com.example.Neoflex.controller;

import com.example.Neoflex.service.CalculateAmountOfVacationPayServiceImpl;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class RequestController {
    private final CalculateAmountOfVacationPayServiceImpl calculateAmountOfVacationPayService =
            new CalculateAmountOfVacationPayServiceImpl();

    @GetMapping("/calculacte")
    public String calculacte(@RequestParam("averageSalaryPerYear") Double averageSalaryPerYear,
                             @RequestParam("numberOfVacationsDay") Integer numberOfVacationsDay,
                             @RequestParam (value = "dateOfStartVacation", required = false)
                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfStartVacation) {
        if (dateOfStartVacation == null) {
            return calculateAmountOfVacationPayService.
                    calculateAmountOfVacationPay(averageSalaryPerYear, numberOfVacationsDay);
        }
        return calculateAmountOfVacationPayService
                .calculateAmountOfVacationPay(averageSalaryPerYear,numberOfVacationsDay, dateOfStartVacation);
    }

}
