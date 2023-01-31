package com.example.base_medecin.medecin;

import entity.Medecin;
import repository.MedecinRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "medecinEditServlet", value = "/medecin/edit")
public class EditServlet extends HttpServlet {
    private final MedecinRepository medecinRepository = new MedecinRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        request.setAttribute("medecin", medecinRepository.find(Integer.parseInt(request.getParameter("id"))));
        request.getRequestDispatcher("/medecin/edit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String titre = request.getParameter("titre");
        int version = Integer.parseInt(request.getParameter("version"));
        Medecin medecin = medecinRepository.find(Integer.parseInt(request.getParameter("id")));
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
