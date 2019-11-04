package il.ac.hit.todolist.model;

import java.util.Iterator;
import java.util.List;

/**
 * 
 * Test class, check the hibernate, create user and item, check the
 * add,delete,get and update.
 */
public class TestClass {

	public static void main(String[] args) throws ToDoException {
		HibernateToDoListDAO test = HibernateToDoListDAO.getInstance();
		Items t1 = new Items(1, "item1 List1", "2020-1-21", false);
		Items t2 = new Items(1, "item2 List1", "2019-3-1", false);
		Users u1 = new Users("user1", "psw1", 1);
		Users u2 = new Users("user2", "psw2", 1);
		Items t3 = new Items(2, "item1 List2 ", "2019-3-1", false);
		Items t4 = new Items(2, "item2 List2", "2019-3-1", false);
		Users u3 = new Users("user3", "psw3", 2);
		Users u4 = new Users("user4", "psw4", 2);
		System.out.println("add 4 items and 4 users");
		test.addItem(t1);
		test.addItem(t2);
		test.addItem(t3);
		test.addItem(t4);
		test.addUser(u1);
		test.addUser(u2);
		test.addUser(u3);
		test.addUser(u4);
		System.out.println("get all the itmes of list1");
		HibernateToDoListDAO test2 = HibernateToDoListDAO.getInstance();
		List<Items> allItems = test2.getItems(1);
		System.out.println("items " + allItems);
		Iterator<Items> i = allItems.iterator();
		while (i.hasNext()) {
			System.out.println(i.next());
		}
		test2.UpdateDone(1, true);
		test2.UpdateDone(4, true);
		test2.UpdateItemDescription(2, "checkUpdate2");
		test2.UpdateItemDescription(3, "checkUpdate3");
		System.out.println("get all the itmes of list2");
		HibernateToDoListDAO test3 = HibernateToDoListDAO.getInstance();
		List<Items> allItems2 = test3.getItems(2);
		Iterator<Items> i2 = allItems2.iterator();
		while (i2.hasNext()) {
			System.out.println(i2.next());
		}
	}
}
