package uy.montdeo.orion.database.entity;

import static com.mysema.query.annotations.PropertyType.DATETIME;
import static javax.persistence.FetchType.EAGER;

import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mysema.query.annotations.QueryType;

import uy.montdeo.orion.database.AbstractEntity;
import uy.montdeo.orion.database.converter.UpperCaseJpaAttributeConverter;

/**
 * Entity that represents a platform user
 * 
 * @author fabian.lobo
 * @since 1.0
 */
@Audited
@Entity
@Table(
	uniqueConstraints = {	@UniqueConstraint(name = "UNQ_USER_USERNAME",  columnNames = "username")	},
	indexes = {	@Index(name = "IDX_USER_SEARCH_BY", columnList = "username, locked, deleted")	}
)
public class User extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = -4207037751056834202L;
	
	/*  	FIELDS		 */
	@Audited
	@JoinColumn(nullable = false, foreignKey = @ForeignKey(name = "FK_USER_GROUP"))
	@ManyToOne(optional = false, fetch = EAGER)
	private UserGroup group;
	
	@Audited
	@JoinColumn(nullable = false, foreignKey = @ForeignKey(name = "FK_USER_SETTINGS"))
	@OneToOne(optional = false, fetch = EAGER)
	private UserSettings settings;
	
	@Audited
	@Column(length = 50, nullable = false)
	private String username;
	
	@Audited
	@Column(length = 150, nullable = false)
	@JsonIgnore
	private String credentials;
	
	@Column(length = 16, nullable = false)
	@JsonIgnore
	@NotAudited
	private String salt;
	
	@Column(nullable = true, columnDefinition = "TIMESTAMP")
	@NotAudited
	@QueryType(DATETIME)
	private ZonedDateTime lastLogin;
	
	@Column(nullable = true, columnDefinition = "TIMESTAMP")
	@NotAudited
	@QueryType(DATETIME)
	private ZonedDateTime lastFailedLogin;
	
	@Column(nullable = false)
	@NotAudited
	private Integer attempts;
	
	@Audited
	@Column
	private Boolean locked;
	
	@Audited
	@Column
	@JsonIgnore
	private Boolean deleted;
	
	@JsonIgnore
	@NotAudited
	@Transient
	private Boolean encrypted;
	
	@Column(length = 250, nullable = true)
	@Convert(converter = UpperCaseJpaAttributeConverter.class)
	@NotAudited
	private String comments;
	
	/*  	GETTERS AND SETTERS 		*/
	public UserGroup getGroup() {										return group;									}
	public void setGroup(UserGroup group) {								this.group = group;								}
	
	public UserSettings getSettings() {									return settings;								}
	public void setSettings(UserSettings settings) {					this.settings = settings;						}
	
	public String getUsername() {										return username;								}
	public void setUsername(String username) {							this.username = username;						}
		
	public String getCredentials() {									return credentials;								}
	public void setCredentials(String credentials) {					this.credentials = credentials;					}
	
	public String getSalt() {											return salt;									}
	public void setSalt(String salt) {									this.salt = salt;								}
		
	public ZonedDateTime getLastLogin() {								return lastLogin;								}
	public void setLastLogin(ZonedDateTime lastLogin) {					this.lastLogin = lastLogin;						}
		
	public ZonedDateTime getLastFailedLogin() {							return lastFailedLogin;							}
	public void setLastFailedLogin(ZonedDateTime lastFailedLogin) {		this.lastFailedLogin = lastFailedLogin;			}
		
	public Integer getAttempts() {										return attempts;								}
	public void setAttempts(Integer attempts) {							this.attempts = attempts;						}
	
	public Boolean isLocked() {											return locked;									}
	public void setLocked(Boolean locked) {								this.locked = locked;							}
		
	public Boolean isDeleted() {										return deleted;									}
	public void setDeleted(Boolean deleted) {							this.deleted = deleted;							}
		
	public Boolean isEncrypted() {										return encrypted;								}
	public void setEncrypted(Boolean encrypted) {						this.encrypted = encrypted;						}
	
	public String getComments() {										return comments;								}
	public void setComments(String comments) {							this.comments = comments;						}
		
	/*  	CONSTRUCTORS		 */
	public User() {
		this.group = new UserGroup();
		this.settings = new UserSettings();
	}

}
