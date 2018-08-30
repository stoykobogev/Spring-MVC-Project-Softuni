package org.softuni.secondtechjmscomment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class SecondtechJmsCommentApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecondtechJmsCommentApplication.class, args);
    }
}
