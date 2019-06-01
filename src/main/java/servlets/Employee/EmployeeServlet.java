package servlets.Employee;

import dao.AdapterDAO;
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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.color.ICC_Profile;
import java.io.IOException;
import java.util.List;

@WebServlet({"/EmployeeServlet", "/employees"})
public class EmployeeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;


    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        String message = null;
        String direction = null;

        IEmployeeDAO employeeDAO = new EmployeeDAO(AdapterImplDAO.getInstance());
        IOrganizationDAO organizationDAO = new OrganizationDAO(AdapterImplDAO.getInstance());
        IPositionDAO positionDAO = new PositionDAO(AdapterImplDAO.getInstance());
        List<Employee> employees = employeeDAO.getAllEmployees();
        List<Position> positions = positionDAO.getAllPositions();
        List<Organization> organizations = organizationDAO.getAllOrganizations();

        switch (action == null ? "QRY" : action) {

            case "QRY":
                //  List<Position> positions = positionDAO.getAllPositions();
                // List<Organization> organizations = organizationDAO.getAllOrganizations();
                //List<Employee> employees = employeeDAO.getAllEmployees();
                if (employees != null) {
                    request.setAttribute("organizationId", organizationDAO);
                    request.setAttribute("positionId", positionDAO);
                    request.setAttribute("employees", employees);
                    request.setAttribute("organizations", organizations);
                    request.setAttribute("positions", positions);
                } else {
                    message = employeeDAO.getMessage();
                }
                direction = "/employeesQry.jsp";

                break;

            case "INS":
                Employee employee = new Employee();
                message = verification(request, employee);
                if (message == null) {
                    employeeDAO.addEmployee(employee);
                    message = employeeDAO.getMessage();

                    if (message != null) {
                        request.setAttribute("organizations", organizations);
                        request.setAttribute("positions", positions);
                        request.setAttribute("organizationId", new OrganizationDAO(AdapterImplDAO.getInstance()));
                        request.setAttribute("positionId", new PositionDAO(AdapterImplDAO.getInstance()));
                        request.setAttribute("employee", employee);
                        direction = "employeesIns.jsp";
                    } else {
                        direction = "EmployeeServlet?action=QRY";
                    }

                } else {
                    request.setAttribute("employee", employee);
                    request.setAttribute("organizations", organizations);
                    request.setAttribute("positions", positions);
                    direction = "employeesIns.jsp";
                }
                break;

            case "FND":
                // employeeId = Integer.valueOf(request.getParameter("employeeId"));
                // employee = employeeDAO.getEmployeeById(employeeId);
                employee = null;

                Integer employeeId = Integer.valueOf(request.getParameter("employeeId"));

                if (employeeId != null) {
                    employee = employeeDAO.getEmployeeById(employeeId);
                  //  Organization organization = organizationDAO.getOrganizationById(employee.getOrganizationId());
                    if (employee != null) {
                        request.setAttribute("organizations", organizations);
                       // request.setAttribute("organization1", organization);
                        request.setAttribute("positions", positions);
                        request.setAttribute("organizationId", new OrganizationDAO(AdapterImplDAO.getInstance()));
                        request.setAttribute("positionId", new PositionDAO(AdapterImplDAO.getInstance()));
                        request.getSession().setAttribute("employee", employee);
                        direction = "employeesUpd.jsp";
                    } else {
                        message = employeeDAO.getMessage();
                        direction = "EmployeeServlet?action=QRY";
                    }

                } else {

                    message = "Ne naideno id.";
                    direction = "EmployeeServlet?action=QRY";
                }
                break;

            case "UPD":
                employeeId = Integer.valueOf(request.getParameter("employeeId"));
                if (employeeId != null) {
                    employee = employeeDAO.getEmployeeById(employeeId);
                    message = verification(request, employee);
                    if (message == null) {
                        employeeDAO.updateEmployee(employee);
                        message = employeeDAO.getMessage();

                        if (message != null) {
                            request.setAttribute("organizations", organizations);
                            request.setAttribute("positions", positions);
                            request.setAttribute("organizationId", new OrganizationDAO(AdapterImplDAO.getInstance()));
                            request.setAttribute("positionId", new PositionDAO(AdapterImplDAO.getInstance()));
                            request.setAttribute("employee", employee);
                            direction = "employeesUpd.jsp";
                        } else {
                            direction = "EmployeeServlet?action=QRY";
                        }


                    }
                }
                else {
                   // request.setAttribute("employee", employee);
                    request.setAttribute("organizations", organizations);
                    request.setAttribute("positions", positions);
                    direction = "employeesUpd.jsp";
                }
                break;


            case "DEL":
                employeeId = Integer.valueOf(request.getParameter("employeeId"));

                if (employeeId != null) {
                    employeeDAO.deleteEmployee(employeeId);
                    message = employeeDAO.getMessage();

                } else {
                    message = "Не найдено сотрудника для удаления.";
                    direction = "EmployeeServlet?action=QRY";
                }

                direction = "EmployeeServlet?action=QRY";
                break;

            default:
                message = "Действие не требуется";

        }

        if (message != null) {
            String msg = "<div class=\"col-md-5 col-md-offset-3\" style=\"text-align: center\">";
            msg += "<div class=\"alert alert-danger\">";
            msg += "<button class=\"close\" data-dismiss=\"alert\"><span>&times;</span></button>";
            msg += "<strong>Внимание!</strong><br/>";
            msg += message;
            msg += "</div></div>";
            request.setAttribute("message", msg);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(direction);
        dispatcher.forward(request, response);

    }

    private String verification(HttpServletRequest request, Employee employee) {
        String message = "<ul>";
        // Integer employeeId = Integer.valueOf(request.getParameter("employeeId"));
        String fio = request.getParameter("fio");
        Long seriaNumber = request.getParameter("seriaNumber") == null ? -1 : Long.valueOf(request.getParameter("seriaNumber"));
        Integer organizationId = request.getParameter("organizationId") == null ? -1 : Integer.valueOf(request.getParameter("organizationId"));
        Integer positionId = request.getParameter("positionId") == null ? -1 : Integer.valueOf(request.getParameter("positionId"));

        //if ((employeeId == null) || (employeeId == 0)) {
        // message += "<li>Ввод неверен!!!</li>";
        //}
        // message = "";
        if ((fio == null) || (fio.trim().length() == 0)) {
            message += "<li>Введите данные!</li>";
        }
        if ((seriaNumber == 0)) {
            message += "<li>Ввод неверен2!!!</li>";
        }
        if ((organizationId == 0)) {
            message += "<li>Ввод неверен3!!!</li>";
        }
        if ((positionId == 0)) {
            message += "<li>Ввод неверен4!!!</li>";
        }

        employee.setFio(fio);
        employee.setSeriaNumber(seriaNumber);
        employee.setOrganizationId(organizationId);
        employee.setPositionId(positionId);

        if (message.equals("<ul>")) {
            message = null;
        } else {
            message += "</ul>";
        }

        return message;
    }

}
