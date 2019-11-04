package il.ac.hit.todolist.model;

import java.util.List;

/**
 * 
 * interface that lists the methods through which the web application uses the
 * DB.
 *
 */
public interface IToDoListDAO {
	/**
	 * 
	 * @param user
	 * @return true if user deletion was successful.
	 */
	public boolean removeUser(int user) throws ToDoException;

	/**
	 * 
	 * @param user
	 * @return true if adding the user was successful.
	 */
	public boolean addUser(Users user) throws ToDoException;

	/**
	 * 
	 * @param username
	 * @return the user if exists in DB.
	 */
	public Users getUser(String username) throws ToDoException;

	/**
	 * 
	 * @param username
	 * @return true if the user exists in DB.
	 */
	public boolean userExistsCheck(String username) throws ToDoException;

	/**
	 * 
	 * @param username
	 * @param password
	 * @return true if the password match to the username in DB.
	 */
	public boolean passwordMatchingUsernameCheck(String username, String password) throws ToDoException;

	/**
	 * 
	 * @param itemId
	 * @return true if item deletion was successful.
	 */
	public boolean removeItem(int itemId) throws ToDoException;

	/**
	 * 
	 * @param item
	 * @return true if adding the item was successful.
	 */
	public boolean addItem(Items item) throws ToDoException;

	/**
	 * 
	 * @param id
	 * @param newdecription
	 * @return true if update the description was successful.
	 */
	public boolean UpdateItemDescription(int id, String newdecription) throws ToDoException;

	/**
	 * 
	 * @param id
	 * @param status
	 * @return true if update the status of the item was successful.
	 */
	public boolean UpdateDone(int id, boolean status) throws ToDoException;

	/**
	 * 
	 * @param id
	 * @param newDeadLine
	 * @return true if update the deadline date was successful.
	 */
	public boolean updateDeadLineItem(int id, String newDeadLine) throws ToDoException;

	/**
	 * 
	 * @param idList
	 * @return list of the items in the id list.
	 */
	public List<Items> getItems(int idList) throws ToDoException;
}