package dao;

import entites.Employee;

import java.sql.SQLException;
import java.util.List;

public interface IEmployeeDAO {
    public void addEmployee(Employee employee);
    public boolean isEmployeeExists(String fio);
    public Employee getEmployeeById(int employeeId);
    public Integer getIdByFio(String fio);
    public String getFioById(int employeeId);
    public void updateEmployee(Employee employee);
    public void deleteEmployee(int employeeId);
    public List<Employee> getAllEmployees();
   public Employee getEmployee(String fio, int seriaNumber) ;
    public String getMessage();
   // public List<Employee> getSorted(String col);



}
