package com.example.base_medecin.rv;

import entity.RV;
import repository.ClientRepository;
import repository.RvRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(name = "rvAddServlet", value = "/rv/add")
public class AddServlet extends HttpServlet {
    private final RvRepository rvRepository = new RvRepository();

    private final ClientRepository clientRepository = new ClientRepository();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        request.setAttribute("clients", clientRepository.findAll());
        request.getRequestDispatcher("/rv/add.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String jour = request.getParameter("jour");
        RV rv = new RV();
        rv.setJour(new Date(jour));
        rvRepository.save(rv);
        response.sendRedirect(request.getContextPath() + "/rv/index");
    }



    public void destroy() {
    }
}