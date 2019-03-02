package ru.kibis.servlets.controller;

import ru.kibis.servlets.action.ActionFactory;
import ru.kibis.servlets.model.User;
import ru.kibis.servlets.storage.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserUpdateServlet extends HttpServlet {
    private final ValidateService validateService = ValidateService.getInstance();
    private ActionFactory factory = ActionFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        int id = Integer.valueOf(req.getParameter("id"));
        if (validateService.findById(id)) {
            User user = validateService.getUserById(id);
            req.setAttribute("user", user);
        }
        req.getRequestDispatcher("/WEB-INF/view/update.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        factory.action("update", req);
        resp.setContentType("text/html");
        resp.sendRedirect(req.getContextPath() + "/servlets");
    }
}

