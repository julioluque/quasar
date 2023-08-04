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

		// Persistir mensajes:
		List<SatelliteEntity> satelliteEntity = recoverSatellitesData();
		satelliteEntity = buildEntity(satellites, satelliteEntity);
		saveMessages(satelliteEntity);

		String[][] mensajes = getMessageMatrix(satellites);
		String m = service.getMessage(mensajes);

		SatellitePositionDto ret = new SatellitePositionDto();
		ret.setPosition(position);
		ret.setMessage(m);

		saveTransmiterData(ret, satelliteEntity.get(3));

		// TODO agregar libreria de exepciones: para lanzar 404

		return ret;
	}

	private void saveTransmiterData(SatellitePositionDto satellitePositionDto, SatelliteEntity satelliteEntity) {
		satelliteEntity.setX(satellitePositionDto.getPosition().getX());
		satelliteEntity.setY(satellitePositionDto.getPosition().getX());
		satelliteEntity.setMessage(satellitePositionDto.getMessage());

		repository.save(satelliteEntity);
	}

	private List<SatelliteEntity> recoverSatellitesData() {
		return repository.findAll();
	}

	private List<SatelliteEntity> buildEntity(SatellitesDto satellites, List<SatelliteEntity> satelliteEntity) {
		return satelliteEntity.stream().map(e -> {
			satellites.getSatellites().stream().filter(s -> s.getName().equalsIgnoreCase(e.getName())).findFirst()
					.ifPresent(s -> e.setMessage(String.join(",", s.getMessage())));
			return e;
		}).toList();
	}

	private void saveMessages(List<SatelliteEntity> satelliteEntity) {
		repository.saveAll(satelliteEntity);
	}

	/**
	 * NIVEL 3 persistiendo en DB por fuera. Se podria agregar un post que de el
	 * alta
	 * 
	 */
	@Override
	public void topSecretUpdate(String name, SatelliteDistanceDto satelliteDistanceDto) {

		// Armo el satelite perteneciente a esta peticion.
		SatelliteDto satellite = new SatelliteDto();
		satellite.setName(name);
		satellite.setDistance(satelliteDistanceDto.getDistance());
		satellite.setMessage(satelliteDistanceDto.getMessage());

		List<SatelliteDto> satelliteList = new ArrayList<>();
		satelliteList.add(satellite);

		SatellitesDto satellites = new SatellitesDto();
		satellites.setSatellites(satelliteList);

		// TODO En esta prueba llamo al servicio anterior pero con las nuevas
		// distancias. aunque no se pide el calculo sino la actualizacion, Creo qeu
		// deberia hacer neuvamente el calculo.

		// TODO teniendo el 4to punto, ahora si con la variacion de una distancia ahora
		// si seria posible calcular el nuevo satelite.
		SatellitePositionDto topSecret = topSecret(satellites);

		// TODO update la coordenada de cada satelite con este servicio

		SatelliteEntity satelliteEntity = new SatelliteEntity();
		satelliteEntity.setName(name);
		satelliteEntity.setX(topSecret.getPosition().getX());
		satelliteEntity.setY(topSecret.getPosition().getY());
		repository.save(satelliteEntity);
	}

	@Override
	public SatellitePositionDto getTopSecret() {

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
