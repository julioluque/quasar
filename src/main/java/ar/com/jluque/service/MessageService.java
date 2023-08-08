package ar.com.jluque.service;

import java.util.List;

import ar.com.jluque.exception.custom.IllegalArgumentCustomException;
import ar.com.jluque.exception.custom.QuasarBuissinesException;

public interface MessageService {

	public List<List<String>> validateAndReplaceWords(String[][] wordMatrix) throws QuasarBuissinesException;

	public List<List<String>> matrixInversion(List<List<String>> messages) throws IllegalArgumentCustomException;

	public String messageBuilder(List<List<String>> messages) throws QuasarBuissinesException;
}
