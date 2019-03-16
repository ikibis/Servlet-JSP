package ru.kibis.servlets.action;

import ru.kibis.servlets.model.Contacts;
import ru.kibis.servlets.model.Role;
import ru.kibis.servlets.model.User;
import ru.kibis.servlets.storage.Validate;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Класс реализует интерфейс Action для создания пользователя
 */
public class Create implements Action {
    /**
     * Определяем формат даты для поля "дата создания пользователя" хранилища
     */
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy");

    /**
     * Метод извлекает из HTTP запроса параметры необходимые для создания объекта User,
     * создает объект User,
     * передает его в сервис валидации, для проверки и последующего добавления в методе add
     *
     * @param validateService выбранный сервис валидации
     * @param req             HTTP запрос
     */
    @Override
    public void doAction(Validate validateService, HttpServletRequest req) {
        final Map<String, String[]> map = req.getParameterMap();
        String date = sdf.format(new Date());
        User user = new User(
                map.get("login")[0],
                map.get("password")[0],
                date,
                Role.valueOf(map.get("role")[0].toUpperCase()),
                new Contacts(
                        map.get("name")[0],
                        map.get("email")[0],
                        map.get("country")[0],
                        map.get("city")[0]
                )
        );
        validateService.add(user);
    }
}


