package uy.montdeo.orion.database.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import uy.montdeo.orion.database.AbstractTranslatableEntity;

/**
 * Entity that represents an address type.
 * 
 * @author fabian.lobo
 * @since 1.0
 */
@Entity
@Table(
		uniqueConstraints = {	@UniqueConstraint(name = "UNQ_ADDTYPE_CODE",  columnNames = "code")	},
		indexes = {	@Index(name = "IDX_ADDTYPE_CODE", columnList = "code")	}
)
public class AddressType extends AbstractTranslatableEntity implements Serializable {

	private static final long serialVersionUID = 7989988588412608408L;
	
	/*  	FIELDS		 */
	@Column(length = 20, nullable = false)
	private String code;
	
	/*  	GETTERS AND SETTERS 		*/
	public String getCode() {								return code;							}
	public void setCode(String code) {						this.code = code;						}

	/*
	 * (non-Javadoc)
	 * @see uy.montdeo.orion.core.AbstractTranslatableEntity#getKey()
	 */
	@Override
	public String getKey() {
		return "addtype.".concat(getCode());
	}

}
