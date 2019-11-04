package il.ac.hit.todolist.model;

/**
 * 
 * This class mapping to users table in DB
 *
 */
public class Items {

	private int id;
	private int listId;
	private String description;
	private String deadLine;
	private boolean done;

	public Items() { // default constructor
	}

	// constructor
	public Items(int listId, String description, String deadLine, boolean done) {
		this.listId = listId;
		this.description = description;
		this.deadLine = deadLine;
		this.done = done;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getListId() {
		return listId;
	}

	public void setListId(int listId) {
		this.listId = listId;
	}

	public String getDescription() {
		return description;
	}
/**
 * 
 * @param description
 * check if the description is valid.
 */
	public void setDescription(String description) {
		ValidateUtility.notEmptyOrNull(description);
		this.description = description;
	}

	public String getDeadLine() {
		return deadLine;
	}

	/**
	 * 
	 * @param deadLine 
	 * check if deadLine date is valid
	 */
	public void setDeadLine(String deadLine) {
		ValidateUtility.validateDate(deadLine);
		this.deadLine = deadLine;
	}

	public boolean getDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	@Override
	public String toString() {
		return "Items [id=" + id + ", listId=" + listId + ", description=" + description + ", deadLine=" + deadLine
				+ ", done=" + done + "]";
	}
}