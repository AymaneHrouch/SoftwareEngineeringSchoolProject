package marksManager.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ProtectedServlet {

	public void doProtectedGet(HttpServletRequest request, HttpServletResponse response);

	public void doProtectedPost(HttpServletRequest request, HttpServletResponse response);

	void doProtectedDelete(HttpServletRequest request, HttpServletResponse response);
}
