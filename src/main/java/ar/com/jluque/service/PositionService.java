package ar.com.jluque.service;

import java.awt.Point;

public interface PositionService {

	public Point triangularFormula(double[][] positions, double[] distances);

	public Point triangulacionLibrary(double[][] positions, double[] distances);
}
