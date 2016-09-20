package uy.montdeo.orion.database.entity;

import static javax.persistence.FetchType.EAGER;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import uy.montdeo.orion.core.AbstractEntity;

/**
 * Entity that represents a physical address.
 * 
 * @author fabian.lobo
 * @since 1.0
 */
@Audited
@Entity
@Table(
	indexes = {	@Index(name = "IDX_CITY_SEARCH_BY", columnList = "street, number, zipCode")	}
)
public class Address extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = -2570679420753470854L;
	
	/*  	FIELDS		 */
	@Audited
	@Column(length = 100, nullable = false)
	private String street;
	
	@Audited
	@Column(length = 10, nullable = false)
	private String number;
	
	@Audited
	@Column(length = 10, nullable = true)
	private String zipCode;
	
	@Audited
	@Column(length = 10, nullable = true)
	private String block;
	
	@Audited
	@Column(length = 10, nullable = true)
	private String apartment;
	
	@Column(length = 250, nullable = true)
	@NotAudited
	private String comments;
	
	@JoinColumn(nullable = false, 	foreignKey = @ForeignKey(name = "FK_ADDRESS_CITY"))
	@ManyToOne(optional = false, fetch = EAGER)
	@NotAudited
	private City city;
	
	@Audited
	@Column(nullable = false, precision = 10, scale = 6)
	private Double latitude;
	
	@Audited
	@Column(nullable = false, precision = 10, scale = 6)
	private Double longitude;
	
	/*  	GETTERS AND SETTERS 		*/
	public String getStreet() {									return street;						}
	public void setStreet(String street) {						this.street = street;				}
	
	public String getNumber() {									return number;						}
	public void setNumber(String number) {						this.number = number;				}
	
	public String getZipCode() {								return zipCode;						}
	public void setZipCode(String zipCode) {					this.zipCode = zipCode;				}
	
	public String getBlock() {									return block;						}
	public void setBlock(String block) {						this.block = block;					}
	
	public String getApartment() {								return apartment;					}
	public void setApartment(String apartment) {				this.apartment = apartment;			}
	
	public String getComments() {								return comments;					}
	public void setComments(String comments) {					this.comments = comments;			}
	
	public City getCity() {										return city;						}
	public void setCity(City city) {							this.city = city;					}
	
	public Double getLatitude() {								return latitude;					}
	public void setLatitude(Double latitude) {					this.latitude = latitude;			}
	
	public Double getLongitude() {								return longitude;					}
	public void setLongitude(Double longitude) {				this.longitude = longitude;			}
	
}
