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

@WebServlet(name = "rvEditServlet", value = "/rv/edit")
public class EditServlet extends HttpServlet {
    private final RvRepository rvRepository = new RvRepository();

    private final ClientRepository clientRepository = new ClientRepository();

    private final CreneauRepository creneauRepository = new CreneauRepository();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        request.setAttribute("clients", clientRepository.findAll());
        request.setAttribute("creneaux", creneauRepository.findAll());
        request.setAttribute("rv", rvRepository.find(Integer.parseInt(request.getParameter("id"))));
        request.getRequestDispatcher("/rv/edit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jour = request.getParameter("jour");
        RV rv = rvRepository.find(Integer.parseInt(request.getParameter("id")));
        int clientId = Integer.parseInt(request.getParameter("client_id"));
        int creneauId = Integer.parseInt(request.getParameter("creneau_id"));
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
