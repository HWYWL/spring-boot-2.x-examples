
package com.example.webflux.handler;

import cn.hutool.json.JSONUtil;
import com.example.webflux.model.Message;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.HandshakeInfo;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 服务端响应
 * @author YI
 * @date 2018-8-15 11:27:15
 */
@Component
public class EchoHandler implements WebSocketHandler {
    @Override
    public Mono<Void> handle(final WebSocketSession session) {
        Message message = new Message();

        return session.send(session.receive().map(msg -> {
                    message.setMsg("服务端返回：" + msg.getPayloadAsText());

                    return session.textMessage(JSONUtil.toJsonStr(message));
                }
            )
        );
    }
}
