package ru.kibis.servlets.controller;

import ru.kibis.servlets.action.ActionFactory;
import ru.kibis.servlets.model.Role;
import ru.kibis.servlets.model.User;
import ru.kibis.servlets.storage.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserServlet extends HttpServlet {
    private ActionFactory factory = ActionFactory.getInstance();
    private final ValidateService validateService = ValidateService.getInstance();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html");
        req.setAttribute("users", validateService.findAll());
        req.getRequestDispatcher("/WEB-INF/view/users.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html");
        int id = Integer.valueOf(req.getParameter("id"));
        HttpSession session = req.getSession();
        if (validateService.findById(id)) {
            User user = validateService.getUserById(id);
            synchronized (session) {
                String role = (String) session.getAttribute("role");
                int userRoleRate = Role.valueOf(role).ordinal();
                int userToUpdateRoleRate = Role.valueOf(user.getRole()).ordinal();
                if (userRoleRate < userToUpdateRoleRate) {
                    factory.action("delete", req);
                    doGet(req, resp);
                } else {
                    req.setAttribute("error", "Not enough rights");
                    doGet(req, resp);
                }
            }
        }
    }
}