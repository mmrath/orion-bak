package uy.montdeo.orion.database.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import uy.montdeo.orion.database.AbstractEntity;

@Audited
@Entity
@Table(
	indexes = {	
		@Index(name = "IDX_PERSON_SEARCH_BY", columnList = "lastName, firstName, secondName, middleName")
	}
)
public class Person extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = -7098365122089312513L;
	
	/*  	FIELDS		 */
	@Audited
	@Column(length = 30, nullable = false)
	private String lastName;
	
	@Audited
	@Column(length = 30, nullable = false)
	private String firstName;
	
	@Audited
	@Column(length = 30, nullable = true)
	private String secondName;
	
	@Audited
	@Column(length = 30, nullable = true)
	private String middleName;
	
	@Audited
	@OneToMany(mappedBy = "person")
	private Set<PersonAddress> addresses;
	
	@Audited
	@OneToMany(mappedBy = "person")
	private Set<Document> documents;
	
	/*  	GETTERS AND SETTERS 		*/
	public String getLastName() {								return lastName;							}
	public void setLastName(String lastName) {					this.lastName = lastName;					}

	public String getFirstName() {								return firstName;							}
	public void setFirstName(String firstName) {				this.firstName = firstName;					}

	public String getSecondName() {								return secondName;							}
	public void setSecondName(String secondName) {				this.secondName = secondName;				}
	
	public String getMiddleName() {								return middleName;							}
	public void setMiddleName(String middleName) {				this.middleName = middleName;				}
	
	public Set<PersonAddress> getAddresses() {					return addresses;							}
	public void setAddresses(Set<PersonAddress> addresses) {	this.addresses = addresses;					}
	
	public Set<Document> getDocuments() {						return documents;							}
	public void setDocuments(Set<Document> documents) {			this.documents = documents;					}

}
