package com.example.base_medecin.client;

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
        List<Client> clients = ClientRepository.findAll();
        response.setContentType("text/html");
        request.setAttribute("clients", clients);
        request.getRequestDispatcher("/client/list.jsp").forward(request, response);
    }

    public void destroy() {
    }
}