package com.example.base_medecin.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Client;
import repository.ClientRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "clientIndexservlet", value = "/api/clients")
public class ClientResource extends HttpServlet {
    private final ClientRepository clientRepository = new ClientRepository();

    @Override
    public void init() throws ServletException {
        super.init();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Client> clients = clientRepository.findAll();
        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), clients);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ObjectMapper mapper = new ObjectMapper();
        Client client = mapper.readValue(request.getInputStream(), Client.class);
        clientRepository.save(client);
        response.setContentType("application/json");
        mapper.writeValue(response.getOutputStream(), client);
    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ObjectMapper mapper = new ObjectMapper();
        Client c = mapper.readValue(request.getInputStream(), Client.class);
        Client client = clientRepository.find(c.getId());
        client.setNom(c.getNom());
        client.setPrenom(c.getPrenom());
        client.setVersion(c.getVersion());
        client.setTitre(c.getTitre());
        clientRepository.save(client);
        response.setContentType("application/json");
        mapper.writeValue(response.getOutputStream(), client);
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ObjectMapper mapper = new ObjectMapper();
        Client client = mapper.readValue(request.getInputStream(), Client.class);
        client = clientRepository.find(client.getId());
        clientRepository.delete(client);
        response.setContentType("application/json");
        response.setStatus(200);
    }

    public void destroy() {
    }
}