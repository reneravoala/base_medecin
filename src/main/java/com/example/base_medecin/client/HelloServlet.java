package com.example.base_medecin.client;

import entity.Client;
import repository.ClientRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "clientIndexServlet", value = "/client/index")
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