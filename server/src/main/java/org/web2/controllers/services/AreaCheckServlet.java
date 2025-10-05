package org.web2.controllers.services;


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import org.validator.ValidatedRecordFactory;
import org.validator.validation.exceptions.ValidationException;
import org.web2.controllers.services.DTOs.AreaCheckRequestDTO;
import org.web2.controllers.services.checkers.Checker;
import org.web2.controllers.services.utils.time.Stopwatch;
import org.web2.model.ResultBean;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Date;

@WebServlet("/check")
public class AreaCheckServlet extends HttpServlet {
    private final Stopwatch timer = new Stopwatch();
    private final Checker checker = new Checker();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Stopwatch elapsedTimeTimer = timer.start();

        try {
            AreaCheckRequestDTO requestData = ValidatedRecordFactory.create(
                    AreaCheckRequestDTO.class,
                    req.getParameter("X"),
                    req.getParameter("Y"),
                    req.getParameter("R")
            );

            boolean hit = checker.check(
                    Float.parseFloat(requestData.X()),
                    Float.parseFloat(requestData.Y()),
                    Integer.parseInt(requestData.R())
            );

            final ResultBean newResult = new ResultBean(
                    Float.parseFloat(requestData.X()),
                    Float.parseFloat(requestData.Y()),
                    Integer.parseInt(requestData.R()),
                    hit,
                    new Date(),
                    elapsedTimeTimer.stop()
            );

            HttpSession session = req.getSession();
            @SuppressWarnings("unchecked")
            ArrayDeque<ResultBean> results = (ArrayDeque<ResultBean>) session.getAttribute("results");
            if (results == null) {
                results = new ArrayDeque<>();
            }

            results.addFirst(newResult);
            session.setAttribute("results", results);
            session.setAttribute("lastResult", newResult);

            RequestDispatcher dispatcher = req.getRequestDispatcher("result.jsp");
            dispatcher.forward(req, resp);
        } catch (ValidationException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }
}
