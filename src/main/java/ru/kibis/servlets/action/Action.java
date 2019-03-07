package ru.kibis.servlets.action;

import ru.kibis.servlets.storage.Validate;

import javax.servlet.http.HttpServletRequest;

public interface Action {
    void doAction(Validate validateService, HttpServletRequest req);
}
