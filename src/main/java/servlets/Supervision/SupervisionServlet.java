package servlets.Supervision;

import dao.*;
import dao.implement.*;
import entites.Employee;
import entites.Position;
import entites.Supervision;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.color.ICC_Profile;
import java.io.IOException;
import java.util.List;

@WebServlet({"/SupervisionServlet", "/supervisions"})
public class SupervisionServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;


    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        String message = null;
        String direction = null;

        ISupervisionDAO supervisionDAO = new SupervisionDAO(AdapterImplDAO.getInstance());

        List<Supervision> supervisions = supervisionDAO.getAllSupervisions();
        switch (action == null ? "QRY" : action) {

            case "QRY":

                if (supervisions != null) {

                    request.setAttribute("supervisions", supervisions);
                } else {
                    message = ((SupervisionDAO) supervisionDAO).getMessage();
                }
                direction = "/supervisionsQry.jsp";

                break;

            case "INS":
                Supervision supervision = new Supervision();
                message = verification(request, supervision);

                if (message == null) {
                    supervisionDAO.addSupervision(supervision);
                    message = ((SupervisionDAO) supervisionDAO).getMessage();

                    if (message != null) {

                        request.setAttribute("supervision", supervision);
                        direction = "supervisionsIns.jsp";
                    } else {
                        direction = "SupervisionServlet?action=QRY";
                    }

                } else {
                    request.setAttribute("supervision", supervision);
                    direction = "supervisionsIns.jsp";
                }
                break;

            case "FND":
                supervision = null;
                Integer modeId = Integer.valueOf(request.getParameter("modeId"));

                if (modeId != null) {
                    supervision = supervisionDAO.getSupervisionById(modeId);

                    if (supervision != null) {


                        request.getSession().setAttribute("supervision", supervision);

                        direction = "supervisionsUpd.jsp";
                    } else {
                        message = ((SupervisionDAO) supervisionDAO).getMessage();
                        direction = "SupervisionServlet?action=QRY";
                    }

                } else {
                    message = "Ne naideno id.";
                    direction = "SupervisionServlet?action=QRY";
                }
                break;

            case "UPD":
                supervision = new Supervision();
                message = verification(request, supervision);

                if (message == null) {
                    supervisionDAO.updateSupervision(supervision);
                    message = ((SupervisionDAO) supervisionDAO).getMessage();

                    if (message != null) {

                        request.setAttribute("supervision", supervision);
                        direction = "supervisionsUpd.jsp";
                    } else {
                        direction = "SupervisionServlet?action=QRY";
                    }

                } else {
                    request.setAttribute("supervision", supervision);
                    direction = "supervisionsUpd.jsp";
                }
                break;

            case "DEL":
                modeId = Integer.valueOf(request.getParameter("modeId"));

                if (modeId != null) {
                    supervisionDAO.deleteSupervision(modeId);
                    message = ((SupervisionDAO) supervisionDAO).getMessage();

                } else {
                    message = "Не найдено сотрудника для удаления.";
                    direction = "SupervisionServlet?action=QRY";
                }

                direction = "SupervisionServlet?action=QRY";
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

    private String verification(HttpServletRequest request, Supervision supervision) {


        String message = "<ul>";
       // Integer modeId = Integer.valueOf(request.getParameter("modeId"));
        String nameMode = request.getParameter("nameMode");
        String resultSupervision = request.getParameter("resultSupervision");
       // if ((modeId == null) || (modeId == 0)) {
         //   message += "<li>Ввод неверен!!!</li>";
       // }
        if ((nameMode == null) || (nameMode.trim().length() == 0)) {
            message += "<li>Введите данные!!!</li>";
        }
        if ((resultSupervision == null) || (resultSupervision.trim().length() == 0)) {
            message += "<li>Введите данные!!!</li>";
        }


       // supervision.setModeId(modeId);
        supervision.setNameMode(nameMode);
        supervision.setResultSupervision(resultSupervision);

        if (message.equals("<ul>")) {
            message = null;
        } else {
            message += "</ul>";
        }

        return message;
    }

}


