package com.servlet;

import com.dao.GoodsinfoDao;
import com.dao.UserDao;
import com.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PipedWriter;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        String name = req.getParameter("username");
        String password1 = req.getParameter("password1");
        String password2 = req.getParameter("password2");
        String sex = req.getParameter("sex");
        String email = req.getParameter("email");
        String[] hobbys = req.getParameterValues("hobbys");
        String phone = req.getParameter("phone");
        String addrs = req.getParameter("addrs");

        String err = "";

        try{
            if(!(password1.equals(password2))){
                req.setAttribute("mus","对不起您的账号密码不一致");
                req.getRequestDispatcher("front/register.jsp").forward(req,resp);
            }
            int newSex = 0;
            if(sex.equals("男")){
                newSex = 1;
            }else if(sex.equals("女")){
                newSex = 2;
            }else {
                throw new Exception();
            }


            StringBuffer s = new StringBuffer();
            for (String a : hobbys){
                s.append(a+" ");
            }

            //创建Dao对象
            UserDao userDap = new UserDao();
            User user = new User(name,password1,newSex,s.toString(),phone,email,addrs);

            int result = userDap.register(user);

            if(result != 0){
                HttpSession session = req.getSession();
                session.setAttribute("username",name);

                PrintWriter out = resp.getWriter();
                out.println("<script type='texy/javascript' >alert('注册成功');window.location.href='/front/login.jsp';</script>");
            }else {
                PrintWriter out = resp.getWriter();
                out.println("<script type='texy/javascript' >alert('注册失败');window.location.href='/front/register.jsp';</script>");
            }
        }catch (Exception e){
            e.printStackTrace();
            err = "上传失败请重试";
        }
    }
}
