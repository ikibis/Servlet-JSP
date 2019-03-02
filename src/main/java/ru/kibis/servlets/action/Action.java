package ru.kibis.servlets.action;

import ru.kibis.servlets.storage.ValidateService;

import javax.servlet.http.HttpServletRequest;

public interface Action {
    boolean doAction(ValidateService validateService, HttpServletRequest req);
}
