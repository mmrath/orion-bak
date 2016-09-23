package uy.montdeo.orion.database.entity;

import static javax.persistence.FetchType.EAGER;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import uy.montdeo.orion.database.AbstractEntity;

@Audited
@Entity
public class PersonAddress extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 8776271598965926935L;
	
	/*  	FIELDS		 */
	@Audited
	@JoinColumn(nullable = false, 	foreignKey = @ForeignKey(name = "FK_PERADDR_PERSON"))
	@ManyToOne(optional = false, fetch = EAGER)
	private Person person;
	
	@Audited
	@JoinColumn(nullable = false, 	foreignKey = @ForeignKey(name = "FK_PERADDR_ADDRESS"))
	@ManyToOne(optional = false, fetch = EAGER)
	private Address address;
	
	@JoinColumn(nullable = false, 	foreignKey = @ForeignKey(name = "FK_PERADDR_ADDTYPE"))
	@ManyToOne(optional = false, fetch = EAGER)
	@NotAudited
	private AddressType type;
	
	@Audited
	@Column(nullable = false)
	private Boolean primary;

	/*  	GETTERS AND SETTERS 		*/
	public Person getPerson() {									return person;						}
	public void setPerson(Person person) {						this.person = person;				}

	public Address getAddress() {								return address;						}
	public void setAddress(Address address) {					this.address = address;				}

	public AddressType getType() {								return type;						}
	public void setType(AddressType type) {						this.type = type;					}

	public Boolean getPrimary() {								return primary;						}
	public void setPrimary(Boolean primary) {					this.primary = primary;				}

}
