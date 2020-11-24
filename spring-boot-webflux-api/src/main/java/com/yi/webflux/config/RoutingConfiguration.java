package com.yi.webflux.config;

import com.yi.webflux.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * reactive web 流式编程接口
 * @author YI
 * @date 2018-8-18 22:11:41
 */
@Configuration
public class RoutingConfiguration {

    /**
     * GET http://localhost:8080/api/user/index
     * GET http://localhost:8080/api/user/1
     * POST http://localhost:8080/api/user/post
     * PUT http://localhost:8080/api/user/put/3
     * DELETE http://localhost:8080/api/user/delete/3
     * @param userHandler
     * @return
     */
    @Bean
    public RouterFunction<ServerResponse> monoRouterFunction(UserHandler userHandler) {
        return route(GET("/api/user/index").and(accept(MediaType.APPLICATION_JSON)), userHandler::getAll)
                .andRoute(GET("/api/user/{id}").and(accept(MediaType.APPLICATION_JSON)), userHandler::getUser)
                .andRoute(POST("/api/user/post").and(accept(MediaType.APPLICATION_JSON)), userHandler::postUser)
                .andRoute(PUT("/api/user/put/{id}").and(accept(MediaType.APPLICATION_JSON)), userHandler::putUser)
                .andRoute(DELETE("/api/user/delete/{id}").and(accept(MediaType.APPLICATION_JSON)), userHandler::deleteUser);
    }

}
