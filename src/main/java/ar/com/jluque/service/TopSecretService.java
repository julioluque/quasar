package ar.com.jluque.service;

import ar.com.jluque.dto.SatelliteDistanceDto;
import ar.com.jluque.dto.SatellitePositionDto;
import ar.com.jluque.dto.SatellitesDto;

public interface TopSecretService {

	public SatellitePositionDto topSecret(SatellitesDto satellites);

	public void topSecretUpdate(String name, SatelliteDistanceDto satellites);

	public SatellitePositionDto getTopSecret();
}
