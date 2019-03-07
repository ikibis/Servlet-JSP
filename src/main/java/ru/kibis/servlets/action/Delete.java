package ru.kibis.servlets.action;

import ru.kibis.servlets.storage.Validate;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

public class Delete implements Action {

    @Override
    public void doAction(Validate validateService, HttpServletRequest req) {
        final Map<String, String[]> map = req.getParameterMap();
        int idToDelete = Integer.valueOf(Objects.requireNonNull(map.get("id")[0]));
        if (validateService.findById(idToDelete) != null) {
            validateService.delete(idToDelete);
        }
    }
}
