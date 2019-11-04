package il.ac.hit.todolist.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.*;

/**
 * Servlet implementation class Router
 */
@WebServlet("/router/*")
public class Router extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String pkg = "il.ac.hit.todolist.controller";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Router() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String str = request.getPathInfo();
		String vec[] = str.split("/");
		String controller = vec[1];
		String action = vec[2];

		// composing the controller class name
		String controllerClassName = controller.substring(0, 1).toUpperCase() + controller.substring(1) + "Controller";
		try {
			Class clazz = Class.forName(pkg + "." + controllerClassName);
			Constructor constructor = clazz.getConstructor(HttpServletRequest.class, HttpServletResponse.class);
			Object object = constructor.newInstance(request, response);
			Method method = clazz.getMethod(action, HttpServletRequest.class, HttpServletResponse.class);
			method.invoke(object, request, response);
		
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException
				| SecurityException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			// sending the user to error message screen
			request.setAttribute("error", "router error");
			request.getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}