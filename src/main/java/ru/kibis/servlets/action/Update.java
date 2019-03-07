package ru.kibis.servlets.action;

import ru.kibis.servlets.model.Role;
import ru.kibis.servlets.model.User;
import ru.kibis.servlets.storage.Validate;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

public class Update implements Action {

    @Override
    public void doAction(Validate validateService, HttpServletRequest req) {
        final Map<String, String[]> map = req.getParameterMap();

        int idToUpdate = Integer.valueOf(Objects.requireNonNull(map.get("id")[0]));
        User user = validateService.findById(idToUpdate);
        User updatedUser = new User(map.get("name")[0],
                map.get("login")[0],
                map.get("password")[0],
                map.get("email")[0],
                Role.valueOf(map.get("role")[0].toUpperCase()));
        validateService.update(user, updatedUser);
    }
}
