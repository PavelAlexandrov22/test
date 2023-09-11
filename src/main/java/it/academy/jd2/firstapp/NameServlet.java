package it.academy.jd2.firstapp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns ="/print_name")
public class NameServlet extends HttpServlet {
    private String NAME_PARAMETER = "name";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        String[] names = req.getParameterMap().get(NAME_PARAMETER);

        if(names != null){
            for (String name : names) {
                writer.write("<p>" + NAME_PARAMETER+ ": " + name);

            }
        }






    }
}
