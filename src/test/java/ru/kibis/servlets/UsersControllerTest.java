package ru.kibis.servlets;

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
import ru.kibis.servlets.controller.UserServlet;
import ru.kibis.servlets.storage.Validate;
import ru.kibis.servlets.storage.ValidateService;
import ru.kibis.servlets.storage.ValidateStub;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

    @RunWith(PowerMockRunner.class)
    @PrepareForTest(ValidateService.class)
    public class UsersControllerTest {

        @Test
        public void whenAddUserThenStoreIt() throws ServletException, IOException {
            Validate validate = new ValidateStub();
            PowerMockito.mockStatic(ValidateService.class);
            Mockito.when(ValidateService.getInstance()).thenReturn(validate);
            HttpServletRequest req = mock(HttpServletRequest.class);
            HttpServletResponse resp = mock(HttpServletResponse.class);
            when(req.getParameter("name")).thenReturn("Admin");
            new UserServlet().doPost(req, resp);
            assertThat(validate.findAll().iterator().next().getName(), is("Admin"));
        }
    }
