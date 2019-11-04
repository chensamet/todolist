package il.ac.hit.todolist.model;

/**
 * 
 * Class, This class provides a service for check validates in set's method.
 *
 */
public class ValidateUtility {
/**
 * 
 * @param inputString
 *	check if the inputString is only letters and numbers,
 *	if not - throw exception.
 */
	public static void onlyLettersAndNumbers(String inputString) {
		if (!inputString.matches("[A-Za-z0-9,_]+")) {
			throw new IllegalArgumentException(
					"user name and password must contain only letters or numbers or '_' and at least one of them");
		}
	}
/**
 * 
 * @param inputString
 *	check if the inputString is not empty or null,
 *	if not - throw exception.
 */
	public static void notEmptyOrNull(String inputString) {
		if (inputString == null || inputString.isEmpty()) {
			throw new IllegalArgumentException("description can't be empty or null");
		}
	}
/**
 * 
 * @param inputString
 * check if the inputString is validate Date,
 * if not - throw exception.
 */
	public static void validateDate(String inputString) {
		if (!inputString.matches("((?:[0-9]{2})[0-9]{2})(-)([0-3]?[0-9])(-)([0-3]?[0-9])")) {
			throw new IllegalArgumentException("date valid error");
		}
	}
}
