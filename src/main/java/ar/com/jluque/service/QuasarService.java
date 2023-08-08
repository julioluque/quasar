package ar.com.jluque.service;

import java.awt.Point;

public interface QuasarService {

	public Point getLocation(double[] distances) throws Exception;

	public String getMessage(String[][] messages) throws Exception;

}
