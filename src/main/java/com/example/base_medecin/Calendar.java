package com.example.base_medecin;


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
import java.util.List;

@WebServlet(name = "calendarIndexServlet", value = "/calendar/index")
public class Calendar extends HttpServlet {
    private final RvRepository rvRepository = new RvRepository();
    private final ClientRepository clientRepository = new ClientRepository();
    private final CreneauRepository creneauRepository = new CreneauRepository();
    @Override
    public void init() throws ServletException {
        super.init();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<RV> rvs;
        String client = "";
        if (request.getParameter("client_id") != null) {
            int clientId = Integer.parseInt(request.getParameter("client_id"));
            rvs = rvRepository.findByClient(clientId);
            client = clientRepository.find(clientId).getNomComplet();
        } else {
            rvs = rvRepository.findAll();
        }
        request.setAttribute("clients", clientRepository.findAll());
        request.setAttribute("creneaux", creneauRepository.findAll());
        request.setAttribute("client", client);
        request.setAttribute("rvs", rvs);
        response.setContentType("text/html");
        request.getRequestDispatcher("/calendar.jsp").forward(request, response);
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
        response.sendRedirect(request.getContextPath() + "/calendar/index");
    }

    public void destroy() {
    }
}