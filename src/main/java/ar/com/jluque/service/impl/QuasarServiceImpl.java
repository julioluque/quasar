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
import ar.com.jluque.mapper.QuasarMapper;
import ar.com.jluque.mapper.ValidateMapper;
import ar.com.jluque.service.MessageService;
import ar.com.jluque.service.PositionService;
import ar.com.jluque.service.QuasarService;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class QuasarServiceImpl implements QuasarService {

	private MessageService messageService;

	private PositionService positionService;

	@Autowired
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	@Autowired
	public void setPositionService(PositionService positionService) {
		this.positionService = positionService;
	}

	@Override
	public Point getLocation(double[] distances) {

		ValidateMapper.amountOfSatellites(distances);

		double[][] positions = QuasarMapper.getPositions();

		Point posicionesLibreria = positionService.triangulacionLibrary(positions, distances);
		Point posicionesFormula = positionService.triangularFormula(positions, distances);
		log.info("posicionesUsandoLibreria: {} ", posicionesLibreria);
		log.info("posicionesUsandoformula: {} ", posicionesFormula);

		return posicionesFormula;
	}

	@Override
	public String getMessage(String[][] messages) {

		ValidateMapper.amountOfMessages(messages);
		ValidateMapper.amountOfWords(messages);

		List<List<String>> splitedMessages = messageService.validateAndSplitWords(messages);
		List<List<String>> messagesInverted = messageService.matrixInversion(splitedMessages);

		return messageService.messageBuilder(messagesInverted);
	}

	/**
	 * NIVEL 2
	 */
	@Override
	public SatellitePositionDto topSecret(SatellitesDto satellites) {

		double[] distance = getDistanceArray(satellites);
		Point p = getLocation(distance);

		String[][] mensajes = getMessageMatrix(satellites);
		String m = getMessage(mensajes);

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
