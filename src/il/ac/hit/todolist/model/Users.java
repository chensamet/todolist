package il.ac.hit.todolist.model;

/**
 * 
 * This class mapping to users table in DB
 */
public class Users {

	private int id; // by the hibernate.
	private String username;
	private String password;
	private int listId;

	public Users() { // default constructor
	}

	// constructor
	public Users(String username, String password, int listId) {
		setUsername(username);
		setPassword(password);
		setListId(listId);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	/**
	 * 
	 * @param username 
	 * check if user name is validate
	 */
	public void setUsername(String username) {
		ValidateUtility.onlyLettersAndNumbers(username);
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	/**
	 * 
	 * @param password 
	 * check if password is validate
	 */
	public void setPassword(String password) {
		ValidateUtility.onlyLettersAndNumbers(password);
		this.password = password;

	}

	public int getListId() {
		return listId;
	}

	public void setListId(int listId) {
		this.listId = listId;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username + ", password=" + password + ", listId=" + listId + "]";
	}
}