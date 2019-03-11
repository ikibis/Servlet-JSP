package ru.kibis.servlets.controller;

import org.codehaus.jackson.map.ObjectMapper;
import ru.kibis.servlets.storage.Validate;
import ru.kibis.servlets.storage.ValidateService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CityServlet extends HttpServlet {
    private final Validate validateService = ValidateService.getInstance();

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String country = req.getParameter("country");
        System.out.println(country);
        List<String> result;
        result = validateService.findCities(country);
        System.out.println(result);
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(result);
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(jsonInString);
        writer.flush();
    }
}
