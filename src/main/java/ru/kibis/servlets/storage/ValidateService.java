package ru.kibis.servlets.storage;

import ru.kibis.servlets.model.Contacts;
import ru.kibis.servlets.model.Role;
import ru.kibis.servlets.model.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Класс слоя валидации. Реализует интерфейс Validate
 */
public class ValidateService implements Validate {
    //private final Store memory = MemoryStore.getInstance();
    /**
     * Получаем объект - хранилище
     */
    private final Store memory = DbStore.getInstance();

    /**
     * Конструктор ValidateService.
     * Задается формат даты для БД.
     * Создается и добавляется в БД первый пользователь с правами ROOT.
     */
    private ValidateService() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy");
        String date = sdf.format(new Date());
        User root = new User("root", "root", date, Role.ROOT,
                new Contacts("root", "root@root", "Russia", "Ekb")
        );
        add(root);
    }

    private static class Holder {
        private static final Validate INSTANCE = new ValidateService();
    }

    /**
     * Метод используется для доступа к слою валидации из сервлетов
     *
     * @return объект Validate
     */
    public static Validate getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Метод добавления нового пользователя в хранилище
     *
     * @param user объект новый пользователь
     * @return true в случае удачного добавления и false если добавление не удалось выполнить
     */
    public boolean add(User user) {
        boolean result = false;
        if (user.getContacts().getName() != null && user.getLogin() != null
                && user.getPassword() != null && user.getContacts().getEmail() != null) {
            result = memory.add(user);
        }
        return result;
    }

    /**
     * Метод добавления изменения существующего пользователя в хранилище
     *
     * @param user        объект существующий пользователь
     * @param updatedUser объект пользователь с обновленными полями
     * @return true в случае удачного изменения и false если изменение не удалось выполнить
     */
    public boolean update(User user, User updatedUser) {
        boolean result = false;
        if (updatedUser.getContacts().getName() != null && updatedUser.getLogin() != null
                && updatedUser.getPassword() != null && updatedUser.getContacts().getEmail() != null) {
            result = memory.update(user, updatedUser);
        }
        return result;
    }

    /**
     * Метод удаления существующего пользователя из хранилища
     *
     * @param id айди пользователя
     */
    public void delete(int id) {
        User user = this.findById(id);
        memory.delete(user);
    }

    /**
     * Метод для поиска всех пользователей
     *
     * @return Список объектов User
     */
    public List<User> findAll() {
        return memory.findAll();
    }

    /**
     * Метод для поиска пользователя по id
     *
     * @param id пользователя
     * @return Объект User
     */
    public User findById(int id) {
        return memory.findById(id);
    }

    /**
     * Метод для проверки залогинился ли пользователь в системе
     *
     * @param login
     * @param password
     * @return объект User
     */
    public User isCredentional(String login, String password) {
        User result = null;
        for (User user : this.findAll()) {
            if (login.equals(user.getLogin()) && password.equals(user.getPassword())) {
                result = user;
                break;
            }
        }
        return result;
    }

    /**
     * Метод для тестирования сервлетов
     */
    @Override
    public void clean() {

    }

    /**
     * Метод для поиска пользователя по логину
     *
     * @param login
     * @return объект User
     */
    @Override
    public User findByLogin(String login) {
        return null;
    }

    /**
     * Метод для поиска всех стран
     *
     * @return Список стран
     */
    public List<String> findCountries() {
        return memory.getCountries();
    }

    /**
     * Метод для поиска всех городов для указанной страны
     *
     * @param country Страна
     * @return Список городов
     */
    @Override
    public List<String> findCities(String country) {
        return memory.getCities(country);
    }
}
