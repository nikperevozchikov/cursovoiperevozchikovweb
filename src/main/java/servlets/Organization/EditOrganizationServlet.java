package servlets.Organization;

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

@WebServlet({"/EditOrganizationServlet", "/organizationsEdit"})
public class EditOrganizationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String nameOrganization = request.getParameter("nameOrganization");
        Long ogrn = Long.valueOf(request.getParameter("ogrn"));
        Date dateFoundation = Date.valueOf(request.getParameter("dateFoundation"));
        Integer eventId = Integer.valueOf(request.getParameter("eventId"));
        Integer modeId = Integer.valueOf(request.getParameter("modeId"));
        Organization organization =(Organization) request.getSession().getAttribute("organization");
        organization.setNameOrganization(nameOrganization);
        organization.setOgrn(ogrn);
        organization.setDateFoundation(dateFoundation);
        organization.setEventId(eventId);
        organization.setModeId(modeId);
        IOrganizationDAO organizationDAO = new OrganizationDAO(AdapterImplDAO.getInstance());
        organizationDAO.updateOrganization(organization);
        response.sendRedirect("/OrganizationServlet");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id= Integer.parseInt(request.getParameter("organizationId"));
        IOrganizationDAO organizationDAO = new OrganizationDAO(AdapterImplDAO.getInstance());
        Organization organization = organizationDAO.getOrganizationById(id);
        IEventDAO eventDAO = new EventDAO(AdapterImplDAO.getInstance());
        ISupervisionDAO supervisionDAO = new SupervisionDAO(AdapterImplDAO.getInstance());
        List<Organization> organizations = organizationDAO.getAllOrganizations();
        List<Event> events = eventDAO.getAllEvents();
        List<Supervision> supervisions = supervisionDAO.getAllSupervisions();
        request.setAttribute("organizations", organizations);
        request.setAttribute("events", events);
        request.setAttribute("supervisions", supervisions);
        request.setAttribute("eventId", new EventDAO(AdapterImplDAO.getInstance()));
        request.setAttribute("modeId", new SupervisionDAO(AdapterImplDAO.getInstance()));
        request.setAttribute("organization", organization);
        request.getRequestDispatcher("organizationsEdit.jsp").forward(request, response);
    }
}