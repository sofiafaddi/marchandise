package fr.univlyon1.tiw1.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/entrepot/*"})
public class EntrepotServlet extends HttpServlet {

    private String dummyContent = null;

    @Override
    public void init() throws ServletException {
        super.init();
        try (BufferedReader dummyReader = new BufferedReader(
                new InputStreamReader(
                        getClass().getResourceAsStream("/dummy-entrepot.json")
                )
        )) {
            StringBuilder data = new StringBuilder();
            String line = null;
            while((line = dummyReader.readLine())!=null) {
                data.append(line).append('\n');
            }
            dummyContent = data.toString();
        } catch (IOException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nom = req.getParameter("nom");
        resp.setContentType("application/json");
        PrintWriter pw = resp.getWriter();
        pw.write(dummyContent);
        pw.close();
    }
}
