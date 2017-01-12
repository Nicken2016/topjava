package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class UserServlet extends javax.servlet.http.HttpServlet {
   private static final Logger LOG = LoggerFactory.getLogger(UserServlet.class);

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        LOG.debug("redirect ot userList");

//      request.getRequestDispatcher("/userList.jsp").forward(request, response);
        response.sendRedirect("userList.jsp");
    }
}
