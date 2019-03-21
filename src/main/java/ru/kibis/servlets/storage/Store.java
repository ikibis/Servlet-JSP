package ru.kibis.servlets.storage;

import ru.kibis.servlets.model.User;

import java.util.List;

/**
 * Интерфейс хранилища пользователей
 */
public interface Store {
    /**
     * Метод добавления нового пользователя в хранилище
     *
     * @param user объект новый пользователь
     * @return true в случае удачного добавления и false если добавление не удалось выполнить
     */
    boolean add(User user);

    /**
     * Метод добавления изменения существующего пользователя в хранилище
     *
     * @param user        объект существующий пользователь
     * @param updatedUser объект пользователь с обновленными полями
     * @return true в случае удачного изменения и false если изменение не удалось выполнить
     */
    boolean update(User user, User updatedUser);

    /**
     * Метод удаления существующего пользователя из хранилища
     *
     * @param user объект существующий пользователь
     * @return true в случае удачного удаления и false если удаление не удалось выполнить
     */
    boolean delete(User user);

    /**
     * Метод для поиска всех пользователей
     *
     * @return Список объектов User
     */
    List<User> findAll();

    /**
     * Метод для поиска пользователя по id
     *
     * @param id пользователя
     * @return Объект User
     */

    User findById(int id);

    /**
     * Метод для поиска всех стран
     *
     * @return Список стран
     */
    List<String> getCountries();

    /**
     * Метод для поиска всех городов для указанной страны
     *
     * @param country Страна
     * @return Список городов
     */
    List<String> getCities(String country);
}
