package uy.montdeo.orion.core;

import static com.mysema.query.annotations.PropertyType.DATETIME;
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysema.query.annotations.QueryType;

import uy.montdeo.orion.core.database.listener.HibernateEventListener;

/**
 * Abstract class in top of the persistent entities' hierarchy.
 * 
 * @author fabian.lobo
 * @since 1.0
 *
 */
@EntityListeners({AuditingEntityListener.class, HibernateEventListener.class})
@MappedSuperclass
public abstract class AbstractEntity {
	
	/*  	FIELDS		 */
	@Id
	@Column()
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	@Version
	private Integer version;
	
	@Embedded
	private Auditory auditory;
	
	/*  	GETTERS AND SETTERS 		*/
	public Integer getId() {												return id;											}
	public void setId(Integer id) {											this.id = id;										}
	
	public Integer getVersion() {											return version;										}
	public void setVersion(Integer version) {								this.version = version;								}
		
	public Auditory getAuditory() {											return auditory;									}
	public void setAuditory(Auditory auditory) {							this.auditory = auditory;							}

	@Embeddable
	public static class Auditory implements Serializable {

		private static final long serialVersionUID = -8623079412597502594L;
				
		@Column(nullable = false, length = 50)
		@CreatedBy
		private String createdBy;
		
		@Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
		@CreatedDate
		@QueryType(DATETIME)
		private ZonedDateTime createdDate;
		
		@Column(nullable = false, length = 50)
		@LastModifiedBy
		private String lastModifiedBy;
		
		@Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
		@LastModifiedDate
		@QueryType(DATETIME)
		private ZonedDateTime lastModifiedDate;
		
		/*  	GETTERS AND SETTERS 		*/
		public String getCreatedBy() {											return createdBy;									}
		public void setCreatedBy(String createdBy) {							this.createdBy = createdBy;							}
			
		public ZonedDateTime getCreatedDate() {									return createdDate;									}
		public void setCreatedDate(ZonedDateTime createdDate) {					this.createdDate = createdDate;						}
		
		public String getLastModifiedBy() {										return lastModifiedBy;								}
		public void setLastModifiedBy(String lastModifiedBy) {					this.lastModifiedBy = lastModifiedBy;				}
			
		public ZonedDateTime getLastModifiedDate() {							return lastModifiedDate;							}
		public void setLastModifiedDate(ZonedDateTime lastModifiedDate) {		this.lastModifiedDate = lastModifiedDate;			}
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof AbstractEntity)) {
			return false;
		}
		AbstractEntity other = (AbstractEntity) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		try {
			return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
		} catch (JsonProcessingException e) {
			return "";
		}
	}

}
