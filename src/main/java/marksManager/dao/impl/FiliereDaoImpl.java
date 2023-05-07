package marksManager.dao.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import marksManager.dao.FiliereDao;
import marksManager.model.Filiere;

public class FiliereDaoImpl extends DefaultDao<Filiere> implements FiliereDao {

	@Override
	public String getTableName() {
		return "filiere";
	}

	@Override
	public String getPrimaryKeyLabel() {
		return "id";
	}

	@Override
	public String[] getColumnLabels() {
		return new String[] { getPrimaryKeyLabel(), "name", "acronym" };
	}

	@Override
	public Filiere map(Map<String, String> filiereMap) {
		return new Filiere(
				Long.parseLong(filiereMap.get("id")),
				(String) filiereMap.get("name"),
				(String) filiereMap.get("acronym"));
	}

	@Override
	public Map<String, String> getColumns(Filiere filiere) {
		HashMap<String, String> map = new HashMap<>();
		map.put("name", filiere.getName());
		map.put("acronym", filiere.getAcronym());

		return map;
	}

	// Demonstration
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		// Printing all professors
		FiliereDao filiereDao = new FiliereDaoImpl();

		// Adding a new professor
		Filiere newFiliere = new Filiere("Génie Mecanique", "GM");
		filiereDao.save(newFiliere);
		sc.nextLine();

		// Editing a professor
		newFiliere.setAcronym("ACRO");
		filiereDao.update(newFiliere);
		sc.nextLine();

		System.out.println("Removing a filiere");
		filiereDao.delete(newFiliere.getId());
	}
}
