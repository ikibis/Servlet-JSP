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

/**
 * Сервлет для списка городов
 */
public class CityServlet extends HttpServlet {
    /**
     * Сервис валидации
     */
    private final Validate validateService = ValidateService.getInstance();

    /**
     * Метод POST извлекает из HTTP запроса параметр "country",
     * формирует список городов для выбранной страны, путем вызова метода findCities сервиса валидалиции,
     * и передает в ответ список городов в формате JSON
     *
     * @param req  HTTP запрос
     * @param resp ответ
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String country = req.getParameter("country");
        List<String> result;
        result = validateService.findCities(country);
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(result);
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(jsonInString);
        writer.flush();
    }
}
