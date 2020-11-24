package com.yi.webflux.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * Spring MVC 注解方式的流式编程
 * @author YI
 * @date 2019-2-14 09:52:07
 */
@RestController
public class HelloController {

    /**
     * 响应式编程的返回值必须是 Flux 或者 Mono ，两者之间可以相互转换。
     * just() 方法可以指定序列中包含的全部元素。
     * @return Mono
     */
    @GetMapping("/hello")
    public Mono<String> hello() {
        return Mono.just("Welcome to reactive world ~");
    }
}
