package ar.com.jluque;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.awt.Point;

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
	void getLotation200Test() throws Exception {
		double[] distance = { 100.0, 115.5, 142.7 };
		Point point = new Point(0, 1);
		when(service.getLocation(any())).thenReturn(point);
		mockMvc.perform(get("/distance/{array_distance}", distance).contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
	}
}
