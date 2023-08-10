package ar.com.jluque;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.awt.Point;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ar.com.jluque.dao.DaoHandler;
import ar.com.jluque.entity.SatelliteEntity;
import ar.com.jluque.exception.custom.QuasarBuissinesException;
import ar.com.jluque.exception.custom.QuasarBussinesNotFoundException;
import ar.com.jluque.repository.SatelliteRepository;
import ar.com.jluque.service.MessageService;
import ar.com.jluque.service.PositionService;
import ar.com.jluque.service.impl.QuasarServiceImpl;

class QuasarServiceTest {

	@InjectMocks
	private QuasarServiceImpl service;

	@Mock
	private MessageService messageService;

	@Mock
	private PositionService positionService;

	@Mock
	private DaoHandler dao;

	@Mock
	private SatelliteRepository repository;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void getLocationOKTest() throws QuasarBussinesNotFoundException {
		double[] inputDistances = { 100.0, 115.5, 142.7 };
		when(dao.recoverSatellitesData()).thenReturn(new ArrayList<SatelliteEntity>());
		Point point = new Point();
		point.setLocation(-487, 1557);
		when(positionService.triangulacionLibrary(any(), any())).thenReturn(point);
		int expectedX = -487;
		assertEquals(expectedX, service.getLocation(inputDistances).getX());
	}

	@Test
	void getLocationNotFoundCustomExceptionTest() throws QuasarBussinesNotFoundException {
		double[] inputDistances = { 100.0, 115.5, 142.7 };
		when(dao.recoverSatellitesData()).thenReturn(new ArrayList<SatelliteEntity>());
		when(positionService.triangulacionLibrary(any(), any())).thenThrow(QuasarBuissinesException.class);
		assertThrows(QuasarBussinesNotFoundException.class, () -> service.getLocation(inputDistances));
	}

	
	
}
