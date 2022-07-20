package com.example.mybatis.config;

import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

/**
 * 类描述:
 *
 * @author 吴智聪
 * @version 1.0
 * @date 2022/5/12 9:59
 */
@Component
public class NettyWebServerFactoryPortCustomizer implements WebServerFactoryCustomizer<NettyReactiveWebServerFactory> {

        @Override
        public void customize(NettyReactiveWebServerFactory serverFactory) {
            serverFactory.setPort(8089);
        }

}