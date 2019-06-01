package servlets.Position;


import dao.AdapterDAO;
import dao.IEmployeeDAO;
import dao.IOrganizationDAO;
import dao.IPositionDAO;
import dao.implement.AdapterImplDAO;
import dao.implement.EmployeeDAO;
import dao.implement.OrganizationDAO;
import dao.implement.PositionDAO;
import entites.Employee;
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

@WebServlet({"/PositionServlet", "/positions"})
public class PositionServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;


    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        String message = null;
        String direction = null;

        IPositionDAO positionDAO = new PositionDAO(AdapterImplDAO.getInstance());
        List<Position> positions = positionDAO.getAllPositions();
        switch (action == null ? "QRY" : action) {

            case "QRY":


                if (positions != null) {

                    request.setAttribute("positions", positions);
                } else {
                    message = ((PositionDAO) positionDAO).getMessage();
                }
                direction = "/positionsQry.jsp";

                break;

            case "INS":
                Position position = new Position();
                message = verification(request, position);

                if (message == null) {
                    positionDAO.addPosition(position);
                    message = ((PositionDAO) positionDAO).getMessage();

                    if (message != null) {

                        request.setAttribute("position", position);
                        direction = "positionsIns.jsp";
                    } else {
                        direction = "PositionServlet?action=QRY";
                    }

                } else {
                    request.setAttribute("position", position);
                    direction = "positionsIns.jsp";
                }
                break;

            case "FND":
                position = null;
                Integer positionId = Integer.valueOf(request.getParameter("positionId"));

                if (positionId != null) {
                    position = positionDAO.getPositionById(positionId);

                    if (position != null) {


                        request.getSession().setAttribute("position", position);

                        direction = "positionsUpd.jsp";
                    } else {
                        message = ((PositionDAO) positionDAO).getMessage();
                        direction = "PositionServlet?action=QRY";
                    }

                } else {
                    message = "Ne naideno id.";
                    direction = "PositionServlet?action=QRY";
                }
                break;

            case "UPD":
                position = new Position();
                message = verification(request, position);

                if (message == null) {
                    positionDAO.updatePosition(position);
                    message = ((PositionDAO) positionDAO).getMessage();

                    if (message != null) {

                        request.setAttribute("position", position);
                        direction = "positionsUpd.jsp";
                    } else {
                        direction = "PositionServlet?action=QRY";
                    }

                } else {
                    request.setAttribute("position", position);
                    direction = "positionsUpd.jsp";
                }
                break;

            case "DEL":
                positionId = Integer.valueOf(request.getParameter("positionId"));

                if (positionId != null) {
                    positionDAO.deletePosition(positionId);
                    message = ((PositionDAO) positionDAO).getMessage();

                } else {
                    message = "Не найдено сотрудника для удаления.";
                    direction = "PositionServlet?action=QRY";
                }

                direction = "PositionServlet?action=QRY";
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

    private String verification(HttpServletRequest request, Position position) {


        String message = "<ul>";
       // Integer positionId = Integer.valueOf(request.getParameter("positionId"));
        String namePosition = request.getParameter("namePosition");
        Double salary = request.getParameter("salary") == null ? -1 : Double.valueOf(request.getParameter("salary"));
       // if ((positionId == null) || (positionId == 0)) {
         //   message += "<li>Ввод неверен!!!</li>";
       // }
        if ((namePosition == null) || (namePosition.trim().length() == 0)) {
            message += "<li>Введите данные!!!</li>";
        }
        if ( (salary== 0)) {
            message += "<li>Ввод неверен!!!</li>";
        }



     // position.setPositionId(positionId);
      position.setNamePosition(namePosition);
      position.setSalary(salary);

        if (message.equals("<ul>")) {
            message = null;
        } else {
            message += "</ul>";
        }

        return message;
    }

}

