package it.academy.jd2.firstapp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/singer")
public class VotingProgram extends HttpServlet {


        private static final long serialVersionUID = 1L;

        private static final String[] ARTISTS = {"Исполнитель 1", "Исполнитель 2", "Исполнитель 3", "Исполнитель 4"};
        private static final String[] GENRES = {"Жанр 1", "Жанр 2", "Жанр 3", "Жанр 4", "Жанр 5"};


        public void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            response.setContentType("text/html");
            PrintWriter print = response.getWriter();
            print.println("<html>");
            print.println("<head>");
            print.println("<title>Voting Form</title>");
            print.println("</head>");
            print.println("<body>");
            print.println("<h1>Voting Form</h1>");
            print.println("<form method=\"post\">");
            print.println("<p>Best artist:</p>");
            for (int i = 0; i < ARTISTS.length; i++) {
                print.println("<input type=\"radio\" name=\"artist\" value=\"" + ARTISTS[i] + "\">" + ARTISTS[i] + "<br>");
            }
            print.println("<p>Your favorite genres (select at least 4):</p>");
            for (int i = 0; i < GENRES.length; i++) {
                print.println("<input type=\"checkbox\" name=\"genres\" value=\"" + GENRES[i] + "\">" + GENRES[i] + "<br>");
            }
            print.println("<br><input type=\"submit\" value=\"Submit\">");
            print.println("</form>");
            print.println("</body>");
            print.println("</html>");

            // Сохраняем значения в атрибуты объекта HttpServletRequest
            request.setAttribute("artists", ARTISTS);
            request.setAttribute("genres", GENRES);
        }

        public void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            String artist = request.getParameter("artist");
            String[] genres = request.getParameterValues("genres");
            if (artist == null & genres == null & genres.length < 4) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid parameters");
                return;
            }
            response.setContentType("text/html");
            PrintWriter print = response.getWriter();
            print.println("<html>");
            print.println("<head>");
            print.println("<title>Voting Resultswewwewee</title>");
            print.println("</head>");
            print.println("<body>");
            print.println("<h1>Voting Results</h1>");
            print.println("<p>Best artist: " + artist + "</p>");
            print.println("<p>Your favorite genres:</p>");
            print.println("<ul>");
            for (int i = 0; i < genres.length; i++) {
                print.println("<li>" + genres[i] + "</li>");
            }
            print.println("</ul>");
            print.println("</body>");
            print.println("</html>");

            // Извлекаем значения из атрибутов объекта HttpServletRequest
            String[] artists = (String[]) request.getAttribute("artists");
            String[] genresList = (String[]) request.getAttribute("genres");
        }
    }

