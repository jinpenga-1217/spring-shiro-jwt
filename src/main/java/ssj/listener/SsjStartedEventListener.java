package ssj.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

import java.net.MalformedURLException;
import java.net.URL;

public class SsjStartedEventListener implements ApplicationListener<ApplicationStartedEvent> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        logServerProperties( event.getApplicationContext().getBean(ServerProperties.class) );
        System.err.println("============================");
    }

    private void logServerProperties(ServerProperties serverProperties) {
        String protocal = serverProperties.getSsl() != null ? "https" : "http";
        int port = serverProperties.getPort() != null ? serverProperties.getPort() : 8080;
        String contextPath = serverProperties.getServlet() != null ? serverProperties.getServlet().getContextPath() : "";
        try {
            String homeUrl = new URL(protocal, "localhost", port, contextPath).toString();
            logger.warn("\n" + homeUrl.toString());

        } catch (MalformedURLException exception) {
            logger.warn("{}://localhost:{}/{}", protocal, port, contextPath);
        }
    }
}
