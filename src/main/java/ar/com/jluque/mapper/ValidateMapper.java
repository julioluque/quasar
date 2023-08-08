package ar.com.jluque.mapper;

import java.util.Arrays;

import ar.com.jluque.exception.custom.IllegalArgumentCustomException;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ValidateMapper {

	public ValidateMapper() {
	}

	public static void amountOfSatellites(double[] distances) throws IllegalArgumentCustomException {
		if (distances == null || distances.length != 3) {
			throw new IllegalArgumentCustomException("review amount of satelites");
		}
	}

	public static void amountOfMessages(String[][] messages) throws IllegalArgumentCustomException {
		if (messages == null || messages.length != 3) {
			throw new IllegalArgumentCustomException("review amount of messages");
		}
	}

	public static void amountOfWords(String[][] messages) throws IllegalArgumentCustomException {
		boolean allSublistsHaveSameSize = Arrays.stream(messages).mapToInt(sublist -> sublist.length).distinct()
				.count() == 1;

		if (!allSublistsHaveSameSize) {
			Arrays.stream(messages).forEach(sublist -> log.info("Cantidad de palabras: " + sublist.length));
			Arrays.stream(messages).flatMap(Arrays::stream).forEach(log::info);
			throw new IllegalArgumentCustomException("Las sublistas NO tienen la misma cantidad de elementos.");
		}
	}

}
