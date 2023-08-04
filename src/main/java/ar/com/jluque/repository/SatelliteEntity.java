package ar.com.jluque.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "SATELLITE")
public class SatelliteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SAT_NAME", unique = true, nullable = false)
	private String name;

	@Column(name = "SAT_DIST", unique = false, nullable = false)
	private double distance;

	@Column(name = "SAT_MSG", unique = false, nullable = false)
	private String[] message;

}
