package ar.com.jluque.service;

import ar.com.jluque.dto.SatelliteDistanceDto;
import ar.com.jluque.dto.SatellitePositionDto;
import ar.com.jluque.dto.SatellitesDto;

public interface TopSecretService {

	public SatellitePositionDto topSecret(SatellitesDto satellites) throws Exception;

	public void topSecretUpdate(String name, SatelliteDistanceDto satellites) throws Exception;

	public SatellitePositionDto getTopSecret();
}
