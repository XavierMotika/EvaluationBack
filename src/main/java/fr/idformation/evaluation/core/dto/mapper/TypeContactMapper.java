package fr.idformation.evaluation.core.dto.mapper;

import fr.idformation.evaluation.core.domain.TypeContact;
import fr.idformation.evaluation.core.dto.TypeContactDTO;

public class TypeContactMapper {

	/**
	 * Method that takes a contact type and converts it to a DTO
	 *
	 * @param typeContact the contact type to be converted
	 * @return a contact type DTO
	 */
	public static TypeContactDTO typeContactToDTO(TypeContact typeContact) {
		TypeContactDTO typeContactDTO = null;
		if (typeContact != null) {
			typeContactDTO = new TypeContactDTO();
			typeContactDTO.setId(typeContact.getId());
			typeContactDTO.setLabel(typeContact.getLabel());
		}
		return typeContactDTO;
	}

	/**
	 * Methode that takes a contact type DTO and converts it to an entity
	 *
	 * @param typeContactDTO the dto to be converted
	 * @return a contact type
	 */
	public static TypeContact dTOToTypeContact(String typeContactDTO) {
		TypeContact typeContact = null;
		if (typeContactDTO != null) {
			typeContact = new TypeContact();
			typeContact.setLabel(typeContactDTO);
			switch (typeContactDTO) {
			case "Téléphone":
				typeContact.setId((short) 1);
				break;
			case "Email":
				typeContact.setId((short) 2);
				break;
			case "Fax":
				typeContact.setId((short) 3);
				break;
			case "Portable":
				typeContact.setId((short) 4);
				break;
			}

		}
		return typeContact;
	}

}
