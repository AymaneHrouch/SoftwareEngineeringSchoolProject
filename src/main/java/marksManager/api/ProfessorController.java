package marksManager.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import marksManager.dao.impl.ElementDaoImpl;
import marksManager.dao.impl.ProfessorDaoImpl;
import marksManager.model.Professor;
import marksManager.service.ProfessorService;
import marksManager.util.Util;

/**
 * Servlet implementation class ProfessorController
 */
public class ProfessorController extends DefaultProtectedServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	private final ProfessorService professorService;

	public ProfessorController(ProfessorService professorService) {
		super();
		this.professorService = professorService;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String pathInfo = request.getPathInfo();
		ProfessorService professorService = new ProfessorService(
				new ProfessorDaoImpl(),
				new ElementDaoImpl(null, null));
		String jsonResponse;

		List<Professor> professors = professorService.getAllProfessors();
		jsonResponse = Util.toJson(professors);
		response.getWriter().print(jsonResponse);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		String requestBody = sb.toString();

		// parse the request body as a JSON object
		Gson gson = new Gson();
		JsonObject json = gson.fromJson(requestBody, JsonObject.class);

		Professor professor = professorService.mapJson(json);
		professorService.createProfessor(professor);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		professorService.deleteProfessor(Long.parseLong(req.getParameter("professorId")));
	}

}
