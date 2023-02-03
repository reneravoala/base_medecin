package com.example.base_medecin.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Medecin;
import repository.MedecinRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "medecinIndexservlet", value = "/api/medecins")
public class MedecinResource extends HttpServlet {
    private final MedecinRepository medecinRepository = new MedecinRepository();

    @Override
    public void init() throws ServletException {
        super.init();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Medecin> medecins = medecinRepository.findAll();
        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), medecins);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ObjectMapper mapper = new ObjectMapper();
        Medecin medecin = mapper.readValue(request.getInputStream(), Medecin.class);
        medecinRepository.save(medecin);
        response.setContentType("application/json");
        mapper.writeValue(response.getOutputStream(), medecin);
    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ObjectMapper mapper = new ObjectMapper();
        Medecin m = mapper.readValue(request.getInputStream(), Medecin.class);
        Medecin medecin = medecinRepository.find(m.getId());
        medecin.setNom(m.getNom());
        medecin.setPrenom(m.getPrenom());
        medecin.setVersion(m.getVersion());
        medecin.setTitre(m.getTitre());
        medecinRepository.save(medecin);
        response.setContentType("application/json");
        mapper.writeValue(response.getOutputStream(), medecin);
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ObjectMapper mapper = new ObjectMapper();
        Medecin medecin = mapper.readValue(request.getInputStream(), Medecin.class);
        medecin = medecinRepository.find(medecin.getId());
        medecinRepository.delete(medecin);
        response.setContentType("application/json");
        mapper.writeValue(response.getOutputStream(), medecin);
    }

    public void destroy() {
    }
}