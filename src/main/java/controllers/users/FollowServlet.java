package controllers.users;

import dao.impl.MySQLUserDAO;
import model.AppUser;
import services.AppUserService;
import services.impl.AppUserServiceImpl;
import utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet (name = "Follow", value = "/follow")
public class FollowServlet extends HttpServlet {

    private AppUserService service;

    @Override
    public void init() throws ServletException {
        service = new AppUserServiceImpl(new MySQLUserDAO());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userLoginFromSession = ServletUtils.getUserLoginFromSession(req);
        AppUser loggedUser = service.getUserByLogin(userLoginFromSession);
        AppUser userToFollow = service.getUserByLogin(req.getParameter(ServletUtils.USER_LOGIN_TO_FOLLOW));

        service.followUser(loggedUser,userToFollow);
        req.getRequestDispatcher("users").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
