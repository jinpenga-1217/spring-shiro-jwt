package ssj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import ssj.config.ShiroConfig;
import ssj.listener.SsjStartedEventListener;

/**
 * @author djp
 */
@Import({ShiroConfig.class})

@EntityScan("ssj.domain")
@ComponentScan({"ssj.web", "ssj.component"})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SsjApplication {

    private static final Logger logger = LoggerFactory.getLogger(SsjApplication.class);

    public static void main(String[] args) {

        try {
            SpringApplication app = new SpringApplication(SsjApplication.class);
            app.addListeners(new SsjStartedEventListener());
            app.run(args);
        } catch (Exception exception) {
            logger.error("failed to run Bootstrap", exception);
            throw exception;
        }
    }

}
