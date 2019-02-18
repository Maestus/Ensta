

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.FilmDao;
import Model.Film;

@WebServlet("/")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FilmDao filmDao;
	
	public void init() {
        filmDao = new FilmDao();
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
 
        try {
            switch (action) {
            case "/new":
                showNewForm(request, response);
                break;
            case "/insert":
                insertFilm(request, response);
                break;
            case "/delete":
                deleteFilm(request, response);
                break;
            case "/edit":
                showEditForm(request, response);
                break;
            case "/update":
                updateFilm(request, response);
                break;
            case "/list":
                listFilm(request, response);
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
 
    private void listFilm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Film> listFilm = filmDao.getAll();
        System.out.println("Nombre d'elements dans la BD : " + listFilm.size());
        request.setAttribute("listFilm", listFilm);
        RequestDispatcher dispatcher = request.getRequestDispatcher("FilmList.jsp");
        dispatcher.forward(request, response);
    }
 
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("FilmForm.jsp");
        dispatcher.forward(request, response);
    }
 
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Film existingFilm = filmDao.get(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("FilmForm.jsp");
        request.setAttribute("film", existingFilm);
        dispatcher.forward(request, response);
 
    }
 
    private void insertFilm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String titre = request.getParameter("titre");
        String realisateur = request.getParameter("realisateur");
        Film newFilm = new Film(titre, realisateur);
        filmDao.insert(newFilm);
        response.sendRedirect("list");
    }
 
    private void updateFilm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
    	System.out.println("ID : " + request.getParameter("id"));
        int id = Integer.parseInt(request.getParameter("id"));
        String titre = request.getParameter("titre");
        String realisateur = request.getParameter("realisateur");
 
        Film film = new Film(id, titre, realisateur);
        filmDao.update(film);
        response.sendRedirect("list");
    }
 
    private void deleteFilm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
 
        Film film = new Film(id, null, null);
        filmDao.delete(film);
        response.sendRedirect("list");
 
    }

}
