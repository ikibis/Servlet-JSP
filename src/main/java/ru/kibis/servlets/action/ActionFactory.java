package ru.kibis.servlets.action;

import ru.kibis.servlets.storage.Validate;
import ru.kibis.servlets.storage.ValidateService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Фабрика для обработки действий пользователя
 */
public class ActionFactory {
    private static class Holder {
        private static final ActionFactory INSTANCE = new ActionFactory();
    }

    /**
     * Метод возвращает новую фабрику действий пользователя
     *
     * @return Фабрика действий пользователя
     */
    public static ActionFactory getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Сервис валидации
     */
    private Validate service = ValidateService.getInstance();

    /**
     * HashMap для хранения действий пользователя
     */
    private Map<String, Action> actionMap = new HashMap<>();

    {
        actionMap.put("create", new Create());
        actionMap.put("update", new Update());
        actionMap.put("delete", new Delete());
    }

    /**
     * Метод проверяет переданное значение для действия пользователя
     * и вызывает для него doAction, если такое действие существует
     *
     * @param action действие пользователя
     * @param req    Http запрос
     */
    public void action(String action, HttpServletRequest req) {
        actionMap.getOrDefault(action, new UnknownAction()).doAction(service, req);
    }

    /**
     * Класс для возможной реализации выброса исключении, при неизвестном действии пользователя
     */
    class UnknownAction implements Action {
        /**
         * Метод для обработки действия пользователя
         *
         * @param validateService выбранный сервис валидации
         * @param req             HTTP запрос
         */
        @Override
        public void doAction(Validate validateService, HttpServletRequest req) {
        }

    }
}
