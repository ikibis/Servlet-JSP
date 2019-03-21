package ru.kibis.servlets.storage;

import ru.kibis.servlets.model.User;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Класс хранилище, реализует интерфейс Store.
 * Хранение объектов User осуществляется в списке CopyOnWriteArrayList.
 * <p>
 * На данный момент не используется
 */
public class MemoryStore implements Store {
    private static Store store;
    /**
     * Список для хранения объектов User
     */
    private List<User> users = new CopyOnWriteArrayList<>();

    /**
     * Метод используется для доступа к хранилущу из слоя валидации
     *
     * @return Объект Store
     */
    public static Store getInstance() {
        if (store == null) {
            store = new MemoryStore();
        }
        return store;
    }

    /**
     * Метод добавления нового пользователя в хранилище.
     * Осуществляет проверку логина и адреса электронной почты на уникальность
     *
     * @param user объект новый пользователь
     * @return true в случае удачного добавления и false если добавление не удалось выполнить
     */
    @Override
    public boolean add(User user) {
        boolean result = true;
        for (User userSearched : users) {
            String userSearchedLogin = userSearched.getLogin();
            String userSearchedEmail = userSearched.getContacts().getEmail();
            if (userSearchedLogin != null && userSearchedEmail != null) {
                if (userSearchedLogin.equals(user.getLogin()) || userSearchedEmail.equals(user.getContacts().getEmail())) {
                    result = false;
                }
            }
        }
        if (result && user != null) {
            users.add(user);
        }
        return result;
    }

    /**
     * Метод изменения существующего пользователя в хранилище.
     * Осуществляет сравнение существующих (user) и обновленных (updatedUser) полей объекта User.
     * Если поля действительно отличаются, то происходит их обновление в существующем объекте (user) через сеттер.
     *
     * @param user        объект существующий пользователь
     * @param updatedUser объект пользователь с обновленными полями
     * @return true в случае удачного изменения и false если изменение не удалось выполнить
     */
    @Override
    public boolean update(User user, User updatedUser) {
        boolean result = false;
        if (!updatedUser.getContacts().getName().equals(user.getContacts().getName())) {
            user.getContacts().setName(updatedUser.getContacts().getName());
            result = true;
        }
        if (!updatedUser.getLogin().equals(user.getLogin()) && this.findByLogin(updatedUser.getLogin()) == null) {
            user.setLogin(updatedUser.getLogin());
            result = true;
        }
        if (!updatedUser.getContacts().getEmail().equals(user.getContacts().getEmail())
                && this.findByEmail(updatedUser.getContacts().getEmail()) == null) {
            user.getContacts().setEmail(updatedUser.getContacts().getEmail());
            result = true;
        }
        if (!updatedUser.getPassword().equals(user.getPassword())) {
            user.setPassword(updatedUser.getPassword());
            result = true;
        }
        if (!updatedUser.getContacts().getCountry().equals(user.getContacts().getCountry())) {
            user.getContacts().setCountry(updatedUser.getContacts().getCountry());
            result = true;
        }
        if (!updatedUser.getContacts().getCity().equals(user.getContacts().getCity())) {
            user.getContacts().setCity(updatedUser.getContacts().getCity());
            result = true;
        }
        return result;
    }

    /**
     * Метод удаления существующего пользователя из хранилища
     *
     * @param user объект существующий пользователь
     * @return true в случае удачного удаления и false если удаление не удалось выполнить
     */
    @Override
    public boolean delete(User user) {
        return users.remove(user);
    }

    /**
     * Метод для поиска всех пользователей
     *
     * @return Список объектов User
     */
    @Override
    public List<User> findAll() {
        return users;
    }

    /**
     * Метод для поиска пользователя по id
     *
     * @param id пользователя
     * @return Объект User
     */
    @Override
    public User findById(int id) {
        User result = null;
        for (User userSearched : users) {
            int userSearchedId = userSearched.getId();
            if (userSearchedId == id) {
                result = userSearched;
                break;
            }
        }
        return result;
    }

    /**
     * Метод для поиска всех стран.
     * Не используется.
     *
     * @return null
     */
    @Override
    public List<String> getCountries() {
        return null;
    }

    /**
     * Метод для поиска всех городов для указанной страны
     * Не используется.
     *
     * @return null
     */
    @Override
    public List<String> getCities(String country) {
        return null;
    }

    /**
     * Метод для поиска пользователя по логину в хранилтще.
     * Этот код используется в методе update данного класса.
     * Вынесен в отдельный метод в процессе рефакторинга.
     *
     * @param login логин пользователя
     * @return объект User
     */
    public User findByLogin(String login) {
        User result = null;
        for (User userSearched : users) {
            String userSearchedLogin = userSearched.getLogin();
            if (userSearchedLogin.equals(login)) {
                result = userSearched;
                break;
            }
        }
        return result;
    }

    /**
     * Метод для поиска пользователя по адресу электронной почты в хранилтще.
     * Этот код используется в методе update данного класса.
     * Вынесен в отдельный метод в процессе рефакторинга.
     *
     * @param email электронная почта пользователя
     * @return объект User
     */
    public User findByEmail(String email) {
        User result = null;
        for (User userSearched : users) {
            String userSearchedEmail = userSearched.getContacts().getEmail();
            if (userSearchedEmail.equals(email)) {
                result = userSearched;
                break;
            }
        }
        return result;
    }
}
