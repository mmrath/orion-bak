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

import uy.montdeo.orion.database.AbstractEntity;
import uy.montdeo.orion.database.converter.UpperCaseJpaAttributeConverter;

/**
 * Entity that represents a platform role permission.
 * 
 * @author fabian.lobo
 * @since 1.0
 */
@Audited
@Entity
@Table(
	uniqueConstraints = {	@UniqueConstraint(name = "UNQ_PERMISSION_TOKEN",  columnNames = "token")	},
	indexes = {	@Index(name = "IDX_PERMISSION_TOKEN", columnList = "token")	}
)
public class UserGroupPermission extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = -6056416864478072025L;

	/*  	FIELDS		 */
	@Audited
	@Column(length = 25, nullable = false)
	private String token;
	
	@Column(length = 100, nullable = true)
	@Convert(converter = UpperCaseJpaAttributeConverter.class)
	@NotAudited
	private String description;
	
	@Column(length = 250, nullable = true)
	@Convert(converter = UpperCaseJpaAttributeConverter.class)
	@NotAudited
	private String comments;
	
	/*  	GETTERS AND SETTERS 		*/
	public String getToken() {								return token;						}
	public void setToken(String token) {					this.token = token;					}
		
	public String getDescription() {						return description;					}
	public void setDescription(String description) {		this.description = description;		}
		
	public String getComments() {							return comments;					}
	public void setComments(String comments) {				this.comments = comments;			}

}
