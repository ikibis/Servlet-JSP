package ru.kibis.servlets.storage;


import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
                             + "user_id varchar(2000), "
                             + "name varchar(2000), "
                             + "login varchar(2000), "
                             + "password varchar(2000), "
                             + "email varchar(2000), "
                             + "date varchar(2000),"
                             + "role varchar(2000));"
             )) {
            st.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public boolean add(User user) {
        boolean result = false;
        if (!this.duplicateCheck(user)) {
            try (Connection connection = SOURCE.getConnection();
                 PreparedStatement st = connection.prepareStatement(
                         "insert into users(user_id, name, login, password, email, date, role) values(?, ?, ?, ?, ?, ?, ?);"
                 )) {
                st.setString(1, String.valueOf(user.getId()));
                st.setString(2, user.getName());
                st.setString(3, user.getLogin());
                st.setString(4, user.getPassword());
                st.setString(5, user.getEmail());
                st.setString(6, user.getCreateDate());
                st.setString(7, user.getRole());
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
        if (!this.duplicateCheck(updatedUser)) {
            try (Connection connection = SOURCE.getConnection();
                 PreparedStatement st = connection.prepareStatement(
                         "update users set name = ?, login = ?, password = ?, email = ?, role = ? WHERE user_id = ?;"
                 )) {
                st.setString(1, updatedUser.getName());
                st.setString(2, updatedUser.getLogin());
                st.setString(3, updatedUser.getPassword());
                st.setString(4, updatedUser.getEmail());
                st.setString(5, updatedUser.getRole());
                st.setString(6, String.valueOf(user.getId()));
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
                     "delete from users where user_id = ?;"
             )) {
            st.setString(1, String.valueOf(user.getId()));
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
                        rs.getString("user_id"),
                        rs.getString("name"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("date"),
                        Role.valueOf(rs.getString("role").toUpperCase())
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
                     "select * from users where user_id = ?;"
             )) {
            st.setString(1, String.valueOf(id));
            ResultSet rs = st.executeQuery();
            rs.next();
            result = new User(
                    String.valueOf(id),
                    rs.getString("name"),
                    rs.getString("login"),
                    rs.getString("password"),
                    rs.getString("email"),
                    rs.getString("date"),
                    Role.valueOf(rs.getString("role").toUpperCase())
            );
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    private boolean duplicateCheck(User user) {
        boolean writeFlag = false;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement users = connection.prepareStatement(
                     "select name from users where users.login = ? OR users.email = ?;"
             )) {
            users.setString(1, user.getLogin());
            users.setString(2, user.getEmail());
            ResultSet rs = users.executeQuery();
            writeFlag = rs.next();
            rs.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return writeFlag;
    }
}