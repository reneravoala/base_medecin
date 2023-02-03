package com.example.base_medecin.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Client;
import repository.ClientRepository;

import java.io.*;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "clientIndexservlet", value = "/client/index")
public class HelloServlet extends HttpServlet {
    private final ClientRepository ClientRepository = new ClientRepository();

    @Override
    public void init() throws ServletException {
        super.init();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Client clients = ClientRepository.find(1);
        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), clients);
    }

    public void destroy() {
    }
}