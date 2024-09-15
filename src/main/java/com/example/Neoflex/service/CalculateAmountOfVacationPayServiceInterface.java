package com.example.Neoflex.service;

// доход сотрудника за 1 день = averageSalaryPerYear (доход сотрудника за 1 год до отпуска) / 12 / 29.3 (среднее количество дней в месяце)
// сумма отпускных = доход сотрудника за 1 день * количество дней отпуска

import java.time.LocalDate;

public interface CalculateAmountOfVacationPayServiceInterface {
    String calculateAmountOfVacationPay(Double averageSalaryPerYear, Integer numberOfVacationsDays); //метод для подсчета без введеной даты начала отпуска
    String calculateAmountOfVacationPay(Double averageSalaryPerYear, Integer numberOfVacationsDays, LocalDate dateOfStartVacation); //метод для подсчета с введеной датой начала отпуска
}
