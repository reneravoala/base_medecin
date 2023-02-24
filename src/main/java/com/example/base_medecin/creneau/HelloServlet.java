package com.example.base_medecin.creneau;

import entity.Creneau;
import repository.CreneauRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "creneauIndexServlet", value = "/creneau/index")
public class HelloServlet extends HttpServlet {
    private final CreneauRepository CreneauRepository = new CreneauRepository();


    @Override
    public void init() throws ServletException {
        super.init();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Creneau> creneau = CreneauRepository.findAll();
        response.setContentType("text/html");
        request.setAttribute("creneau", creneau);
        request.getRequestDispatcher("/creneau/list.jsp").forward(request, response);
    }

    public void destroy() {
    }
}