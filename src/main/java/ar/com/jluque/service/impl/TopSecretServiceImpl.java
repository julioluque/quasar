package ar.com.jluque.service.impl;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.jluque.dto.PositionDto;
import ar.com.jluque.dto.SatelliteDistanceDto;
import ar.com.jluque.dto.SatelliteDto;
import ar.com.jluque.dto.SatellitePositionDto;
import ar.com.jluque.dto.SatellitesDto;
import ar.com.jluque.entity.SatelliteEntity;
import ar.com.jluque.repository.SatelliteRepository;
import ar.com.jluque.service.QuasarService;
import ar.com.jluque.service.TopSecretService;

@Service
public class TopSecretServiceImpl implements TopSecretService {

	private QuasarService service;

	private SatelliteRepository repository;

	@Autowired
	public void setRepository(SatelliteRepository repository) {
		this.repository = repository;
	}

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
	 * NIVEL 3 TODO Primero: guardar coordenadas de satellites en DB - se resolvio
	 * persistiendo en DB por fuera. Se podria agregar un post que de el alta
	 * 
	 */
	@Override
	public void topSecretUpdate(String name, SatelliteDistanceDto satelliteDistanceDto) {

		SatelliteEntity satelliteEntity = repository.findByName(name);

		// persistir SatelliteDto
		SatelliteDto satellite = new SatelliteDto();
		satellite.setName(name);
		satellite.setDistance(satelliteDistanceDto.getDistance());
		satellite.setMessage(satelliteDistanceDto.getMessage());
		
		List<SatelliteDto> satelliteList = new ArrayList<>();
		satelliteList.add(satellite);
		
		SatellitesDto satellites = new SatellitesDto();
		satellites.setSatellites(satelliteList);
		
		SatellitePositionDto topSecret = topSecret(satellites);
		
		// TODO update la coordenada de cada satelite con este servicio

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
