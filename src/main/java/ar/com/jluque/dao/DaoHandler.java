package ar.com.jluque.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import ar.com.jluque.dto.SatellitePositionDto;
import ar.com.jluque.entity.SatelliteEntity;
import ar.com.jluque.exception.custom.NotFoundCustomException;
import ar.com.jluque.repository.SatelliteRepository;

@Component
public class DaoHandler {

	private SatelliteRepository repository;

	@Autowired
	public void setRepository(SatelliteRepository repository) {
		this.repository = repository;
	}

	public List<SatelliteEntity> recoverSatellitesData() throws NotFoundCustomException {
		Optional<List<SatelliteEntity>> entityList = Optional.ofNullable(repository.findAll());
		entityList.orElseThrow(() -> new NotFoundCustomException("No se encontraron Satellites en la base de datos"));
		return entityList.get();
	}

	public void saveTransmiterData(SatellitePositionDto satellitePositionDto, SatelliteEntity satelliteEntity)
			throws DataAccessException {
		satelliteEntity.setX(satellitePositionDto.getPosition().getX());
		satelliteEntity.setY(satellitePositionDto.getPosition().getY());
		satelliteEntity.setMessage(satellitePositionDto.getMessage());

		repository.save(satelliteEntity);
	}

	public void saveEntity(SatellitePositionDto satellitePositionDto, String name) throws DataAccessException {
		SatelliteEntity satelliteEntity = new SatelliteEntity();
		satelliteEntity.setName(name);
		satelliteEntity.setX(satellitePositionDto.getPosition().getX());
		satelliteEntity.setY(satellitePositionDto.getPosition().getY());
		
		repository.save(satelliteEntity);
	}
	
	public void saveMessages(List<SatelliteEntity> satelliteEntity) throws DataAccessException {
		repository.saveAll(satelliteEntity);
	}
}
