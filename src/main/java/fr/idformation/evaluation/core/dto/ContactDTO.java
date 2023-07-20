package fr.idformation.evaluation.core.dto;

public class ContactDTO {

	/**
	 * The Contact DTO's id.
	 */
	private Integer id;

	/**
	 * The Contact DTO's linked typeContact.
	 */
	private String typeContactDTO;

	/**
	 * The Contact DTO's value can be either a phone number, an email, a fax or a
	 * mobile phone number.
	 */
	private String value;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param pId the id to set
	 */
	public void setId(final Integer pId) {
		this.id = pId;
	}

	/**
	 * @return the DTO's typeContact
	 */
	public String getTypeContact() {
		return typeContactDTO;
	}

	/**
	 * @param pTypeContactDTO the DTO's typeContact to set
	 */
	public void setTypeContact(final String pTypeContactDTO) {
		this.typeContactDTO = pTypeContactDTO;
	}

	/**
	 * @return the DTO's value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param pValue the DTO's value to set
	 */
	public void setValue(final String pValue) {
		this.value = pValue;
	}

	/**
	 * The Contact DTO's default contructor.
	 */
	public ContactDTO() {
		super();

	}
}
