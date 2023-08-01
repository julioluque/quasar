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
	 * Fuente del mensaje:
	 * 
	 * @param distances - distancia al emisor tal cual se recibe en cada satelite.
	 * @return Point - las coordenadas de x e y del emisor del mensaje.
	 */
	@GetMapping("/distance/{array_distance}")
	public ResponseEntity<Point> getLocation(@PathVariable(value = "array_distance") double[] distances) {

		double[] miArray = { 100.0, 200.0 };
		distances = miArray;

		return new ResponseEntity<>(service.getLocation(distances), HttpStatus.OK);
	}

	/**
	 * Contenido del mensaje
	 * 
	 * @param messages - el mensaje tal cual es recibido por cada satelite.
	 * @return String - el mensaje tal cual lo genera el emisonr del mensaje.
	 */
	@PostMapping("/message/")
	public ResponseEntity<String> getMessage(@RequestBody String[][] messages) {

        
		String[] m1 = { "este", "", "", "mensaje", "" };
		String[] m2 = { "", "es", "", "", "secreto" };
		String[] m3 = { "este", "", "un", "", "" };

		String[][] mensajes = { m1, m2, m3 };

//		String[][] mensajes = { { "este", "", "", "mensaje", "" }, { "", "es", "", "", "secreto" },{ "este", "", "un", "", "" } };

		messages = mensajes;

		return new ResponseEntity<>(service.getMessage(messages), HttpStatus.OK);
	}

}
