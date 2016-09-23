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
 * Entity that represents a state, province or any other country main geographical division.
 * 
 * @author fabian.lobo
 * @since 1.0
 */
@Entity
@Table(
	uniqueConstraints = {	@UniqueConstraint(name = "UNQ_STATE_CODE",  columnNames = "code")	},
	indexes = {	@Index(name = "IDX_STATE_CODE", columnList = "code")	}
)
public class State extends AbstractTranslatableEntity implements Serializable {

	private static final long serialVersionUID = -6723563283024480288L;
	
	/*  	FIELDS		 */
	@Column(length = 5, nullable = false)
	private String code;
	
	@ManyToOne(optional = false, fetch = EAGER)
	@JoinColumn(nullable = false, 	foreignKey = @ForeignKey(name = "FK_STATE_COUNTRY"))
	private Country country;
	
	/*  	GETTERS AND SETTERS 		*/
	public String getCode() {								return code;							}
	public void setCode(String code) {						this.code = code;						}
	
	public Country getCountry() {							return country;							}
	public void setCountry(Country country) {				this.country = country;					}

	/*
	 * (non-Javadoc)
	 * @see uy.montdeo.kudos.core.AbstractTranslatableEntity#getKey()
	 */
	@Override
	public String getKey() {
		return "state."
			.concat(getCountry().getAlpha2Code())
			.concat("_")
			.concat(getCode());
	}
}
