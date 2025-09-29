package org.web2.controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.web2.controllers.services.utils.logging.SimpleLogger;

import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/lab")
public class ControllerServlet extends HttpServlet {
    private final Logger logger = SimpleLogger.create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String x = req.getParameter("X");
        String y = req.getParameter("Y");
        String r = req.getParameter("R");

        logger.info(String.format("Received params (x:%s y:%s r:%s)", x, y, r));

        if (x != null && y != null && r != null) {
            logger.info("Redirecting to check");

            RequestDispatcher dispatcher = req.getRequestDispatcher("/check");
            dispatcher.forward(req, resp);
        } else {
            logger.info("Redirecting to root");

            RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
