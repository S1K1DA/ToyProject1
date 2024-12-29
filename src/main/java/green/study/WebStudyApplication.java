package green.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class WebStudyApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebStudyApplication.class, args);
    }

}
