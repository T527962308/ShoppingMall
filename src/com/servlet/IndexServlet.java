package com.servlet;

import com.dao.GoodsinfoDao;
import com.entity.Goodsinfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;Charset=utf-8");

        GoodsinfoDao goodsinfoDao = new GoodsinfoDao();
        List list = goodsinfoDao.watchGoods();


        req.setAttribute("goodsinfo",list);

        req.getRequestDispatcher("/front/index.jsp").forward(req,resp);
    }
}
