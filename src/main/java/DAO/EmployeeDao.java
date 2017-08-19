package DAO;



import java.util.Iterator;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import POJO.Employee;
@Repository
public class EmployeeDao {
@Autowired

private SessionFactory sessionFactory;



public SessionFactory getSessionFactory() {
	return sessionFactory;
}



public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
}



public void saveEmployee(Employee e){  
	/* Session session = sessionFactory.openSession();
    System.out.println("Entered");
	session.save(e);
    System.out.println("Done");*/
	
	Session session = sessionFactory.openSession();
	session.beginTransaction();
	session.save(e);
	System.out.println("reached");
	session.getTransaction().commit();
	
}  
/* Using criteria example
 public void listEmployees()
{
	
	Session session = sessionFactory.openSession();
	session.beginTransaction();
	Criteria criteria = session.createCriteria(Employee.class);
	criteria.add(Restrictions.gt("id", 200));
	List employees=criteria.list();
	for(Iterator  iterator=employees.iterator();iterator.hasNext();)
	{
		Employee employee = (Employee) iterator.next();
		System.out.print("ID: " +employee.getId() ); 
        System.out.print("Name: " + employee.getName()); 
        System.out.println("Salary: " + employee.getSalary()); 
        
	}
	session.getTransaction().commit();

}*/
/*public void listEmployees()
{

Session session = sessionFactory.openSession();
session.beginTransaction();
String hql="FROM Employee";
Query query=session.createQuery(hql);
query.setFirstResult(0);
query.setMaxResults(4);
List employees=query.list();
System.out.println(employees);
for(Iterator  iterator=employees.iterator();iterator.hasNext();)
{
	Employee employee = (Employee) iterator.next();
	System.out.print("ID: " +employee.getId() ); 
    System.out.print("Name: " + employee.getName()); 
    System.out.println("Salary: " + employee.getSalary()); 
    
}
session.getTransaction().commit();


}*/

/* Method to  READ all the employees using Entity Query */
public void listEmployees()
{
	Session session = sessionFactory.openSession();
	session.beginTransaction();
	String sql="select * from emp123";
	SQLQuery query= session.createSQLQuery(sql);
	query.addEntity(Employee.class);
	List employees=query.list();
	
	for(Iterator  iterator=employees.iterator();iterator.hasNext();)
	{
		Employee employee = (Employee) iterator.next();
		System.out.print("ID: " +employee.getId() ); 
	    System.out.print("Name: " + employee.getName()); 
	    System.out.println("Salary: " + employee.getSalary()); 
	    
	}

	
	session.getTransaction().commit();
	
}


}
