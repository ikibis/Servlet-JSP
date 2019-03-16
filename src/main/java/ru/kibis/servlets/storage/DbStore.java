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

public class DbStore implements Store {
    private static final Logger LOGGER = LogManager.getLogger(DbStore.class.getName());
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final DbStore INSTANCE = new DbStore();

    private DbStore() {
        SOURCE.setUrl("jdbc:postgresql://127.0.0.1:5432/tracker");
        SOURCE.setUsername("postgres");
        SOURCE.setPassword("fastin");
        SOURCE.setDriverClassName("org.postgresql.Driver");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
    }

    public static Store getInstance() {
        INSTANCE.createNewDB();
        return INSTANCE;
    }

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
                st.setString(8, String.valueOf(this.getId(user)));
                st.executeUpdate();
                result = true;
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        return result;
    }

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
        System.out.println(count);
        return count;
    }

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