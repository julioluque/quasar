package ar.com.jluque;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.awt.Point;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import ar.com.jluque.controller.QuasarController;
import ar.com.jluque.dao.DaoHandler;
import ar.com.jluque.exception.custom.NotFoundCustomException;
import ar.com.jluque.repository.SatelliteRepository;
import ar.com.jluque.service.QuasarService;

public class QuasarControllerTest {

	@InjectMocks
	private QuasarController controller;

	@Mock
	private QuasarService service;

	@Mock
	private DaoHandler dao;

	@Mock
	private SatelliteRepository repository;

	private MockMvc mockMvc;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	void getLocation200Test() throws Exception {
		double[] distance = { 100.0, 115.5, 142.7 };
		String arrayDistance = Arrays.toString(distance);
		arrayDistance = arrayDistance.substring(1, arrayDistance.length() - 1).replaceAll("\\s+", "");
		Point point = new Point(0, 1);

		when(service.getLocation(any())).thenReturn(point);
		mockMvc.perform(get("/quasar/distance/{array_distance}", arrayDistance)
				.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk());
	}

	@Test
	void getLocation4xxGenericTest() throws Exception {
		double[] distance = { 100.0, 115.5, 142.7 };
		String arrayDistance = Arrays.toString(distance);
		arrayDistance = arrayDistance.substring(1, arrayDistance.length() - 1).replaceAll("\\s+", "");

		when(service.getLocation(any())).thenThrow(NotFoundCustomException.class);
		mockMvc.perform(get("/quasar/distanceERROR/{array_distance}", arrayDistance)
				.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().is4xxClientError());
	}

	@Test
	void getLocation404NotFoundCustomTest() throws Exception {
		double[] distance = { 100.0, 115.5, 142.7 };
		String arrayDistance = Arrays.toString(distance);
		arrayDistance = arrayDistance.substring(1, arrayDistance.length() - 1).replaceAll("\\s+", "");

		when(service.getLocation(any())).thenThrow(NotFoundCustomException.class);
		mockMvc.perform(get("/quasar/distance/{array_distance}", arrayDistance)
				.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isNotFound());
	}

	@Test
	void getLocation500Test() throws Exception {
		when(service.getLocation(any())).thenThrow(RuntimeException.class);
		mockMvc.perform(get("/quasar/distance/xxx").contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isInternalServerError());
	}
}
