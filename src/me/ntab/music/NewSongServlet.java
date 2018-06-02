package me.ntab.music;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "NewSongServlet")
public class NewSongServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String artist = request.getParameter("artist");
        String trackNum = request.getParameter("trackNum");
        String duration = request.getParameter("duration");
        String albumId = request.getParameter("albumId");
        AlbumsDataProvider.addSong(albumId, title, artist, trackNum, duration);
        response.sendRedirect("album.jsp?id=" + albumId);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String albumId = request.getParameter("albumId");
        response.sendRedirect("album.jsp?id=" + albumId);
    }
}
