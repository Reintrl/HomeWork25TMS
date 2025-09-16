import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;

@WebServlet("/load-book")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 10)
public class BookLoadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/loadPage.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            Part filePart = req.getPart("file");
            String name = filePart.getSubmittedFileName();
            filePart.write("C:\\Users\\reinb\\IdeaProjects\\HomeWork25TMS\\src\\main\\books\\" + name);
            resp.getWriter().append("success");
        } catch (Exception e) {
            resp.getWriter().append("unsuccess");
        }
    }
}
