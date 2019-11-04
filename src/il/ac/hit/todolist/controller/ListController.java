package il.ac.hit.todolist.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import il.ac.hit.todolist.model.*;

/**
 * Class, responsible for get the user list, update item, delete item and add
 * item to the list.
 */
public class ListController extends ControllerUtility {

	public ListController() { // default constructor
	}

	// Constructor
	public ListController(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	/**
	 * This method fetches the data of the user's table and moves it to a page to
	 * display it.
	 */
	public void getList(HttpServletRequest request, HttpServletResponse response) {
		try {
			Users userLogged = (Users) request.getSession().getAttribute("userLogged");
			if (userLogged != null) {
				HibernateToDoListDAO dao = HibernateToDoListDAO.getInstance();
				int listId = userLogged.getListId();
				List<Items> itemsList = dao.getItems(listId); // get the item of the login user.
				Iterator<Items> itemsIterator = itemsList.iterator();
				request.getSession().setAttribute("items", itemsIterator);
				request.getServletContext().getRequestDispatcher("/getList.jsp").forward(request, response);
			} else { // if the user don't logged in.
				request.setAttribute("error", "Please login first");
				request.getServletContext().getRequestDispatcher("/logIn.jsp").forward(request, response);
			}
		} catch (ToDoException | ServletException | IOException e) {
			request.setAttribute("error", "Unknown error in get list.");
		} finally {
			forwardToErrorPage();
		}

	}

	/**
	 * This method adds an item to the list of user's items.
	 */
	public void addItem(HttpServletRequest request, HttpServletResponse response) {
		try {
			Users userLogged = (Users) request.getSession().getAttribute("userLogged");
			if (userLogged != null) {
				String description = request.getParameter("taskDsctiption");
				String deadLine = request.getParameter("deadLine");
				String idList = request.getParameter("listId");
				int id = Integer.parseInt(idList);
				if (description != null && !description.isEmpty()) {
					Items newItem = new Items(id, description, deadLine, false);
					HibernateToDoListDAO.getInstance().addItem(newItem); // adding the item to the list
					request.getRequestDispatcher("/router/list/getList").forward(request, response);
				} else {
					request.setAttribute("error", "description can't be empty");
				}
			} else {
				request.setAttribute("error", "Please login first");
			}
		} catch (ToDoException | ServletException | IOException e) {
			request.setAttribute("error", "error in adding item");
		} finally {
			forwardToErrorPage();
		}
	}

	/**
	 * This method delete an item from the user's list items.
	 */
	public void deleteItem(HttpServletRequest request, HttpServletResponse response) {
		try {
			Users userLogged = (Users) request.getSession().getAttribute("userLogged");
			if (userLogged != null) {
				String idTask = request.getParameter("itemId");
				int id = Integer.parseInt(idTask);
				HibernateToDoListDAO.getInstance().removeItem(id); // delete the item from the user list
				request.getRequestDispatcher("/router/list/getList").forward(request, response);
			} else { // if the user not logged in
				request.setAttribute("error", "Please login first");
			}
		} catch (ToDoException | ServletException | IOException e) {
			request.setAttribute("error", "error in delete item");
		} finally {
			forwardToErrorPage();
		}
	}

	/**
	 * This method get's from the user a form with updates and changes the list
	 * accordingly.
	 */
	@SuppressWarnings("null")
	public void updateItem(HttpServletRequest request, HttpServletResponse response) throws ToDoException {
		try {
			Users userLogged = (Users) request.getSession().getAttribute("userLogged");
			if (userLogged != null) {
				String taskId = request.getParameter("idTask");
				if (taskId != null) {
					int id = Integer.parseInt(taskId);
					String deadLine = request.getParameter("deadLine");
					String description = request.getParameter("description");
					boolean status = Boolean.parseBoolean(request.getParameter("status"));
					HibernateToDoListDAO dao = HibernateToDoListDAO.getInstance();
					if (description != null && !(description.isEmpty())) {
						dao.UpdateItemDescription(id, description);
					}
					if (deadLine != null) {
						dao.updateDeadLineItem(id, deadLine);
					}
					dao.UpdateDone(id, status);
					request.getRequestDispatcher("/router/list/getList").forward(request, response);
				}
			} else {
				request.setAttribute("error", "Please login first");
			}
		} catch (ToDoException | ServletException | IOException e) {
			request.setAttribute("error", "error in update item");
		} catch (IllegalArgumentException e) {
			request.setAttribute("error", e.getMessage());
		} finally {
			forwardToErrorPage();
		}
	}
}
