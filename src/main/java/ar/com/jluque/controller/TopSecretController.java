package ar.com.jluque.controller;

import org.glassfish.jaxb.core.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.jluque.dto.SatelliteDistanceDto;
import ar.com.jluque.dto.SatellitePositionDto;
import ar.com.jluque.dto.SatellitesDto;
import ar.com.jluque.service.TopSecretService;

@RestController
@RequestMapping
public class TopSecretController {

	private TopSecretService service;

	@Autowired
	public void setService(TopSecretService service) {
		this.service = service;
	}

	/**
	 * NIVEL 2
	 * 
	 * @param satellites
	 * @return
	 * @exception TODO libreria de exepciones 404 en caso de no encontrarlo
	 */
	@PostMapping("/topsecret/")
	public ResponseEntity<SatellitePositionDto> topSecret(@RequestBody SatellitesDto satellites) {
		return new ResponseEntity<>(service.topSecret(satellites), HttpStatus.OK);
	}

	/**
	 * NIVEL 3
	 * 
	 * @param satellites
	 * @return
	 */
	@PutMapping("/topsecret_split/{satellite_name}")
	public ResponseEntity<String> topSecretUpdate(@PathVariable(value = "satellite_name") String name,
			@RequestBody SatelliteDistanceDto satellites) {

		service.topSecretUpdate(name, satellites);
		return new ResponseEntity<>("Updated", HttpStatus.OK);
	}

	@GetMapping("/topsecret/")
	public ResponseEntity<SatellitePositionDto> getTopSecret() {
		return new ResponseEntity<>(service.getTopSecret(), HttpStatus.OK);
	}
}
