package ar.com.jluque.service.impl;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.languagetool.JLanguageTool;
import org.languagetool.language.Spanish;
import org.languagetool.rules.RuleMatch;
import org.springframework.stereotype.Service;

import ar.com.jluque.dto.PositionDto;
import ar.com.jluque.dto.SatelliteDistanceDto;
import ar.com.jluque.dto.SatelliteDto;
import ar.com.jluque.dto.SatellitePositionDto;
import ar.com.jluque.dto.SatellitesDto;
import ar.com.jluque.mapper.QuasarMapper;
import ar.com.jluque.mapper.TriangulateMapper;
import ar.com.jluque.mapper.ValidateMapper;
import ar.com.jluque.service.QuasarService;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class QuasarServiceImpl implements QuasarService {

	@Override
	public Point getLocation(double[] distances) {

		ValidateMapper.amountOfSatellites(distances);

		double[][] positions = QuasarMapper.getPositions();

		Point posicionesLibreria = TriangulateMapper.triangulacionLibrary(positions, distances);
		Point posicionesFormula = TriangulateMapper.triangularFormula(positions, distances);
		log.info("posicionesUsandoLibreria: {} ", posicionesLibreria);
		log.info("posicionesUsandoformula: {} ", posicionesFormula);

		return posicionesFormula;
	}

	@Override
	public String getMessage(String[][] messages) {

		ValidateMapper.amountOfMessages(messages);
		ValidateMapper.amountOfWords(messages);
		
		List<List<String>> splitedMessages = validateAndSplitWords(messages);
		return buildMessage(splitedMessages);
	}

	private List<List<String>> validateAndSplitWords(String[][] wordMatrix) {
		JLanguageTool languageTool = new JLanguageTool(new Spanish());
		List<List<String>> mensajeOriginalList = Arrays.stream(wordMatrix).map(Arrays::asList)
				.collect(Collectors.toList());

		List<List<String>> mensajeModificadoList = new ArrayList<>();

		mensajeOriginalList.stream().forEach(palabras -> {
			List<String> palabrasModificadas = new ArrayList<>();

			palabras.forEach(palabra -> {
				try {
					List<RuleMatch> matches = languageTool.check(palabra);

					if (matches.isEmpty()) {
						log.info("[{}]\t es valida", palabra);
					} else {
						log.info("[{}]\t no reconocida. La reemplazamos con espacio", palabra);
						palabra = "";

					}
					palabrasModificadas.add(palabra);

				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			mensajeModificadoList.add(palabrasModificadas);
		});

		log.info("compare Original: {}\ncompare Updated: {}", mensajeOriginalList, mensajeModificadoList);
		return mensajeModificadoList;
	}

	private String buildMessage(List<List<String>> messages) {
		//TODO buildMessage
		return messages.toString();
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
