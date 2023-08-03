package ar.com.jluque.service;

import java.util.List;

public interface MessageService {

	public List<List<String>> validateAndSplitWords(String[][] wordMatrix);

	public List<List<String>> matrixInversion(List<List<String>> messages);

	public String buildMessage(List<List<String>> messages);
}
