package servlets.Event;


import dao.*;
import dao.implement.*;
import entites.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet({"/EditEventServlet", "/eventsEdit"})
public class EditEventServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String nameEvent = request.getParameter("nameEvent");
        Date dateOfEvent = Date.valueOf(request.getParameter("dateOfEvent"));
        String resultOfEvent = request.getParameter("resultOfEvent");
        Event event = (Event) request.getSession().getAttribute("event");
        event.setNameEvent(nameEvent);
        event.setDateOfEvent(dateOfEvent);
        event.setResultOfEvent(resultOfEvent);

        IEventDAO eventDAO = new EventDAO(AdapterImplDAO.getInstance());
        eventDAO.updateEvent(event);
        response.sendRedirect("/EventServlet");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("eventId"));


        IEventDAO eventDAO = new EventDAO(AdapterImplDAO.getInstance());
        Event event = ((EventDAO) eventDAO).getEventById(id);
        List<Event> events = eventDAO.getAllEvents();

        request.setAttribute("events", events);
        request.setAttribute("eventId", new EventDAO(AdapterImplDAO.getInstance()));
        request.setAttribute("event", event);
        request.getRequestDispatcher("eventsEdit.jsp").forward(request, response);

    }
}