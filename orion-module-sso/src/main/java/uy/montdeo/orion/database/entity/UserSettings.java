package uy.montdeo.orion.database.entity;

import java.io.Serializable;
import java.util.Locale;
import java.util.TimeZone;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.envers.Audited;

import uy.montdeo.orion.database.AbstractEntity;

/**
 * Entity that represents a platform user's settings
 * 
 * @author fabian.lobo
 * @since 1.0
 */
@Audited
@Entity
public class UserSettings extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 9009102814014684475L;

	/*  	FIELDS		 */
	@Audited
	@Column(length = 20, nullable = false)
	private Locale locale;
	
	@Audited
	@Column(length = 50, nullable = false)
	private TimeZone timezone;

	/*  	GETTERS AND SETTERS 		*/
	public Locale getLocale() {									return locale;							}
	public void setLocale(Locale locale) {						this.locale = locale;					}

	public TimeZone getTimezone() {								return timezone;						}
	public void setTimezone(TimeZone timezone) {				this.timezone = timezone;				}
		
	
}
