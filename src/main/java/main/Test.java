package main;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import DAO.EmployeeDao;
import POJO.Employee;



public class Test {

	
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		System.out.println("1");
			ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		System.out.println("2");
		EmployeeDao dao=(EmployeeDao)ctx.getBean("employeeDao",EmployeeDao.class);
		System.out.println("3");
		//Employee emp=dao.getEmp(10);
	
	/*Employee emp = new Employee();
	emp.setId(208);
	emp.setName("surender");
	emp.setSalary(400000);
	//dao.saveEmployee(emp);
	
	dao.listEmployees();*/
}
}
