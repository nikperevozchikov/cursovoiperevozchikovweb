package servlets.Supervision;

import dao.ISupervisionDAO;
import dao.implement.AdapterImplDAO;
import dao.implement.SupervisionDAO;
import entites.Supervision;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet({"/EditSupervisionServlet", "/supervisionsEdit"})
public class EditSupervisionServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String nameMode = request.getParameter("nameMode");
        String resultSupervision = request.getParameter("resultSupervision");
        Supervision supervision = (Supervision) request.getSession().getAttribute("supervision");
        supervision.setNameMode(nameMode);
        supervision.setResultSupervision(resultSupervision);
        ISupervisionDAO supervisionDAO = new SupervisionDAO(AdapterImplDAO.getInstance());
        supervisionDAO.updateSupervision(supervision);
        response.sendRedirect("/SupervisionServlet");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("modeId"));
        ISupervisionDAO supervisionDAO = new SupervisionDAO(AdapterImplDAO.getInstance());
        Supervision supervision = ((SupervisionDAO) supervisionDAO).getSupervisionById(id);
        List<Supervision> supervisions = supervisionDAO.getAllSupervisions();
        request.setAttribute("supervisions", supervisions);
        request.setAttribute("supervision", supervision);
        request.getRequestDispatcher("supervisionsEdit.jsp").forward(request, response);
    }
}