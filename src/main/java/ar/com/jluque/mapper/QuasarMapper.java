package ar.com.jluque.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

import ar.com.jluque.dto.SatelliteDto;
import ar.com.jluque.dto.SatellitesDto;
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
			if (!Objects.isNull(satellite))
				throw new IllegalArgumentCustomException(
						"Error al buscar la posicion. Satellite no enconrado en la posicion: " + i);
			matrizCoordenadas[i][0] = satellite.getX();
			matrizCoordenadas[i][1] = satellite.getY();
		});

		return matrizCoordenadas;
	}

	public static double[] getDistanceArray(SatellitesDto satellites) {
		return satellites.getSatellites().stream().mapToDouble(SatelliteDto::getDistance).toArray();
	}

	/**
	 * Mergeamos los satellites del input con los que fueron recuperados por base de
	 * datos. Tomamos el nombre como key y seteamos los mensajes.
	 * 
	 * @param satellites
	 * @param satelliteEntity
	 * @return
	 */
	public static List<SatelliteEntity> buildEntity(SatellitesDto satellites, List<SatelliteEntity> satelliteEntity) {
		return satelliteEntity.stream().map(e -> {
			satellites.getSatellites().stream().filter(s -> s.getName().equalsIgnoreCase(e.getName())).findFirst()
					.ifPresent(s -> e.setMessage(String.join(",", s.getMessage())));
			return e;
		}).toList();
	}

	public static String[][] getMessageMatrix(SatellitesDto satellites) {
		List<String[]> mensajesList = satellites.getSatellites().stream().map(SatelliteDto::getMessage).toList();

		int listSize = mensajesList.size();
		int arrayLength = mensajesList.get(0).length;

		String[][] mensajes = new String[listSize][arrayLength];

		for (int i = 0; i < listSize; i++) {
			mensajes[i] = mensajesList.get(i);
		}
		return mensajes;
	}

}