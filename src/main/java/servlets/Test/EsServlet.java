package servlets.Test;

import dao.AdapterDAO;
import dao.IEmployeeDAO;
import dao.implement.AdapterImplDAO;
import dao.implement.EmployeeDAO;
import dao.implement.OrganizationDAO;
import dao.implement.PositionDAO;
import entites.Employee;
import entites.Position;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//@WebServlet("/es")
public class EsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IEmployeeDAO employeeDAO = new EmployeeDAO(AdapterImplDAO.getInstance());
        List<Employee> employees= employeeDAO.getAllEmployees();
       req.setAttribute("organizationId", new OrganizationDAO(AdapterImplDAO.getInstance()));
       req.setAttribute("positionId", new PositionDAO(AdapterImplDAO.getInstance()));
        req.setAttribute("employee",employees);
        req.getRequestDispatcher("es.jsp").forward(req,resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

}