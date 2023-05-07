package marksManager.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpServletProxy extends HttpServlet {
	private final marksManager.api.ProtectedServlet api;

	public HttpServletProxy(marksManager.api.ProtectedServlet api) {
		this.api = api;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (authenticate(username, password)) {
			// api.doGet(request, response);
		} else {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}
	}

	private boolean authenticate(String username, String password) {
		return true;
	}
}
