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
import uy.montdeo.orion.core.AbstractEntity;

/**
 * Entity that represents a document.
 * 
 * @author fabian.lobo
 * @since 1.0
 */
@Entity
@Table(
	indexes = {	
		@Index(name = "IDX_DOCUMENT_VALUE", columnList = "value")
	}
)
public class Document extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = -3405592588685500603L;

	/*  	FIELDS		 */
	@ManyToOne(optional = false, fetch = EAGER)
	@JoinColumn(nullable = false, 	foreignKey = @ForeignKey(name = "FK_DOCUMENT_COUNTRY"))
	private Country country;
	
	@ManyToOne(optional = false, fetch = EAGER)
	@JoinColumn(nullable = false, 	foreignKey = @ForeignKey(name = "FK_DOCUMENT_DOCTYPE"))
	private DocumentType type;
	
	@Column(length = 30, nullable = false)
	private String value;

	/*  	GETTERS AND SETTERS 		*/
	public Country getCountry() {								return country;							}
	public void setCountry(Country country) {					this.country = country;					}

	public DocumentType getType() {								return type;							}
	public void setType(DocumentType type) {					this.type = type;						}

	public String getValue() {									return value;							}
	public void setValue(String value) {						this.value = value;						}
	
	

}
