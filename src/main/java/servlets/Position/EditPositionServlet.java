package servlets.Position;


import dao.IEventDAO;
import dao.IOrganizationDAO;
import dao.IPositionDAO;
import dao.ISupervisionDAO;
import dao.implement.*;
import entites.Event;
import entites.Organization;
import entites.Position;
import entites.Supervision;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet({"/EditPositionServlet", "/positionsEdit"})
public class EditPositionServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String namePosition = request.getParameter("namePosition");
        Double salary = Double.valueOf(request.getParameter("salary"));
        Position position =(Position) request.getSession().getAttribute("position");
        position.setNamePosition(namePosition);
        position.setSalary(salary);
        IPositionDAO positionDAO = new PositionDAO(AdapterImplDAO.getInstance());
        positionDAO.updatePosition(position);
        response.sendRedirect("/PositionServlet");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id= Integer.parseInt(request.getParameter("positionId"));

        IPositionDAO positionDAO = new PositionDAO(AdapterImplDAO.getInstance());
       Position position = positionDAO.getPositionById(id);

        List<Position> positions = positionDAO.getAllPositions();

        request.setAttribute("positions", positions);

        request.getRequestDispatcher("positionsEdit.jsp").forward(request, response);

    }
}