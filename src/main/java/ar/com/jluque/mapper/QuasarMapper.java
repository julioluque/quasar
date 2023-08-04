package ar.com.jluque.mapper;

import java.util.List;
import java.util.stream.IntStream;

import ar.com.jluque.entity.SatelliteEntity;

public class QuasarMapper {

	public QuasarMapper() {
	}

	public static double[][] getPositions(List<SatelliteEntity> satelliteEntityList) {

		double[][] matrizCoordenadas = new double[3][2];

		IntStream.range(0, satelliteEntityList.size()-1).forEach(i -> {
			SatelliteEntity satellite = satelliteEntityList.get(i);
			matrizCoordenadas[i][0] = satellite.getX();
			matrizCoordenadas[i][1] = satellite.getY();
		});

		return matrizCoordenadas;
	}

}