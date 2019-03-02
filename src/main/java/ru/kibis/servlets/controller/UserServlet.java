package ru.kibis.servlets.controller;

import ru.kibis.servlets.action.ActionFactory;
import ru.kibis.servlets.storage.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {
    private ActionFactory factory = ActionFactory.getInstance();
    private final ValidateService validateService = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html");
        req.setAttribute("users", validateService.findAll());
        req.getRequestDispatcher("/WEB-INF/view/users.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html");
        factory.action("delete", req);
        resp.setContentType("text/html");
        doGet(req, resp);
    }
}