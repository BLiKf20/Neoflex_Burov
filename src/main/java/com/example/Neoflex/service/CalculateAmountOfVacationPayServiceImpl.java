package com.example.Neoflex.service;

import com.example.Neoflex.data.Holidays;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CalculateAmountOfVacationPayServiceImpl implements CalculateAmountOfVacationPayServiceInterface {
    private static final Double averageNumberOfDaysAtMonth = 29.3; // среднее количество дней в месяце
    private static final Integer numberOfMonths = 12;

    @Override
    public String calculateAmountOfVacationPay(Double averageSalaryPerYear,
                                               Integer numberOfVacationsDays) {
        String result;
        result = (averageSalaryPerYearIsValid(averageSalaryPerYear) &&
                numberOfVacationsDayIsValid(numberOfVacationsDays)) ?
                String.format("%.2f",averageSalaryPerYear / numberOfMonths /
                        averageNumberOfDaysAtMonth * numberOfVacationsDays) :
                "Введены не корректные данные";
        return "Сумма отпускных без учета праздников составит: " + result;
    }

    @Override
    public String calculateAmountOfVacationPay(Double averageSalaryPerYear,
                                               Integer numberOfVacationsDays,
                                               LocalDate dateOfStartVacation) {
        String result;
        Integer numberOfVacationsDaysWithoutHolidaysAndWeekends = (averageSalaryPerYearIsValid(averageSalaryPerYear) 
                                                                   && numberOfVacationsDayIsValid(numberOfVacationsDays)) ?
                getNumberOfVacationsDaysWithoutHolidaysAndWeekends(dateOfStartVacation, dateOfStartVacation
                                                                   .plusDays(numberOfVacationsDays)) : -1;
        result = (numberOfVacationsDaysWithoutHolidaysAndWeekends > 0) ?
                String.format("%.2f", averageSalaryPerYear / numberOfMonths /
                        averageNumberOfDaysAtMonth * numberOfVacationsDaysWithoutHolidaysAndWeekends) :
                "Введены не корректные данные";
        /*return "Количество дней запланированного отпуска составляет: "+ numberOfVacationsDays +
                "\nКоличество праздников в отпуске составляет: " + (numberOfVacationsDays - numberOfVacationsDaysWithoutHolidaysAndWeekends) +
                "\nСумма отпускных с учетом праздников составит: " + result;
*/
        return "Сумма отпускных с учетом праздников составит: " + result;
    }

    private boolean averageSalaryPerYearIsValid(Double averageSalaryPerYear) { // проверяем корректно ли введена средняя годовая зарплата
        return averageSalaryPerYear != null &&  averageSalaryPerYear > 0;
    }

    private boolean numberOfVacationsDayIsValid(Integer numberOfVacationsDay) { // проверяем корректно ли введено количество дней отпуска
        return numberOfVacationsDay != 0 && numberOfVacationsDay > 0;
    }

    private Integer getNumberOfVacationsDaysWithoutHolidaysAndWeekends (LocalDate dateOfStartVacation,
                                                                        LocalDate dateOfEndVacation){
        Integer numberOfVacationsDaysWithoutHolidaysAndWeekends = 0;
        while (!dateOfStartVacation.equals(dateOfEndVacation)) {
            if (Holidays.getHolidaysMap().containsKey(dateOfStartVacation.getMonth())) {
                if (Holidays.getHolidaysMap().get(dateOfStartVacation.getMonth())
                        .contains(dateOfStartVacation.getDayOfMonth())) {
                    dateOfStartVacation = dateOfStartVacation.plusDays(1);
                    continue;
                }
            }
            numberOfVacationsDaysWithoutHolidaysAndWeekends++;
            dateOfStartVacation = dateOfStartVacation.plusDays(1);
        }


        return numberOfVacationsDaysWithoutHolidaysAndWeekends;
    }
}
