package uy.montdeo.orion.database.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import com.fasterxml.jackson.annotation.JsonIgnore;

import uy.montdeo.orion.database.AbstractEntity;
import uy.montdeo.orion.database.converter.UpperCaseJpaAttributeConverter;

@Audited
@Entity
@Table(
	uniqueConstraints = {	@UniqueConstraint(name = "UNQ_ORGANIZATION_NAME",  columnNames = "name")	},
	indexes = {	@Index(name = "IDX_ORGANIZATION_SEARCH_BY", columnList = "name, active, deleted")	}
)
public class Organization extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = -5116023192190902131L;
	
	/*  	FIELDS		 */
	@Audited
	@Column(length = 50, nullable = false)
	@Convert(converter = UpperCaseJpaAttributeConverter.class)
	private String name;
	
	@Audited
	@Column
	private Boolean active;
	
	@Audited
	@Column
	@JsonIgnore
	private Boolean deleted;
	
	@Column(length = 250, nullable = true)
	@Convert(converter = UpperCaseJpaAttributeConverter.class)
	@NotAudited
	private String comments;
	
	/*  	GETTERS AND SETTERS 		*/
	public String getName() {											return name;									}
	public void setName(String name) {									this.name = name;								}
		
	public Boolean isActive() {											return active;									}
	public void setActive(Boolean active) {								this.active = active;							}
		
	public Boolean isDeleted() {										return deleted;									}
	public void setDeleted(Boolean deleted) {							this.deleted = deleted;							}
		
	public String getComments() {										return comments;								}
	public void setComments(String comments) {							this.comments = comments;						}

}
