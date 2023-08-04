package ar.com.jluque.mapper;

import static ar.com.jluque.utils.QuasarConstant.SATELLITE_KENOBI;
import static ar.com.jluque.utils.QuasarConstant.SATELLITE_SATO;
import static ar.com.jluque.utils.QuasarConstant.SATELLITE_SKYWALKER;

import java.util.List;

import ar.com.jluque.entity.SatelliteEntity;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class QuasarMapper {

	public QuasarMapper() {
	}

	public static double[][] getPositions() {
		return new double[][] { { SATELLITE_KENOBI[0], SATELLITE_KENOBI[1] },
				{ SATELLITE_SKYWALKER[0], SATELLITE_SKYWALKER[1] }, { SATELLITE_SATO[0], SATELLITE_SATO[1] } };
	}

	public static double[][] getPositionsV2(List<SatelliteEntity> satelliteEntityList) {

		double[][] matrizCoordenadas = new double[3][2];
		
		for (int i = 0; i < satelliteEntityList.size(); i++) {
			SatelliteEntity satellite = satelliteEntityList.get(i);
				matrizCoordenadas[i][0] = satellite.getX();
				matrizCoordenadas[i][1] = satellite.getY();
		}

		return matrizCoordenadas;
	}

}