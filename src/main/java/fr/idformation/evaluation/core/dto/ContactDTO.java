package fr.idformation.evaluation.core.dto;

public class ContactDTO {

	private Integer id;

	/**
	 * The Contact DTO's linked typeContact
	 */
	private String typeContactDTO;

	/**
	 * The Contact DTO's value can be either a phone number, an email, a fax or a
	 * mobile phone number
	 */
	private String value;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the DTO's typeContact
	 */
	public String getTypeContact() {
		return typeContactDTO;
	}

	/**
	 * @param typeContact the DTO's typeContact to set
	 */
	public void setTypeContact(String typeContactDTO) {
		this.typeContactDTO = typeContactDTO;
	}

	/**
	 * @return the DTO's value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the DTO's value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * The Contact DTO's default contructor
	 */
	public ContactDTO() {
		super();

	}
}
