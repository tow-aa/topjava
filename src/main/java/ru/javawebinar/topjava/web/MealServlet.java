package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("meal servlet");
        List<MealTo> meals = Arrays.asList(
                new MealTo(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500, true),
                new MealTo(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000, true),
                new MealTo(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500, true),
                new MealTo(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000, false),
                new MealTo(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500, false),
                new MealTo(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510, false)
        );
        request.setAttribute("meals",meals);
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }
}