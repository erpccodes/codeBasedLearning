// GreetingServlet.java: A simple servlet to process user input and forward it to JSP for display

package com.example;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GreetingServlet")  // Maps this servlet to /GreetingServlet URL
public class GreetingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // The doPost method handles form submission (since form uses POST method)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {

        // Get the user input (name) from the request parameter
        String username = request.getParameter("username");

        // Add the username to the request so it can be accessed by JSP
        request.setAttribute("username", username);

        // Forward the request to the result JSP page (greeting.jsp) to display the message
        request.getRequestDispatcher("greeting.jsp").forward(request, response);
    }
}
