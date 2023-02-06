package com.example.base_medecin.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.RV;
import repository.ClientRepository;
import repository.CreneauRepository;
import repository.RvRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "rvIndexservlet", value = "/api/rvs")
public class RVResource extends HttpServlet {
    private final RvRepository rvRepository = new RvRepository();
    private final ClientRepository clientRepository = new ClientRepository();
    private final CreneauRepository creneauRepository = new CreneauRepository();

    @Override
    public void init() throws ServletException {
        super.init();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<RV> rvx = rvRepository.findAll();
        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), rvx);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ObjectMapper mapper = new ObjectMapper();
        RV rv = mapper.readValue(request.getInputStream(), RV.class);
        rvRepository.save(rv);
        response.setContentType("application/json");
        mapper.writeValue(response.getOutputStream(), rv);
    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ObjectMapper mapper = new ObjectMapper();
        RV r = mapper.readValue(request.getInputStream(), RV.class);
        RV rv = rvRepository.find(r.getId());
        rv.setJour(r.getJour());
        rv.setCreneau(creneauRepository.find(r.getCreneau().getId()));
        rv.setClient(clientRepository.find(r.getClient().getId()));
        rvRepository.save(rv);
        response.setContentType("application/json");
        mapper.writeValue(response.getOutputStream(), rv);
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ObjectMapper mapper = new ObjectMapper();
        RV rv = mapper.readValue(request.getInputStream(), RV.class);
        rv = rvRepository.find(rv.getId());
        rvRepository.delete(rv);
        response.setContentType("application/json");
        mapper.writeValue(response.getOutputStream(), rv);
    }

    public void destroy() {
    }
}