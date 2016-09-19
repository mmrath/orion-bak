package uy.montdeo.orion.core;

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
	
	@Transient
	private String name;

	public String getName() {									return name;						}
	public void setName(String name) {							this.name = name;					}
	
	/**
	 * Key used to look for the translatable text
	 * 
	 * @return the key used by the entity implementing this interface
	 */
	@JsonIgnore
	public abstract String getKey();

}
