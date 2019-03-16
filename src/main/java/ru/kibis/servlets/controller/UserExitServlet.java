package ru.kibis.servlets.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Сервлет для выхода пользователя из системы
 */
public class UserExitServlet extends HttpServlet {
    /**
     * Метод GET осуществляет переадресацию на страницу ввода логина и пароля
     *
     * @param req  HTTP запрос
     * @param resp ответ
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect(String.format("%s/login", req.getContextPath()));
    }

    /**
     * Метод POST осуществляет очистку атрибутов для текущей сессии и делает ее недействительной,
     * вызывает метод doGet данного сервлета
     *
     * @param req  HTTP запрос
     * @param resp ответ
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        session.invalidate();
        doGet(req, resp);
    }
}