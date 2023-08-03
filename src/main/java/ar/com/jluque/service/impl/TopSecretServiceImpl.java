package ar.com.jluque.service.impl;

import java.awt.Point;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.jluque.dto.PositionDto;
import ar.com.jluque.dto.SatelliteDistanceDto;
import ar.com.jluque.dto.SatelliteDto;
import ar.com.jluque.dto.SatellitePositionDto;
import ar.com.jluque.dto.SatellitesDto;
import ar.com.jluque.service.QuasarService;
import ar.com.jluque.service.TopSecretService;

@Service
public class TopSecretServiceImpl implements TopSecretService {

	private QuasarService service;

	@Autowired
	public void setService(QuasarService service) {
		this.service = service;
	}

	/**
	 * NIVEL 2
	 */
	@Override
	public SatellitePositionDto topSecret(SatellitesDto satellites) {

		double[] distance = getDistanceArray(satellites);
		Point p = service.getLocation(distance);

		PositionDto position = new PositionDto();
		position.setX(p.getX());
		position.setY(p.getY());

		String[][] mensajes = getMessageMatrix(satellites);
		String m = service.getMessage(mensajes);

		SatellitePositionDto ret = new SatellitePositionDto();
		ret.setPosition(position);
		ret.setMessage(m);

		// TODO agregar libreria de exepciones: para lanzar 404

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
		Point p = service.getLocation(null);

		// getMensaje
		String m = service.getMessage(null);

		PositionDto position = new PositionDto();
		position.setX(p.getX());
		position.setY(p.getY());

		SatellitePositionDto ret = new SatellitePositionDto();
		ret.setPosition(position);
		ret.setMessage(m);

		return ret;
	}

	private double[] getDistanceArray(SatellitesDto satellites) {
		return satellites.getSatellites().stream().mapToDouble(SatelliteDto::getDistance).toArray();
	}

	private String[][] getMessageMatrix(SatellitesDto satellites) {
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