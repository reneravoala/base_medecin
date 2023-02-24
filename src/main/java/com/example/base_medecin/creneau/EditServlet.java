package com.example.base_medecin.creneau;

import entity.Creneau;
import entity.Medecin;
import repository.CreneauRepository;
import repository.MedecinRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "creneauEditServlet", value = "/creneau/edit")
public class EditServlet extends HttpServlet {
    private final CreneauRepository creneauRepository = new CreneauRepository();
    private final MedecinRepository medecinRepository = new MedecinRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Medecin> medecin = medecinRepository.findAll("");

        response.setContentType("text/html");
        request.setAttribute("medecins", medecin);

        request.setAttribute("creneau", creneauRepository.find(Integer.parseInt(request.getParameter("id"))));
        request.getRequestDispatcher("/creneau/edit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int version = Integer.parseInt(request.getParameter("version"));
        int hd_debut = Integer.parseInt(request.getParameter("hd_debut"));
        int md_debut = Integer.parseInt(request.getParameter("md_debut"));
        int h_fin = Integer.parseInt(request.getParameter("h_fin"));
        int m_fin = Integer.parseInt(request.getParameter("m_fin"));
        int medecins_id = Integer.parseInt(request.getParameter("medecins_id"));

        Creneau creneau = creneauRepository.find(Integer.parseInt(request.getParameter("id")));
        creneau.sethFin(h_fin);
        creneau.setVersion(version);
        creneau.setmFin(m_fin);
        creneau.setHdDebut(hd_debut);
        creneau.setMdDebut(md_debut);
        creneau.setMedecin(medecinRepository.find(medecins_id));

        creneauRepository.save(creneau);
        response.sendRedirect(request.getContextPath() + "/creneau/index");
    }

    public void destroy() {
    }
}
