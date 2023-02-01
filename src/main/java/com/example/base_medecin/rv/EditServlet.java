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
        rv.setJour(new Date(jour));
        rvRepository.save(rv);
        response.sendRedirect(request.getContextPath() + "/rv/index");
    }

    public void destroy() {
    }
}
