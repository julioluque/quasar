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
import org.languagetool.synthesis.SynthesizerTools;
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
		ValidateMapper.amountMessages(messages);

		System.out.println(">>>>>>>>>COMPARAMOS validateWord<<<<<<<<<");
		validateWord(messages);

		System.out.println(">>>>>>>>>COMPARAMOS validateWord2<<<<<<<<<");
		validateWord2(messages);

		return "Este es el mensaje";
	}

	private void validateWord(String[][] wordMatrix) {
		JLanguageTool languageTool = new JLanguageTool(new Spanish());

		List<List<String>> palabrasList = Arrays.stream(wordMatrix).map(Arrays::asList).toList();

		try {

			palabrasList.stream().flatMap(List::stream).forEach(palabra -> {
				try {
					List<RuleMatch> matches = languageTool.check(palabra);

					if (matches.isEmpty()) {
						System.out.println("La palabra '" + palabra + "' es válida.");
					} else {
						System.out.print("La palabra '" + palabra
								+ "' es inexistente o incorrecta. y la reemplazamos con espacio");
						palabra = "";
						System.out.println(" -->  nueva palabra [" + palabra + "agregartexto]");

					}
				} catch (IOException e) {
					e.printStackTrace();
				}

			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void validateWord2(String[][] wordMatrix) {
		JLanguageTool languageTool = new JLanguageTool(new Spanish());
		List<List<String>> palabrasList = Arrays.stream(wordMatrix).map(Arrays::asList).collect(Collectors.toList());

		List<List<String>> palabrasListModificada = new ArrayList<>();
		try {

			palabrasList.stream().forEach(palabras -> {
				List<String> palabrasModificadas = new ArrayList<>();

				palabras.forEach(palabra -> {
					try {
						List<RuleMatch> matches = languageTool.check(palabra);

						if (matches.isEmpty()) {
							System.out.println("La palabra '" + palabra + "' es válida.");
//                            palabrasModificadas.add(palabra);
						} else {
							System.out.print("La palabra '" + palabra
									+ "' es inexistente o incorrecta. y la reemplazamos con espacio");
							palabra = "";
							System.out.println(" -->  nueva palabra [" + palabra + "agregartexto]");

						}
						palabrasModificadas.add(palabra);

					} catch (IOException e) {
						e.printStackTrace();
					}
				});
				palabrasListModificada.add(palabrasModificadas);
			});

//            palabrasList = palabrasListModificada;
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("---1----");
		palabrasList.forEach(palabras -> {
			palabras.forEach(palabra -> {
				System.out.println("La palabra modificada es: " + palabra);
			});
		});
		palabrasListModificada.forEach(palabras -> {
			palabras.forEach(palabra -> {
				System.out.println("La palabra modificada es: " + palabra);
			});
		});

		System.out.println("---2----");
		System.out.println(palabrasList);
		System.out.println(palabrasListModificada);

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
