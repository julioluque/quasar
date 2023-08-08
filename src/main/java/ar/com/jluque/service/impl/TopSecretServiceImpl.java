package ar.com.jluque.service.impl;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import ar.com.jluque.dao.DaoHandler;
import ar.com.jluque.dto.PositionDto;
import ar.com.jluque.dto.SatelliteDistanceDto;
import ar.com.jluque.dto.SatelliteDto;
import ar.com.jluque.dto.SatellitePositionDto;
import ar.com.jluque.dto.SatellitesDto;
import ar.com.jluque.entity.SatelliteEntity;
import ar.com.jluque.exception.custom.NotFoundCustomException;
import ar.com.jluque.service.QuasarService;
import ar.com.jluque.service.TopSecretService;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class TopSecretServiceImpl implements TopSecretService {

	private QuasarService service;

	private DaoHandler dao;
	
	@Autowired
	public void setDao(DaoHandler dao) {
		this.dao = dao;
	}
	
	@Autowired
	public void setService(QuasarService service) {
		this.service = service;
	}

	/**
	 * NIVEL 2
	 * 
	 * @throws Exception
	 */
	@Override
	public SatellitePositionDto topSecret(SatellitesDto satellites) throws DataAccessException {

		double[] distance = getDistanceArray(satellites);
		Point p = service.getLocation(distance);

		PositionDto position = new PositionDto();
		position.setX(p.getX());
		position.setY(p.getY());

		List<SatelliteEntity> satelliteEntity = dao.recoverSatellitesData();
		satelliteEntity = buildEntity(satellites, satelliteEntity);
		dao.saveMessages(satelliteEntity);

		String[][] mensajes = getMessageMatrix(satellites);
		String m = service.getMessage(mensajes);

		SatellitePositionDto ret = new SatellitePositionDto();
		ret.setPosition(position);
		ret.setMessage(m);

		dao.saveTransmiterData(ret, satelliteEntity.get(3));

		// TODO agregar libreria de exepciones: para lanzar 404

		return ret;
	}




	/**
	 * Mergeamos los satellites del input con los que fueron recuperados por base de
	 * datos. Tomamos el nombre como key y seteamos los mensajes.
	 * 
	 * @param satellites
	 * @param satelliteEntity
	 * @return
	 */
	private List<SatelliteEntity> buildEntity(SatellitesDto satellites, List<SatelliteEntity> satelliteEntity) {
		return satelliteEntity.stream().map(e -> {
			satellites.getSatellites().stream().filter(s -> s.getName().equalsIgnoreCase(e.getName())).findFirst()
					.ifPresent(s -> e.setMessage(String.join(",", s.getMessage())));
			return e;
		}).toList();
	}



	/**
	 * NIVEL 3 persistiendo en DB por fuera. Se podria agregar un post que de el
	 * alta
	 * 
	 * @throws Exception
	 * 
	 */
	@Override
	public void topSecretUpdate(String name, SatelliteDistanceDto satelliteDistanceDto) throws Exception {

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
		dao.saveEntity(satelliteEntity);
	}

	public void topSecretUpdateV2(String name, SatelliteDistanceDto satelliteDistanceDto) {
		// armar una nueva tabla con las distancias y mensajes
		// esta tabla debe ser independiente de las otras
		// sobre esta deberia correr un schedule
		// la tabla debe esperar que se completen con los tres satelites
		// una vez completados los satelites. debe llamar al servicio topSecret
		// la respuesta debe ser la misma que topSecret
		// WARMING: ACTUALIAR LA POSICION DE CADA SATELLITE?
	}

	@Override
	public SatellitePositionDto getTopSecret() {

		List<SatelliteEntity> satelliteEntity = dao.recoverSatellitesData();

		SatellitePositionDto ret = new SatellitePositionDto();

		for (SatelliteEntity s : satelliteEntity) {
			if (s.getName().equals("TRANSMITER")) {
				ret.getPosition().setX(s.getX());
				ret.getPosition().setY(s.getY());
				ret.setMessage(s.getMessage());
			}
		}

		if (StringUtils.isBlank(ret.getMessage())) {
			log.info("Lanzar exepcion, mensaje no identificable");
			throw new NotFoundCustomException("Satelite: ");
		}

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
