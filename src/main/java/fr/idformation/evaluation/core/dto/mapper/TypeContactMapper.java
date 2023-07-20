package fr.idformation.evaluation.core.dto.mapper;

import fr.idformation.evaluation.ConstantList;
import fr.idformation.evaluation.core.domain.TypeContact;
import fr.idformation.evaluation.core.dto.TypeContactDTO;

public class TypeContactMapper {

	/**
	 * Method that takes a contact type and converts it to a DTO.
	 *
	 * @param pTypeContact the contact type to be converted
	 * @return a contact type DTO
	 */
	public static TypeContactDTO typeContactToDTO(final TypeContact pTypeContact) {
		TypeContactDTO typeContactDTO = null;
		if (pTypeContact != null) {
			typeContactDTO = new TypeContactDTO();
			typeContactDTO.setId(pTypeContact.getId());
			typeContactDTO.setLabel(pTypeContact.getLabel());
		}
		return typeContactDTO;
	}

	/**
	 * Methode that takes a contact type DTO and converts it to an entity.
	 *
	 * @param pTypeContactDTO the dto to be converted
	 * @return a contact type
	 */
	public static TypeContact dTOToTypeContact(final String pTypeContactDTO) {
		TypeContact typeContact = null;
		if (pTypeContactDTO != null) {
			typeContact = new TypeContact();
			typeContact.setLabel(pTypeContactDTO);
			switch (pTypeContactDTO) {
			case "Téléphone":
				typeContact.setId(ConstantList.TELEPHONE);
				break;
			case "Email":
				typeContact.setId(ConstantList.EMAIL);
				break;
			case "Fax":
				typeContact.setId(ConstantList.FAX);
				break;
			case "Portable":
				typeContact.setId(ConstantList.PORTABLE);
				break;
			default:
				typeContact.setId(null);
			}

		}
		return typeContact;
	}

}
