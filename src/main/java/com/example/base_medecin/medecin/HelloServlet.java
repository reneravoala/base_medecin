package com.example.base_medecin.medecin;

import entity.Medecin;
import repository.MedecinRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "medecinIndexServlet", value = "/medecin/index")
public class HelloServlet extends HttpServlet {
    private final MedecinRepository MedecinRepository = new MedecinRepository();


    @Override
    public void init() throws ServletException {
        super.init();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Medecin> medecins = MedecinRepository.findAll();
        response.setContentType("text/html");
        request.setAttribute("medecins", medecins);
        request.getRequestDispatcher("/medecin/list.jsp").forward(request, response);
    }

    public void destroy() {
    }
}