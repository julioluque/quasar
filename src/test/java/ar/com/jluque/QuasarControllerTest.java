package ar.com.jluque;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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

}
