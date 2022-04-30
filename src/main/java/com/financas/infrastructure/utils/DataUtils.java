package com.financas.infrastructure.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;

import lombok.experimental.UtilityClass;

@UtilityClass
public class DataUtils {

	public static LocalDate getDiaUtilMaisProximo(LocalDate dataOriginal) {
		DayOfWeek diaDaSemana = dataOriginal.getDayOfWeek();
		
		if (diaDaSemana.equals(DayOfWeek.SATURDAY)) {
			return dataOriginal.minusDays(1);
		} 
		if (diaDaSemana.equals(DayOfWeek.SUNDAY)) {
			return dataOriginal.minusDays(2);
		}
		
		return dataOriginal;		
	}
	
}
