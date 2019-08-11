package com.qisimanxiang.workreport.dalaran.protocol.http;

import com.qisimanxiang.workreport.dalaran.protocol.config.DalaranConfig;
import com.qisimanxiang.workreport.dalaran.protocol.log.DalaranLogger;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

/**
 * HTTP协议转换
 * 首选需要一个http服务，我么太水，首先想到的当然是用netty来玩。
 * 没用过netty？不要紧，看看nio、reactor模型，大致理解一下，
 * 知道些重要的原理就行了，搞个这个不至于去搞懂完整的netty，
 * 毕竟给你的时间往往是有限的，很多时候你要去衡量一下效率问题。
 * 官网链接在此 https://netty.io/
 * <p>
 * 那么我们需要哪些配置呢？
 * 1.端口
 * 2.Path Map
 * 3.JobCall
 * 4.thread pool
 * 5.log
 * <p>
 * <p>
 * 既然是方法转http接口，那么我们肯定要定义一下http接口的相关细节规范
 * 1. method . get方法传参有限制，post最合适。
 * 你在想restful？ 就目前集成平台的场景其实没有太大必要。方法统一为post，一是可以降低代码复杂度，二是降低排查和对接难度。
 * 2. 出入参 . 方法有入参和出参,一般来讲单参和无参足以满足需求，拓展性也更好
 * 3. 序列化 . 对应我们的请求数据对象和响应数据对象，从请求解析、序列化方便通用的角度看，json是最合适的,使用jackson即可。
 * <p>
 * <p>
 * 实现的过程先不要想着直接配置化，建议先定义局部变量，功能调试的确没问题后再调整为配置化。
 * 否则配置的调整会比较麻烦，增加调试过程的复杂度。"啥？你觉得这都不是事儿！？赞你！"
 *
 * @author wangmeng
 * @date 2019-08-04
 */
@ConditionalOnBean(DalaranConfig.class)
@Component
@Slf4j
public class DalaranHttpProtocolStarter {

    @Autowired
    private DalaranConfig config;

    private DalaranLogger logger;

    public void start() throws InterruptedException {
        int port = config.getHttp().getPort();
        ServerBootstrap bootstrap = new ServerBootstrap();
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();
        bootstrap.group(boss, work)
                .handler(new LoggingHandler(LogLevel.INFO))
                .channel(NioServerSocketChannel.class)
                .childHandler(createChannelInitializer());
        ChannelFuture f = bootstrap.bind(new InetSocketAddress(port)).sync();
        log.info("Dalaran server start up on port : " + port);
        f.channel().closeFuture().sync();
    }

    private HttpServerInitializer createChannelInitializer(){
        int maxContent = config.getHttp().getMaxContent();
        return new HttpServerInitializer(logger,maxContent);
    }
}
