package ru.kibis.servlets.action;

import ru.kibis.servlets.model.User;
import ru.kibis.servlets.storage.ValidateService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface Action {
    boolean doAction(ValidateService validateService, HttpServletRequest req);
    List<User> doAction(ValidateService validateService);
}
