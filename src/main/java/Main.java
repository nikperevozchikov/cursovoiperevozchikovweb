import dao.AdapterDAO;
import dao.IEmployeeDAO;
import dao.implement.AdapterImplDAO;
import dao.implement.EmployeeDAO;
import entites.Employee;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args)  {
        AdapterDAO dao = AdapterImplDAO.getInstance();
        IEmployeeDAO employeeDAO = new EmployeeDAO(dao);
        List<Employee> employeeList= employeeDAO.getAllEmployees();

        System.out.println(employeeList);
    }
}
