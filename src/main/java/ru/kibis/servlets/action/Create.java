package ru.kibis.servlets.action;

import ru.kibis.servlets.model.User;
import ru.kibis.servlets.storage.ValidateService;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class Create implements Action {
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy");

    @Override
    public void doAction(ValidateService validateService, HttpServletRequest req) {
        final Map<String, String[]> map = req.getParameterMap();
        String date = sdf.format(new Date());
        User user = new User(
                map.get("name")[0],
                map.get("login")[0],
                map.get("password")[0],
                map.get("email")[0], date);
        validateService.add(user);
    }
}


