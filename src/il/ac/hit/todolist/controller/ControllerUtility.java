package il.ac.hit.todolist.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import il.ac.hit.todolist.model.*;

/**
 * 
 * Class, This class provides a service for several controllers.
 */
public class ControllerUtility {

	protected HttpServletRequest request;
	protected HttpServletResponse response;

	public ControllerUtility() {
	}

	/**
	 * constructor
	 * 
	 * @param request
	 * @param response
	 */
	protected ControllerUtility(HttpServletRequest request, HttpServletResponse response) {
		setRequest(request);
		setResponse(response);
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	private void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	private void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	/**
	 * forward the user to error page
	 */
	protected void forwardToErrorPage() {
		try {
			if (getRequest().getAttribute("error") != null) {
				getRequest().getRequestDispatcher("/error.jsp").forward(request, response);
			}
		} catch (ServletException | IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * forward the user to the page of their list.
	 */
	protected void forwardToUserList() throws ToDoException {
		try {
			request.getRequestDispatcher("/router/list/getList").forward(request, response);
		} catch (ServletException | IOException e) {
			throw new ToDoException(e.getMessage(), e);
		}
	}
}
