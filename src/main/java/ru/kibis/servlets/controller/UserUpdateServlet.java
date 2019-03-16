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

/**
 * Сервлет для изменения пользователя
 */
public class UserUpdateServlet extends HttpServlet {
    /**
     * Сервис валидации
     */
    private final Validate validateService = ValidateService.getInstance();
    /**
     * Фабрика для действий пользователя
     */
    private ActionFactory factory = ActionFactory.getInstance();

    /**
     * Метод GET извлекает из параметров HTTP запроса "id",
     * с помощью сервиса валидации ищет пользователя с таким "id".
     * Если пользователь существует, то запрашиваются атрибуты сессии "login" и "role".
     * И происходит проверка наличия прав на осуществление операции изменения.
     * Если прав хватает, объект User в качестве атрибута передается на JSP страницу редактирования пользователя.
     * Если прав не хватает, то в атрибут запроса передается сообщение об ошибке и
     * происходит переход на основной сервлет.
     *
     * @param req  HTTP запрос
     * @param resp ответ
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        int id = Integer.valueOf(req.getParameter("id"));
        HttpSession session = req.getSession();
        User user = validateService.findById(id);
        if (user != null) {
            String role = (String) session.getAttribute("role");
            String login = (String) session.getAttribute("login");
            int userRoleRate = Role.valueOf(role).ordinal();
            int userToUpdateRoleRate = Role.valueOf(user.getRole()).ordinal();
            if (userRoleRate < userToUpdateRoleRate || login.equals(user.getLogin())) {
                req.setAttribute("user", user);
                req.getRequestDispatcher("/WEB-INF/view/update.jsp").forward(req, resp);
            } else {
                req.setAttribute("error", "Not enough rights");
                req.getRequestDispatcher(req.getContextPath() + "/servlets").forward(req, resp);
            }
        }
    }

    /**
     * Метод POST вызывает метод action фабрки для изменения пользователя,
     * осуществляет редирект на основной сервлет.
     *
     * @param req  HTTP запрос
     * @param resp ответ
     * @throws IOException
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        factory.action("update", req);
        resp.setContentType("text/html");
        resp.sendRedirect(req.getContextPath() + "/servlets");
    }
}

