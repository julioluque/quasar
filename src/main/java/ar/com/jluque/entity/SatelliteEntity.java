package ar.com.jluque.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "SATELLITE")
public class SatelliteEntity {

	@Id
	@Column(name = "SAT_NAME", unique = true, nullable = false)
	private String name;

	@Column(name = "SAT_CORD_X", unique = false, nullable = false)
	private double x;

	@Column(name = "SAT_CORD_Y", unique = false, nullable = false)
	private double y;

}
