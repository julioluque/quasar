package ar.com.jluque.service;

import java.util.List;

public interface MessageService {

	public List<List<String>> validateAndReplaceWords(String[][] wordMatrix);

	public List<List<String>> matrixInversion(List<List<String>> messages);

	public String messageBuilder(List<List<String>> messages);
}
