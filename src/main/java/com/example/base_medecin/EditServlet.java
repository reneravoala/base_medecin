package com.example.base_medecin;

import entity.Client;
import repository.ClientRepository;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "EditServlet", value = "/edit")
public class EditServlet extends HttpServlet {
    ClientRepository clientRepository = new ClientRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        System.out.println(request.getParameter("id"));

        request.setAttribute("client", clientRepository.find(1));
        request.getRequestDispatcher("/edit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String titre = request.getParameter("titre");
        int version = Integer.parseInt(request.getParameter("version"));
        Client client = clientRepository.find(1);
        client.setNom(nom);
        client.setPrenom(prenom);
        client.setTitre(titre);
        client.setVersion(version);
        clientRepository.save(client);
        response.sendRedirect(request.getContextPath() + "/index");
    }
}
