package ar.com.jluque.service;

import java.awt.Point;

import ar.com.jluque.exception.custom.QuasarBuissinesException;

public interface PositionService {

	public Point triangularFormula(double[][] positions, double[] distances) throws QuasarBuissinesException;

	public Point triangulacionLibrary(double[][] positions, double[] distances) throws QuasarBuissinesException;
}
