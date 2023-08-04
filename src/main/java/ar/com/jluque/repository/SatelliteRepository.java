package ar.com.jluque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.jluque.dto.SatelliteDto;

@Repository
public interface SatelliteRepository extends JpaRepository<SatelliteDto, String>{

}
