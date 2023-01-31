package com.example.base_medecin.creneau;

import entity.Client;
import entity.Creneau;
import repository.CreneauRepository;

import java.io.*;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "creneauIndexservlet", value = "/creneau")
public class HelloServlet extends HttpServlet {
    private final CreneauRepository creneauRepository = new CreneauRepository();

    @Override
    public void init() throws ServletException {
        super.init();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Creneau> creneau = creneauRepository.findAll();
        response.setContentType("text/html");
        request.setAttribute("creneau", creneau);
        request.getRequestDispatcher("/creneau/list_dr.jsp").forward(request, response);
    }

    public void destroy() {
    }
}