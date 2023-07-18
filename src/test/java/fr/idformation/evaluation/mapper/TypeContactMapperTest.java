package fr.idformation.evaluation.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import fr.idformation.evaluation.core.domain.TypeContact;
import fr.idformation.evaluation.core.dto.TypeContactDTO;
import fr.idformation.evaluation.core.dto.mapper.TypeContactMapper;
import io.jsonwebtoken.lang.Assert;

@SpringBootTest
public class TypeContactMapperTest {

	@Test
	void defaultConstructorTestReturnsNotNull() {
		// when
		TypeContactMapper typeContactrMapperConstructor = new TypeContactMapper();
		// then
		Assert.isTrue(typeContactrMapperConstructor != null, "Should not be null, but it is");
	}

	@Test
	void nullToDTOReturnsNull() {
		// given
		TypeContact typeContact = null;
		// when
		TypeContactDTO typeContactDTO = TypeContactMapper.typeContactToDTO(typeContact);
		// then
		Assert.isTrue(typeContactDTO == null, "Should not be null, but it is");
	}

	@Test
	void typeContactToDTOReturnsSameData() {
		// given
		TypeContact typeContact = new TypeContact();
		typeContact.setId((short) 1);
		typeContact.setLabel("Téléphone");
		// when
		TypeContactDTO typeContactDTO = TypeContactMapper.typeContactToDTO(typeContact);
		// then
		Assert.isTrue(typeContactDTO.getId() == typeContact.getId(), "Should be the same, but it isn't");
		Assert.isTrue(typeContactDTO.getLabel() == typeContact.getLabel(), "Should be the same, but it isn't");
	}

	@Test
	void nullToTypeContactReturnsNull() {
		// given
		String typeContactDTO = null;
		// when
		TypeContact typeContact = TypeContactMapper.dTOToTypeContact(typeContactDTO);
		// then
		Assert.isTrue(typeContact == null, "Should not be null, but it is");
	}

	@Test
	void case1ToTypeContactReturns1() {
		// given
		String typeContactDTO = "Téléphone";
		// when
		TypeContact typeContact = TypeContactMapper.dTOToTypeContact(typeContactDTO);
		// then
		Assert.isTrue(typeContact.getId() == 1, "Should be 1, but it isn't");
	}

	@Test
	void case2ToTypeContactReturns2() {
		// given
		String typeContactDTO = "Email";
		// when
		TypeContact typeContact = TypeContactMapper.dTOToTypeContact(typeContactDTO);
		// then
		Assert.isTrue(typeContact.getId() == 2, "Should be 2, but it isn't");
	}

	@Test
	void case3ToTypeContactReturns3() {
		// given
		String typeContactDTO = "Fax";
		// when
		TypeContact typeContact = TypeContactMapper.dTOToTypeContact(typeContactDTO);
		// then
		Assert.isTrue(typeContact.getId() == 3, "Should be 3, but it isn't");
	}

	@Test
	void case4ToTypeContactReturns4() {
		// given
		String typeContactDTO = "Portable";
		// when
		TypeContact typeContact = TypeContactMapper.dTOToTypeContact(typeContactDTO);
		// then
		Assert.isTrue(typeContact.getId() == 4, "Should be 4, but it isn't");
	}

}
