package il.ac.hit.todolist.model;
/**
 * 
 * Class Exception that the program work with
 */
@SuppressWarnings("serial")
public class ToDoException extends Exception {

	public ToDoException() {
		super();
	}

	public ToDoException(String argString, Throwable throwable) {
		super(argString, throwable);
	}
}
