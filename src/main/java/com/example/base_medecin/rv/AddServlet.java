package com.example.base_medecin.rv;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "rvAddServlet", value = "/rv/add")
public class AddServlet extends HttpServlet {
    private final RvRepository rvRepository = new RvRepository();

    private final ClientRepository clientRepository = new ClientRepository();

    private final CreneauRepository creneauRepository = new CreneauRepository();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        request.setAttribute("clients", clientRepository.findAll());
        request.setAttribute("creneaux", creneauRepository.findAll());
        request.getRequestDispatcher("/rv/add.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String jour = request.getParameter("jour");
        int clientId = Integer.parseInt(request.getParameter("client_id"));
        int creneauId = Integer.parseInt(request.getParameter("creneau_id"));
        RV rv = new RV();
        try {
            rv.setJour(new SimpleDateFormat("yyyy-MM-dd").parse(jour));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        rv.setClient(clientRepository.find(clientId));
        rv.setCreneau(creneauRepository.find(creneauId));
        rvRepository.save(rv);
        response.sendRedirect(request.getContextPath() + "/rv/index");
    }



    public void destroy() {
    }
}