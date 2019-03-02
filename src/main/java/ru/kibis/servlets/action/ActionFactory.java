package ru.kibis.servlets.action;

import ru.kibis.servlets.model.User;
import ru.kibis.servlets.storage.ValidateService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActionFactory {
    private static class Holder {
        private static final ActionFactory INSTANCE = new ActionFactory();
    }

    public static ActionFactory getInstance() {
        return Holder.INSTANCE;
    }

    private ValidateService service = new ValidateService();
    private Map<String, Action> actionMap = new HashMap<>();

    {
        actionMap.put("create", new Create());
        actionMap.put("update", new Update());
        actionMap.put("delete", new Delete());
        actionMap.put("findAll", new FindAll());
    }

    public void action(String action, HttpServletRequest req) {
        actionMap.getOrDefault(action, new UnknownAction()).doAction(service, req);
    }

    public List<User> action(String action) {
        return actionMap.getOrDefault(action, new UnknownAction()).doAction(service);
    }

    class UnknownAction implements Action {

        @Override
        public boolean doAction(ValidateService validateService, HttpServletRequest req) {
            return false;
        }

        @Override
        public List<User> doAction(ValidateService validateService) {
            return null;
        }
    }
}
