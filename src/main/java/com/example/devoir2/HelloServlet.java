package com.example.devoir2;

import java.io.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;


@WebServlet(name = "helloServlet", value = {"/hello-servlet", "/view1", "/view2"})
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");

        // Boucle: Déterminer quelle vue afficher en fonction de l'URL
        String requestURI = request.getRequestURI();

        if (requestURI.endsWith("/view1")) {
            displayView1(request, response, out);
        } else if (requestURI.endsWith("/view2")) {
            displayView2(request, response, out);
        } else {
            displayDefaultView(out);
        }

        out.println("</body></html>");
    }

    private void displayDefaultView(PrintWriter out) {
        out.println("<h1>" + message + "</h1>");
        out.println("<p>Ceci est la vue par défaut de HelloServlet.</p>");
    }

    private void displayView1(HttpServletRequest request, HttpServletResponse response, PrintWriter out)
            throws ServletException, IOException {
        // Inclure le contenu de view1.jsp
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view1.jsp");
        dispatcher.include(request, response);
    }

    private void displayView2(HttpServletRequest request, HttpServletResponse response, PrintWriter out)
            throws ServletException, IOException {
        // Inclure le contenu de view2.jsp
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view2.jsp");
        dispatcher.include(request, response);
    }


    public void destroy() {
    }
}
