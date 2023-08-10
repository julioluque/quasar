package ar.com.jluque;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ar.com.jluque.dao.DaoHandler;
import ar.com.jluque.entity.SatelliteEntity;
import ar.com.jluque.exception.custom.IllegalArgumentCustomException;
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

	@Test
	void getMessageOkTest() {

		List<String> lista1 = new ArrayList<>();
		lista1.add("este");
		lista1.add("");
		lista1.add("");
		lista1.add("mensaje");
		lista1.add("");
		List<String> lista2 = new ArrayList<>();
		lista2.add("");
		lista2.add("es");
		lista2.add("");
		lista2.add("");
		lista2.add("");
		List<String> lista3 = new ArrayList<>();
		lista3.add("este");
		lista3.add("");
		lista3.add("un");
		lista3.add("");
		lista3.add("secreto");

		List<List<String>> splitedMessages = new ArrayList<>();
		splitedMessages.add(lista1);
		splitedMessages.add(lista2);
		splitedMessages.add(lista3);

		when(messageService.validateAndReplaceWords(any())).thenReturn(splitedMessages);

		List<String> listaInvertida1 = new ArrayList<>();
		listaInvertida1.add("este");
		listaInvertida1.add("");
		listaInvertida1.add("este");

		List<String> listaInvertida2 = new ArrayList<>();
		listaInvertida2.add("");
		listaInvertida2.add("es");
		listaInvertida2.add("");

		List<String> listaInvertida3 = new ArrayList<>();
		listaInvertida3.add("");
		listaInvertida3.add("");
		listaInvertida3.add("un");

		List<String> listaInvertida4 = new ArrayList<>();
		listaInvertida4.add("mensaje");
		listaInvertida4.add("");
		listaInvertida4.add("");

		List<String> listaInvertida5 = new ArrayList<>();
		listaInvertida5.add("");
		listaInvertida5.add("");
		listaInvertida5.add("secreto");

		List<List<String>> messagesInverted = new ArrayList<>();
		splitedMessages.add(listaInvertida1);
		splitedMessages.add(listaInvertida2);
		splitedMessages.add(listaInvertida3);
		splitedMessages.add(listaInvertida4);
		splitedMessages.add(listaInvertida5);
		when(messageService.matrixInversion(anyList())).thenReturn(messagesInverted);

		String messageExpected = "este es un mensaje secretos";
		when(messageService.messageBuilder(anyList())).thenReturn(messageExpected);

		String[][] messagesInput = { { "este", "es", "un", "mensae", "asdf" },
				{ "este", "asdf", "un", "fqdf", "secreto" }, { "este", "es", "un", "mensaje", "asdf" } };

		assertEquals(messageExpected, service.getMessage(messagesInput));
	}

	@Test
	void getMessageIllegalArgumentCustomExceptionTest() {

		List<String> lista1 = new ArrayList<>();
		lista1.add("este");
		lista1.add("");
		lista1.add("");
		lista1.add("mensaje");
		lista1.add("");
		List<String> lista2 = new ArrayList<>();
		lista2.add("");
		lista2.add("es");
		lista2.add("");
		lista2.add("");
		lista2.add("");
		List<String> lista3 = new ArrayList<>();
		lista3.add("este");
		lista3.add("");
		lista3.add("un");
		lista3.add("");
		lista3.add("secreto");

		List<List<String>> splitedMessages = new ArrayList<>();
		splitedMessages.add(lista1);
		splitedMessages.add(lista2);
		splitedMessages.add(lista3);

		when(messageService.validateAndReplaceWords(any())).thenReturn(splitedMessages);

		List<String> listaInvertida1 = new ArrayList<>();
		listaInvertida1.add("este");
		listaInvertida1.add("");
		listaInvertida1.add("este");

		List<String> listaInvertida2 = new ArrayList<>();
		listaInvertida2.add("");
		listaInvertida2.add("es");
		listaInvertida2.add("");

		List<String> listaInvertida3 = new ArrayList<>();
		listaInvertida3.add("");
		listaInvertida3.add("");
		listaInvertida3.add("un");

		List<String> listaInvertida4 = new ArrayList<>();
		listaInvertida4.add("mensaje");
		listaInvertida4.add("");
		listaInvertida4.add("");

		List<String> listaInvertida5 = new ArrayList<>();
		listaInvertida5.add("");
		listaInvertida5.add("");
		listaInvertida5.add("secreto");

		List<List<String>> messagesInverted = new ArrayList<>();
		splitedMessages.add(listaInvertida1);
		splitedMessages.add(listaInvertida2);
		splitedMessages.add(listaInvertida3);
		splitedMessages.add(listaInvertida4);
		splitedMessages.add(listaInvertida5);
		when(messageService.matrixInversion(anyList())).thenReturn(messagesInverted);

		String messageExpected = "este es un mensaje secretos";
		when(messageService.messageBuilder(anyList())).thenReturn(messageExpected);

		String[][] messagesInput = { { "este", "es", "un", "mensae", "asdf", "elementoextra" },
				{ "este", "asdf", "un", "fqdf", "secreto" }, { "este", "es", "un", "mensaje", "asdf" } };

		assertThrows(IllegalArgumentCustomException.class, () -> service.getMessage(messagesInput));

	}

	@Test
	void getMessageQuasarBuissinesExceptionTest() {

		List<String> lista1 = new ArrayList<>();
		lista1.add("este");
		lista1.add("");
		lista1.add("");
		lista1.add("mensaje");
		lista1.add("");
		List<String> lista2 = new ArrayList<>();
		lista2.add("");
		lista2.add("es");
		lista2.add("");
		lista2.add("");
		lista2.add("");
		List<String> lista3 = new ArrayList<>();
		lista3.add("este");
		lista3.add("");
		lista3.add("un");
		lista3.add("");
		lista3.add("secreto");

		List<List<String>> splitedMessages = new ArrayList<>();
		splitedMessages.add(lista1);
		splitedMessages.add(lista2);
		splitedMessages.add(lista3);

		when(messageService.validateAndReplaceWords(any())).thenThrow(QuasarBuissinesException.class);

		String[][] messagesInput = { { "este", "es", "un", "mensae", "asdf" },
				{ "este", "asdf", "un", "fqdf", "secreto" }, { "este", "es", "un", "mensaje", "asdf" } };

		assertThrows(QuasarBuissinesException.class, () -> service.getMessage(messagesInput));

	}

}
