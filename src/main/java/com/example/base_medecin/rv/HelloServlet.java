package com.example.base_medecin.rv;

import entity.RV;
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


    @Override
    public void init() throws ServletException {
        super.init();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<RV> rvs;
        if (request.getParameter("client_id") != null) {
            rvs = rvRepository.findByClient(Integer.parseInt(request.getParameter("client_id")));
        } else {
            rvs = rvRepository.findAll();
        }
        response.setContentType("text/html");
        request.setAttribute("rvs", rvs);
        request.getRequestDispatcher("/rv/list.jsp").forward(request, response);
    }

    public void destroy() {
    }
}