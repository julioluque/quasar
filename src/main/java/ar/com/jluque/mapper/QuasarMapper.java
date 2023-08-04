package ar.com.jluque.mapper;

import static ar.com.jluque.utils.QuasarConstant.SATELLITE_KENOBI;
import static ar.com.jluque.utils.QuasarConstant.SATELLITE_SATO;
import static ar.com.jluque.utils.QuasarConstant.SATELLITE_SKYWALKER;

import java.util.List;

import ar.com.jluque.entity.SatelliteEntity;

public class QuasarMapper {

	public QuasarMapper() {
	}

	public static double[][] getPositions() {
		return new double[][] { { SATELLITE_KENOBI[0], SATELLITE_KENOBI[1] },
				{ SATELLITE_SKYWALKER[0], SATELLITE_SKYWALKER[1] }, { SATELLITE_SATO[0], SATELLITE_SATO[1] } };
	}

	public static double[][] getPositionsV2(List<SatelliteEntity> satelliteEntityList) {

		double kenoX1 = 0;
		double kenoY1 = 0;

		double skayX2 = 0;
		double skayY2 = 0;

		double satoX3 = 0;
		double satoY3 = 0;

		
		for (SatelliteEntity satelliteEntity : satelliteEntityList) {
			if (satelliteEntity.getName().equalsIgnoreCase("KENOBI")) {
				kenoX1 = satelliteEntity.getX();
				kenoY1 = satelliteEntity.getY();
			} else if (satelliteEntity.getName().equalsIgnoreCase("SKYWALKER")) {
				skayX2 = satelliteEntity.getX();
				skayY2 = satelliteEntity.getY();
			} else if (satelliteEntity.getName().equalsIgnoreCase("SATO")) {
				satoX3 = satelliteEntity.getX();
				satoY3 = satelliteEntity.getY();
			}
		}

		
		return new double[][] { { kenoX1, kenoY1 }, { skayX2, skayY2 }, { satoX3, satoY3 } };
	}

}