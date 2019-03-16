package ru.kibis.servlets;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import ru.kibis.servlets.controller.UserCreateServlet;
import ru.kibis.servlets.controller.UserServlet;
import ru.kibis.servlets.controller.UserUpdateServlet;
import ru.kibis.servlets.model.User;
import ru.kibis.servlets.storage.Validate;
import ru.kibis.servlets.storage.ValidateService;
import ru.kibis.servlets.storage.ValidateStub;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)

public class UsersControllerTest {
    Validate validate;
    HttpServletRequest req;
    HttpServletResponse resp;
    Map<String, String[]> map;
    @Mock
    HttpSession session;
    @Mock
    RequestDispatcher rd;

    @Before
    public void beforeTests() throws IOException {
        validate = ValidateStub.getInstance();
        PowerMockito.mockStatic(ValidateService.class);
        Mockito.when(ValidateService.getInstance()).thenReturn(validate);
        req = mock(HttpServletRequest.class);
        resp = mock(HttpServletResponse.class);
        map = new HashMap<>();
        map.put("name", new String[]{"ilya"});
        map.put("login", new String[]{"Kibis"});
        map.put("password", new String[]{"1224"});
        map.put("email", new String[]{"ilya@ilya"});
        map.put("role", new String[]{"ADMIN"});
        map.put("country", new String[]{"Russia"});
        map.put("city", new String[]{"spb"});
        when(req.getParameterMap()).thenReturn(map);
        new UserCreateServlet().doPost(req, resp);
    }

    @After
    public void afterTests() {
        validate.clean();
    }

    @Test
    public void whenAddUserThenStoreIt() {
        User user = validate.findAll().iterator().next();
        assertThat(user.getId(), is(0));
        assertThat(user.getContacts().getName(), is("ilya"));
        assertThat(user.getLogin(), is("Kibis"));
        assertThat(user.getPassword(), is("1224"));
        assertThat(user.getContacts().getEmail(), is("ilya@ilya"));
        assertThat(user.getRole(), is("ADMIN"));
        System.out.println("whenAddUserThenStoreIt");
    }

    @Test
    public void whenUpdateUserThenStoreIt() throws IOException {
        Map<String, String[]> mapToUpdate = new HashMap<>();
        mapToUpdate.put("id", new String[]{"0"});
        mapToUpdate.put("name", new String[]{"ilyaNew"});
        mapToUpdate.put("login", new String[]{"KibisNew"});
        mapToUpdate.put("password", new String[]{"1224New"});
        mapToUpdate.put("email", new String[]{"ilya@ilyaNew"});
        mapToUpdate.put("role", new String[]{"USER"});
        mapToUpdate.put("country_update", new String[]{"Russia"});
        mapToUpdate.put("city_update", new String[]{"spb"});
        when(req.getParameterMap()).thenReturn(mapToUpdate);
        new UserUpdateServlet().doPost(req, resp);
        User user = validate.findAll().iterator().next();
        assertThat(user.getContacts().getName(), is("ilyaNew"));
        assertThat(user.getLogin(), is("KibisNew"));
        assertThat(user.getPassword(), is("1224New"));
        assertThat(user.getContacts().getEmail(), is("ilya@ilyaNew"));
        assertThat(user.getRole(), is("USER"));
        mapToUpdate.clear();
    }

    @Test
    public void whenDeleteUser() throws ServletException, IOException {
        assertThat(validate.findAll().iterator().next().getLogin(), is("Kibis"));
        Map<String, String[]> mapToDelete = new HashMap<>();
        mapToDelete.put("id", new String[]{"0"});
        when(req.getParameter("id")).thenReturn("0");
        when(req.getParameterMap()).thenReturn(mapToDelete);
        when(req.getSession()).thenReturn(session);
        when(session.getAttribute("role")).thenReturn("ADMIN");
        when(session.getAttribute("login")).thenReturn("Kibis");
        when(req.getRequestDispatcher("/WEB-INF/view/users.jsp")).thenReturn(rd);
        new UserServlet().doPost(req, resp);
        assertThat(validate.findByLogin("Kibis") == null, is(true));
        mapToDelete.clear();
    }

    @Test
    public void whenFindAllUsers() {
        assertThat(validate.findAll().iterator().hasNext(), is(true));
    }
}
