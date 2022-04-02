package ssj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author djp
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class SsjApplication {

    private static Logger logger = LoggerFactory.getLogger(SsjApplication.class);
    
    public static void main(String[] args) {
       
        try {
            SpringApplication.run(SsjApplication.class, args);
        } catch (Exception exception) {
            logger.error("failed to run Bootstrap", exception);
            throw exception;
        }
    }

}
