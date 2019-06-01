package servlets.Event;


import dao.*;
import dao.implement.*;
import entites.Employee;
import entites.Event;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.color.ICC_Profile;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet({"/EventServlet", "/events"})
public class EventServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;


    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        String message = null;
        String direction = null;
        IEventDAO eventDAO = new EventDAO(AdapterImplDAO.getInstance());
        List<Event> events = eventDAO.getAllEvents();
        switch (action == null ? "QRY" : action) {

            case "QRY":


                if (events != null) {

                    request.setAttribute("events", events);
                } else {
                    message = ((EventDAO) eventDAO).getMessage();
                }
                direction = "/eventsQry.jsp";

                break;

            case "INS":
                Event event = new Event();
                message = verification(request, event);

                if (message == null) {
                    eventDAO.addEvent(event);
                    message = ((EventDAO) eventDAO).getMessage();

                    if (message != null) {

                        request.setAttribute("event", event);
                        direction = "eventsIns.jsp";
                    } else {
                        direction = "EventServlet?action=QRY";
                    }

                } else {
                    request.setAttribute("event", event);
                    direction = "eventsIns.jsp";
                }
                break;

            case "FND":
                event = null;
                Integer eventId = Integer.valueOf(request.getParameter("eventId"));

                if (eventId != null) {
                    event = ((EventDAO) eventDAO).getEventById(eventId);

                    if (event != null) {

                        request.getSession().setAttribute("event", event);

                        direction = "eventsUpd.jsp";
                    } else {
                        message = ((EventDAO) eventDAO).getMessage();
                        direction = "EventServlet?action=QRY";
                    }

                } else {
                    message = "Ne naideno id.";
                    direction = "EventServlet?action=QRY";
                }
                break;

            case "UPD":
                event = new Event();
                message = verification(request, event);

                if (message == null) {
                    eventDAO.updateEvent(event);
                    message = ((EventDAO) eventDAO).getMessage();

                    if (message != null) {

                        request.setAttribute("event", event);
                        direction = "eventsUpd.jsp";
                    } else {
                        direction = "EventServlet?action=QRY";
                    }

                } else {
                    request.setAttribute("event", event);
                    direction = "eventsUpd.jsp";
                }
                break;

            case "DEL":
                eventId = Integer.valueOf(request.getParameter("eventId"));

                if (eventId != null) {
                    eventDAO.deleteEvent(eventId);
                    message = ((EventDAO) eventDAO).getMessage();

                } else {
                    message = "Не найдено сотрудника для удаления.";
                    direction = "EventServlet?action=QRY";
                }

                direction = "EventServlet?action=QRY";
                break;

            default:
                message = "Действие не требуется";

        }

        if (message != null) {
            String msg = "<div class=\"col-md-5 col-md-offset-3\" style=\"text-align: center\">";
            msg += "<div class=\"alert alert-danger\">";
            msg += "<button class=\"close\" data-dismiss=\"alert\"><span>&times;</span></button>";
            msg += "<strong>Внимание!!!</strong><br/>";
            msg += message;
            msg += "</div></div>";
            request.setAttribute("message", msg);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(direction);
        dispatcher.forward(request, response);

    }

    private String verification(HttpServletRequest request, Event event) {


        String message = "<ul>";
       // Integer eventId = Integer.valueOf(request.getParameter("eventId"));
        String nameEvent = request.getParameter("nameEvent");
        Date dateOfEvent = request.getParameter("organizationId") == null ? new Date(-1) :Date.valueOf(request.getParameter("dateOfEvent"));
        String resultOfEvent = request.getParameter("resultOfEvent");
        //if ((eventId == null) || (eventId == 0)) {
          //  message += "<li>Ввод неверен!!!</li>";
       // }
        if ((nameEvent == null) || (nameEvent.trim().length() == 0)) {
            message += "<li>Введите данные!!!</li>";
        }
        if ((resultOfEvent == null) || (resultOfEvent.trim().length() == 0)) {
            message += "<li>Введите данные!!!</li>";
        }


       // event.setEventId(eventId);
     event.setNameEvent(nameEvent);
     event.setDateOfEvent(dateOfEvent);
     event.setResultOfEvent(resultOfEvent);
        if (message.equals("<ul>")) {
            message = null;
        } else {
            message += "</ul>";
        }

        return message;
    }

}
