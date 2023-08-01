package ar.com.jluque.controller;

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
import ar.com.jluque.service.QuasarService;

@RestController
@RequestMapping
public class TopSecretController {

	private QuasarService service;

	@Autowired
	public void setService(QuasarService service) {
		this.service = service;
	}

	/**
	 * NIVEL 2
	 * 
	 * @param satellites
	 * @return
	 * @exception 404 en caso de no encontrarlo
	 */
	@PostMapping("/topsecret/")
	public ResponseEntity<SatellitePositionDto> topSecret(@RequestBody SatellitesDto satellites) {

//		Gson gson = new Gson();
//		System.out.println(gson.toJson(satellites));
//		
//		String json = "{\"satellites\":[{\"name\":\"kenobi\",\"distance\":100.0,\"message\":[\"este\",\"\",\"\",\"mensaje\",\"\"]},{\"name\":\"skywalker\",\"distance\":115.5,\"message\":[\"\",\"es\",\"\",\"\",\"secreto\"]},{\"name\":\"sato\",\"distance\":142.7,\"message\":[\"este\",\"\",\"un\",\"\",\"\"]}]}";
//		satellites = gson.fromJson(json, SatellitesDto.class);

//		SatelliteDto kenobi = new SatelliteDto();
//		kenobi.setName("kenobi");
//		kenobi.setDistance(100.0);
//		kenobi.setMessage(new String[] { "", "es", "", "", "secreto" });
//
//		SatelliteDto skywalker = new SatelliteDto();
//		skywalker.setName("kenobi");
//		skywalker.setDistance(100.0);
//		skywalker.setMessage(new String[] { "", "es", "", "", "secreto" });
//
//		SatelliteDto sato = new SatelliteDto();
//		sato.setName("kenobi");
//		sato.setDistance(100.0);
//		sato.setMessage(new String[] { "este", "", "un", "", "" });
//
//		List<SatelliteDto> satellitesList = new ArrayList<SatelliteDto>();
//		satellitesList.add(kenobi);
//		satellitesList.add(skywalker);
//		satellitesList.add(sato);
//
//		satellites.setSatellites(satellitesList);
//		System.out.println(gson.toJson(satellites));

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
		service.topSecretUpdate(satellites);
		return new ResponseEntity<>("Updated", HttpStatus.OK);
	}

	@GetMapping("/topsecret/")
	public ResponseEntity<SatellitePositionDto> getTopSecret() {
		return new ResponseEntity<>(service.getTopSecret(), HttpStatus.OK);
	}
}
