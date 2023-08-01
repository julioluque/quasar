package ar.com.jluque.service.impl;

import java.awt.Point;

import org.springframework.stereotype.Service;

import ar.com.jluque.dto.PositionDto;
import ar.com.jluque.dto.SatelliteDistanceDto;
import ar.com.jluque.dto.SatellitePositionDto;
import ar.com.jluque.dto.SatellitesDto;
import ar.com.jluque.service.QuasarService;

@Service
public class QuasarServiceImpl implements QuasarService {

	/**
	 * Retornamos la fuente del mensaje.
	 * 
	 * La unidad de distancia en los parámetros de getLocation es la misma que la
	 * que se utiliza para indicar la posición de cada satélite
	 */
	@Override
	public Point getLocation(double[] distances) {

		double x = 10.0;
		double y = 20.0;

		try {
			x = distances[0] + x;
			y = distances[1] + y;
		} catch (Exception e) {
			System.out.println("error");
		}

		Point location = new Point();
		location.setLocation(x, y);

		return location;
	}

	/**
	 * Retornamos el contenido del mensaje
	 * 
	 * El mensaje recibido en cada satélite se recibe en forma de arreglo de
	 * strings.
	 * 
	 * Cuando una palabra del mensaje no pueda ser determinada, se reemplaza por un
	 * string en blanco en el array. Ejemplo: [“este”, “es”, “”, “mensaje”]
	 * 
	 * Considerar que existe un desfase (a determinar) en el mensaje que se recibe
	 * en cada satélite. Ejemplo:
	 * 
	 * @Kenobi: [“”, “este”, “es”, “un”, “mensaje”]
	 * @Skywalker: [“este”, “”, “un”, “mensaje”]
	 * @Sato: [“”, ””, ”es”, ””, ”mensaje”]
	 * 
	 */
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
