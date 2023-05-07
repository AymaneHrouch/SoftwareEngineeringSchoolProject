package marksManager.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class DefaultProtectedServlet extends HttpServlet
		implements ProtectedServlet {

	public void doProtectedGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			this.doGet(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doProtectedPost(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			this.doPost(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doProtectedDelete(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			this.doDelete(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
}
