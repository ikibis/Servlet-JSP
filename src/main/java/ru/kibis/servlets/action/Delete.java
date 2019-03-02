package ru.kibis.servlets.action;

import ru.kibis.servlets.model.User;
import ru.kibis.servlets.storage.ValidateService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Delete implements Action {

    @Override
    public boolean doAction(ValidateService validateService, HttpServletRequest req) {
        final Map<String, String[]> map = req.getParameterMap();
        boolean result = false;
        int idToDelete = Integer.valueOf(Objects.requireNonNull(map.get("id")[0]));
        if (validateService.findById(idToDelete)) {
            validateService.delete(idToDelete);
            result = true;
        }
        return result;
    }

    @Override
    public List<User> doAction(ValidateService validateService) {
        return null;
    }
}
