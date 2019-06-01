package servlets.Employee;


import dao.IEmployeeDAO;
import dao.IOrganizationDAO;
import dao.IPositionDAO;
import dao.implement.AdapterImplDAO;
import dao.implement.EmployeeDAO;
import dao.implement.OrganizationDAO;
import dao.implement.PositionDAO;
import entites.Employee;
import entites.Organization;
import entites.Position;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet({"/EditEmployeeServlet", "/employeesEdit"})
public class EditEmployeeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String fio = request.getParameter("fio");
        Long seriaNumber = Long.valueOf(request.getParameter("seriaNumber"));
        Integer organizationId = Integer.valueOf(request.getParameter("organizationId"));
        Integer positionId = Integer.valueOf(request.getParameter("positionId"));
        Employee employee = (Employee) request.getSession().getAttribute("employee");
        employee.setFio(fio);
        employee.setSeriaNumber(seriaNumber);
        employee.setOrganizationId(organizationId);
        employee.setPositionId(positionId);
        IEmployeeDAO employeeDAO = new EmployeeDAO(AdapterImplDAO.getInstance());
        employeeDAO.updateEmployee(employee);
        response.sendRedirect("/EmployeeServlet");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id= Integer.parseInt(request.getParameter("employeeId"));
        IEmployeeDAO employeeDAO = new EmployeeDAO(AdapterImplDAO.getInstance());
        Employee employee = employeeDAO.getEmployeeById(id);
        IOrganizationDAO organizationDAO = new OrganizationDAO(AdapterImplDAO.getInstance());
        IPositionDAO positionDAO = new PositionDAO(AdapterImplDAO.getInstance());
        List<Employee> employees = employeeDAO.getAllEmployees();
        List<Position> positions = positionDAO.getAllPositions();
        List<Organization> organizations = organizationDAO.getAllOrganizations();
        Organization organizationn = organizationDAO.getOrganizationById(employee.getOrganizationId());
        Position positionn = positionDAO.getPositionById(employee.getPositionId());
        request.setAttribute("organizations", organizations);
        request.setAttribute("positions", positions);
        request.setAttribute("employees", employees);
        request.setAttribute("organizationId", new OrganizationDAO(AdapterImplDAO.getInstance()));
        request.setAttribute("positionId", new PositionDAO(AdapterImplDAO.getInstance()));
        request.setAttribute("employee", employee);
        request.setAttribute("organization1", organizationn);
        request.setAttribute("position1", positionn);
        request.getRequestDispatcher("employeesUpd.jsp").forward(request, response);

    }
}