package il.ac.hit.todolist.controller;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import il.ac.hit.todolist.model.*;

/**
 * Class, responsible for user register, login and logout.
 */
public class UserController extends ControllerUtility {

	public UserController() { // default constructor
	}

	// Constructor
	public UserController(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	/**
	 * This parameter is used as the ID for a new list.
	 */
	private static AtomicInteger listIDInteger = new AtomicInteger();

	/**
	 * @return an id for a new list.
	 */
	public static int getListId() {
		return listIDInteger.getAndIncrement();
	}

	/**
	 * This method is invoked when a request for registration is received.
	 */
	public void register(HttpServletRequest request, HttpServletResponse response) {
		try {
			Users userLogged = (Users) request.getSession().getAttribute("userLogged");
			if (userLogged != null) { // if the user already logged
				forwardToUserList();
			} else {
				HibernateToDoListDAO dao = HibernateToDoListDAO.getInstance();
				String username = request.getParameter("usn");
				String password = request.getParameter("psw");
				if (validateParameters(username, password)) {
					if (!dao.userExistsCheck(username)) { // if the user does't already exists in DB
						int listIdInteger = getListId();
						Users newCurrentUser = new Users(username, password, listIdInteger);
						if (dao.addUser(newCurrentUser)) { // add user to DB
							request.getSession().setAttribute("userLogged", newCurrentUser);
							forwardToUserList();
						}
					} else {
						request.setAttribute("error", "user already exists in the DB");
					}
				} else {
					request.setAttribute("error", "Invalid parameters");
				}
			}
		} catch (ToDoException e) {
			request.setAttribute("error", "Unknown error while register");
		} catch (IllegalArgumentException e) {
			request.setAttribute("error", e.getMessage());
		} finally {
			forwardToErrorPage();
		}
	}

	/**
	 * This method is invoked when a request for login is received.
	 */
	public void logIn(HttpServletRequest request, HttpServletResponse response) {
		try {
			Users userLogged = (Users) request.getSession().getAttribute("userLogged");
			if (userLogged != null) { // if the user already logged
				forwardToUserList();
			} else {
				String username = request.getParameter("usn");
				String password = request.getParameter("psw");
				if (validateParameters(username, password)) { // check if the parameters are valid
					HibernateToDoListDAO dao = HibernateToDoListDAO.getInstance();
					if (dao.passwordMatchingUsernameCheck(username, password)) { // check if the username match to
																					// password.
						Users currentUser = dao.getUser(username); // get's the user
						request.getSession().setAttribute("userLogged", currentUser);
						forwardToUserList();
					} else {
						request.setAttribute("error", "the password dosen't match or the user dosen't exists");
					}
				} else {
					request.setAttribute("error", "Invalide parameters or no user logged in");
				}
			}
		} catch (ToDoException e) {
			request.setAttribute("error", "Unknown error while login");
		} finally {
			forwardToErrorPage();
		}
	}

	/**
	 * 
	 * @param username
	 * @param password
	 * @return if the user name and the password are valid or not.
	 */
	private boolean validateParameters(String username, String password) {
		boolean isValid = false;
		if (username != null && password != null && !(username.isEmpty()) && !(password.isEmpty())) {
			isValid = true;
		}
		return isValid;
	}

	/**
	 * This method is invoked when a request for logout is received.
	 */
	public void logout(HttpServletRequest request, HttpServletResponse response) throws ToDoException {
		Users userLogged = (Users) request.getSession().getAttribute("userLogged");
		if (userLogged != null) { // if the user logged
			request.getSession().invalidate(); // Invalid this session
			try {
				request.getServletContext().getRequestDispatcher("/logIn.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				throw new ToDoException(e.getMessage(), e);
			}
		}
	}
}