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
    private Map<Integer, User> map = new ConcurrentHashMap<>();
    int id = 0;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        StringBuilder buffer = new StringBuilder();
        try (BufferedReader reader = req.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(new String(buffer), User.class);
        map.put(id++, user);
        System.out.println(map.size());
        doGet(req, resp);
    }
}

