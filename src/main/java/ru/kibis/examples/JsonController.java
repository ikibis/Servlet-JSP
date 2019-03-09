package ru.kibis.examples;

import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class JsonController extends HttpServlet {
    private final Map<Integer, User> map = new ConcurrentHashMap<>();
    int id = 0;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setAttribute("users", map);
        req.getRequestDispatcher("/index.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        BufferedReader reader = req.getReader();
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(reader, User.class);
        map.put(id++, user);
        doGet(req, resp);
    }
}
