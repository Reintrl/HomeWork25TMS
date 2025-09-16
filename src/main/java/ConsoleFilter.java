import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.time.LocalDateTime;

@WebFilter("/*")
public class ConsoleFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
            String method = httpRequest.getMethod();

            System.out.println("[" + LocalDateTime.now() + "] " +
                    "Request: " + method + " " + httpRequest.getRequestURI());
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
