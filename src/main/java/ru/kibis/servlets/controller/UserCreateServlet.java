package ru.kibis.servlets.controller;

import ru.kibis.servlets.action.ActionFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет для создания нового пользователя
 */
public class UserCreateServlet extends HttpServlet {
    /**
     * Фабрика для действий пользователя
     */
    private ActionFactory factory = ActionFactory.getInstance();

    /**
     * Метод GET осуществляет переадресацию на JSP страницу создания нового пользователя
     *
     * @param req  HTTP запрос
     * @param resp ответ
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html");
        req.getRequestDispatcher("/WEB-INF/view/create.jsp").forward(req, resp);
    }

    /**
     * Метод POST вызывает метод action фабрки для создания нового пользователя,
     * осуществляет редирект на основной сервлет
     *
     * @param req  HTTP запрос
     * @param resp ответ
     * @throws IOException
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        factory.action("create", req);
        resp.setContentType("text/html");
        resp.sendRedirect(req.getContextPath() + "/servlets");

    }
}