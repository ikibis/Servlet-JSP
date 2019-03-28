package ru.kibis.servlets.controller;

import org.codehaus.jackson.map.ObjectMapper;
import ru.kibis.servlets.storage.Validate;
import ru.kibis.servlets.storage.ValidateService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Сервлет для списка стран
 */
public class CountryServlet extends HttpServlet {
    /**
     * Сервис валидации
     */
    private final Validate validateService = ValidateService.getInstance();

    /**
     * Метод POST формирует список стран, путем вызова метода findCountries сервиса валидалиции,
     * и передает в ответ список стран в формате JSON
     *
     * @param req  HTTP запрос
     * @param resp ответ
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(validateService.findCountries());
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(jsonInString);
        writer.flush();
    }
}
