package br.com.erudio.unitests.mockito.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;
import br.com.erudio.services.PersonServices;
import br.com.erudio.unittestes.mapper.mocks.MockPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import br.com.erudio.exceptions.RequiredObjectisNullException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServicesTest {

	MockPerson input;

	@InjectMocks
	private PersonServices services;
	@Mock
	PersonRepository repository;
	@BeforeEach
	void setUp() throws Exception {
		input = new MockPerson();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindById() throws Exception {
		Person entity = input.mockEntity(1);
		entity.setId(1l);

		when(repository.findById(1l)).thenReturn(Optional.of(entity));
		var result = services.findById(1l);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains(""));
		assertEquals("Addres Test1" , result.getAddress());
		assertEquals("First Name Test1" , result.getFirstName());
		assertEquals("Last Name Test1" , result.getLastName());
		assertEquals("Female" , result.getGender());
	}

	@Test
	void testCreate() throws Exception {
		Person entity = input.mockEntity(1);
		Person persisted = entity;
		persisted.setId(1l);

		PersonVO vo = input.mockVO(1);
		vo.setKey(1l);

		when(repository.save(entity)).thenReturn(persisted);
		var result = services.create(vo);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains(""));
		assertEquals("Addres Test1" , result.getAddress());
		assertEquals("First Name Test1" , result.getFirstName());
		assertEquals("Last Name Test1" , result.getLastName());
		assertEquals("Female" , result.getGender());
	}

	@Test
	void testUpdate() throws Exception {
		Person entity = input.mockEntity(1);
		entity.setId(1l);
		Person persisted = entity;
		persisted.setId(1l);

		PersonVO vo = input.mockVO(1);
		vo.setKey(1l);
		when(repository.findById(1l)).thenReturn(Optional.of(entity));
		when(repository.save(entity)).thenReturn(persisted);
		var result = services.update(vo);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains(""));
		assertEquals("Addres Test1" , result.getAddress());
		assertEquals("First Name Test1" , result.getFirstName());
		assertEquals("Last Name Test1" , result.getLastName());
		assertEquals("Female" , result.getGender());
	}

	@Test
	void testDelete() throws Exception {
		Person entity = input.mockEntity(1);
		entity.setId(1l);

		when(repository.findById(1l)).thenReturn(Optional.of(entity));
		services.delete(1l);
	}
	@Test
	void testFindByAll() {
		Person entity = input.mockEntity(1);
		entity.setId(1l);
		List<Person> people = new ArrayList<>();
		people.add(entity);
		when(repository.findAll()).thenReturn(people);
		var results = services.findByAll();
		var result = results.get(0);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains(""));
		assertEquals("Addres Test1" , result.getAddress());
		assertEquals("First Name Test1" , result.getFirstName());
		assertEquals("Last Name Test1" , result.getLastName());
		assertEquals("Female" , result.getGender());
	}
	@Test
	void testCreateWithNullPerson() throws Exception {
		Exception exception = assertThrows(RequiredObjectisNullException.class, () -> {
			services.create(null);
		});

		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}
	@Test
	void testUpdateWithNullPerson() throws Exception {
		Exception exception = assertThrows(RequiredObjectisNullException.class, () -> {
			services.update(null);
		});

		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}
}
