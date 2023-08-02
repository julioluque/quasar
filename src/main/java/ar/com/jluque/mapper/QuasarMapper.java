package ar.com.jluque.mapper;

import java.awt.Point;

import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer.Optimum;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;

public class QuasarMapper {

	public QuasarMapper() {
	}

	/**
	 * Formula de triangulacion para encontrar las coordenadas del origen (x, y) 
	 * 
	 * Dadas las coordenadas (x1, y1), (x2, y2) y (x3, y3)
	 * Con distancia d1, d2 y d3
	 * 
	 * https://www.geogebra.org/graphing
	 * 
	 * #1_Diferencias - calculamos las diferencias de coordenadas entre cada satélite y el origen:
     * dif1_x = x1 - x
     * dif1_y = y1 - y
     * dif2_x = x2 - x
     * dif2_y = y2 - y
     * dif3_x = x3 - x
     * dif3_y = y3 - y
     * 
     * #Distancias - establecemos un sistema de ecuaciones usando las distancias desde los satélites al origen:
     * (dif1_x)^2 + (dif1_y)^2 = d1^2
     * (dif2_x)^2 + (dif2_y)^2 = d2^2
     * (dif3_x)^2 + (dif3_y)^2 = d3^2
     * 
     * #3_Coordenadas - resolvemos el sistema de ecuaciones para encontrar las coordenadas (x, y) del origen.
	 */
	public static Point triangularFormula(double[][] positions, double d1, double d2, double d3) {
		// mapear cordenadas

		double x1 = positions[0][0];
		double y1 = positions[0][1];

		double x2 = positions[1][0];
		double y2 = positions[1][1];

		double x3 = positions[2][0];
		double y3 = positions[2][1];

		// Distancias mapear triangulacion.
		double A = 2 * (x2 - x1);
		double B = 2 * (y2 - y1);
		double C = Math.pow(d1, 2) - Math.pow(d2, 2) - Math.pow(x1, 2) + Math.pow(x2, 2) - Math.pow(y1, 2)
				+ Math.pow(y2, 2);
		double D = 2 * (x3 - x2);
		double E = 2 * (y3 - y2);
		double F = Math.pow(d2, 2) - Math.pow(d3, 2) - Math.pow(x2, 2) + Math.pow(x3, 2) - Math.pow(y2, 2)
				+ Math.pow(y3, 2);

		// 3_Coordenadas fuente o coordenadas del mensaje
		double x = (C * E - F * B) / (E * A - B * D);
		double y = (C - A * x) / B;

		return new Point((int) x, (int) y);
	}

	/**
	 * Usamos la libreria el artifact trilateration de la libreria lemmingapex
	 * @Libreria: https://github.com/lemmingapex/trilateration
	 */
	public static Point triangulacionLibrary(double[][] positions, double[] distances) {
		NonLinearLeastSquaresSolver solver = new NonLinearLeastSquaresSolver(
				new TrilaterationFunction(positions, distances), new LevenbergMarquardtOptimizer());
		Optimum optimum = solver.solve();
		double[] centroid = optimum.getPoint().toArray();
		
		return new Point((int) centroid[0], (int) centroid[1]);
	}

}