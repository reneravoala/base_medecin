package com.example.base_medecin;

import entity.Client;
import repository.ClientRepository;

import java.io.*;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/index")
public class HelloServlet extends HttpServlet {
    private final ClientRepository ClientRepository = new ClientRepository();

    private List<Client> clients;

    @Override
    public void init() throws ServletException {
        clients = ClientRepository.findAll("");
        super.init();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        request.setAttribute("clients", clients);
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }

    public void destroy() {
    }
}