package ru.kibis.servlets.controller;

import ru.kibis.servlets.model.User;
import ru.kibis.servlets.storage.Validate;
import ru.kibis.servlets.storage.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Сервлет для входа пользователя в систему
 */
public class UserLoginServlet extends HttpServlet {
    /**
     * Сервис валидации
     */
    private final Validate validateService = ValidateService.getInstance();

    /**
     * Метод GET осуществляет переадресацию на JSP страницу ввода логина и пароля
     *
     * @param req  HTTP запрос
     * @param resp ответ
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, resp);
    }

    /**
     * Метод POST извлекает из параметров HTTP запроса "login" и "password",
     * передает извлеченные значения в сервис валидации для проверки
     * на существование пользователя с такими "login" и "password".
     * Если такой пользователь существует, то его "login" и "role" устанавливаются в атрибуты сессии
     * и происходит редирект на основной сервлет.
     * Если такой пользователь не существует, то в атрибут запроса передается сообщение об ошибке
     * и происходит вызов метода doGet данного сервлета.
     *
     * @param req  HTTP запрос
     * @param resp ответ
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = validateService.isCredentional(login, password);
        if (user != null) {
            HttpSession session = req.getSession();
            String role = user.getRole();
            session.setAttribute("login", login);
            session.setAttribute("role", role);
            resp.sendRedirect(String.format("%s/servlets", req.getContextPath()));
        } else {
            req.setAttribute("error", "Credential invalid");
            doGet(req, resp);
        }
    }
}