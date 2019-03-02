package ru.kibis.servlets.action;

import ru.kibis.servlets.model.User;
import ru.kibis.servlets.storage.ValidateService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class FindAll implements Action {

    @Override
    public boolean doAction(ValidateService validateService, HttpServletRequest req) {
        return false;
    }

    @Override
    public List<User> doAction(ValidateService validateService) {
        return validateService.findAll();
    }
}
