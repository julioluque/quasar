package ar.com.jluque.controller;

import java.awt.Point;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.jluque.exception.custom.IllegalArgumentCustomException;
import ar.com.jluque.exception.custom.NotFoundCustomException;
import ar.com.jluque.exception.custom.QuasarBuissinesException;
import ar.com.jluque.service.QuasarService;

@RestController
@RequestMapping(path = "/quasar")
public class QuasarController {

	private QuasarService service;

	@Autowired
	public void setService(QuasarService service) {
		this.service = service;
	}

	/**
	 * * NIVEL 1 Fuente del mensaje:
	 * 
	 * @param distances - distancia al emisor tal cual se recibe en cada satelite.
	 * @return Point - las coordenadas de x e y del emisor del mensaje.
	 * @throws Exception
	 */
	@GetMapping("/distance/{array_distance}")
	public ResponseEntity<Point> getLocation(@PathVariable(value = "array_distance") double[] distances)
			throws NotFoundCustomException, IllegalArgumentCustomException, QuasarBuissinesException {
		return new ResponseEntity<>(service.getLocation(distances), HttpStatus.OK);
	}

	/**
	 * * NIVEL 1 Contenido del mensaje
	 * 
	 * @param messages - el mensaje tal cual es recibido por cada satelite.
	 * @return String - el mensaje tal cual lo genera el emisonr del mensaje.
	 * @throws Exception
	 */
	@PostMapping("/message/")
	public ResponseEntity<String> getMessage(@RequestBody String[][] messages)
			throws IllegalArgumentCustomException, QuasarBuissinesException {
		return new ResponseEntity<>(service.getMessage(messages), HttpStatus.OK);
	}

}
