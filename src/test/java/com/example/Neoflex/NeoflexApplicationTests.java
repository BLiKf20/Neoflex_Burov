package com.example.Neoflex;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.Neoflex.service.CalculateAmountOfVacationPayServiceImpl;
import com.example.Neoflex.service.CalculateAmountOfVacationPayServiceInterface;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;

@SpringBootTest
class NeoflexApplicationTests {

	@Test
	void contextLoads() {
	}

	CalculateAmountOfVacationPayServiceInterface calculate = new CalculateAmountOfVacationPayServiceImpl();

	@Test
	public void testServiceCalculateAmountOfVacationPayWithoutHolidays(){
		String actual = calculate.calculateAmountOfVacationPay(
				1200000.0, 21);
		String expected = "Сумма отпускных без учета праздников составит: 71672,35";
		Assertions.assertEquals(expected, actual);
	}

	@Test
	public void testServiceCalculateAmountOfVacationPayWithHolidays(){

		String actual = calculate.calculateAmountOfVacationPay(
				1200000.0, 21,
				LocalDate.of(2024, 6, 3));
		String expected = "Сумма отпускных с учетом праздников составит: 68259,39";
		Assertions.assertEquals(expected, actual);
	}

	@Test
	public void testServiceCalculateAmountOfVacationInvalidNumberOfVacationsDay(){
		String actual = calculate.calculateAmountOfVacationPay(
				1200000.0, -21);
		String expected = "Сумма отпускных без учета праздников составит: Введены не корректные данные";
		Assertions.assertEquals(expected, actual);
	}

	@Test
	public void testServiceCalculateAmountOfVacationInvalidNumberOfVacationsDayWithDate(){
		String actual = calculate.calculateAmountOfVacationPay(
				1200000.0, -21,
				LocalDate.of(2024, 6, 3));
		String expected = "Сумма отпускных с учетом праздников составит: Введены не корректные данные";
		Assertions.assertEquals(expected, actual);
	}

	@Test
	public void testServiceCalculateAmountOfVacationInvalidAverageSalaryPerYear(){
		String actual = calculate.calculateAmountOfVacationPay(
				-1200000.0, 21,
				LocalDate.of(2024, 6, 3));
		String expected = "Сумма отпускных с учетом праздников составит: Введены не корректные данные";
		Assertions.assertEquals(expected, actual);
	}
	

}
