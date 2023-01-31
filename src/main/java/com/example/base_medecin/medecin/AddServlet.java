package com.example.base_medecin.medecin;

import entity.Medecin;
import repository.MedecinRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "medecinAddServlet", value = "/medecin/add")
public class AddServlet extends HttpServlet {
    private final MedecinRepository medecinRepository = new MedecinRepository();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        request.getRequestDispatcher("/medecin/add.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String titre = request.getParameter("titre");
        int version = Integer.parseInt(request.getParameter("version"));
        Medecin medecin = new Medecin();
        medecin.setNom(nom);
        medecin.setPrenom(prenom);
        medecin.setTitre(titre);
        medecin.setVersion(version);
        medecinRepository.save(medecin);
        response.sendRedirect(request.getContextPath() + "/medecin/index");
    }



    public void destroy() {
    }
}