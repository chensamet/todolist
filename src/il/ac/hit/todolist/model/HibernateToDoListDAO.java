package il.ac.hit.todolist.model;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import java.util.List;

/**
 * 
 * This class implements the interface IToDoList, it use Hibernate and implement
 * the singleton pattern
 *
 */
public class HibernateToDoListDAO implements IToDoListDAO {

	private static HibernateToDoListDAO hibernateInstance = null;
	private SessionFactory factory = null;

	// Constructor.
	private HibernateToDoListDAO() {
		if (this.factory == null) {
			this.factory = new AnnotationConfiguration().configure().buildSessionFactory();
		}
	}

	/**
	 * 
	 * static method to get instance of singleton.
	 */
	public static synchronized HibernateToDoListDAO getInstance() {
		if (hibernateInstance == null) {
			hibernateInstance = new HibernateToDoListDAO();

		}
		return hibernateInstance;
	}

	/**
	 * 
	 * method that get the session factory.
	 */
	private SessionFactory getFactory() {
		if (this.factory == null) {
			this.factory = new AnnotationConfiguration().configure().buildSessionFactory();
		}
		return factory;
	}

	/**
	 * add item to the DB
	 * 
	 * @return true if item added successfully to DB, else return false.
	 */
	@SuppressWarnings("finally")
	@Override
	public boolean addItem(Items item) throws ToDoException {
		boolean success = false;
		Session session = null;
		try {
			session = factory.openSession();
			session.beginTransaction();
			session.save(item); // add the item to DB.
			session.getTransaction().commit();
			success = true;
		} catch (HibernateException e) {
			if (session != null) {
				session.getTransaction().rollback();
				throw new ToDoException("add item error", e);
			}
		} finally {
			session.close();
			return success;
		}
	}

	/**
	 * add user to the DB
	 * 
	 * @return true if user added successfully to DB, else return false.
	 */
	@SuppressWarnings("finally")
	@Override
	public boolean addUser(Users user) throws ToDoException {
		boolean success = false;
		Session session = null;
		try {
			session = factory.openSession();
			session.beginTransaction();
			session.save(user); // add the user to DB.
			session.getTransaction().commit();
			success = true;
		} catch (HibernateException e) {
			if (session != null) {
				session.getTransaction().rollback();
				throw new ToDoException("add user error", e);
			}
		} finally {
			session.close();
			return success;
		}
	}

	/**
	 * delete item from the DB
	 * 
	 * @return true if item deletion successfully from DB, else return false.
	 */
	@SuppressWarnings("finally")
	@Override
	public boolean removeItem(int itemId) throws ToDoException {
		boolean success = false;
		Session session = null;
		try {
			session = factory.openSession();
			session.beginTransaction();
			Items item = (Items) session.get(Items.class, itemId); // search if there item with this id.
			if (item != null) {
				session.delete(item);
				session.getTransaction().commit();
			}
		} catch (HibernateException e) {
			if (session != null) {
				session.getTransaction().rollback();
				throw new ToDoException("remove item error", e);
			}
		} finally {
			session.close();
			return success;
		}

	}

	/**
	 * delete user from the DB
	 * 
	 * @return true if user deletion successfully from DB, else return false.
	 */
	@SuppressWarnings("finally")
	@Override
	public boolean removeUser(int userid) throws ToDoException {
		boolean success = false;
		Session session = null;
		try {
			session = factory.openSession();
			session.beginTransaction();
			Users user = (Users) session.get(Users.class, userid); // search if there user with this id.
			if (user != null) {
				session.delete(user);
				session.getTransaction().commit();
			}
		} catch (HibernateException e) {
			if (session != null) {
				session.getTransaction().rollback();
				throw new ToDoException("remove user error", e);
			}
		} finally {
			session.close();
			return success;
		}
	}

	/**
	 * update item description by getting id and the new description
	 * 
	 * @return true if the item update successfully in DB, else return false.
	 */
	@SuppressWarnings("finally")
	@Override
	public boolean UpdateItemDescription(int id, String newdecription) throws ToDoException {
		boolean success = false;
		Session session = null;
		try {
			session = factory.openSession();
			session.beginTransaction();
			Items item = (Items) session.get(Items.class, id); // search if there item with this id.
			if (item != null) {
				item.setDescription(newdecription);
				session.getTransaction().commit();
				success = true;
			}
		} catch (HibernateException e) {
			if (session != null) {
				session.getTransaction().rollback();
				throw new ToDoException("update description error", e);
			}
		} finally {
			session.close();
			return success;
		}
	}

