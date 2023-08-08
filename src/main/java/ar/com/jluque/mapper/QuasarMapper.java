package ar.com.jluque.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

import ar.com.jluque.entity.SatelliteEntity;
import ar.com.jluque.exception.custom.IllegalArgumentCustomException;

public class QuasarMapper {

	public QuasarMapper() {
	}

	public static double[][] getPositions(List<SatelliteEntity> satelliteEntityList)
			throws IllegalArgumentCustomException {

		double[][] matrizCoordenadas = new double[3][2];

		IntStream.range(0, satelliteEntityList.size() - 1).forEach(i -> {
			SatelliteEntity satellite = satelliteEntityList.get(i);
			if (Objects.isNull(satellite))
				throw new IllegalArgumentCustomException(
						"Error al buscar la posicion. Satellite no enconrado en la posicion: " + i);
			matrizCoordenadas[i][0] = satellite.getX();
			matrizCoordenadas[i][1] = satellite.getY();
		});

		return matrizCoordenadas;
	}

}