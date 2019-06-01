package servlets.Test;


import dao.AdapterDAO;
import dao.IEmployeeDAO;
import dao.implement.AdapterImplDAO;
import dao.implement.EmployeeDAO;
import entites.Employee;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet("/EmployeServlet")
public class EmployeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter pw = resp.getWriter();
        try {
            EmployeeDAO employeeDAO = new EmployeeDAO(AdapterImplDAO.getInstance());
//String[] countries = {"india", "afro"};
//req.setAttribute("countries", countries);
            List<Employee> col = employeeDAO.getAllEmployees();
            for (Employee st : col) {
                int employeeId = st.getEmployeeId();
                req.setAttribute("employeeId", employeeId);
                String fio = st.getFio();
                req.setAttribute("fio", fio);
                long seriaNumber = st.getSeriaNumber();
                req.setAttribute("seriaNumber", seriaNumber);
            }
            req.setAttribute("col", col);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/employes.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            System.out.println("oshib");
        }

    }}
