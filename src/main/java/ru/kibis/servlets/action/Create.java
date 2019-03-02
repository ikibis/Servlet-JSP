package ru.kibis.servlets.action;

import ru.kibis.servlets.model.User;
import ru.kibis.servlets.storage.ValidateService;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Create implements Action {
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy");

    @Override
    public boolean doAction(ValidateService validateService, HttpServletRequest req) {
        final Map<String, String[]> map = req.getParameterMap();
        boolean result = false;
        String date = sdf.format(new Date());
        User user = new User(
                map.get("name")[0],
                map.get("login")[0],
                map.get("password")[0],
                map.get("email")[0], date);
        if (validateService.add(user)) {
            result = true;
        }
        return result;
    }
}


