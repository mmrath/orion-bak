package uy.montdeo.orion.database.entity;

import static javax.persistence.FetchType.EAGER;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import uy.montdeo.orion.database.AbstractEntity;

/**
 * Entity that represents a document format for a given document type and country.
 * 
 * @author fabian.lobo
 * @since 1.0
 */
@Entity
public class DocumentFormat extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 564065637075452922L;
	
	/*  	FIELDS		 */
	@ManyToOne(optional = false, fetch = EAGER)
	@JoinColumn(nullable = false, 	foreignKey = @ForeignKey(name = "FK_DOCFORMAT_COUNTRY"))
	private Country country;
	
	@ManyToOne(optional = false, fetch = EAGER)
	@JoinColumn(nullable = false, 	foreignKey = @ForeignKey(name = "FK_DOCFORMAT_DOCTYPE"))
	private DocumentType type;
	
	@Column(length = 30, nullable = false)
	private String pattern;

	/*  	GETTERS AND SETTERS 		*/
	public Country getCountry() {								return country;							}
	public void setCountry(Country country) {					this.country = country;					}

	public DocumentType getType() {								return type;							}
	public void setType(DocumentType type) {					this.type = type;						}

	public String getPattern() {								return pattern;							}
	public void setPattern(String pattern) {					this.pattern = pattern;					}
	
	

}
