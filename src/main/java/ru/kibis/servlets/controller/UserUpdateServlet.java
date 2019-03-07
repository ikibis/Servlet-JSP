package ru.kibis.servlets.controller;

import ru.kibis.servlets.action.ActionFactory;
import ru.kibis.servlets.model.Role;
import ru.kibis.servlets.model.User;
import ru.kibis.servlets.storage.Validate;
import ru.kibis.servlets.storage.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserUpdateServlet extends HttpServlet {
    private final Validate validateService = ValidateService.getInstance();
    private ActionFactory factory = ActionFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        int id = Integer.valueOf(req.getParameter("id"));
        HttpSession session = req.getSession();
        User user = validateService.findById(id);
        if (user != null) {
            synchronized (session) {
                String role = (String) session.getAttribute("role");
                int userRoleRate = Role.valueOf(role).ordinal();
                int userToUpdateRoleRate = Role.valueOf(user.getRole()).ordinal();
                if (userRoleRate < userToUpdateRoleRate) {
                    req.setAttribute("user", user);
                    req.getRequestDispatcher("/WEB-INF/view/update.jsp").forward(req, resp);
                } else {
                    req.setAttribute("error", "Not enough rights");
                    req.getRequestDispatcher(req.getContextPath() + "/servlets").forward(req, resp);
                }
            }
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        factory.action("update", req);
        resp.setContentType("text/html");
        resp.sendRedirect(req.getContextPath() + "/servlets");
    }
}

