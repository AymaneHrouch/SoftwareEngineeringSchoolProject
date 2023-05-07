package marksManager.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.gson.JsonObject;

import marksManager.dao.impl.ElementDaoImpl;
import marksManager.dao.impl.FiliereDaoImpl;
import marksManager.dao.impl.ModuleDaoImpl;
import marksManager.dao.impl.ProfessorDaoImpl;
import marksManager.model.Professor;

public class ProfessorServiceTest {
	ProfessorService professorService;

	@BeforeEach
	public void init() {
		professorService = new ProfessorService(new ProfessorDaoImpl(),
				new ElementDaoImpl(new ModuleDaoImpl(new FiliereDaoImpl()),
						new ProfessorDaoImpl()));
	}

	@Test
	public void shouldGetProfessorWhenExists() {
		Professor professor = professorService.getProfessor(1L);
		assertNotNull(professor);
	}

	@Test
	public void shouldReturnNullWhenProfessorDoesNotExistAndTryingToFindIt() {
		Professor professor = professorService.getProfessor(9999L);
		assertNull(professor);
	}

	@Test
	public void shouldThrowWhenProfessorToUpdateDoesNotExist() {
		Long professorId = 9999L;
		Professor newProfessor = new Professor("hhh", "hhhh", "speciality");
		Exception exception = assertThrows(Exception.class, () -> {
			professorService.updateProfessor(professorId, newProfessor);
		});

		assertEquals("Professor with id=" + professorId + " does NOT exist.",
				exception.getMessage());
	}

	@Test
	public void shouldThrowIfFirstNameEntryIsAbsent() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("lastName", "this lastName");
			jsonObject.addProperty("speciality", "this is speciality");
			professorService.mapJson(jsonObject);
		});

		assertEquals("firstName entry is required\n", exception.getMessage());
	}

	@Test
	public void shouldThrowIfLastNameEntryIsAbsent() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("firstName", "hehe");
			jsonObject.addProperty("speciality", "this is speciality");
			professorService.mapJson(jsonObject);
		});

		assertEquals("lastName entry is required\n", exception.getMessage());
	}

	@Test
	public void shouldGetAllProfessors() {
		List<Professor> professors = professorService.getAllProfessors();
		assertTrue(professors.size() > 0);
	}

	@Test
	public void shouldThrowIfSpecialityEntryIsAbsent() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("firstName", "hehe");
			jsonObject.addProperty("lastName", "this lastName");
			professorService.mapJson(jsonObject);
		});

		assertEquals("speciality entry is required\n", exception.getMessage());
	}

	@Test
	public void shouldThrowAndShowMultipleErrorMessagesWhenMultipleEntriesAreAbsent() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("speciality", "this is speciality");
			professorService.mapJson(jsonObject);
		});

		assertEquals("firstName entry is required\nlastName entry is required\n",
				exception.getMessage());
	}

	@Test
	public void shouldMapJsonToProfessorObject() {
		Professor professor = new Professor("f1", "l1", "s1");

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("firstName", "f1");
		jsonObject.addProperty("lastName", "l1");
		jsonObject.addProperty("speciality", "s1");

		Professor returnedProfessor = professorService.mapJson(jsonObject);
		assertEquals(professor, returnedProfessor);
	}
}
