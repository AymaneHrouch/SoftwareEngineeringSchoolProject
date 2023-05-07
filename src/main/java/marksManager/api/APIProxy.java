package marksManager.api;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import marksManager.dao.impl.ElementDaoImpl;
import marksManager.dao.impl.ModuleDaoImpl;
import marksManager.dao.impl.ProfessorDaoImpl;
import marksManager.service.ProfessorService;
import marksManager.util.Util;

/**
 * Servlet implementation class APIProxy
 */
public class APIProxy extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProtectedServlet protectedServlet;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public APIProxy() {
		super();
		this.protectedServlet = null;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String requestURI = request.getRequestURI();

		boolean isUserValid = validateUser(request);
		if (!isUserValid) {
			response.getWriter().append("Your credentials are NOT correct.");
			return;
		}

		if (requestURI.startsWith("/marksManager/professors")) {
			protectedServlet = new ProfessorController(
					new ProfessorService(new ProfessorDaoImpl(), new ElementDaoImpl(
							new ModuleDaoImpl(null), new ProfessorDaoImpl())));
			protectedServlet.doProtectedGet(request, response);
		} else {
			HashMap<String, Object> res = new HashMap<>();
			res.put("loggedIn", true);
			response.getWriter().append(Util.toJson(res));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestURI = request.getRequestURI();

		boolean isUserValid = validateUser(request);
		if (!isUserValid) {
			response.getWriter().append("Your credentials are NOT correct.");
			return;
		}

		if (requestURI.startsWith("/marksManager/login")) {
			HashMap<String, Object> res = new HashMap<>();
			res.put("loggedIn", true);
			response.getWriter().append(Util.toJson(res));
		} else if (requestURI.startsWith(("/marksManager/professors"))) {

			protectedServlet = new ProfessorController(
					new ProfessorService(new ProfessorDaoImpl(), new ElementDaoImpl(
							new ModuleDaoImpl(null), new ProfessorDaoImpl()))

			);
			protectedServlet.doProtectedPost(request, response);
		}
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String requestURI = request.getRequestURI();

		boolean isUserValid = validateUser(request);
		if (!isUserValid) {
			response.getWriter().append("Your credentials are NOT correct.");
			return;
		}

		if (requestURI.startsWith(("/marksManager/professors"))) {
			protectedServlet = new ProfessorController(
					new ProfessorService(new ProfessorDaoImpl(), new ElementDaoImpl(
							new ModuleDaoImpl(null), new ProfessorDaoImpl())));
			protectedServlet.doProtectedDelete(request, response);
		}
	}

	private boolean authenticate(String username, String password) {
		if (username.equals("admin") && password.equals("admin")) {
			return true;
		}
		return false;

	}

	public boolean validateUser(HttpServletRequest request) {
		String requestURI = request.getRequestURI();

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		boolean isAuthenticated = authenticate(username, password);

		return isAuthenticated;
	}

}
