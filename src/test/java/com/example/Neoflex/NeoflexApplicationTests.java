package com.example.Neoflex;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.Neoflex.service.CalculateAmountOfVacationPayServiceImpl;
import com.example.Neoflex.service.CalculateAmountOfVacationPayServiceInterface;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class NeoflexApplicationTests {

	@Test
	void contextLoads() {
	}

	CalculateAmountOfVacationPayServiceInterface calculate = new CalculateAmountOfVacationPayServiceImpl();

	@Test
	public void testServiceСalculateAmountOfVacationPayWithoutHolidays(){
		String actual = calculate.calculateAmountOfVacationPay(
				1200000.0, 21);
		String expected = "Сумма отпускных без учета праздников составит: 71672,35";
		Assertions.assertEquals(expected, actual);
	}

	@Test
	public void testServiceСalculateAmountOfVacationPayWithHolidays(){

		String actual = calculate.calculateAmountOfVacationPay(
				1200000.0, 21,
				LocalDate.of(2024, 06, 03));
		String expected = "Сумма отпускных с учетом праздников составит: 68259,39";
		Assertions.assertEquals(expected, actual);
	}

	@Test
	public void testServiceСalculateAmountOfVacationInvalidNumberOfVacationsDay(){
		String actual = calculate.calculateAmountOfVacationPay(
				1200000.0, -21);
		String expected = "Сумма отпускных без учета праздников составит: Введены не корректные данные";
		Assertions.assertEquals(expected, actual);
	}

	@Test
	public void testServiceСalculateAmountOfVacationInvalidNumberOfVacationsDayWithDate(){
		String actual = calculate.calculateAmountOfVacationPay(
				1200000.0, -21,
				LocalDate.of(2024, 06, 03));
		String expected = "Сумма отпускных с учетом праздников составит: Введены не корректные данные";
		Assertions.assertEquals(expected, actual);
	}

	@Test
	public void testServicecalculateAmountOfVacationInvalidAverageSalaryPerYear(){
		String actual = calculate.calculateAmountOfVacationPay(
				-1200000.0, 21,
				LocalDate.of(2024, 06, 03));
		String expected = "Сумма отпускных с учетом праздников составит: Введены не корректные данные";
		Assertions.assertEquals(expected, actual);
	}
	

}
