import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@WebServlet("/book")
public class BookDownloadServlet extends HttpServlet {
    private static final Random RANDOM = new Random();
    private static final String UPLOAD_DIR = "C:\\Users\\reinb\\IdeaProjects\\HomeWork25TMS\\src\\main\\books\\";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<File> files = Files.list(Paths.get(UPLOAD_DIR))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .collect(Collectors.toList());

        if (files.isEmpty()) {
            System.out.println("В папке нет файлов");
            return;
        }

        File randomFile = files.get(RANDOM.nextInt(files.size()));

        resp.setContentType(getServletContext().getMimeType(randomFile.getName()));
        resp.setHeader("Content-Disposition",
                "attachment; filename=\"" + randomFile.getName() + "\"");
        resp.setContentLength((int) randomFile.length());
        try (InputStream in = new FileInputStream(randomFile);
             OutputStream out = resp.getOutputStream()) {

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            getServletContext().getRequestDispatcher("/success.html").forward(req, resp);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            getServletContext().getRequestDispatcher("/unsuccess.html").forward(req, resp);
        }
    }
}