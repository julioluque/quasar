package ar.com.jluque.mapper;

import static ar.com.jluque.utils.QuasarConstant.SATELLITE_KENOBI;
import static ar.com.jluque.utils.QuasarConstant.SATELLITE_SATO;
import static ar.com.jluque.utils.QuasarConstant.SATELLITE_SKYWALKER;

import java.awt.Point;

import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer.Optimum;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;

public class QuasarMapper {

	public QuasarMapper() {
	}

	public static double[][] getPositions() {
		return new double[][] { { SATELLITE_KENOBI[0], SATELLITE_KENOBI[1] },
				{ SATELLITE_SKYWALKER[0], SATELLITE_SKYWALKER[1] }, { SATELLITE_SATO[0], SATELLITE_SATO[1] } };
	}


}