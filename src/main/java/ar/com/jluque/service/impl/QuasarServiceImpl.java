package ar.com.jluque.service.impl;

import java.awt.Point;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.jluque.entity.SatelliteEntity;
import ar.com.jluque.exception.custom.IllegalArgumentCustomException;
import ar.com.jluque.exception.custom.NotFoundCustomException;
import ar.com.jluque.exception.custom.QuasarBuissinesException;
import ar.com.jluque.mapper.QuasarMapper;
import ar.com.jluque.mapper.ValidateMapper;
import ar.com.jluque.repository.SatelliteRepository;
import ar.com.jluque.service.MessageService;
import ar.com.jluque.service.PositionService;
import ar.com.jluque.service.QuasarService;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class QuasarServiceImpl implements QuasarService {

	private MessageService messageService;

	private PositionService positionService;

	private SatelliteRepository repository;

	@Autowired
	public void setRepository(SatelliteRepository repository) {
		this.repository = repository;
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
			throws NotFoundCustomException, IllegalArgumentCustomException, QuasarBuissinesException {

		ValidateMapper.amountOfSatellites(distances);

		List<SatelliteEntity> satelliteEntityList = findAllSatellites();
		double[][] positions = QuasarMapper.getPositions(satelliteEntityList);

		Point posicionesLibreria = positionService.triangulacionLibrary(positions, distances);
		Point posicionesFormula = positionService.triangularFormula(positions, distances);
		log.info("posicionesUsandoLibreria: {} ", posicionesLibreria);
		log.info("posicionesUsandoformula: {} ", posicionesFormula);

		return posicionesLibreria;
	}

	private List<SatelliteEntity> findAllSatellites() throws NotFoundCustomException {
		Optional<List<SatelliteEntity>> entityList = Optional.ofNullable(repository.findAll());
		entityList.orElseThrow(() -> new NotFoundCustomException("No se encontraron Satellites en la base de datos"));
		return entityList.get();
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
