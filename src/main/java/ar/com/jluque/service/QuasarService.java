package ar.com.jluque.service;

import java.awt.Point;

import ar.com.jluque.dto.SatelliteDistanceDto;
import ar.com.jluque.dto.SatellitePositionDto;
import ar.com.jluque.dto.SatellitesDto;

public interface QuasarService {

	public Point getLocation(double[] distances);

	public String getMessage(String[][] messages);

	public SatellitePositionDto topSecret(SatellitesDto satellites);

	public void topSecretUpdate(SatelliteDistanceDto satellites);

	public SatellitePositionDto getTopSecret();

}
