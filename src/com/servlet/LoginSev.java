package com.servlet;

import com.dao.UserDao;
import com.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginSev extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("username");
        String password = req.getParameter("password");
        try{
            if("".equals(userName) && "".equals(password)){
                req.setAttribute("mus","对不起，账号或密码不能为空");
                req.getRequestDispatcher("LogOn.jsp").forward(req,resp);
                throw new Exception();
            }
            UserDao userDao = new UserDao();
            User user = new User(userName,password);
            int result = userDao.Verification(user);
            if(result != 0){
                //获取对象信息
                resp.sendRedirect("indexServlet");
            }else {
                req.setAttribute("mus","对不起，您的账号或者密码错误");
                req.getRequestDispatcher("LogOn.jsp").forward(req,resp);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
