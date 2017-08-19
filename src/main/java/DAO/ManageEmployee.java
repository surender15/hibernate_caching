package DAO;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import POJO.Employee;

public class ManageEmployee {
	private static SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public static void main(String[] args) throws InterruptedException {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		ManageEmployee ME = (ManageEmployee) ctx.getBean("manageEmployee", ManageEmployee.class);

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		// Get employee with id=1
		Employee employee = (Employee) session.get(Employee.class, 1);
		printData(employee, 1);
		// waiting for sometime to change the data in backend
		Thread.sleep(10000);
		Employee employee1 = (Employee) session.get(Employee.class, 1);
		printData(employee, 1);
		Thread.sleep(2000);
		// Create a new session
		Session newSession = sessionFactory.openSession();
		// Get employee with id=1, notice the logs for query
		Employee employee2 = (Employee) newSession.get(Employee.class, 1);
		printData(employee2, 1);
		Thread.sleep(2000);
		// Start: evict example to remove sspecific object from hibernate first
		// level cache
		// Get employee with id=2 , first time the query in logs
		Employee employee3 = (Employee) session.get(Employee.class, 2);
		printData(employee3, 2);
		Thread.sleep(2000);
		// evict the employee object with id=1
		session.evict(employee);
		System.out.println("Session contains employee with id=1 ?" + session.contains(employee));
		// since object is removed from first level cache, you will see query in
		// logs
		Thread.sleep(2000);
		Employee employee4 = (Employee) session.load(Employee.class, 1);
		printData(employee4, 1);
		Thread.sleep(2000);
		// this object is still present, so you won't see query in logs
		Employee employee5 = (Employee) session.load(Employee.class, 2);
		printData(employee5, 2);
		Thread.sleep(2000);
		// END: evict example
		//Start clearing everthing from first level cache
		session.clear();
		Employee employee6=(Employee)session.get(Employee.class, 1);
		printData(employee6, 1);
		Thread.sleep(2000);
		Employee employee7=(Employee)session.get(Employee.class, 2);
		printData(employee7, 2);
		Thread.sleep(2000);
		System.out.println("Session with Employee contains id=2 ?"+ session.contains(employee7));
	tx.commit();
	sessionFactory.close();
		
		/* Add few employee records in database */
		// Integer empID1 = ME.addEmployee(1, "surender", 100000);
		// Integer empID2 = ME.addEmployee(2, "priyanka", 90000);
		// Integer empID3 = ME.addEmployee(300, "Paul", 10000);

		/* List down all the employees */
		// ME.listEmployees();

		/* Update employee's records */
		// ME.updateEmployee(empID1, 5000);

		/* Delete an employee from the database */
		// ME.deleteEmployee(empID2);

		/* List down new list of the employees */
		// ME.listEmployees();

		/* Hibernate Caching – First Level Cache Example */

	}

	/* Method to CREATE an employee in the database */
	public Integer addEmployee(int id, String name, int salary) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Integer employeeID = null;
		try {
			tx = session.beginTransaction();
			Employee employee = new Employee(id, name, salary);
			employeeID = (Integer) session.save(employee);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return employeeID;
	}

	/* Method to READ all the employees */
	public void listEmployees() {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List employees = session.createQuery("FROM Employee").list();
			for (Iterator iterator = employees.iterator(); iterator.hasNext();) {
				Employee employee = (Employee) iterator.next();
				System.out.print("Id: " + employee.getId());
				System.out.print("  Name: " + employee.getName());
				System.out.println("  Salary: " + employee.getSalary());
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/* Method to UPDATE salary for an employee */
	public void updateEmployee(Integer EmployeeID, int salary) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Employee employee = (Employee) session.get(Employee.class, EmployeeID);
			employee.setSalary(salary);
			session.update(employee);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/* Method to DELETE an employee from the records */
	public void deleteEmployee(Integer EmployeeID) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Employee employee = (Employee) session.get(Employee.class, EmployeeID);
			session.delete(employee);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	private static void printData(Employee emp, int id) {

		System.out.println(id + ":: Name=" + emp.getName() + ", salary=" + emp.getSalary());

	}
}
