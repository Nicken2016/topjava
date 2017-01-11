package ru.javawebinar.topjava.web;

import java.io.IOException;

public class UserServlet extends javax.servlet.http.HttpServlet {
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.sendRedirect("userList.jsp");
//        request.getRequestDispatcher("/userList.jsp").forward(request, response);
    }
}
