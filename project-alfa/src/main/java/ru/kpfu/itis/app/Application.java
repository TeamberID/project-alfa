package ru.kpfu.itis.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import ru.kpfu.itis.app.model.EchoHandler;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */
@EnableWebSocket
@SpringBootApplication
@EnableJpaRepositories(basePackages = "ru.kpfu.itis.app.repositories")
@EntityScan(basePackages = "ru.kpfu.itis.app.model")
public class Application extends SpringBootServletInitializer implements
        WebSocketConfigurer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        // конфигурирует статику
        return application.sources(Application.class);
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(echoWebSocketHandler(), "/echoHandler").withSockJS();
    }

    @Bean
    public WebSocketHandler echoWebSocketHandler() {
        return new EchoHandler();
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
