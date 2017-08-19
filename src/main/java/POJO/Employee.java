package POJO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="emp123")
public class Employee {  
@Id

private int id;  

@Override
public String toString() {
	return "Employee [id=" + id + ", name=" + name + ", salary=" + salary + "]";
}
private String name;  
private float salary;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}

public Employee() {
	super();
}
public Employee(int id, String name, float salary) {
	super();
	this.id = id;
	this.name = name;
	this.salary = salary;
}
public float getSalary() {
	return salary;
}
public void setSalary(float salary) {
	this.salary = salary;
}

public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}  


}