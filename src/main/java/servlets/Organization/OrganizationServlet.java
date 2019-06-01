package servlets.Organization;

import dao.*;
import dao.implement.*;
import entites.*;

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

@WebServlet({"/OrganizationServlet", "/organizations"})
public class OrganizationServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        String message = null;
        String direction = null;

        IOrganizationDAO organizationDAO = new OrganizationDAO(AdapterImplDAO.getInstance());
        IEventDAO eventDAO = new EventDAO(AdapterImplDAO.getInstance());
        ISupervisionDAO supervisionDAO = new SupervisionDAO(AdapterImplDAO.getInstance());
        List<Organization> organizations = organizationDAO.getAllOrganizations();
        List<Event> events = eventDAO.getAllEvents();
        List<Supervision> supervisions = supervisionDAO.getAllSupervisions();
        switch (action == null ? "QRY" : action) {

            case "QRY":


                if (organizations != null) {
                    request.setAttribute("eventId", eventDAO);
                    request.setAttribute("modeId", supervisionDAO);
                    request.setAttribute("organizations", organizations);
                    request.setAttribute("events", events);
                    request.setAttribute("supervisions", supervisions);
                } else {
                    message = ((OrganizationDAO) organizationDAO).getMessage();
                }
                direction = "/organizationsQry.jsp";

                break;

            case "INS":
                Organization organization = new Organization();
                message = verification(request, organization);

                if (message == null) {
                    organizationDAO.addOrganization(organization);
                    message = ((OrganizationDAO) organizationDAO).getMessage();

                    if (message != null) {
                        request.setAttribute("eventId", eventDAO);
                        request.setAttribute("modeId", supervisionDAO);
                        request.setAttribute("organization", organization);
                        request.setAttribute("events", events);
                        request.setAttribute("supervisions", supervisions);
                        direction = "organizationsIns.jsp";
                    } else {
                        direction = "OrganizationServlet?action=QRY";
                    }

                } else {
                    request.setAttribute("events", events);
                    request.setAttribute("supervisions", supervisions);
                    request.setAttribute("organization", organization);
                    direction = "organizationsIns.jsp";
                }
                break;

            case "FND":
                organization = null;
                Integer organizationId = Integer.valueOf(request.getParameter("organizationId"));

                if (organizationId != null) {
                    organization = organizationDAO.getOrganizationById(organizationId);

                    if (organization != null) {
                        request.setAttribute("eventId", eventDAO);
                        request.setAttribute("modeId", supervisionDAO);
                        request.getSession().setAttribute("organization", organization);
                        request.setAttribute("events", events);
                        request.setAttribute("supervisions", supervisions);
                        direction = "organizationsUpd.jsp";
                    } else {
                        message = ((OrganizationDAO) organizationDAO).getMessage();
                        direction = "OrganizationServlet?action=QRY";
                    }

                } else {
                    message = "Ne naideno id.";
                    direction = "OrganizationServlet?action=QRY";
                }
                break;

            case "UPD":
                organization = new Organization();
                message = verification(request, organization);

                if (message == null) {
                    organizationDAO.updateOrganization(organization);
                    message = ((OrganizationDAO) organizationDAO).getMessage();

                    if (message != null) {
                        request.setAttribute("eventId", eventDAO);
                        request.setAttribute("modeId", supervisionDAO);
                        request.setAttribute("organization", organization);
                        direction = "organizationsUpd.jsp";
                    } else {
                        direction = "OrganizationServlet?action=QRY";
                    }

                } else {
                    request.setAttribute("organization", organization);
                    direction = "organizationsUpd.jsp";
                }
                break;

            case "DEL":
                organizationId = Integer.valueOf(request.getParameter("organizationId"));

                if (organizationId != null) {
                    organizationDAO.deleteOrganization(organizationId);
                    message = ((OrganizationDAO) organizationDAO).getMessage();

                } else {
                    message = "Не найдено сотрудника для удаления.";
                    direction = "OrganizationServlet?action=QRY";
                }

                direction = "OrganizationServlet?action=QRY";
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

    private String verification(HttpServletRequest request, Organization organization) {


        String message = "<ul>";
        // Integer organizationId = Integer.valueOf(request.getParameter("organizationId"));
        String nameOrganization = request.getParameter("nameOrganization");
        Long ogrn = request.getParameter("ogrn") == null ? -1 : Long.valueOf(request.getParameter("ogrn"));
        Date dateFoundation = request.getParameter("ogrn") == null ? new Date(-1) : Date.valueOf(request.getParameter("dateFoundation"));
        Integer eventId = request.getParameter("eventId") == null ? -1 : Integer.valueOf(request.getParameter("eventId"));
        Integer modeId = request.getParameter("modeId") == null ? -1 : Integer.valueOf(request.getParameter("modeId"));
        if ((nameOrganization == null) || (nameOrganization.trim().length() == 0)) {
            message += "<li>Введите данные!!!</li>";
        }
        if ((ogrn == 0)) {
            message += "<li>Ввод неверен!!!</li>";
        }
        if ((eventId == 0)) {
            message += "<li>Ввод невере!!!</li>";
        }

        if ((modeId == 0)) {
            message += "<li>Ввод неверен!!!</li>";
        }

        organization.setNameOrganization(nameOrganization);
        organization.setOgrn(ogrn);
        organization.setDateFoundation(dateFoundation);
        organization.setEventId(eventId);
        organization.setModeId(modeId);
        if (message.equals("<ul>")) {
            message = null;
        } else {
            message += "</ul>";
        }

        return message;
    }

}

