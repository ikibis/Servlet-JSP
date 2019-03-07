package ru.kibis.servlets;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import ru.kibis.servlets.controller.UserCreateServlet;
import ru.kibis.servlets.controller.UserUpdateServlet;
import ru.kibis.servlets.storage.Validate;
import ru.kibis.servlets.storage.ValidateService;
import ru.kibis.servlets.storage.ValidateStub;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @Before
    public void beforeTests() {
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
    }

    @Test
    public void whenAddUserThenStoreIt() throws IOException {
        when(req.getParameterMap()).thenReturn(map);
        new UserCreateServlet().doPost(req, resp);
        assertThat(validate.findAll().iterator().next().getId(), is(0));
        assertThat(validate.findAll().iterator().next().getName(), is("ilya"));
        assertThat(validate.findAll().iterator().next().getLogin(), is("Kibis"));
        assertThat(validate.findAll().iterator().next().getPassword(), is("1224"));
        assertThat(validate.findAll().iterator().next().getEmail(), is("ilya@ilya"));
        assertThat(validate.findAll().iterator().next().getRole(), is("ADMIN"));
        map.clear();
    }

    @Test
    public void whenUpdateUserThenStoreIt() throws IOException {
        this.whenAddUserThenStoreIt();
        Map<String, String[]> mapToUpdate = new HashMap<>();
        mapToUpdate.put("id", new String[]{"0"});
        mapToUpdate.put("name", new String[]{"ilyaNew"});
        mapToUpdate.put("login", new String[]{"KibisNew"});
        mapToUpdate.put("password", new String[]{"1224New"});
        mapToUpdate.put("email", new String[]{"ilya@ilyaNew"});
        mapToUpdate.put("role", new String[]{"USER"});
        when(req.getParameterMap()).thenReturn(mapToUpdate);
        new UserUpdateServlet().doPost(req, resp);
        assertThat(validate.findAll().iterator().next().getName(), is("ilyaNew"));
        assertThat(validate.findAll().iterator().next().getLogin(), is("KibisNew"));
        assertThat(validate.findAll().iterator().next().getPassword(), is("1224New"));
        assertThat(validate.findAll().iterator().next().getEmail(), is("ilya@ilyaNew"));
        assertThat(validate.findAll().iterator().next().getRole(), is("USER"));
        map.clear();
        mapToUpdate.clear();
    }

   /* @Test
    public void whenDeleteUser() throws ServletException, IOException {
        this.whenAddUserThenStoreIt();
        Map<String, String[]> mapToDelete = new HashMap<>();
        mapToDelete.put("id", new String[]{"0"});
        when(req.getParameter("id")).thenReturn("0");
        when(session.getAttribute("role")).thenReturn(mapToDelete);

        when(req.getParameterMap()).thenReturn(mapToDelete);
        new UserServlet().doPost(req, resp);
        assertThat(validate.findAll().iterator().next().getName(), is(java.util.Optional.ofNullable(null)));
    }*/

    @Test
    public void whenFindAllUsers() {

        assertThat(validate.findAll().iterator().next().getName(), is("ilyaNew"));
        assertThat(validate.findAll().iterator().next().getLogin(), is("KibisNew"));
        assertThat(validate.findAll().iterator().next().getPassword(), is("1224New"));
        assertThat(validate.findAll().iterator().next().getEmail(), is("ilya@ilyaNew"));
        assertThat(validate.findAll().iterator().next().getRole(), is("USER"));
    }
}
