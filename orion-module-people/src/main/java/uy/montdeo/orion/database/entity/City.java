package uy.montdeo.orion.database.entity;

import static javax.persistence.FetchType.EAGER;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import uy.montdeo.orion.database.AbstractTranslatableEntity;

/**
 * Entity that represents a city.
 * 
 * @author fabian.lobo
 * @since 1.0
 */
@Entity
@Table(
	uniqueConstraints = {	@UniqueConstraint(name = "UNQ_CITY_CODE",  columnNames = "code")	},
	indexes = {	@Index(name = "IDX_CITY_CODE", columnList = "code")	}
)
public class City extends AbstractTranslatableEntity implements Serializable {
	
	private static final long serialVersionUID = 6721561788701492551L;

	/*  	FIELDS		 */
	@Column(length = 5, nullable = false)
	private String code;
	
	@ManyToOne(optional = false, fetch = EAGER)
	@JoinColumn(nullable = false, 	foreignKey = @ForeignKey(name = "FK_CITY_STATE"))
	private State state;
	
	@Column(nullable = false, precision = 10, scale = 6)
	private Double latitude;
	
	@Column(nullable = false, precision = 10, scale = 6)
	private Double longitude;
	
	/*  	GETTERS AND SETTERS 		*/
	public String getCode() {									return code;							}
	public void setCode(String code) {							this.code = code;						}
	
	public State getState() {									return state;							}
	public void setState(State state) {							this.state = state;						}
	
	public Double getLatitude() {								return latitude;						}
	public void setLatitude(Double latitude) {					this.latitude = latitude;				}
	
	public Double getLongitude() {								return longitude;						}
	public void setLongitude(Double longitude) {				this.longitude = longitude;				}

	/*
	 * (non-Javadoc)
	 * @see uy.montdeo.kudos.core.AbstractTranslatableEntity#getKey()
	 */
	@Override
	public String getKey() {
		return "city."
				.concat(getState().getCountry().getAlpha2Code())
				.concat("_")
				.concat(getState().getCode())
				.concat("_")
				.concat(getCode());
	}
}
