package ar.com.jluque.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.languagetool.JLanguageTool;
import org.languagetool.language.Spanish;
import org.languagetool.rules.RuleMatch;
import org.springframework.stereotype.Service;

import ar.com.jluque.service.MessageService;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class MessageServiceImpl implements MessageService {

	public List<List<String>> validateAndSplitWords(String[][] wordMatrix) {
		JLanguageTool languageTool = new JLanguageTool(new Spanish());
		List<List<String>> mensajeOriginalList = Arrays.stream(wordMatrix).map(Arrays::asList)
				.collect(Collectors.toList());
		log.info("Original Message{}", mensajeOriginalList);

		List<List<String>> mensajeModificadoList = new ArrayList<>();

		mensajeOriginalList.stream().forEach(palabras -> {
			List<String> palabrasModificadas = new ArrayList<>();

			palabras.forEach(palabra -> {
				try {
					List<RuleMatch> matches = languageTool.check(palabra);
					if (!matches.isEmpty()) {
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

		log.info("Updated Message{}", mensajeModificadoList);
		return mensajeModificadoList;
	}

	/**
	 * armar un alista final x1 x2 x3 x4 y1 y2 y3 y4 z1 z2 z3 z4
	 * 
	 * x1 y1 z1 x2 y2 z2 x3 y3 z3 x4 y4 z4
	 * 
	 * @param messages
	 * @return
	 */
	public List<List<String>> matrixInversion(List<List<String>> messages) {

		int filaSize = messages.size();
		int columnaSize = messages.get(0).size();

		List<List<String>> matrizInvertida = new ArrayList<>();

		for (int c = 0; c < columnaSize; c++) {
			matrizInvertida.add(new ArrayList<>());
		}

		for (int newY = 0; newY < filaSize; newY++) {
			List<String> fila = messages.get(newY);
			for (int newX = 0; newX < columnaSize; newX++) {
				matrizInvertida.get(newX).add(fila.get(newX));
			}
		}

		// revision matriz invertida.
		log.info(matrizInvertida);

		return matrizInvertida;
	}

	public String buildMessage(List<List<String>> messages) {
//		x1 y1 z1
//		x2 y2 z2
//		x3 y3 z3
//		x4 y4 z4
		String message = "";
		for (List<String> msgs : messages) {
			String aux = "";
			log.info(msgs);
			for (String m : msgs) {
				if (aux.equals("") && m.equals("")) {
					log.info("case 1");
					aux = m;
				} else if (aux.equals("") && !m.equals("")) {
					log.info("case 2");
					aux = m;
				} else if (!aux.equals("") && !m.equals("")) {
					log.info("case 3");
					if (!aux.equalsIgnoreCase(m)) 
						log.error("Error de comparacion de mensaje. '{}' != '{}'", aux, m);
					aux = m;
				}
			}
			message = message.concat(aux).concat(" ");
		}

		log.info("El mensaje es: {}", message);

		// tomar primer elemento de cada lista. y compararlos
		return message.trim();
	}
}
