package ar.com.jluque.service.impl;

import java.awt.Point;
import java.io.IOException;
import java.util.List;

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

@Service
public class QuasarServiceImpl implements QuasarService {

	@Override
	public Point getLocation(double[] distances) {

		ValidateMapper.amountSatellites(distances);

		double[][] positions = QuasarMapper.getPositions();

		Point posicionesLibreria = TriangulateMapper.triangulacionLibrary(positions, distances);
		Point posicionesFormula = TriangulateMapper.triangularFormula(positions, distances);
		System.out.println(posicionesLibreria);
		System.out.println(posicionesFormula);
		return posicionesFormula;
	}

	@Override
	public String getMessage(String[][] messages) {
		for (String[] ms : messages) {
			for (String m : ms) {
				validateWord(m);
			}
		}
		return "Este es el mensaje";
	}

	private void validateWord(String word) {
		// Crea una instancia de JLanguageTool para el idioma español
		JLanguageTool languageTool = new JLanguageTool(new Spanish());
		try {
			// Obtiene la lista de errores ortográficos en el texto proporcionado
			List<RuleMatch> matches = languageTool.check(word);

			if (!matches.isEmpty()) {
				System.out.println("La palabra '" + word + "' es inexistente o incorrecta.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
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
