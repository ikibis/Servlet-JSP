package ru.kibis.servlets.action;

import ru.kibis.servlets.storage.ValidateService;

import javax.servlet.http.HttpServletRequest;

public interface Action {
    void doAction(ValidateService validateService, HttpServletRequest req);
}
