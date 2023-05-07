package marksManager.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import marksManager.dao.ElementDao;
import marksManager.dao.ModuleDao;
import marksManager.dao.ProfessorDao;
import marksManager.model.Element;
import marksManager.model.Professor;

public class ElementDaoImpl extends DefaultDao<Element> implements ElementDao {

	private final ModuleDao moduleDao;
	private final ProfessorDao professorDao;

	public ElementDaoImpl(ModuleDao moduleDao, ProfessorDao professorDao) {
		this.moduleDao = moduleDao;
		this.professorDao = professorDao;
	}

	@Override
	public String getTableName() {
		return "element";
	}

	@Override
	public String getPrimaryKeyLabel() {
		return "id";
	}

	@Override
	public String[] getColumnLabels() {
		return new String[] { getPrimaryKeyLabel(), "name", "coefficient", "module",
				"professor" };
	}

	@Override
	public Element map(Map<String, String> tMap) {
		marksManager.model.Module module = null;
		Professor professor = null;

		Optional<marksManager.model.Module> moduleOptional = moduleDao
				.findById(Long.parseLong(tMap.get("module")));
		if (moduleOptional.isPresent())
			module = moduleOptional.get();

		Optional<Professor> professorOptional = professorDao
				.findById(Long.parseLong(tMap.get("professor")));
		if (professorOptional.isPresent())
			professor = professorOptional.get();

		Element element = new Element(tMap.get("name"),
				Double.parseDouble(tMap.get("coefficient")), module,
				professor);

		return element;
	}

	@Override
	public Map<String, String> getColumns(Element element) {
		Map<String, String> map = new HashMap<>();
		map.put("name", element.getName());
		map.put("coefficient", String.valueOf(element.getCoefficient()));
		map.put("module", String
				.valueOf(element.getModule() != null ? element.getModule().getId() : ""));
		map.put("professor", String.valueOf(
				element.getProfessor() != null ? element.getProfessor().getId() : ""));
		return map;
	}

	@Override
	public List<Element> findElementsByProfessorId(Long professorId) {
		return findBy("professor", professorId.toString());
	}

}
