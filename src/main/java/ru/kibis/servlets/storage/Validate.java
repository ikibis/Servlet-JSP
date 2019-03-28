package ru.kibis.servlets.storage;

import ru.kibis.servlets.model.User;

import java.util.List;

/**
 * Интерфейс слоя валидации
 */
public interface Validate {
    /**
     * Метод добавления нового пользователя в хранилище
     *
     * @param user объект новый пользователь
     * @return true в случае удачного добавления и false если добавление не удалось выполнить
     */
    boolean add(User user);

    /**
     * Метод для поиска всех пользователей
     *
     * @return Список объектов User
     */
    List<User> findAll();

    /**
     * Метод изменения существующего пользователя в хранилище
     *
     * @param user        объект существующий пользователь
     * @param updatedUser объект пользователь с обновленными полями
     * @return true в случае удачного изменения и false если изменение не удалось выполнить
     */
    boolean update(User user, User updatedUser);

    /**
     * Метод удаления существующего пользователя из хранилища
     *
     * @param id айди пользователя
     */
    void delete(int id);

    /**
     * Метод для поиска пользователя по id
     *
     * @param id пользователя
     * @return Объект User
     */
    User findById(int id);

    /**
     * Метод для проверки залогинился ли пользователь в системе
     *
     * @param login
     * @param password
     * @return объект User
     */
    User isCredentional(String login, String password);

    /**
     * Метод для тестирования сервлетов
     */
    void clean();

    /**
     * Метод для поиска пользователя по логину
     *
     * @param login
     * @return объект User
     */
    User findByLogin(String login);

    /**
     * Метод для поиска всех стран
     *
     * @return Список стран
     */
    List<String> findCountries();

    /**
     * Метод для поиска всех городов для указанной страны
     *
     * @param country Страна
     * @return Список городов
     */
    List<String> findCities(String country);
}
