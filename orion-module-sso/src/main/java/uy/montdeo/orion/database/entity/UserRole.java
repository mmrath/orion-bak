package uy.montdeo.orion.database.entity;

import static javax.persistence.FetchType.EAGER;
import static org.hibernate.annotations.FetchMode.SUBSELECT;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Fetch;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import uy.montdeo.orion.database.AbstractEntity;
import uy.montdeo.orion.database.converter.UpperCaseJpaAttributeConverter;

/**
 * Entity that represents a platform user's role.
 * 
 * @author fabian.lobo
 * @since 1.0
 */
@Audited
@Entity
@Table(
	uniqueConstraints = {	@UniqueConstraint(name = "UNQ_ROLE_NAME",  columnNames = "name")	},
	indexes = {	@Index(name = "IDX_ROLE_NAME", columnList = "name")	}
)
public class UserRole extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = -4489420045475077549L;
	
	/*  	FIELDS		 */
	@Audited
	@Column(length = 50, nullable = false)
	@Convert(converter = UpperCaseJpaAttributeConverter.class)
	private String name;
	
	@Column(length = 250, nullable = true)
	@Convert(converter = UpperCaseJpaAttributeConverter.class)
	@NotAudited
	private String comments;
	
	@Audited
	@Fetch(SUBSELECT)
	@JoinTable(
		joinColumns = @JoinColumn(foreignKey = @ForeignKey(name = "FK_ROLE_PERMISSION")), 
		inverseJoinColumns = @JoinColumn(foreignKey = @ForeignKey(name = "FK_PERMISSION_ROLE"))
	)
	@ManyToMany(fetch = EAGER)
	private Set<UserRolePermission> permissions;
	
	/*  	GETTERS AND SETTERS 		*/
	public String getName() {												return name;							}
	public void setName(String name) {										this.name = name;						}
		
	public String getComments() {											return comments;						}
	public void setComments(String comments) {								this.comments = comments;				}
		
	public Set<UserRolePermission> getPermissions() {						return permissions;						}
	public void setPermissions(Set<UserRolePermission> permissions) {		this.permissions = permissions;			}

}
