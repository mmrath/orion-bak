package uy.montdeo.orion.database;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Abstract class used to mark a persistent entity as translatable (meaning that its name or description should be looked
 * in the messages_*.properties files
 * 
 * @author fabian.lobo
 * @since 1.0
 */
@MappedSuperclass
public abstract class AbstractTranslatableEntity extends AbstractEntity {
	
	/*  	FIELDS		 */
	@Column(length = 30, nullable = false)
	@JsonIgnore
	private String key;
	
	@Column(nullable = false)
	@JsonIgnore
	private Boolean translatable;
	
	@Transient
	private String description;

	/*  	GETTERS AND SETTERS 		*/
	public String getKey() {									return key;										}
	public void setKey(String key) {							this.key = key;									}

	public Boolean getTranslatable() {							return translatable;							}
	public void setTranslatable(Boolean translatable) {			this.translatable = translatable;				}

	public String getDescription() {							return description;								}
	public void setDescription(String description) {			this.description = description;					}

		
}
