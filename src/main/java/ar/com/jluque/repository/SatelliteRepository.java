package ar.com.jluque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.jluque.entity.SatelliteEntity;

@Repository
public interface SatelliteRepository extends JpaRepository<SatelliteEntity, String> {

	public SatelliteEntity findByName(String name);
}
