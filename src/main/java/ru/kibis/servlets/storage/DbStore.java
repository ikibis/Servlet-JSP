package ru.kibis.servlets.storage;


import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.kibis.servlets.model.Contacts;
import ru.kibis.servlets.model.Role;
import ru.kibis.servlets.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Класс хранилище, реализует интерфейс Store.
 * Хранение полей объектов User осуществляется в БД PostgreSQL.
 */
public class DbStore implements Store {
    private static final Logger LOGGER = LogManager.getLogger(DbStore.class.getName());
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final DbStore INSTANCE = new DbStore();

    /**
     * Конструктор для объекта DbStore.
     * Используется в методе getInstance данного класса.
     * Задаются настройки для подключения к БД.
     * Используется JDBC.
     */
    private DbStore() {
        SOURCE.setUrl("jdbc:postgresql://127.0.0.1:5432/tracker");
        SOURCE.setUsername("postgres");
        SOURCE.setPassword("fastin");
        SOURCE.setDriverClassName("org.postgresql.Driver");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
    }

    /**
     * Метод используется для доступа к хранилущу из слоя валидации.     *
     *
     * @return Объект Store
     */
    public static Store getInstance() {
        INSTANCE.createNewDB();
        return INSTANCE;
    }

    /**
     * Создает новую таблицу в БД, если она еще не создана.
     */
    private void createNewDB() {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "create table if not exists users ( "
                             + "id serial primary key, "
                             + "name varchar(2000), "
                             + "login varchar(2000), "
                             + "password varchar(2000), "
                             + "email varchar(2000), "
                             + "date varchar(2000),"
                             + "role varchar(2000),"
                             + "country varchar(2000),"
                             + "city varchar(2000));"
             )) {
            st.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * Метод добавления нового пользователя в хранилище.
     * Осуществляется проверка добавляемого пользователя на уникальность, путем вызова duplicateCheck.
     * Если проверка проходит успешно, то осуществляется добавление новой записи в БД.
     *
     * @param user объект новый пользователь
     * @return true в случае удачного добавления и false если добавление не удалось выполнить
     */
    @Override
    public boolean add(User user) {
        boolean result = false;
        if (this.duplicateCheck(user) == 0) {
            try (Connection connection = SOURCE.getConnection();
                 PreparedStatement st = connection.prepareStatement(
                         "insert into users(name, login, password, email, date, role, country, city) values(?, ?, ?, ?, ?, ?, ?, ?);"
                 )) {
                st.setString(1, user.getContacts().getName());
                st.setString(2, user.getLogin());
                st.setString(3, user.getPassword());
                st.setString(4, user.getContacts().getEmail());
                st.setString(5, user.getCreateDate());
                st.setString(6, user.getRole());
                st.setString(7, user.getContacts().getCountry());
                st.setString(8, user.getContacts().getCity());
                st.executeUpdate();
                result = true;
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        return result;
    }

    /**
     * Метод изменения существующего пользователя в хранилище.
     * Осуществляется проверка обновленных полей объекта User на уникальность, путем вызова duplicateCheck.
     * Если проверка проходит успешно, то соответствующая запись в БД обновляется.
     *
     * @param user        объект существующий пользователь
     * @param updatedUser объект пользователь с обновленными полями
     * @return true в случае удачного изменения и false если изменение не удалось выполнить
     */
    @Override
    public boolean update(User user, User updatedUser) {
        boolean result = false;
        if (this.duplicateCheck(updatedUser) <= 1) {
            try (Connection connection = SOURCE.getConnection();
                 PreparedStatement st = connection.prepareStatement(
                         "update users set name = ?, login = ?, password = ?, email = ?, role = ?, country = ?, city = ? WHERE id = ?;"
                 )) {
                st.setString(1, updatedUser.getContacts().getName());
                st.setString(2, updatedUser.getLogin());
                st.setString(3, updatedUser.getPassword());
                st.setString(4, updatedUser.getContacts().getEmail());
                st.setString(5, updatedUser.getRole());
                st.setString(6, updatedUser.getContacts().getCountry());
                st.setString(7, updatedUser.getContacts().getCity());
                st.setInt(8, this.getId(user));
                st.executeUpdate();
                result = true;
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
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
        boolean result = true;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "delete from users where users.id = ?;"
             )) {
            st.setInt(1, this.getId(user));
            st.executeUpdate();
        } catch (SQLException e) {
            result = false;
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Метод для поиска всех пользователей
     *
     * @return Список объектов User
     */
    @Override
    public List<User> findAll() {
        List<User> users = new CopyOnWriteArrayList<>();
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "select * from users;"
             )) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                users.add(new User(
                        rs.getString("id"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("date"),
                        Role.valueOf(rs.getString("role").toUpperCase()),
                        new Contacts(
                                rs.getString("name"),
                                rs.getString("email"),
                                rs.getString("country"),
                                rs.getString("city")
                        )
                ));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
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
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "select * from users where id = ?;"
             )) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.next();
            result = new User(
                    rs.getString("id"),
                    rs.getString("login"),
                    rs.getString("password"),
                    rs.getString("date"),
                    Role.valueOf(rs.getString("role").toUpperCase()),
                    new Contacts(
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("country"),
                            rs.getString("city")
                    )
            );
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }
    /**
     * Метод для поиска пользователя по id
     *
     * @param user Объект User
     * @return id пользователя
     */
    public int getId(User user) {
        int result = -1;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "select id from users where login = ? and email = ?;"
             )) {
            st.setString(1, user.getLogin());
            st.setString(2, user.getContacts().getEmail());
            ResultSet rs = st.executeQuery();
            rs.next();
            result = Integer.valueOf(rs.getString("id"));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Метод осуществляет проверку объекта User на наличие дублирования в полях логин и адрес электронной почты.
     *
     * @param user
     * @return
     */
    private int duplicateCheck(User user) {
        int count = 0;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement users = connection.prepareStatement(
                     "select name from users where login = ? OR email = ?;"
             )) {
            users.setString(1, user.getLogin());
            users.setString(2, user.getContacts().getEmail());
            ResultSet rs = users.executeQuery();
            while (rs.next()) {
                count++;
            }
            rs.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return count;
    }

    /**
     * Метод для поиска всех стран.
     *
     * @return Список стран List<String>
     */
    @Override
    public List<String> getCountries() {
        List<String> countries = new CopyOnWriteArrayList<>();
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "select country_name from country;"
             )) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                countries.add(rs.getString("country_name"));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return countries;
    }

    /**
     * Метод для поиска всех городов для указанной страны.
     *
     * @return Список городов List<String>
     */
    @Override
    public List<String> getCities(String country) {
        List<String> cities = new CopyOnWriteArrayList<>();
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "select city_name from city where country_id = ("
                             + "select id from country where country_name = ?"
                             + ");"
             )) {
            ps.setString(1, country);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cities.add(rs.getString("city_name"));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return cities;
    }
}