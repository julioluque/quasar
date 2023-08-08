package ar.com.jluque.service;

import java.awt.Point;

import ar.com.jluque.exception.custom.IllegalArgumentCustomException;
import ar.com.jluque.exception.custom.NotFoundCustomException;
import ar.com.jluque.exception.custom.QuasarBuissinesException;

public interface QuasarService {

	public Point getLocation(double[] distances)
			throws NotFoundCustomException, IllegalArgumentCustomException, QuasarBuissinesException;

	public String getMessage(String[][] messages) throws IllegalArgumentCustomException, QuasarBuissinesException;

}
