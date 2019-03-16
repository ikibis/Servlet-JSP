package ru.kibis.servlets.action;

import ru.kibis.servlets.storage.Validate;

import javax.servlet.http.HttpServletRequest;

/**
 * Интерфейс для действий пользователя
 */
public interface Action {
    /**
     * Метод для обработки действия пользователя
     * @param validateService выбранный сервис валидации
     * @param req HTTP запрос
     */
    void doAction(Validate validateService, HttpServletRequest req);
}
