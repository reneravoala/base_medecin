package com.example.base_medecin.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Creneau;
import repository.CreneauRepository;
import repository.MedecinRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "creneauIndexservlet", value = "/api/creneaus")
public class CreneauResource extends HttpServlet {
    private final CreneauRepository creneauRepository = new CreneauRepository();

    private final MedecinRepository medecinRepository = new MedecinRepository();

    @Override
    public void init() throws ServletException {
        super.init();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Creneau> creneaux = creneauRepository.findAll();
        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), creneaux);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ObjectMapper mapper = new ObjectMapper();
        Creneau creneau = mapper.readValue(request.getInputStream(), Creneau.class);
        creneau.setMedecin(creneau.getMedecin());
        creneauRepository.save(creneau);
        response.setContentType("application/json");
        mapper.writeValue(response.getOutputStream(), creneau);
    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ObjectMapper mapper = new ObjectMapper();
        Creneau c = mapper.readValue(request.getInputStream(), Creneau.class);
        Creneau creneau = creneauRepository.find(c.getId());
        creneau.setHdDebut(c.getHdDebut());
        creneau.setMdDebut(c.getMdDebut());
        creneau.sethFin(c.gethFin());
        creneau.setmFin(c.getmFin());
        creneau.setVersion(c.getVersion());
        creneau.setMedecin(medecinRepository.find(c.getMedecin().getId()));
        creneauRepository.save(creneau);
        response.setContentType("application/json");
        mapper.writeValue(response.getOutputStream(), creneau);
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ObjectMapper mapper = new ObjectMapper();
        Creneau creneau = mapper.readValue(request.getInputStream(), Creneau.class);
        creneau = creneauRepository.find(creneau.getId());
        creneauRepository.delete(creneau);
        response.setContentType("application/json");
        mapper.writeValue(response.getOutputStream(), creneau);
    }

    public void destroy() {
    }
}