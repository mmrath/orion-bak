package uy.montdeo.orion.database.entity;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.EAGER;
import static org.hibernate.annotations.FetchMode.SUBSELECT;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.envers.Audited;

import uy.montdeo.orion.database.AbstractEntity;
import uy.montdeo.orion.database.converter.UpperCaseJpaAttributeConverter;

@Audited
@Entity
@Table(
	indexes = {	@Index(name = "IDX_GROUP_NAME", columnList = "name")	}
)
public class UserGroup extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 2217788743565348663L;
	
	public enum DataScope {
		ALL, ORGANIZATION, GROUP
	}
	
	/*  	FIELDS		 */
	@Audited
	@Column(length = 50, nullable = false)
	@Convert(converter = UpperCaseJpaAttributeConverter.class)
	private String name;
	
	@Audited
	@Column(length = 50, nullable = false)
	private String landingUrl;
	
	@Audited
	@JoinColumn(nullable = false, foreignKey = @ForeignKey(name = "FK_GROUP_ORGANIZATION"))
	@ManyToOne(optional = false, fetch = EAGER)
	private Organization organization;
	
	@Audited
	@Column(length = 20, nullable = false)
	@Enumerated(STRING)
	private DataScope scope;
	
	@Audited
	@Fetch(SUBSELECT)
	@JoinTable(
		joinColumns = @JoinColumn(foreignKey = @ForeignKey(name = "FK_GROUP_USER")), 
		inverseJoinColumns = @JoinColumn(foreignKey = @ForeignKey(name = "FK_USER_GROUP"))
	)
	@ManyToMany(fetch = EAGER)
	private Set<User> users;
	
	@Audited
	@Fetch(SUBSELECT)
	@JoinTable(
		joinColumns = @JoinColumn(foreignKey = @ForeignKey(name = "FK_GROUP_PERMISSION")), 
		inverseJoinColumns = @JoinColumn(foreignKey = @ForeignKey(name = "FK_PERMISSION_GROUP"))
	)
	@ManyToMany(fetch = EAGER)
	private Set<UserGroupPermission> permissions;
	
	/*  	GETTERS AND SETTERS 		*/
	public String getName() {											return name;									}
	public void setName(String name) {									this.name = name;								}
	
	public String getLandingUrl() {										return landingUrl;								}
	public void setLandingUrl(String landingUrl) {						this.landingUrl = landingUrl;					}
	
	public Organization getOrganization() {								return organization;							}
	public void setOrganization(Organization organization) {			this.organization = organization;				}
	
	public DataScope getScope() {										return scope;									}
	public void setScope(DataScope scope) {								this.scope = scope;								}
	
	public Set<User> getUsers() {										return users;									}
	public void setUsers(Set<User> users) {								this.users = users;								}
	
	public Set<UserGroupPermission> getPermissions() {					return permissions;								}
	public void setPermissions(Set<UserGroupPermission> permissions) {	this.permissions = permissions;					}

}
