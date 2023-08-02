package ar.com.jluque.service.impl;

import java.awt.Point;

import org.springframework.stereotype.Service;

import ar.com.jluque.dto.PositionDto;
import ar.com.jluque.dto.SatelliteDistanceDto;
import ar.com.jluque.dto.SatelliteDto;
import ar.com.jluque.dto.SatellitePositionDto;
import ar.com.jluque.dto.SatellitesDto;
import ar.com.jluque.mapper.QuasarMapper;
import ar.com.jluque.service.QuasarService;
import static ar.com.jluque.utils.QuasarConstant.*;

@Service
public class QuasarServiceImpl implements QuasarService {

	@Override
	public Point getLocation(double[] distances) {

		if (distances == null || distances.length != 3) {
			throw new IllegalArgumentException("review amount of satelites");
		}

		// distancia al emisor
		double distanceToKenobi = distances[0];
		double distanceToSkywalker = distances[1];
		double distanceToSato = distances[2];

		double[][] positions = new double[][] { { SATELLITE_KENOBI[0], SATELLITE_KENOBI[1] },
				{ SATELLITE_SKYWALKER[0], SATELLITE_SKYWALKER[1] }, { SATELLITE_SATO[0], SATELLITE_SATO[1] } };

		Point posicionesLibreria= QuasarMapper.triangulacionLibrary(positions, distances);
		Point posicionesFormula = QuasarMapper.triangularFormula(positions, distanceToKenobi, distanceToSkywalker,
				distanceToSato);
		System.out.println("posicionesLibreria: " + posicionesLibreria);
		System.out.println("posicionesFormula: " + posicionesFormula);

		return posicionesFormula;
	}

	@Override
	public String getMessage(String[][] messages) {
		// TODO split
		return "Este es el mensaje";
	}

	/**
	 * NIVEL 2
	 */
	@Override
	public SatellitePositionDto topSecret(SatellitesDto satellites) {

		// TODO persistir satelite.

		double[] distance = satellites.getSatellites().stream().mapToDouble(SatelliteDto::getDistance).toArray();
		Point p = getLocation(distance);

		// getMensaje
		String m = getMessage(null);

		PositionDto position = new PositionDto();
		position.setX(p.getX());
		position.setY(p.getY());

		SatellitePositionDto ret = new SatellitePositionDto();
		ret.setPosition(position);
		ret.setMessage(m);

		return ret;
	}

	/**
	 * NIVEL 3
	 */
	@Override
	public void topSecretUpdate(SatelliteDistanceDto satellites) {
		// TODO update
	}

	@Override
	public SatellitePositionDto getTopSecret() {
		// TODO persistir satelite.

		// getLocation
		Point p = getLocation(null);

		// getMensaje
		String m = getMessage(null);

		PositionDto position = new PositionDto();
		position.setX(p.getX());
		position.setY(p.getY());

		SatellitePositionDto ret = new SatellitePositionDto();
		ret.setPosition(position);
		ret.setMessage(m);

		return ret;
	}

}
