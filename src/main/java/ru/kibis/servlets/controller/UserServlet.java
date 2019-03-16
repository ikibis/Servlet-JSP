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
 * Основной сервлет
 */
public class UserServlet extends HttpServlet {
    /**
     * Фабрика для действий пользователя
     */
    private ActionFactory factory = ActionFactory.getInstance();
    /**
     * Сервис валидации
     */
    private final Validate validateService = ValidateService.getInstance();

    /**
     * Метод GET помещает список пользователей в атрибут запроса,
     * осуществляет переадресацию на основную JSP страницу вывода всех пользователей.
     *
     * @param req  HTTP запрос
     * @param resp ответ
     * @throws IOException
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html");
        req.setAttribute("users", validateService.findAll());
        req.getRequestDispatcher("/WEB-INF/view/users.jsp").forward(req, resp);
    }

    /**
     * Метод POST (удаление пользователя) извлекает из параметров HTTP запроса "id",
     * с помощью сервиса валидации ищет пользователя с таким "id".
     * Если пользователь существует, то запрашиваются атрибуты сессии "login" и "role".
     * И происходит проверка наличия прав на осуществление операции удаления.
     * Если прав хватает, то для фабрики действий вызывается метод action на удаление пользователя.
     * Если прав не хватает, то в атрибут запроса передается сообщение об ошибке
     * происходит вызов метода doGet данного сервлета.
     *
     * @param req  HTTP запрос
     * @param resp ответ
     * @throws IOException
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
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
                factory.action("delete", req);
                doGet(req, resp);
            } else {
                req.setAttribute("error", "Not enough rights");
                doGet(req, resp);
            }
        }
    }
}