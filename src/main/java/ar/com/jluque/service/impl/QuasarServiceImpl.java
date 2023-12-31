package ar.com.jluque.service.impl;

import java.awt.Point;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.jluque.dao.DaoHandler;
import ar.com.jluque.entity.SatelliteEntity;
import ar.com.jluque.exception.custom.IllegalArgumentCustomException;
import ar.com.jluque.exception.custom.NotFoundCustomException;
import ar.com.jluque.exception.custom.QuasarBuissinesException;
import ar.com.jluque.exception.custom.QuasarBussinesNotFoundException;
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

	private DaoHandler dao;

	@Autowired
	public void setDao(DaoHandler dao) {
		this.dao = dao;
	}

	@Autowired
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	@Autowired
	public void setPositionService(PositionService positionService) {
		this.positionService = positionService;
	}

	@Override
	public Point getLocation(double[] distances)
			throws  QuasarBussinesNotFoundException{
		try {

			ValidateMapper.amountOfSatellites(distances);

			List<SatelliteEntity> satelliteEntityList = dao.recoverSatellitesData();
			double[][] positions = QuasarMapper.getPositions(satelliteEntityList);

			Point posicionesLibreria = positionService.triangulacionLibrary(positions, distances);
			Point posicionesFormula = positionService.triangularFormula(positions, distances);
			log.info("posicionesUsandoLibreria: {} ", posicionesLibreria);
			log.info("posicionesUsandoformula: {} ", posicionesFormula);

			return posicionesLibreria;
		} catch (NotFoundCustomException | IllegalArgumentCustomException | QuasarBuissinesException e) {
			log.error("Error general, no se puedo obtener la ubicacion.");
			throw new QuasarBussinesNotFoundException("Error general, no se puedo obtener la ubicacion" + e.getMessage());
		}
	}

	@Override
	public String getMessage(String[][] messages) throws IllegalArgumentCustomException, QuasarBuissinesException {

		ValidateMapper.amountOfMessages(messages);
		ValidateMapper.amountOfWords(messages);

		List<List<String>> splitedMessages = messageService.validateAndReplaceWords(messages);
		List<List<String>> messagesInverted = messageService.matrixInversion(splitedMessages);

		return messageService.messageBuilder(messagesInverted);
	}

}
