import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ServletListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("ServletListener contextInitialized");
    }
}
