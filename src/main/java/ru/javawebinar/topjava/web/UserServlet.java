package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.repository.inmemory.InMemoryMealRepositoryImpl;
import ru.javawebinar.topjava.repository.inmemory.InMemoryUserRepositoryImpl;
import ru.javawebinar.topjava.web.user.AbstractUserController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

public class UserServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);
    private AdminRestController adminUserController;

    //  private UserRepository repository;
    //  private AbstractUserController repository;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        //   repository = new InMemoryUserRepositoryImpl();
        //     repository = new AdminRestController();
        ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        adminUserController = appCtx.getBean(AdminRestController.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("forward to users");
        request.setAttribute("users", adminUserController.getAll());
        log.debug("size:" + adminUserController.getAll().size());
        request.getRequestDispatcher("/users.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("User id set");
        String action = req.getParameter("userId");
        SecurityUtil.setUserId(Integer.parseInt(action));
        //  req.setAttribute("users", repository.getAll());
        //  req.getRequestDispatcher("/users.jsp").forward(req, resp);
        resp.sendRedirect("/topjava");
    }
}