	/**
	 * update item status by getting id and the new status
	 * 
	 * @return true if the item update successfully in DB, else return false.
	 */
	@SuppressWarnings("finally")
	@Override
	public boolean UpdateDone(int id, boolean status) throws ToDoException {
		boolean success = false;
		Session session = null;
		try {
			session = getFactory().openSession();
			session.beginTransaction();
			Items item = (Items) session.get(Items.class, id); // search if there item with this id.
			if (item != null) {
				item.setDone(status);
				session.getTransaction().commit();
				success = true;
			}
		} catch (HibernateException e) {
			if (session != null) {
				session.getTransaction().rollback();
				throw new ToDoException("update status error", e);
			}
		} finally {
			session.close();
			return success;
		}
	}

	/**
	 * update item deadLine date by getting id and the new date
	 * 
	 * @return true if the item update successfully in DB, else return false.
	 */
	@SuppressWarnings("finally")
	@Override
	public boolean updateDeadLineItem(int id, String newDeadLine) throws ToDoException {
		boolean success = false;
		Session session = null;
		try {
			session = getFactory().openSession();
			session.beginTransaction();
			Items item = (Items) session.get(Items.class, id); // search if there item with this id.
			if (item != null) {
				item.setDeadLine(newDeadLine);
				session.getTransaction().commit();
				success = true;
			}
		} catch (HibernateException e) {
			if (session != null) {
				session.getTransaction().rollback();
				throw new ToDoException("update status error", e);
			}
		} finally {
			session.close();
			return success;
		}
	}

	/**
	 * @return list with all the items by the id list of the user.
	 */
	@SuppressWarnings({ "unchecked", "finally" })
	@Override
	public List<Items> getItems(int idList) throws ToDoException {
		Session session = null;
		List<Items> itemsList = null;
		try {
			session = factory.openSession();
			session.beginTransaction();
			itemsList = (List<Items>) session.createSQLQuery("SELECT * FROM `items` WHERE LISTID =" + idList)
					.addEntity(Items.class).list();
		} catch (HibernateException e) {
			if (session != null) {
				session.getTransaction().rollback();
				throw new ToDoException("get items error", e);
			}
		} finally {
			session.close();
			return itemsList;
		}
	}

	/**
	 * 
	 * @param username
	 * @return the user by the user name, if exists.
	 */
	@SuppressWarnings({ "finally", "unchecked" })
	public Users getUser(String username) throws ToDoException {

		Users user = null;
		Session session = null;
		try {
			session = getFactory().openSession();
			session.beginTransaction();
			List<Users> users = (List<Users>) session
					.createSQLQuery("SELECT * FROM `users` WHERE USERNAME='" + username + "'").addEntity(Users.class)
					.list();
			if (users.size() == 1) {
				user = users.get(0);
			}
		} catch (HibernateException e) {
			if (session != null) {
				session.getTransaction().rollback();
				throw new ToDoException("get user error", e);
			}
		} finally {
			session.close();
			return user;
		}
	}

	/**
	 * 
	 * @param username
	 * @return true if the user exists in DB, else return false.
	 */
	@SuppressWarnings("finally")
	public boolean userExistsCheck(String username) throws ToDoException {
		boolean exists = false;
		Session session = null;
		try {
			session = getFactory().openSession();
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<Users> users = (List<Users>) session
					.createSQLQuery("SELECT * FROM `users` WHERE USERNAME='" + username + "'").addEntity(Users.class)
					.list();
			if (users.size() != 0) {
				exists = true;
			}
		} catch (HibernateException e) {
			if (session != null) {
				session.getTransaction().rollback();
				throw new ToDoException("check if exists error", e);
			}
		} finally {
			session.close();
			return exists;
		}
	}

	/**
	 * 
	 * @param username
	 * @param password
	 * @return if the user name match to the password or not
	 */
	@SuppressWarnings("finally")
	public boolean passwordMatchingUsernameCheck(String username, String password) {
		boolean isValid = false;
		Session session = null;
		try {
			session = getFactory().openSession();
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<Users> currentUser = (List<Users>) session
					.createSQLQuery(
							"SELECT * FROM `users` WHERE USERNAME='" + username + "' AND PASSWORD ='" + password + "'")
					.addEntity(Users.class).list();
			if (currentUser.size() == 1) {
				isValid = true;
			}
		} catch (HibernateException e) {
			if (session != null) {
				session.getTransaction().rollback();
				throw new ToDoException("error in login check", e);
			}
		} finally {
			return isValid;
		}
	}
}
