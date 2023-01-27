package com.example.base_medecin;

import entity.Client;
import repository.ClientRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "addServlet", value = "/add")
public class AddServlet extends HttpServlet {
    private final ClientRepository clientRepository = new ClientRepository();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        request.getRequestDispatcher("/add.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String titre = request.getParameter("titre");
        int version = Integer.parseInt(request.getParameter("version"));
        Client client = new Client();
        client.setNom(nom);
        client.setPrenom(prenom);
        client.setTitre(titre);
        client.setVersion(version);
        clientRepository.save(client);
        response.sendRedirect(request.getContextPath() + "/index");
    }



    public void destroy() {
    }
}