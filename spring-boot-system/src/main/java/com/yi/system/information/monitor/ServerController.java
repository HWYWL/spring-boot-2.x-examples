package com.yi.system.information.monitor;

import com.yi.system.information.domain.Server;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 服务器监控
 *
 * @author YI
 * @date 2019-1-22 15:11:30
 */
@Controller
public class ServerController {

    @GetMapping("/")
    public String server(ModelMap model) throws Exception {
        Server server = new Server();
        server.copyTo();
        model.put("server", server);
        return "index";
    }
}
