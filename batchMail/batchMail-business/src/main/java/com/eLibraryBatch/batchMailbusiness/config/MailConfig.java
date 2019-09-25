package com.eLibraryBatch.batchMailbusiness.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@PropertySource(name = "myProperties", value = "file:${CONF_DIR}/application-gmail.properties")
public class MailConfig {

    @Value("${host}")
    private String host;

    @Value("${port}")
    private int port;

    @Value("${usernamemail}")
    private String userName;

    @Value("${passwordmail}")
    private String password;

    @Value("${protocol}")
    private String protocol;

    @Value("${auth}")
    private boolean auth;

    @Value("${ttls}")
    private boolean ttls;

    @Value("${maildebug}")
    private boolean mailDebug;

    @Bean
    public JavaMailSender getJavaMailSender() {

        // inject information from application-gmail.properties

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);

        mailSender.setUsername(userName);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", protocol);
        props.put("mail.smtp.auth", auth);
        props.put("mail.smtp.starttls.enable", ttls);
        props.put("mail.debug", mailDebug);

        return mailSender;
    }

//    @PostConstruct
//    public void afterInitialize() {
//        System.out.println(host);
//        System.out.println(port);
//        System.out.println(userName);
//        System.out.println(password);
//        System.out.println(protocol);
//        System.out.println(auth);
//        System.out.println(ttls);
//        System.out.println(mailDebug);
//
//    }
}
