package ru.kibis.servlets.action;

import ru.kibis.servlets.model.Contacts;
import ru.kibis.servlets.model.Role;
import ru.kibis.servlets.model.User;
import ru.kibis.servlets.storage.Validate;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

/**
 * Класс реализует интерфейс Action для изменения существующего пользователя
 */
public class Update implements Action {
    /**
     * Метод извлекает из HTTP запроса id пользователя,
     * через сервис валидации находит пользователя user по данному id,
     * создает новый объект updatedUser,
     * передает user и updatedUser в сервис валидации, для проверки и последующего изменения в методе update
     *
     * @param validateService выбранный сервис валидации
     * @param req             HTTP запрос
     */
    @Override
    public void doAction(Validate validateService, HttpServletRequest req) {
        final Map<String, String[]> map = req.getParameterMap();
        int idToUpdate = Integer.valueOf(Objects.requireNonNull(map.get("id")[0]));
        User user = validateService.findById(idToUpdate);
        User updatedUser = new User(
                map.get("login")[0],
                map.get("password")[0],
                Role.valueOf(map.get("role")[0].toUpperCase()),
                new Contacts(
                        map.get("name")[0],
                        map.get("email")[0],
                        map.get("country_update")[0],
                        map.get("city_update")[0]
                )
        );
        validateService.update(user, updatedUser);
    }
}
