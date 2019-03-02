package ru.kibis.servlets.action;

import ru.kibis.servlets.model.User;
import ru.kibis.servlets.storage.ValidateService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Update implements Action {

    @Override
    public boolean doAction(ValidateService validateService, HttpServletRequest req) {
        final Map<String, String[]> map = req.getParameterMap();
        boolean result = false;
        User user = null;
        int idToUpdate = Integer.valueOf(Objects.requireNonNull(map.get("id")[0]));
        if (validateService.findById(idToUpdate)) {
            user = validateService.getUserById(idToUpdate);
        }
        User udatedUser = new User(map.get("name")[0],
                map.get("login")[0],
                map.get("password")[0],
                map.get("email")[0]);
        if (validateService.update(user, udatedUser)) {
            result = true;
        }
        return result;
    }

    @Override
    public List<User> doAction(ValidateService validateService) {
        return null;
    }
}
