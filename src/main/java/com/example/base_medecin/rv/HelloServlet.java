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
import java.util.List;

@WebServlet(name = "rvIndexServlet", value = "/rv/index")
public class HelloServlet extends HttpServlet {
    private final RvRepository rvRepository = new RvRepository();
    private final ClientRepository clientRepository = new ClientRepository();


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
        request.setAttribute("client", client);
        response.setContentType("text/html");
        request.setAttribute("rvs", rvs);
        request.getRequestDispatcher("/rv/list.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        rvRepository.delete(rvRepository.find(id));
        response.sendRedirect(request.getContextPath() + "/rv/index");
    }

    public void destroy() {
    }
}