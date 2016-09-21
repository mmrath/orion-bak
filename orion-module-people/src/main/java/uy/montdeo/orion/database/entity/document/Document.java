package uy.montdeo.orion.database.entity.document;

import static javax.persistence.FetchType.EAGER;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import uy.montdeo.orion.database.AbstractEntity;
import uy.montdeo.orion.database.entity.address.Country;

/**
 * Entity that represents a document.
 * 
 * @author fabian.lobo
 * @since 1.0
 */
@Audited
@Entity
@Table(
	indexes = {	
		@Index(name = "IDX_DOCUMENT_VALUE", columnList = "value")
	}
)
public class Document extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = -3405592588685500603L;

	/*  	FIELDS		 */
	@JoinColumn(nullable = false, 	foreignKey = @ForeignKey(name = "FK_DOCUMENT_COUNTRY"))
	@ManyToOne(optional = false, fetch = EAGER)
	@NotAudited
	private Country country;
	
	@JoinColumn(nullable = false, 	foreignKey = @ForeignKey(name = "FK_DOCUMENT_DOCTYPE"))
	@ManyToOne(optional = false, fetch = EAGER)
	@NotAudited
	private DocumentType type;
	
	@Audited
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
