package uy.montdeo.orion.database.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import uy.montdeo.orion.database.AbstractTranslatableEntity;

/**
 * Entity that represents a country.
 * 
 * @author fabian.lobo
 * @since 1.0
 */
@Entity
@Table(
	uniqueConstraints = {	
		@UniqueConstraint(name = "UNQ_COUNTRY_ALPHA2CODE",  columnNames = "alpha2Code"),	
		@UniqueConstraint(name = "UNQ_COUNTRY_ALPHA3CODE",  columnNames = "alpha3Code"),
		@UniqueConstraint(name = "UNQ_COUNTRY_NUMERICCODE",  columnNames = "numericCode")
	},
	indexes = {	
		@Index(name = "IDX_COUNTRY_ALPHA2CODE", columnList = "alpha2Code"),
		@Index(name = "IDX_COUNTRY_ALPHA3CODE", columnList = "alpha3Code"),
		@Index(name = "IDX_COUNTRY_NUMERICCODE", columnList = "numericCode")
	}
)
public class Country extends AbstractTranslatableEntity implements Serializable {

	private static final long serialVersionUID = -8907565886671506088L;

	/*  	FIELDS		 */
	@Column(length = 2, nullable = false)
	private String alpha2Code;
	
	@Column(length = 3, nullable = true)
	private String alpha3Code;
	
	@Column(length = 3, nullable = true)
	private String numericCode;
	
	@Column(length = 5, nullable = true)
	private String phoneCode;
	
	/*  	GETTERS AND SETTERS 		*/
	public String getAlpha2Code() {										return alpha2Code;								}
	public void setAlpha2Code(String alpha2Code) {						this.alpha2Code = alpha2Code;					}
	
	public String getAlpha3Code() {										return alpha3Code;								}
	public void setAlpha3Code(String alpha3Code) {						this.alpha3Code = alpha3Code;					}
	
	public String getNumericCode() {									return numericCode;								}
	public void setNumericCode(String numericCode) {					this.numericCode = numericCode;					}
	
	public String getPhoneCode() {										return phoneCode;								}
	public void setPhoneCode(String phoneCode) {						this.phoneCode = phoneCode;						}
	
	/*
	 * (non-Javadoc)
	 * @see uy.montdeo.kudos.core.AbstractTranslatableEntity#getKey()
	 */
	@Override
	public String getKey() {
		return "country.".concat(getAlpha2Code());
	}
	
	
}
