package ar.com.jluque.service.impl;

import java.awt.Point;

import org.springframework.stereotype.Service;

import ar.com.jluque.dto.PositionDto;
import ar.com.jluque.dto.SatelliteDistanceDto;
import ar.com.jluque.dto.SatellitePositionDto;
import ar.com.jluque.dto.SatellitesDto;
import ar.com.jluque.service.QuasarService;
import ar.com.jluque.utils.QuasarConstant;

@Service
public class QuasarServiceImpl implements QuasarService {

	@Override
	public Point getLocation(double[] distances) {

		// distancias base.
		Point owner = new Point();
		owner.setLocation(distances[0], distances[1]);
		double distance = owner.distance(distances[0], distances[1]);

		// Distancia a origen
		Point kenobi = new Point();
		kenobi.setLocation(QuasarConstant.SATELLITE_KENOBI[0], QuasarConstant.SATELLITE_KENOBI[1]);
		kenobi.distance(0, 0);

		Point skywalker = new Point();
		skywalker.setLocation(QuasarConstant.SATELLITE_SKYWALKER[0], QuasarConstant.SATELLITE_SKYWALKER[1]);
		skywalker.distance(0, 0);

		Point sato = new Point(500, 100);
		sato.setLocation(QuasarConstant.SATELLITE_SATO[0], QuasarConstant.SATELLITE_SATO[1]);
		sato.distance(0, 0);

		double distKenoSkyw = kenobi.distance(skywalker.x, skywalker.y);
		double distKenoSato = kenobi.distance(sato.x, sato.y);
		double distSkywSato = skywalker.distance(sato.x, sato.y);

		// distancia al emisor

		// mapear cordenadas

		// mapear triangulacion.

		// fuente o coordenadas del mensaje
		return new Point(0, 0);
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
